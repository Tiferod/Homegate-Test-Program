package hello;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact{
	
	private String phone, name, phoneCountryPrefix, priority, email, relation;
	private URI link;
	
	public Contact() {
		
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Contact(String phone, String name, String phoneCountryPrefix, String priority, String email,
			String relation) {
		this.phone = phone;
		this.name = name;
		this.phoneCountryPrefix = phoneCountryPrefix;
		this.priority = priority;
		this.email = email;
		this.relation = relation;
	}
	
	public void setContact(Contact contact) {
		this.phone = contact.phone;
		this.name = contact.name;
		this.phoneCountryPrefix = contact.phoneCountryPrefix;
		this.priority = contact.priority;
		this.email = contact.email;
		this.relation = contact.relation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneCountryPrefix() {
		return phoneCountryPrefix;
	}

	public void setPhoneCountryPrefix(String phoneCountryPrefix) {
		this.phoneCountryPrefix = phoneCountryPrefix;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public URI getLink() {
		return link;
	}

	public void setLink(URI link) {
		this.link = link;
	}
}
