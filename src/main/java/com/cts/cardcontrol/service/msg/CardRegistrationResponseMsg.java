package com.cts.cardcontrol.service.msg;

import java.util.ArrayList;
import java.util.List;

import com.cts.cardcontrol.ResponseStatus;

public class CardRegistrationResponseMsg implements IResponseMessage {

	private List<String> messages = new ArrayList<String>();

	private ResponseStatus status;

	@Override
	public List<String> getMessages() {
		return this.messages;
	}

	@Override
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public ResponseStatus getStatus() {
		return this.status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	/**
	 * Add the response message to the list
	 * @param message
	 */
	public void addMessage(String message){
		messages.add(message);
	}

	

}