package hello;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Home{
	
	private String homeId, firstName, lastName, emergencyDoorCode, pinCode, offlineTestMode, timeZone, VerificationPassword;
	private URI link;
	private Adress adress;
	private int nbrOfContacts;
	
	@JsonIgnore
	int numberOfDevices = 0;
	
	private List<User> users;
	private List<Device> devices;
	
	public Home() {
		
	}

	public Home(String firstName, String lastName, String emergencyDoorCode, String pinCode, String offlineTestMode,
			String timeZone, String verificationPassword, Adress adress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emergencyDoorCode = emergencyDoorCode;
		this.pinCode = pinCode;
		this.offlineTestMode = offlineTestMode;
		this.timeZone = timeZone;
		this.VerificationPassword = verificationPassword;
		this.adress = adress;
		nbrOfContacts = 0;
	}
	
	public void setHome(Home home) throws URISyntaxException {
		setHomeId(home.homeId);
		setFirstName(home.firstName);
		setLastName(home.lastName);
		setEmergencyDoorCode(home.emergencyDoorCode);
		setPinCode(home.pinCode);
		setOfflineTestMode(home.offlineTestMode);
		setTimeZone(home.timeZone);
		setVerificationPassword(home.VerificationPassword);
		setDevices(home.devices);
		setUsers(home.users);
	}
	
	public int getNumberOfDevices() {
		return numberOfDevices;
	}

	public void setNumberOfDevices(int numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
	}

	public String getHomeId() {
		return homeId;
	}
	
	public void setHomeId(String homeId) throws URISyntaxException {
		this.homeId = homeId;
		link = new URI("http://home-test-api.datek.no/homes/" + homeId);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmergencyDoorCode() {
		return emergencyDoorCode;
	}

	public void setEmergencyDoorCode(String emergencyDoorCode) {
		this.emergencyDoorCode = emergencyDoorCode;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getOfflineTestMode() {
		return offlineTestMode;
	}

	public void setOfflineTestMode(String offlineTestMode) {
		this.offlineTestMode = offlineTestMode;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getVerificationPassword() {
		return VerificationPassword;
	}

	public void setVerificationPassword(String verificationPassword) {
		VerificationPassword = verificationPassword;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
	public URI getLink() {
		return link;
	}
	
	public void setLink(URI link) {
		this.link = link;
	}

	public int getNbrOfContacts() {
		return nbrOfContacts;
	}

	public void setNbrOfContacts(int nbrOfContacts) {
		this.nbrOfContacts = nbrOfContacts;
	}
	
	public List<Device> getDevices() {
		if (numberOfDevices == 0) {
			return new ArrayList<Device>();
		}
		else {
			return devices;
		}
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "\n \n homeId = " + homeId +
        		"\n firstName = " + firstName +
                "\n lastName = " + lastName +
                "\n emergencyDoorCode = " + emergencyDoorCode +
                "\n pinCode = " + pinCode +
                "\n offlineTestMode = " + offlineTestMode +
                "\n timeZone = " + timeZone +
                "\n VerificationPassword = " + VerificationPassword +
                "\n adress = " + adress +
                '\n';
	}
	
	public String patchDevices() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		for (Device device : devices) {
			HttpEntity<Device> request = new HttpEntity<Device>(device, headers);
			try {
				restTemplate.exchange(link + "/devices/" + device.getSerialNo(), HttpMethod.PATCH, request, Device.class);
			} catch (HttpStatusCodeException e){
				return e.getResponseBodyAsString();
			} catch(RestClientException e){
				return e.getMessage();
			}
		}
		return "All the " + numberOfDevices + " devices have been successfuly patched.";
	}
	
	public String patchDevice(int i) {
		if (i < numberOfDevices) {
			RestTemplate restTemplate = new RestTemplate();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(requestFactory);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			Device device = getDevices().get(i);
			HttpEntity<Device> request = new HttpEntity<Device>(device, headers);
			try {
				restTemplate.exchange(link + "/devices/" + device.getSerialNo(), HttpMethod.PATCH, request, Device.class);
			} catch (HttpStatusCodeException e){
				return e.getResponseBodyAsString();
			} catch(RestClientException e){
				return e.getMessage();
			}
			return "Your devices was successfuly patched.";
		}
		else {
			return "There are only " + numberOfDevices + " devices attached to this homegate.";
		}
	}
	
	public String deleteDevices() {
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(headers);
		for (Device device : devices) {
			try {
				restTemplate.exchange(link + "/devices/" + device.getSerialNo(), HttpMethod.DELETE, request, String.class);
			} catch (HttpStatusCodeException e){
				return e.getResponseBodyAsString();
			} catch(RestClientException e){
				return e.getMessage();
			}
		}
		numberOfDevices = 0;
		return "All the " + numberOfDevices + " devices have been successfully deleted from the homegate";
	}
	
	public String deleteDevice(int i) throws URISyntaxException {
		if (i < numberOfDevices) {
			RestTemplate restTemplate = new RestTemplate();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(requestFactory);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> request = new HttpEntity<String>(headers);
			Device device = getDevices().get(i);
			try {
				restTemplate.exchange(link + "/devices/" + device.getSerialNo(), HttpMethod.DELETE, request, String.class);
			} catch (HttpStatusCodeException e){
				return e.getResponseBodyAsString();
			} catch(RestClientException e){
				return e.getMessage();
			}
			numberOfDevices--;
			update();
			return "The device " + device.getName() + " has been successfully deleted from the homegate";
		}
		else {
			return "There are only " + numberOfDevices + " devices attached to this homegate.";
		}
	}
	
	public String deleteDevice(Device device) throws URISyntaxException {
		int i = 0;
		for (Device _device : getDevices()) {
			if (device.getSerialNo().equals(_device.getSerialNo())) {
				return deleteDevice(i);
			}
			i++;
		}
		return "No such device has been found";
	}
	
	public String identifyDevices() throws InterruptedException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		for (Device device : devices) {
			try {
				restTemplate.postForEntity(link + "/devices/" + device.getSerialNo() + "/identify", request, String.class);
			} catch (HttpStatusCodeException e){
				return e.getResponseBodyAsString();
			} catch(RestClientException e){
				return e.getMessage();
			}
			Thread.sleep(5000);
		}
		return "All the " + numberOfDevices + " devices are identifying themselves.";
	}
	
	public String identifyDevice(int i) throws URISyntaxException {
		if (i < numberOfDevices) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> request = new HttpEntity<String>(headers);
			RestTemplate restTemplate = new RestTemplate();
			try {
				restTemplate.postForEntity(link + "/devices/" + devices.get(i).getSerialNo() + "/identify",request,String.class);
			} catch (HttpStatusCodeException e){
				return e.getResponseBodyAsString();
			} catch(RestClientException e){
				return e.getMessage();
			}
			update();
			return "The device " + devices.get(i).getName() +" is identifying itself.";
		}
		else {
			return "There are only " + numberOfDevices + " attached to this homegate.";
		}
	}
	
	public boolean addDevice(int time) throws URISyntaxException {
		System.out.println("\n The homegate is scanning for devices...");
		int initialNumberOfDevices = numberOfDevices;
		acceptDevices();
		long beginningTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - beginningTime < time*1000) {
			update();
			if (numberOfDevices != initialNumberOfDevices) {
				System.out.println("\n The device " + getDevices().get(numberOfDevices-1).getName() + " has been successfully added to the homegate.");
				return true;
			}
		}
		System.out.println("\n No device has been detected.");
		return false;
	}

	public String postHome() throws URISyntaxException {
		URI url = new URI("http://home-test-api.datek.no/homes");
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Home> request = new HttpEntity<Home>(this, headers);
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    		homeId = restTemplate.postForEntity(url,request,String.class).getHeaders().getLocation().toString().substring(36);
    	} catch (HttpStatusCodeException e){
    		return e.getResponseBodyAsString();
    	} catch(RestClientException e){
    		return e.getMessage();
    	}
    	setLink(new URI(url + "/" + homeId));
    	setHome(restTemplate.getForObject(link, Home.class));
    	return "The home has been succesfully created.";
	}
		
	public String addHomegate(Homegate homegate) throws URISyntaxException {
		if (homegate.getLink().toString().lastIndexOf("homes") != -1)  {
        	return "This homegate is already attached to a home. Please detach it before choosing a new home.";
        }
		else {
			URI url = new URI(link + "/homegate/" + homegate.getHomegateId());
	    	HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	        HttpEntity<String> request = new HttpEntity<String>(headers);
	        RestTemplate restTemplate = new RestTemplate();
	        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	        restTemplate.setRequestFactory(requestFactory);
	        if (restTemplate.getForObject(link, String.class).lastIndexOf("homegate") != -1)  {
	        	return "This home has already a homegate attached. Please detach it before choosing a new home.";
	        }
	        else {
	        	try {
		        	restTemplate.exchange(url, HttpMethod.PATCH, request, String.class);
		        } catch (HttpStatusCodeException e){
		        	if (e.getStatusCode() == HttpStatus.BAD_REQUEST && e.getMessage().equals("Homegate already assigned to other home")) {
		        		return "This homegate is already assigned to another home. Please detach it before choosing a new home.";
		        	}
		        	else {
		        		return e.getResponseBodyAsString();
		        	}
		        } catch(RestClientException e){
		        	return e.getMessage();
		        }
		        homegate.setLink(new URI(link + "/homegate"));
		        homegate.setHomegate(restTemplate.getForObject(homegate.getLink(), Homegate.class));
		        return "The homegate has been succesfully added to the home.";
	        }
        }
	}
	
	public String addContact(Contact contact) throws URISyntaxException {
    	URI url = new URI(link + "/contacts");
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Contact> request = new HttpEntity<Contact>(contact, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
        	contact.setLink(restTemplate.postForEntity(url, request, String.class).getHeaders().getLocation());
        } catch (HttpStatusCodeException e){
        	return e.getResponseBodyAsString();
        } catch(RestClientException e){
        	return e.getMessage();
        }
        nbrOfContacts++;
        contact.setLink(new URI(url + "/" + nbrOfContacts));
    	contact.setContact(restTemplate.getForObject(contact.getLink(), Contact.class));
        return "The contact has been succesfully added to the home.";
    }
	
	public String addUser(User user) throws URISyntaxException {
    	URI url = new URI(link + "/users");
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> request = new HttpEntity<User>(user, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
        	restTemplate.postForEntity(url, request, User.class);
        } catch (HttpStatusCodeException e){
        	return e.getResponseBodyAsString();
        } catch(RestClientException e){
        	return e.getMessage();
        }
        update();
        for (User _user : getUsers()) {
        	if (user.getEmail().equals(_user.getEmail())) {
        		user.setUser(_user);
        	}
        }
        return "The user has been succesfully added to the home.";
    }
	
	public String deleteUser(User user) throws URISyntaxException {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
        	restTemplate.exchange(link + "/users/" + user.getIndex(), HttpMethod.DELETE, request, String.class);
        } catch (HttpStatusCodeException e){
        	return e.getResponseBodyAsString();
        } catch(RestClientException e){
        	return e.getMessage();
        }
        update();
        return "The user has been successfully deleted.";
    }
	
	public String deleteUsers() throws URISyntaxException {
    	for (User user : getUsers()) {
    		deleteUser(user);
    	}
    	return "All the users have been successfully deleted";
    }
	
	public String acceptDevices() throws URISyntaxException {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);
        try {
        	restTemplate.exchange(link + "/homegate/cmd/scan", HttpMethod.POST, request, String.class);
        } catch (HttpStatusCodeException e){
        	return e.getResponseBodyAsString();
        } catch(RestClientException e){
        	return e.getMessage();
        }
        return "The home now accepts devices.";
    }
	
	public String detachHomegate(Homegate homegate) throws URISyntaxException {
		if (!homegate.gotHome())  {
        	return "This homegate is not attached to a home.";
        }
		else if (!homegate.getHomeId().equals(homeId)) {
			return "This homegate is not attached to this home.";
		}
		else {
			URI url = new URI(link + "/homegate");
	    	HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	        HttpEntity<String> request = new HttpEntity<String>(headers);
	        RestTemplate restTemplate = new RestTemplate();
	        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	        restTemplate.setRequestFactory(requestFactory);
        	try {
	        	restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
	        } catch (HttpStatusCodeException e){
	        	return e.getResponseBodyAsString();
	        } catch(RestClientException e){
	        	return e.getMessage();
	        }
	        homegate.setLink(new URI("http://home-test-api.datek.no/homegates/" + homegate.getHomegateId()));
	        homegate.setHomegate(restTemplate.getForObject(homegate.getLink(), Homegate.class));
	        return "The homegate has been succesfully detached to the home.";
        }
	}
	
	public String update() throws URISyntaxException {
		if (homeId == null) {
			return "You have to set a homeId first.";
		}
		else {
			URI url = new URI("http://home-test-api.datek.no/homes");
	    	RestTemplate restTemplate = new RestTemplate();
	    	try {
	    		setHome(restTemplate.getForObject(new URI(url + "/" + homeId), Home.class));
	    	} catch (HttpStatusCodeException e){
	    		if (e.getStatusCode() == HttpStatus.NOT_FOUND || e.getStatusCode() == HttpStatus.BAD_REQUEST) {
	    			return "\nThere is no home with the Id : " + homeId +".";
	    		}
	    		else {
	    			return e.getResponseBodyAsString();
	    		}
	    	} catch(RestClientException e){
	    		return e.getMessage();
	    	}
	    	setLink(new URI(url + "/" + homeId));
	    	if (devices == null) {
	    		numberOfDevices = 0;
	    	}
	    	else {
	    		numberOfDevices = devices.size();
	    	}
	    	return "The home has been succesfully updated from the server.";
		}
	}
	
	public Device updateDevice(Device device) throws URISyntaxException {
		for (Device d : getDevices()) {
			if (device.getSerialNo().equals(d.getSerialNo())) {
				return d;
			}
		}
		return device;
	}
	
	public String turnDeviceOn(Device device) throws URISyntaxException {
		update();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);
        try {
        	restTemplate.exchange(link + "/devices/" + device.getSerialNo() + "/endpoints/3/clusters/6/cmd/1", HttpMethod.POST, request, String.class);
        } catch (HttpStatusCodeException e){
        	return e.getResponseBodyAsString();
        } catch(RestClientException e){
        	return e.getMessage();
        }
        update();
        return ("The " + device.getProductType() + " has been successfully turned on.");
	}
	
	public String turnDeviceOff(Device device) throws URISyntaxException {
		update();
		if (device.getEndpoints().get(0).getCluster("onOff").getAttribute("onOff").getValue().equals("true")) {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	        HttpEntity<String> request = new HttpEntity<String>(headers);
	        RestTemplate restTemplate = new RestTemplate();
	        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	        restTemplate.setRequestFactory(requestFactory);
	        try {
	        	restTemplate.exchange(link + "/devices/" + device.getSerialNo() + "/endpoints/3/clusters/6/cmd/2", HttpMethod.POST, request, String.class);
	        } catch (HttpStatusCodeException e){
	        	return e.getResponseBodyAsString();
	        } catch(RestClientException e){
	        	return e.getMessage();
	        }
	        update();
	        return ("The " + device.getProductType() + " has been successfully turned off.");
		}
		return ("The " + device.getProductType() + " was already off.");
	}
	
	public List<Event> getEvents(LocalDateTime from, LocalDateTime to) throws URISyntaxException {
    	RestTemplate restTemplate = new RestTemplate();
    	from = from.minusHours(2);
    	to = to.minusHours(2);
    	String sFrom = Integer.toString(from.getYear());
    	if (from.getMonthValue() < 10) {
    		sFrom += "0";
    	}
    	sFrom += Integer.toString(from.getMonthValue());
    	if (from.getDayOfMonth() < 10) {
    		sFrom += "0";
    	}
    	sFrom += Integer.toString(from.getDayOfMonth());
    	sFrom += "T";
    	if (from.getHour() < 10) {
    		sFrom += "0";
    	}
    	sFrom += Integer.toString(from.getHour());
    	if (from.getMinute() < 10) {
    		sFrom += "0";
    	}
    	sFrom += Integer.toString(from.getMinute());
    	if (from.getSecond() < 10) {
    		sFrom += "0";
    	}
    	sFrom += Integer.toString(from.getSecond());
    	sFrom += "Z";
    	
    	String sTo = Integer.toString(to.getYear());
    	if (to.getMonthValue() < 10) {
    		sTo += "0";
    	}
    	sTo += Integer.toString(to.getMonthValue());
    	if (to.getDayOfMonth() < 10) {
    		sTo += "0";
    	}
    	sTo += Integer.toString(to.getDayOfMonth());
    	sTo += "T";
    	if (to.getHour() < 10) {
    		sTo += "0";
    	}
    	sTo += Integer.toString(to.getHour());
    	if (to.getMinute() < 10) {
    		sTo += "0";
    	}
    	sTo += Integer.toString(to.getMinute());
    	if (to.getSecond() < 10) {
    		sTo += "0";
    	}
    	sTo += Integer.toString(to.getSecond());
    	sTo += "Z";
    	try {
    		return (restTemplate.getForObject(new URI(link + "/events?page=0&size=10&from=" + sFrom + "&to=" + sTo), EmbeddedEvents.class).get_embedded().getEvents());
    	} catch (HttpStatusCodeException e){
    		System.out.println(e.getResponseBodyAsString());
    		return new ArrayList<Event>();
    	} catch(RestClientException e){
    		System.out.println(e.getMessage());
    		return new ArrayList<Event>();
    	}
	}
	
	public String deleteHome() {
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
        	restTemplate.exchange(link, HttpMethod.DELETE, request, String.class);
        } catch (HttpStatusCodeException e){
        	return e.getResponseBodyAsString();
        } catch(RestClientException e){
        	return e.getMessage();
        }
		return "The home " + homeId + " has been successfully deleted.";
	}
}
