package com.cts.cardcontrol.service.msg;

import java.util.List;

import com.cts.cardcontrol.bean.dto.CardRegistrationDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardInfoResponseMsg extends CardRegistrationResponseMsg {
	@JsonProperty("cards")
	private List<CardRegistrationDto> cards;

	public List<CardRegistrationDto> getCards() {
		return cards;
	}

	public void setCards(List<CardRegistrationDto> cards) {
		this.cards = cards;
	}
	

}
