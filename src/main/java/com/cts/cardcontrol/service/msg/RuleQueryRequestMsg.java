package com.cts.cardcontrol.service.msg;

import java.util.ArrayList;
import java.util.List;

import com.cts.cardcontrol.exception.CardControlException;

public class RuleQueryRequestMsg implements IRequestMessage{
	private String cardNumber;
	private String customerId;
	
	/**
	 * Validation messages
	 */
	private List<String> validationMessages = new ArrayList<String>(5);
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setValidationMessages(List<String> validationMessages) {
		this.validationMessages = validationMessages;
	}

	@Override
	public void validate() throws CardControlException, Exception {
		if (null == getCardNumber()) {
			validationMessages.add("Card # can't be empty");
		}
		if(null == getCustomerId()){
			validationMessages.add("Customer Id can't be empty");
		}

	}

	@Override
	public List<String> getValidationMessages() {		
		return validationMessages;
	}

}
