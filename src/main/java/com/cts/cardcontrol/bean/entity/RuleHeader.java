package com.cts.cardcontrol.bean.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RULES_HEADER")
public class RuleHeader extends AuditInfo implements Identifiable<Long> {

	/**
	 * ID column
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RULE_HEADER_ID")
	@JsonIgnore
	private Long id;
	/**
	 * Rule Group ID
	 */
	@JsonIgnore
	private String ruleGroupID;
	/**
	 * Rule Name/ Description
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
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	/**
	 * Expiry Date
	 */
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
	/**
	 * Rule Level Value
	 */
	@ManyToOne
	@JoinColumn(name = "RULE_LEVEL_VALUE")
	@JsonIgnore
	private CardRegistration cardRegistration;
	
	@ElementCollection
	@CollectionTable(name ="RULE_DETAIL",joinColumns=@JoinColumn(name="RULE_HEADER_ID"))
	private List<RuleDetail> ruleDetails = new ArrayList<RuleDetail>(6);
	

	public CardRegistration getCardRegistration() {
		return cardRegistration;
	}

	public void setCardRegistration(CardRegistration cardRegistration) {
		this.cardRegistration = cardRegistration;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getRuleGroupID() {
		return ruleGroupID;
	}

	public void setRuleGroupID(String ruleGroupID) {
		this.ruleGroupID = ruleGroupID;
	}

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

	public void setId(Long id) {
		this.id = id;
	}

	public List<RuleDetail> getRuleDetails() {
		return ruleDetails;
	}

	public void setRuleDetails(List<RuleDetail> ruleDetails) {
		this.ruleDetails = ruleDetails;
	}
	public void addRuleDetail(RuleDetail ruleDetail){
		getRuleDetails().add(ruleDetail);
	}



}
