package com.cts.cardcontrol.bean.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RuleHeaderDto {
	
	/**
	 * Rule Name/Description
	 */
	private String ruleDescription;
	/**
	 * Rule Level
	 */
	private String ruleLevel;
	/**
	 * Decision Code
	 */
	private String decisionCode;
	/**
	 * Active Flag
	 */
	private String activeFlag;
	/**
	 * Start Date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	/**
	 * Expiry Date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
	/**
	 * Group Name of the UI Control
	 */
	private String groupName;
	
	private List<RuleDetailDto> controlDetails = new ArrayList<RuleDetailDto>(6);
	// Need to remove 
	private List<RuleDetailDto> ruleDetails = new ArrayList<RuleDetailDto>(6);

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getRuleLevel() {
		return ruleLevel;
	}

	public void setRuleLevel(String ruleLevel) {
		this.ruleLevel = ruleLevel;
	}

	public String getDecisionCode() {
		return decisionCode;
	}

	public void setDecisionCode(String decisionCode) {
		this.decisionCode = decisionCode;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List<RuleDetailDto> getControlDetails() {
		return controlDetails;
	}

	public void setControlDetails(List<RuleDetailDto> controlDetails) {
		this.controlDetails = controlDetails;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<RuleDetailDto> getRuleDetails() {
		return ruleDetails;
	}

	public void setRuleDetails(List<RuleDetailDto> ruleDetails) {
		this.ruleDetails = ruleDetails;
	}

	@Override
	public String toString() {
		return "RuleHeaderDto [ruleDescription=" + ruleDescription + ", ruleLevel=" + ruleLevel + ", decisionCode="
				+ decisionCode + ", activeFlag=" + activeFlag + ", startDate=" + startDate + ", expiryDate="
				+ expiryDate + ", groupName=" + groupName + ", controlDetails=" + controlDetails + ", ruleDetails="
				+ ruleDetails + "]";
	}


	

}
