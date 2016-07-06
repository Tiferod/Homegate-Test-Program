package hello;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User{
	
	private String index, firstName, lastName, phone, role, pin, validFrom, email, validTo;
	private URI link;
	
	public User() {
		
	}

	public User(String firstName, String lastName, String phone, String role, String pin, String validFrom, String validTo,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.role = role;
		this.pin = pin;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.email = email;
	}
	
	public void setUser(User user) {
		this.index = user.index;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		if (user.phone != null) {
			this.phone = user.phone;
		}
		this.role = user.role;
		if (user.pin != null) {
			this.pin = user.pin;
		}
		this.validFrom = user.validFrom;
		this.validTo = user.validTo;
		this.email = user.email;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public URI getLink() {
		return link;
	}

	public void setLink(URI link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "User [index=" + index + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", role=" + role + ", pin=" + pin + ", validFrom=" + validFrom + ", validTo=" + validTo 
				+ ", email=" + email + "]";
	}
}
