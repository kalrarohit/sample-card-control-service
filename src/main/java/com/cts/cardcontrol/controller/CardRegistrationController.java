package com.cts.cardcontrol.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cardcontrol.TransactionType;
import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.service.IService;
import com.cts.cardcontrol.service.msg.CardInfoRequestMsg;
import com.cts.cardcontrol.service.msg.CardRegistrationRequestMsg;
import com.cts.cardcontrol.service.msg.CardRegistrationResponseMsg;

@RestController
public class CardRegistrationController {
	private static Logger LOGGER = LoggerFactory.getLogger(CardRegistrationController.class);
	
	
	@Autowired
	private IService<CardRegistrationResponseMsg, CardRegistrationRequestMsg > cardRegistrationService;
	
	@CrossOrigin
	@RequestMapping(value = "/srv/registration/register", method = RequestMethod.POST, consumes = "application/json")
	CardRegistrationResponseMsg registerCard(@RequestBody CardRegistrationRequestMsg payload) throws CardControlException,Exception {
		LOGGER.info("Payload in registerCard : {}",payload);
		return cardRegistrationService.processRequest(payload);
		
	}
	@CrossOrigin
	@RequestMapping(value = "/srv/registration/details", method = RequestMethod.POST, consumes = "application/json")
	CardRegistrationResponseMsg fetchDetails(@RequestBody CardInfoRequestMsg payload) throws CardControlException,Exception {
		LOGGER.info("Payload in fetchDetails : {}",payload);
		return cardRegistrationService.processRequest(payload);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/srv/registration/txntype", method = RequestMethod.GET)
	TransactionType[] getTxnType() throws CardControlException,Exception {		
		return TransactionType.values();
		
	}
	

}
