package com.cts.cardcontrol.bean;

import java.util.List;
import java.util.Map;

public class ControlGroup {

	private String groupName;
	private List< Map<String,String>> controlDetails;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List< Map<String,String>> getControlDetails() {
		return controlDetails;
	}
	public void setControlDetails(List< Map<String,String>> controlDetails) {
		this.controlDetails = controlDetails;
	}
	


}
