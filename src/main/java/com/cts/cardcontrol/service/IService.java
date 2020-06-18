package com.cts.cardcontrol.service;

import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.service.msg.IRequestMessage;
import com.cts.cardcontrol.service.msg.IResponseMessage;

/**
 * @author 445150
 *
 * @param <T>
 * @param <U>
 */
public interface IService<T extends IResponseMessage, U extends IRequestMessage> {


	T processRequest(U requestMessage) throws CardControlException,Exception;

	void preProcess(U requestMessage) throws CardControlException,Exception;
	
	void validateReqest(U requestMessage) throws CardControlException,Exception;

	T process(U requestMessage) throws CardControlException,Exception;

	T postProcess(T requestMessage) throws CardControlException,Exception;
}
