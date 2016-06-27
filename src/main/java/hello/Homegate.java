package hello;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Homegate{

    private String homegateId, serialNo, description, telNo, simIccid, vendor, model, firmwareVersion, wlanApn, partnerId;
    private URI link;
    
    public Homegate() {
    	
    }

	public Homegate(String simIccid, String vendor, String wlanApn, String description,
			 String model, String firmwareVersion, String telNo, String serialNo) {
		this.serialNo = serialNo;
		this.description = description;
		this.telNo = telNo;
		this.simIccid = simIccid;
		this.vendor = vendor;
		this.model = model;
		this.firmwareVersion = firmwareVersion;
		this.wlanApn = wlanApn;
	}
	
	public boolean createHomegate(String serialNo) throws URISyntaxException {
		URI url = new URI("http://home-test-api.datek.no/homegates/serialNo/" + serialNo);
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    		this.setHomegate(restTemplate.getForObject(url, Homegate.class));
        } catch (HttpStatusCodeException e){
        	if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        		System.out.println("No homegate with the serial number " + serialNo + " has been found.");
        		return false;
        	}
        	else {
        		System.out.println(e.getResponseBodyAsString());
        		return false;
        	}
        } catch(RestClientException e){
        	System.out.println(e.getMessage());
        	return false;
        }
    	this.setLink(url);
    	System.out.println("The homegate " + serialNo + " has been found.");
		return true;
	}
	
	public void setHomegate(Homegate homegate) {
		this.homegateId = homegate.homegateId;
		this.serialNo = homegate.serialNo;
		this.description = homegate.description;
		this.telNo = homegate.telNo;
		this.simIccid = homegate.simIccid;
		this.vendor = homegate.vendor;
		this.model = homegate.model;
		this.firmwareVersion = homegate.firmwareVersion;
		this.wlanApn = homegate.wlanApn;
		this.partnerId = homegate.partnerId;
	}

	public String getHomegateId() {
		return homegateId;
	}
	
	private void setHomegateId (String homegateId) {
		this.homegateId = homegateId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getSimIccid() {
		return simIccid;
	}

	public void setSimIccid(String simIccid) {
		this.simIccid = simIccid;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public String getWlanApn() {
		return wlanApn;
	}

	public void setWlanApn(String wlanApn) {
		this.wlanApn = wlanApn;
	}

	public String getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
	
	public String getPartnerId() {
		return partnerId;
	}

    public URI getLink() {
		return link;
	}

	public void setLink(URI link) {
		this.link = link;
	}

	@Override
    public String toString() {
        return "\n \n homegateId = " + homegateId +
        		"\n serialNo = " + serialNo +
                "\n description = " + description +
                "\n telNo = " + telNo +
                "\n simIccid = " + simIccid +
                "\n vendor = " + vendor +
                "\n model = " + model +
                "\n firmwareVersion = " + firmwareVersion +
                "\n wlanApn = " + wlanApn +
                "\n partnerId = " + partnerId +
                '\n';
    }
    
    public String postHomegate() throws URISyntaxException {
    	URI url = new URI("http://home-test-api.datek.no/homegates");
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Homegate> request = new HttpEntity<Homegate>(this, headers);
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    		homegateId = restTemplate.postForEntity(url,request,String.class).getHeaders().getLocation().toString().substring(40);
        } catch (HttpStatusCodeException e){
        	String errorpayload = e.getResponseBodyAsString();
        	this.setHomegate(restTemplate.getForObject(url + "/serialNo/" + serialNo, Homegate.class));
        	if (restTemplate.getForObject(url + "/serialNo/" + serialNo, String.class).lastIndexOf("homes") != -1)  {
        		setLink(new URI(url + "/" + homegateId));
        	}
	        else {
	        	setLink(new URI(restTemplate.getForObject(url + "/serialNo/" + serialNo, String.class).substring(restTemplate.getForObject(url + "/serialNo/" + serialNo, String.class).lastIndexOf("http"), restTemplate.getForObject(url + "/serialNo/" + serialNo, String.class).lastIndexOf("/homegate")+8)));
	        }
        	return "This homegate was already created. The object homegate has been updated to match the online version.";
        } catch(RestClientException e){
        	return e.getMessage();
        }
    	setLink(new URI("http://home-test-api.datek.no/homegates/" + homegateId));
    	return "The homegate has been created";
    }
    
    public String addPartner(Partner partner) throws URISyntaxException {
    	if (partnerId != null) {
    		return "This homegate has already a partner.";
    	}
    	else {
	    	this.partnerId = partner.getId();
	    	HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	        HttpEntity<Homegate> request = new HttpEntity<Homegate>(this, headers);
	        RestTemplate restTemplate = new RestTemplate();
	        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	        restTemplate.setRequestFactory(requestFactory);
	        restTemplate.exchange(link, HttpMethod.PATCH, request, String.class);
	        return "The partner " + partner.getName() +" has been associated to this homegate.";
    	}
    }
    
    public boolean gotHome() {
    	RestTemplate restTemplate = new RestTemplate();
    	String body = restTemplate.getForObject(link, String.class);
    	return body.indexOf("homes") != -1;
    }
    
    public String getHomeId() {
    	if (gotHome()) {
    		RestTemplate restTemplate = new RestTemplate();
        	String body = restTemplate.getForObject(link, String.class);
        	return body.substring(body.indexOf("homes") + 6, body.indexOf("homegate", body.indexOf("homes") + 6) - 1);
    	}
    	else {
    		return "There is no home attached to this homegate";
    	}
    }
}

