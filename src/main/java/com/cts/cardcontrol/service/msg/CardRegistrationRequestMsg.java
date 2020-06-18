package com.cts.cardcontrol.service.msg;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardRegistrationRequestMsg implements IRequestMessage {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CardRegistrationRequestMsg.class);
	/**
	 * Card Number
	 */
	private String cardNumber;
	/**
	 * Exp date
	 */
	private String expiryDate;
	/**
	 * Request type i.e. Registration or De-Registration
	 */
	private String requestType;
	/**
	 * The Customer id
	 */
	private String customerId;
	/**
	 * The name on the card
	 */
	private String nameOnCard;
	/**
	 * Validation messages
	 */
	private List<String> validationMessage = new ArrayList<String>(5);

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expDate) {
		this.expiryDate = expDate;
	}

	public void validate() {
		if (null == getCardNumber() || getCardNumber().isEmpty()) {
			validationMessage.add("Card Number Can't be empty");
		}
		if (null == getExpiryDate() || getExpiryDate().isEmpty()) {
			validationMessage.add("Expiry date Can't be empty");
		}
		if(null == getRequestType() || getRequestType().isEmpty()){
			validationMessage.add("Request type Can't be empty");
		}
		if(null == getCustomerId() || getCustomerId().isEmpty()){
			validationMessage.add("Customer Id Can't be empty");
		}
		if(null == getNameOnCard() || getNameOnCard().isEmpty()){
			validationMessage.add("Name on Can't be empty");
		}
		
		/*
		//Expiry date validation
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CardControlConstant.CARD_EXPIRY_DATE_FORMAT);
		simpleDateFormat.setLenient(false);
		Date expiry;
		boolean expired = false;
		try {
			expiry = simpleDateFormat.parse(getExpiryDate());
			expired = expiry.before(new Date());
		} catch (ParseException e) {
			LOGGER.error("Error occurred during parsing Card Expiry Date", e);
			validationMessage.add("Invalid Card Expiry date");
		}
		if(expired){
			validationMessage.add("Card Already Expired");
		}*/
 

	}

	public List<String> getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(List<String> validationMessage) {
		this.validationMessage = validationMessage;
	}

	@Override
	public List<String> getValidationMessages() {
		return validationMessage;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	

}