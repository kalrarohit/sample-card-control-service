package com.cts.cardcontrol.service.msg;

import java.util.ArrayList;
import java.util.List;

public class CardInfoRequestMsg extends CardRegistrationRequestMsg {
	
	private List<String> validationMessage = new ArrayList<String>(5);
	
	public void validate() {

		if(null == getCustomerId() || getCustomerId().isEmpty()){
			validationMessage.add("Customer Id Can't be empty");
		}

	}
	
	public void setValidationMessage(List<String> validationMessage) {
		this.validationMessage = validationMessage;
	}

	@Override
	public List<String> getValidationMessages() {
		return validationMessage;
	}

}
