package com.cts.cardcontrol.service.msg;

import java.util.ArrayList;
import java.util.List;

import com.cts.cardcontrol.bean.dto.RuleHeaderDto;
import com.cts.cardcontrol.bean.entity.RuleHeader;
import com.cts.cardcontrol.exception.CardControlException;

public class RuleConfigurationRequestMsg implements IRequestMessage {

	/**
	 * Card-On-Off
	 */
	private RuleHeaderDto cardOnOff;

	/**
	 * Transaction type on off
	 */
	private RuleHeaderDto txnTypeOnOff;
	/**
	 * Transaction limit Single
	 */
	private RuleHeaderDto tnxLimitSingle;
	/**
	 * Transaction limit daily
	 */
	private RuleHeaderDto tnxLimitDaily;
	/**
	 * Transaction limit Weekly
	 */
	private RuleHeaderDto tnxLimitWeekly;
	/**
	 * List of control group
	 */
	private List<RuleHeaderDto> controlGroups;

	/**
	 * Validation messages
	 */
	private List<String> validationMessages = new ArrayList<String>(5);
	/**
	 * Card #
	 */
	private String cardNumber;
	private String customerId;

	@Override
	public void validate() throws CardControlException, Exception {
		if (null == getCardNumber()) {
			validationMessages.add("Card # can't be empty");
		}
		if(null == getCustomerId() || getCustomerId().isEmpty()){
			validationMessages.add("Customer Id Can't be empty");
		}

	}

	@Override
	public List<String> getValidationMessages() {
		return validationMessages;
	}

	public void setValidationMessages(List<String> validationMessage) {
		this.validationMessages = validationMessage;
	}

	public void addMessage(String message) {
		this.validationMessages.add(message);
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public RuleHeaderDto getCardOnOff() {
		return cardOnOff;
	}

	public void setCardOnOff(RuleHeaderDto cardOnOff) {
		this.cardOnOff = cardOnOff;
	}

	public RuleHeaderDto getTxnTypeOnOff() {
		return txnTypeOnOff;
	}

	public void setTxnTypeOnOff(RuleHeaderDto txnTypeOnOff) {
		this.txnTypeOnOff = txnTypeOnOff;
	}

	public RuleHeaderDto getTnxLimitSingle() {
		return tnxLimitSingle;
	}

	public void setTnxLimitSingle(RuleHeaderDto tnxLimitSingle) {
		this.tnxLimitSingle = tnxLimitSingle;
	}

	public RuleHeaderDto getTnxLimitDaily() {
		return tnxLimitDaily;
	}

	public void setTnxLimitDaily(RuleHeaderDto tnxLimitDaily) {
		this.tnxLimitDaily = tnxLimitDaily;
	}

	public RuleHeaderDto getTnxLimitWeekly() {
		return tnxLimitWeekly;
	}

	public void setTnxLimitWeekly(RuleHeaderDto tnxLimitWeekly) {
		this.tnxLimitWeekly = tnxLimitWeekly;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<RuleHeaderDto> getControlGroups() {
		return controlGroups;
	}

	public void setControlGroups(List<RuleHeaderDto> controlGroups) {
		this.controlGroups = controlGroups;
	}

	@Override
	public String toString() {
		return "RuleConfigurationRequestMsg [cardOnOff=" + cardOnOff + ", txnTypeOnOff=" + txnTypeOnOff
				+ ", tnxLimitSingle=" + tnxLimitSingle + ", tnxLimitDaily=" + tnxLimitDaily + ", tnxLimitWeekly="
				+ tnxLimitWeekly + ", controlGroups=" + controlGroups + ", validationMessages=" + validationMessages
				+ ", cardNumber=" + cardNumber + ", customerId=" + customerId + "]";
	}



}