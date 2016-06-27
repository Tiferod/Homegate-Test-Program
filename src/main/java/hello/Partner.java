package hello;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner{
	
	private String name, id;
	private URI link;
	
	public Partner() {
		
	}
	
	public Partner(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public void setPartner(Partner partner) {
		this.name = partner.name;
		this.id = partner.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public URI getLink() {
		return link;
	}

	public void setLink(URI link) {
		this.link = link;
	}

	public String postPartner() throws URISyntaxException {
		URI url = new URI("http://home-test-api.datek.no/partners");
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Partner> request = new HttpEntity<Partner>(this, headers);
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    		restTemplate.postForEntity(url,request,String.class);
        } catch (HttpStatusCodeException e){
        	return e.getResponseBodyAsString();
        } catch(RestClientException e){
        	return "An unknown error has occured.";
        }
    	setLink(new URI("http://home-test-api.datek.no/partners/" + id));
    	this.setPartner(restTemplate.getForObject(link, Partner.class));
        return "The partner has been succesfully posted.";
	}
}
