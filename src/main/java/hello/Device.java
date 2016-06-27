package hello;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Device{
	
	private String serialNo, addedAt, name, productType;
	
	private ProfileActions profileActions;
	
	private List<Endpoint> endpoints;
	
	public Device() {
		
	}
	
	public Device(String serialNo, String addedAt, String name, String productType) {
		this.serialNo = serialNo;
		this.addedAt = addedAt;
		this.name = name;
		this.productType = productType;
	}
	
	public Device(Device device) {
		this.serialNo = device.serialNo;
		this.addedAt = device.addedAt;
		this.name = device.name;
		this.productType = device.productType;
		this.profileActions = device.profileActions;
		this.endpoints = device.endpoints;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public ProfileActions getProfileActions() {
		return profileActions;
	}

	public void setProfileActions(ProfileActions profileActions) {
		this.profileActions = profileActions;
	}

	public List<Endpoint> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	@Override
	public String toString() {
		String toReturn =  "\n \n serialNo = " + serialNo +
        		"\n addedAt = " + addedAt +
                "\n name = " + name +
                "\n productType = " + productType +
                "\n ";
		if (profileActions != null) {
			toReturn += profileActions.toString() +'\n';
		}
		return toReturn;
	}
}
