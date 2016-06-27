package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Adress{
	
	private String street, zipCode, city, country;
	private Location location;
	
	public Adress() {
		
	}

	public Adress(String street, String zipCode, String city, String country, Location location) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.location = location;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "\n \n street = " + street +
        		"\n zipCode = " + zipCode +
                "\n city = " + city +
                "\n country = " + country +
                "\n location = " + location +
                '\n';
	}
}
