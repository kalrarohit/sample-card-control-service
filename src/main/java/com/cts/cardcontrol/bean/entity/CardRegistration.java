package com.cts.cardcontrol.bean.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name = "CARD_REGISTRATION")
public class CardRegistration extends AuditInfo implements Identifiable<String> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CardRegistration.class);

	/*
	 * @Id // @GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * @GenericGenerator(name = "assigned-sequence", strategy =
	 * "com.cts.cardcontrol.StringSequenceIdentifier", parameters = {
	 * 
	 * @org.hibernate.annotations.Parameter(name = "sequence_name", value =
	 * CardControlConstant.CARD_REGISTRATION_SEQ),
	 * 
	 * @org.hibernate.annotations.Parameter(name = "sequence_prefix", value =
	 * ""), })
	 * 
	 * @GeneratedValue(generator = "assigned-sequence", strategy =
	 * GenerationType.SEQUENCE)
	 */
	@Id
	@Column(name = "CARD_NUMBER")
	private String id;
	/**
	 * Customer Id
	 */
	private String customerId;
	/**
	 * The name present on the card
	 */
	private String nameOnCard;
	/**
	 * Registration status of the card
	 */
	private String status;
	/**
	 * Expiry date of the card
	 */
	private String expiryDate;
	/**
	 * Card Limit
	 */
	private Long cardLimit;
	/**
	 * Cash withdrawal limit
	 */
	private Long cashLimit;
	/**
	 * POS Purchase Limit
	 */
	private Long pospurchaseLimit;
	/**
	 * Enrollment date
	 */
	@Temporal(TemporalType.DATE)
	private Date enrolledDate = new Date();

	@OneToMany(mappedBy = "cardRegistration", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval=true)
	//private List<RuleHeader> ruleHeaders = new ArrayList<>(4);
	@MapKey(name = "ruleDescription")
	private Map<String, RuleHeader> ruleHeaders = new HashMap<String, RuleHeader>(5);
	
	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(Long cardLimit) {
		this.cardLimit = cardLimit;
	}

	public Long getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(Long cashLimit) {
		this.cashLimit = cashLimit;
	}

	public Long getPospurchaseLimit() {
		return pospurchaseLimit;
	}

	public void setPospurchaseLimit(Long pospurchaseLimit) {
		this.pospurchaseLimit = pospurchaseLimit;
	}

	public Date getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Map<String, RuleHeader> getRuleHeaders() {
		return ruleHeaders;
	}

	public void setRuleHeaders(Map<String, RuleHeader> ruleHeaders) {
		this.ruleHeaders = ruleHeaders;
	}

	public void addRuleHeader(RuleHeader rulesHeader){
		if(rulesHeader == null){
			LOGGER.error("RulesHeader can't be null");
			throw new IllegalArgumentException("Null child");			
		}
		rulesHeader.setCardRegistration(this);
		getRuleHeaders().put(rulesHeader.getRuleDescription(), rulesHeader);
	}

	public static Logger getLOGGER() {
		return LOGGER;
	}

	public static void setLOGGER(Logger lOGGER) {
		LOGGER = lOGGER;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}


}