package hello;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Endpoint{
	
	private String id;
	
	private List<Cluster> clusters;
	
	public Endpoint() {
		
	}
	
	public Endpoint(Endpoint endpoint) {
		this.id = endpoint.id;
		this.clusters = endpoint.clusters;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}
	
	@JsonIgnore
	public Cluster getCluster(String name) {
		for (Cluster cluster : getClusters()) {
			if (cluster.getName().equals(name)) {
				return cluster;
			}
		}
		System.out.println("Cluster not found.");
		return new Cluster();
	}
	
	@JsonIgnore
	public Cluster getCluster(int id) {
		for (Cluster cluster : getClusters()) {
			if (cluster.getId() == DecimalToHex(id)) {
				return cluster;
			}
		}
		System.out.println("Cluster not found.");
		return new Cluster();
	}
	
	@JsonIgnore
	public static String DecimalToHex(int decimalId) {
		String hexId = Integer.toHexString(decimalId);
		while (hexId.length() < 4) {
			hexId = "0" + hexId;
		}
		hexId = "0x" + hexId;
		return hexId;
	}
	
	@JsonIgnore
	public static int HexToDecimal(String hexId) {
		return Integer.parseInt(hexId.substring(2), 16);
	}
}
