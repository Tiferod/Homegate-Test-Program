package hello;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event{
	
	private String createdAt, deviceProductType, type, userFirstName, userLastName; 
	
	public Event() {
		
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDeviceProductType() {
		return deviceProductType;
	}

	public void setDeviceProductType(String deviceProductType) {
		this.deviceProductType = deviceProductType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	@JsonIgnore
	public LocalDateTime getCreatedTime() {
		return LocalDateTime.of(Integer.parseInt(createdAt.substring(0, 4)), Integer.parseInt(createdAt.substring(5, 7)), Integer.parseInt(createdAt.substring(8, 10)),
								Integer.parseInt(createdAt.substring(11, 13)), Integer.parseInt(createdAt.substring(14, 16)), Integer.parseInt(createdAt.substring(17, 19)));
	}

	@Override
	public String toString() {
		return "Event [createdAt=" + createdAt + ", deviceProductType=" + deviceProductType + ", type=" + type
				+ ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + "]";
	}
}
