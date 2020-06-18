package com.cts.cardcontrol.rest.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.rest.client.BaseRestClient;

@Component
public class Base24AuthRestClient extends BaseRestClient {

	private static Logger LOGGER = LoggerFactory.getLogger(Base24AuthRestClient.class);

	@Autowired
	public Base24AuthRestClient(RestOperations restClient, @Value("${base24auth.service.url}") String baseUrl) {
		super(restClient, baseUrl);
	}

	@Override
	protected <U> void preExecute(U request) throws CardControlException {
		// No Op
	}

	@Override
	protected <T> T postExecute(T restResponse) throws CardControlException {
		return restResponse;
	}

}
