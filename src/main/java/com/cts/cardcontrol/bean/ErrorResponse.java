package com.cts.cardcontrol.bean;

import java.util.ArrayList;
import java.util.List;

import com.cts.cardcontrol.ResponseStatus;
import com.cts.cardcontrol.service.msg.IResponseMessage;

public class ErrorResponse implements IResponseMessage {
	/**
	 * Error message 
	 */
	private List<String> messages = new ArrayList<>();
	/**
	 * Error status
	 */
	private ResponseStatus status = ResponseStatus.FAILURE;


	@Override
	public ResponseStatus getStatus() {
		return status;
	}

	@Override
	public List<String> getMessages() {
		return messages;
	}

	@Override
	public void setMessages(List<String> messages) {
		this.messages = messages;
		
	}

	@Override
	public void setStatus(ResponseStatus status) {
		this.status = status;
		
	}
	
	public void addMessage(String message){
		messages.add(message);
		
	}
	

}
