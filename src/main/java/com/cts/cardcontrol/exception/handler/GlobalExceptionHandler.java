/**
 * 
 */
package com.cts.cardcontrol.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.cts.cardcontrol.bean.ErrorResponse;
import com.cts.cardcontrol.exception.CardControlException;

/**
 * @author 445150
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ExceptionHandler(value = NoHandlerFoundException.class)  
    public ErrorResponse handleBaseException(NoHandlerFoundException e){ 
		LOGGER.info("Handler not define",e);
		ErrorResponse response = new ErrorResponse();
		response.addMessage("Handler not define");		
        return response;  
    } 
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = CardControlException.class)  
    public ErrorResponse handleBaseException(CardControlException e){ 
		LOGGER.info("Business Exception occurred",e);
		ErrorResponse response = new ErrorResponse();
		response.addMessage(e.getMessage());		
        return response;  
    } 


	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
	public ErrorResponse handleException(Exception e) {
		LOGGER.info("Exeception details :", e);
		ErrorResponse response = new ErrorResponse();
		response.addMessage("Something went wrong");		
        return response;
	}

}
