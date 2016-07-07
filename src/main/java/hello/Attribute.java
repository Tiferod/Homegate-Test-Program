package hello;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute{
	
	private String id, name, value, updatedAt;
	
	public Attribute() {
		
	}
	
	public Attribute(Attribute attribute) {
		this.id = attribute.id;
		this.name = attribute.name;
		this.value = attribute.value;
		this.updatedAt = attribute.updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@JsonIgnore
	public LocalDateTime getUpdatedTime() {
		return LocalDateTime.of(Integer.parseInt(updatedAt.substring(0, 4)), Integer.parseInt(updatedAt.substring(5, 7)), Integer.parseInt(updatedAt.substring(8, 10)),
								Integer.parseInt(updatedAt.substring(11, 13)) + 2, Integer.parseInt(updatedAt.substring(14, 16)), Integer.parseInt(updatedAt.substring(17, 19)));
	}
}
