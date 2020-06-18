package com.cts.cardcontrol.service.msg;

import java.util.List;

import com.cts.cardcontrol.exception.CardControlException;

public interface IRequestMessage {
	
	/**
	 * This will perform basic validation of the request message and populate the validation message list.
	 * @throws CardControlException
	 * @throws Exception
	 */
	public void validate() throws CardControlException,Exception;
	/**
	 * Return the validation message list
	 * @return
	 */
	
	public List<String> getValidationMessages();

}
