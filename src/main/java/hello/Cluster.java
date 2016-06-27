package hello;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cluster{
	
	private String id, name;
	
	private List<Attribute> attributes;
	
	public Cluster() {
		
	}
	
	public Cluster(Cluster cluster) {
		this.id = cluster.id;
		this.name = cluster.name;
		this.attributes = cluster.attributes;
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

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	@JsonIgnore
	public Attribute getAttribute(int id) {
		for (Attribute attribute : getAttributes()) {
			if (attribute.getId() == Endpoint.DecimalToHex(id)) {
				return attribute;
			}
		}
		System.out.println("Attribute not found.");
		return new Attribute();
	}
	
	@JsonIgnore
	public Attribute getAttribute(String name) {
		for (Attribute attribute : getAttributes()) {
			if (attribute.getName().equals(name)) {
				return attribute;
			}
		}
		System.out.println("Attribute not found.");
		return new Attribute();
	}
}
