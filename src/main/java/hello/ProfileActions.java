package hello;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileActions{
	
	private List<String> arm, disarm, partlyArm;
	
	public ProfileActions() {
		arm = new ArrayList<String>();
		disarm = new ArrayList<String>();
		partlyArm = new ArrayList<String>();
	}
	
	public ProfileActions(String arm, String disarm, String partlyArm) {
		this.arm = new ArrayList<String>();
		this.disarm = new ArrayList<String>();
		this.partlyArm = new ArrayList<String>();
		this.arm.add(arm);
		this.disarm.add(disarm);
		this.partlyArm.add(partlyArm);
	}
	
	public ProfileActions(ProfileActions profileActions) {
		this(profileActions.getArm().get(0), profileActions.getDisarm().get(0), profileActions.getPartlyArm().get(0));
	}

	public List<String> getArm() {
		return arm;
	}

	public void setArm(List<String> arm) {
		this.arm = arm;
	}

	public List<String> getDisarm() {
		return disarm;
	}

	public void setDisarm(List<String> disarm) {
		this.disarm = disarm;
	}

	public List<String> getPartlyArm() {
		return partlyArm;
	}

	public void setPartlyArm(List<String> partlyArm) {
		this.partlyArm = partlyArm;
	}

	@Override
	public String toString() {
		String toReturn = "ProfileActions ";
		if (arm != null) {
			toReturn += "arm = " + arm.get(0) + '\n';
		}
		if (disarm != null) {
			toReturn += "                disarm = " + disarm.get(0) + '\n';
		}
		if (partlyArm != null) {
			toReturn += "                partlyArm = " + partlyArm.get(0) + '\n';
		}
		return toReturn;
	}
}
