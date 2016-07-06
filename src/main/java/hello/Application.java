package hello;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
    	String str = "";
    	Scanner sc = new Scanner(System.in);
    	boolean b = true;
    	Homegate homegate = new Homegate();
    	Home home = new Home();
    	while (b) {
    		System.out.println("\nPlease enter the serial number of the homegate you want to test :\n");
    		str = sc.next();
    		if (homegate.createHomegate(str)) {
    			b = false;
    		}
    	}
    	System.out.println(homegate.toString());
    	if (homegate.gotHome()) {
    		System.out.println("\nYour homegate is already attached to a home. Its Id is : " + homegate.getHomeId() + ".");
    		b = true;
    		while (b) {
    			System.out.println("\nDo you want to detach it ? Y/N\n");
        		str = sc.next();
        		if (str.startsWith("Y")) {
        			Home home1 = new Home();
        			home1.setHomeId(homegate.getHomeId());
        			System.out.println(home1.detachHomegate(homegate));
        			while (b) {
        				System.out.println("\nAdditionnaly, do you want to delete this home ? Y/N\n");
        	    		str = sc.next();
        	    		if (str.startsWith("Y")) {
        	    			System.out.println(home1.deleteHome());
        	    			b = false;
        	    		}
        	    		else if (str.startsWith("N")) {
        	    			b = false;
        	    		}
        	    	}
        			System.out.println("\nTherefore, we need a home to add your homegate to.");
        			b = true;
        			while (b) {
        				System.out.println("\nIs the home already created ? Y/N\n");
        	    		str = sc.next();
        	    		if (str.startsWith("Y")) {
        	    			System.out.println("\nPlease enter the Id of the home.\n");
        	    			str = sc.next();
        	    			home.setHomeId(str);
        	    			str = home.update();
        	    			System.out.println(str);
        	    			if (str.startsWith("The home")) {
        	    				b = false;
        	    			}
        	    		}
        	    		else if (str.startsWith("N")) {
        	    			System.out.println("\nTherefore, the test will create a home for the homegate to be associated to.\nWrite anything and press Enter to continue.");
        	    			b = false;
        	    			str = sc.next();
        	    			Location location = new Location("44.920675", "4.266843");
        	    	    	Adress adress = new Adress("30 rue du 19 mars 1962", "30190", "La Calmette", "FR", location);
        	    	    	Home home2 = new Home("Dorian", "Paret", "1956", "1495", "none", "Europe/Oslo", "Bonjour", adress);
        	    	    	home.setHome(home2);
        	    	    	System.out.println(home.postHome());
        	    		}
        	    	}
        	    	home.update();
        	    	System.out.println(home.addHomegate(homegate));
        	    	home.update();
        		}
        		else if (str.startsWith("N")) {
        			home.setHomeId(homegate.getHomeId());
        			home.update();
        			b = false;
        		}
        	}
    	}
    	else {
    		System.out.println("\nThere is no home attached to your homegate. Therefore, we need one.");
    		b = true;
			while (b) {
				System.out.println("\nIs the home already created ? Y/N\n");
	    		str = sc.next();
	    		if (str.startsWith("Y")) {
	    			System.out.println("\nPlease enter the Id of the home.\n");
	    			str = sc.next();
	    			home.setHomeId(str);
	    			str = home.update();
	    			System.out.println(str);
	    			if (str.startsWith("The home")) {
	    				b = false;
	    			}
	    		}
	    		else if (str.startsWith("N")) {
	    			System.out.println("\nTherefore, the test will create a home for the homegate to be associated to.\nWrite anything and press Enter to continue.");
	    			b = false;
	    			str = sc.next();
	    			Location location = new Location("44.920675", "4.266843");
	    	    	Adress adress = new Adress("30 rue du 19 mars 1962", "30190", "La Calmette", "FR", location);
	    	    	Home home2 = new Home("Dorian", "Paret", "1956", "1495", "none", "Europe/Oslo", "Bonjour", adress);
	    	    	home.setHome(home2);
	    	    	System.out.println(home.postHome());
	    		}
	    	}
	    	home.update();
	    	System.out.println(home.addHomegate(homegate));
	    	home.update();
    	}
    	System.out.println(home.toString());
    	home.deleteUsers();
    	System.out.println("\nDeleting the users...\n");
    	Thread.sleep(5000);
    	System.out.println("\nWe will now associate an user to it.\n");
    	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    	StringBuilder sb = new StringBuilder();
    	Random random = new Random();
    	for (int i = 0; i < 10; i++) {
    	    char c = chars[random.nextInt(chars.length)];
    	    sb.append(c);
    	}
    	String email = sb.toString();
    	User user = new User("Oscar", "Bergan", "+4746620851", "master", "1857", "2016-05-10T11:00:00+0000", "2026-05-10T11:00:00+0000", email + "@gmail.com");
    	home.update();
    	System.out.println("\nAdding a new user...\n");
    	home.addUser(user);
    	/*Thread.sleep(5000);
    	home.update();
    	user = home.getUsers().get(0);*/
    	System.out.println(user.toString());
    	System.out.println("\nAn user has been associated to the homegate.");
    	System.out.println("\nNow take a look at the device associated to the homegate.\n");
    	for(Device device : home.getDevices()) {
    		System.out.println(device.toString());
    		System.out.println("\nThis device is associated to the homegate. Do you want to delete it ? It could be added again later. Y/N");
    		b = true;
    		while (b) {
        		str = sc.next();
        		b = !(str.startsWith("Y") || str.startsWith("N"));
        		if (str.startsWith("Y")) {
        			System.out.println(home.deleteDevice(device));
        		}
        	}
    	}
    	home.update();
    	System.out.println(home.getDevices().toString());
    	System.out.println("\nDo you want to add some devices to the homegate ? Y/N");
		str = sc.next();
		if (str.startsWith("Y")) {
			b = true;
			while (b) {
				home.addDevice(90);
				System.out.println("\n Do you have any other device to add ? Y/N");
				str = sc.next();
				if (str.startsWith("Y")) {
					b = true;
				}
				else if (str.startsWith("N")) {
					b = false;
					System.out.println("\n The detection has ended.");
				}
				else {
					System.out.println("\n Please write 'Y' or 'N' to continue.");
				}
			}
		}
    	home.update();
    	if (home.numberOfDevices == 0) {
    		System.out.println("\nNo device has been found. The test will end.");
    	}
    	else if (home.numberOfDevices == 1) {
    		System.out.println("\n1 device has been found");
    	}
    	else {
    		System.out.println("\n" + home.numberOfDevices + " devices have been found.");
    	}
    	System.out.println(home.getDevices().toString());
    	System.out.println("\nAs the first part of the test, the devices will identify themselves. You should note a device's flashing or beeping.");
    	System.out.println("Sometimes, the devices could take some time to react. Don't worry.");
    	System.out.println("\nThe devices are now identifying themselves...");
    	System.out.println(home.identifyDevices());
    	Thread.sleep(60000);
    	System.out.println("\nThe identification should be finished. If so, write anything and press Enter to continue. If not, please wait until the end.");
    	str = sc.next();
    	LocalDateTime startDate = LocalDateTime.now();
    	Event armEvent = new Event();
    	Event disarmEvent = new Event();
    	for (Device device : home.getDevices()) {
    		home.update();
    		if (device.getProductType().equals("null")) {
    			System.out.println("\nOne of your devices has not been recognized well. You should reset and reconnect it to the homegate again.");
    		}
    		else {
    			System.out.println("\nWe have detected that one of your devices is a " + device.getProductType() + ". Do you want to test it ? Y/N");
    			startDate = LocalDateTime.now();
    			str = sc.next();
    			if (str.startsWith("Y")) {
    				b = true;
    				switch(device.getProductType()) {
    				case "keyPad" :
    					System.out.println("\nIn order to test it, please type your pin and arm the alarm.");
    					armEvent = new Event();
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("keyPad") && event.getType().equals("home.armed")) {
    								b = false;
    								armEvent = event;
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nThe user : " + armEvent.getUserFirstName() + " " + armEvent.getUserLastName() + " has armed the alarm. You can now disarm it.");
    					b = true;
    					disarmEvent = new Event();
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("keyPad") && event.getType().equals("home.disarmed")) {
    								b = false;
    								disarmEvent = event;
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nThe user : " + disarmEvent.getUserFirstName() + " " + disarmEvent.getUserLastName() + " has disarmed the alarm.");
    					break;
    				case "lightBulb" :
    					home.turnDeviceOff(device);
    					ProfileActions profileActions = new ProfileActions("on", "off", "on");
    					device.setProfileActions(profileActions);
    					System.out.println("\nWe have set some ProfileActions to this lightBulb for further testing. When you will arm the alarm, the lightBulb should turn on. When you will disarm the alarm, the lightBulb should turn off.");
    					System.out.println("\nIn order to test it, please turn it on.");
    					Thread.sleep(5000);
    					home.update();
    					device = home.updateDevice(device);
    					while (device.getEndpoints().get(0).getCluster("onOff").getAttribute("onOff").getValue().equals("false")) {
    						Thread.sleep(1000);
    						home.update();
    						device = home.updateDevice(device);
    					}
    					System.out.println("\nThe lightBulb has been turned on. You can now turn it off.");
    					Thread.sleep(5000);
    					while (device.getEndpoints().get(0).getCluster("onOff").getAttribute("onOff").getValue().equals("true")) {
    						Thread.sleep(1000);
    						home.update();
    						device = home.updateDevice(device);
    					}
    					System.out.println("\nThe lightBulb has been turned off.");
    					break;
    				case "detectorPet" :   			
    					System.out.println("\nIn order to test it, please move something in front of it.");
    					while (device.getEndpoints().get(0).getCluster("iasZone").getAttributes().get(0).getValue().equals("16")  && device.getEndpoints().get(0).getCluster("iasZone").getAttributes().get(0).getUpdatedTime().isBefore(startDate)) {
    						Thread.sleep(1000);
    						home.update();
    						device = home.updateDevice(device);
    					}
    					System.out.println("\nThe detectorPet has detected a movement.");
    					break;
    				case "smokeDetector" :
    					System.out.println("\nIn order to test it, please inject some smoke inside the smokeDetector.");
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("smokeDetector") && event.getType().equals("alarm.activated")) {
    								b = false;
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nThe smokeDetector has detected some smoke. Please now silence the alarm by pressing the button on the smokeDetector.");
    					b = true;
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("smokeDetector") && event.getType().equals("alarm.deactivated")) {
    								b = false;
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nThe alarm has been silenced. Please now type your pin on keyPad to deactivate it. When it's done, write anything and press Enter to go on in the test.");
    					str = sc.next();
    					break;
    				case "waterDetector" :
    					System.out.println("\nIn order to test it, please put the nodes in contact of water.");
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("waterDetector") && event.getType().equals("alarm.activated")) {
    								b = false;
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nThe waterDetector has detected some water. Please now silence the alarm by pressing the button on the waterDetector or waiting a few seconds.");
    					b = true;
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("waterDetector") && event.getType().equals("alarm.deactivated")) {
    								b = false;
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nThe alarm has been silenced. Please now type your pin on keyPad to deactivate it. When it's done, write anything and press Enter to go on in the test.");
    					str = sc.next();
    					break;
    				case "magnetContact" :
    					System.out.println("\nIn order to test it, please keep the temper switch pressed.");
    					while (device.getEndpoints().get(0).getCluster("iasZone").getAttributes().get(0).getValue().equals("21") && device.getEndpoints().get(0).getCluster("iasZone").getAttributes().get(0).getUpdatedTime().isBefore(startDate)) {
    						Thread.sleep(1000);
    						home.update();
    						device = home.updateDevice(device);
    					}
    					System.out.println("\nThe tamper switch is pressed. While doing, please put the magnet opposite the rib-marks of the door contact.");
    					while (!device.getEndpoints().get(0).getCluster("iasZone").getAttributes().get(0).getValue().equals("16")) {
    						Thread.sleep(1000);
    						home.update();
    						device = home.updateDevice(device);
    					}
    					System.out.println("\nThe door contact has detected the magnet. Please now remove it.");
    					while (!device.getEndpoints().get(0).getCluster("iasZone").getAttributes().get(0).getValue().equals("17")) {
    						Thread.sleep(1000);
    						home.update();
    						device = home.updateDevice(device);
    					}
    					System.out.println("\nThe magnet has been removed. You can now stop pressing the tamper switch.");
    					while (!device.getEndpoints().get(0).getCluster("iasZone").getAttributes().get(0).getValue().equals("21")) {
    						Thread.sleep(1000);
    						home.update();
    						device = home.updateDevice(device);
    					}
    					System.out.println("\nThe tamper switch is not pressed anymore.");
    					break;
    				case "keyFob" :
    					System.out.println("\nIn order to test it, please press one of the two top buttons to arm the alarm.");
    					armEvent = new Event();
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("keyFob") && (event.getType().equals("home.armed") || event.getType().equals("home.partly.armed"))) {
    								b = false;
									armEvent = event;
    								if (event.getType().equals("home.armed")) {									
    									str = "Away Arm";
    								}
    								else if (event.getType().equals("home.partly.armed")) {
    									str = "Home Arm";
    								}
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nYou have pressed the " + str + " Button. You can now disarm the alarm by pressing the Disarm Button.");
    					b = true;
    					disarmEvent = new Event();
    					while (b) {
    						for (Event event : home.getEvents(startDate, LocalDateTime.now())) {
    							if (event.getDeviceProductType().equals("keyFob") && event.getType().equals("home.disarmed")) {
    								b = false;
    								disarmEvent = event;
    							}
    						}
    						Thread.sleep(1000);
    					}
    					System.out.println("\nYou have pressed the Disarm Button. The alarm is now disarmed.");
    					break;
    				default :
    					System.out.println("\nThere is not yet a test for the " + device.getProductType() + ".");
    					break;
    				}
    				System.out.println("\nThis is the end of the " + device.getProductType() + " test.");
    				System.out.println("\nWrite anything and press Enter to test an other device.");
    				str = sc.next();
    			}
    		}
    	}
    	System.out.println("\nThis is the end of the test. Write anything and press Enter to end the program.");
		str = sc.next();
    	sc.close();
    }
}