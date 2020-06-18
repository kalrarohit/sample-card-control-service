package com.cts.cardcontrol.service.msg;

import java.util.List;

import com.cts.cardcontrol.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface IResponseMessage {
	@JsonProperty("status")
	public ResponseStatus getStatus();
	@JsonProperty("messages")
	public List<String> getMessages();
	public void setMessages(List<String> messages);
	public void setStatus(ResponseStatus status);

}
