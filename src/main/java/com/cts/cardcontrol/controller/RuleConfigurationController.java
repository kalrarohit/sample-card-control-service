package com.cts.cardcontrol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.service.IService;
import com.cts.cardcontrol.service.msg.RuleConfigurationRequestMsg;
import com.cts.cardcontrol.service.msg.RuleConfigurationResponseMsg;
import com.cts.cardcontrol.service.msg.RuleQueryRequestMsg;
import com.cts.cardcontrol.service.msg.RuleQueryResponseMsg;

@RestController
public class RuleConfigurationController {

	private static Logger LOGGER = LoggerFactory.getLogger(RuleConfigurationController.class);

	@Autowired
	private IService<RuleConfigurationResponseMsg, RuleConfigurationRequestMsg> ruleConfigurationService;
	@Autowired
	private IService<RuleQueryResponseMsg, RuleQueryRequestMsg> ruleQueryService;

	@CrossOrigin
	@RequestMapping(value = "/srv/rule/configure", method = RequestMethod.POST, consumes = "application/json")
	RuleConfigurationResponseMsg configureRule(@RequestBody RuleConfigurationRequestMsg payload) throws CardControlException, Exception {
		LOGGER.info("Payload in configureRule : {}", payload);
		return ruleConfigurationService.processRequest(payload);

	}
	
	@CrossOrigin
	@RequestMapping(value = "/srv/rule/detail", method = RequestMethod.POST, consumes = "application/json")
	RuleQueryResponseMsg getRule(@RequestBody RuleQueryRequestMsg payload) throws CardControlException, Exception {
		LOGGER.info("Payload in getRule : {}", payload);
		return ruleQueryService.processRequest(payload);

	}

}
