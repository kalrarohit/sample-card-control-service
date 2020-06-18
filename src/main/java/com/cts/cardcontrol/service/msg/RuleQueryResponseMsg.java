package com.cts.cardcontrol.service.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cts.cardcontrol.ResponseStatus;
import com.cts.cardcontrol.bean.ControlGroup;
import com.cts.cardcontrol.bean.dto.CardRegistrationDto;
import com.cts.cardcontrol.bean.dto.RuleHeaderDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RuleQueryResponseMsg implements IResponseMessage {
	
	private List<String> messages = new ArrayList<String>();
	private String cardNumber;
	private CardRegistrationDto card;
	private Map<String,RuleHeaderDto> ruleInfo;
	private List<ControlGroup> controlGroups = new ArrayList<>();

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Map<String, RuleHeaderDto> getRuleInfo() {
		return ruleInfo;
	}

	public void setRuleInfo(Map<String, RuleHeaderDto> ruleInfo) {
		this.ruleInfo = ruleInfo;
	}

	private ResponseStatus status;

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
		getMessages().add(message);
	}
	
	public List<ControlGroup> getControlGroups() {
		return controlGroups;
	}

	public void setControlGroups(List<ControlGroup> controlGroups) {
		this.controlGroups = controlGroups;
	}
	
	public void addControlGroup(ControlGroup controlGroup){
		controlGroups.add(controlGroup);
	}

	public CardRegistrationDto getCard() {
		return card;
	}

	public void setCard(CardRegistrationDto card) {
		this.card = card;
	}

	
}
