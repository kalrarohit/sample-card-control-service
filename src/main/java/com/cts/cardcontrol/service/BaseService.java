package com.cts.cardcontrol.service;

import com.cts.cardcontrol.ResponseStatus;
import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.service.msg.IRequestMessage;
import com.cts.cardcontrol.service.msg.IResponseMessage;

/**
 * @author 445150
 *
 * @param <T>
 * @param <U>
 */
public abstract class BaseService<T extends IResponseMessage, U extends IRequestMessage> implements IService<T,U> {
	
	/**
	 * This method define the processing flow for all service calss
	 * Individual Service class need provide the logic in preProcess, process and postProcess 
	 * method.
	 */
	public final T processRequest(U requestMessage) throws CardControlException,Exception{
		preProcess(requestMessage);
		validateReqest(requestMessage);
		T response = null;
		if(requestMessage.getValidationMessages() == null || requestMessage.getValidationMessages().isEmpty()){
			response= process(requestMessage);
		}else{
			response = getResponseInstance();
			response.setMessages(requestMessage.getValidationMessages());
			response.setStatus(ResponseStatus.FAILURE);
		}
		
		return postProcess(response );		
		
	}
	public  void validateReqest(U requestMessage) throws CardControlException,Exception{
		requestMessage.validate();
	}
	
	/**
	 * Instantiate the response message
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	/*public T createReaponse() throws InstantiationException, IllegalAccessException{
		ParameterizedType genericSuperclass = (ParameterizedType) (getClass().getGenericSuperclass());
		Class<T> response = (Class<T>) genericSuperclass.getActualTypeArguments()[0].getClass();
		System.out.println("Arguments 0 "+genericSuperclass.getActualTypeArguments()[0].getTypeName());
		System.out.println("Arguments 0"+(Class<T>) genericSuperclass.getActualTypeArguments()[0].getClass());
		
		
		return response.newInstance();
	}*/
	
	/**
	 * This response instance will be use to retun the validation messages on request
	 * @return
	 */
	public abstract T getResponseInstance();
	

}
