package com.cts.cardcontrol.service.msg;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.cardcontrol.exception.CardControlException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthRequestMessage implements IRequestMessage {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AuthRequestMessage.class);
	
	private String userId;
	private String password;
	private List<String> messages = new ArrayList<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = "";
		try {
			jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error Occurred duering stringfy",e);
		}
		return jsonStr;
	}

	@Override
	public void validate() throws CardControlException, Exception {
		
		
	}

	@Override
	public List<String> getValidationMessages() {
		return messages;
	}

}
