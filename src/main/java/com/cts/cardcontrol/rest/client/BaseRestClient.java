/**
 * 
 */
package com.cts.cardcontrol.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.cts.cardcontrol.exception.CardControlException;

/**
 * @author 445150
 *
 */
public abstract class BaseRestClient {

	private static Logger LOGGER = LoggerFactory.getLogger(BaseRestClient.class);

	/**
	 * Rest Client
	 */
	protected RestOperations restClient;

	/**
	 * The base URL of end point
	 */
	protected String url;

	public BaseRestClient(RestOperations restClient, String url) {
		this.restClient = restClient;
		this.url = url;
	}
	
	/**
	 * 
	 * @param relativeUrl
	 * @param request
	 * @param mediaType
	 * @param httpMethod
	 * @param responseType
	 * @return
	 * @throws RestClientException
	 * @throws CardControlException
	 */
	public <T,U> T execute(String relativeUrl, U request, MediaType mediaType, HttpMethod httpMethod,
			Class<T> responseType) throws RestClientException, CardControlException {

		preExecute(request);

		HttpHeaders requestHeaders = new HttpHeaders();
		if (null != mediaType) {
			requestHeaders.setContentType(mediaType);
		} else {
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		}
		String completeUrl = url;
		if (null != relativeUrl) {
			completeUrl = url + relativeUrl;
		}

		HttpEntity<U> entity = new HttpEntity<U>(request, requestHeaders);

		T restResponse= (T) restClient.exchange(completeUrl, HttpMethod.POST, entity, responseType).getBody();
		return postExecute(restResponse);
	}
	/**
	 * This is the hook to perform some operation on request before sending to the server
	 * @param request
	 * @throws CardControlException
	 */
	protected abstract <U> void preExecute(U request) throws CardControlException;
	/**
	 * This is the hook to perform operation on response received from server
	 * @param restResponse
	 * @return
	 * @throws CardControlException
	 */
	protected abstract <T> T postExecute(T restResponse) throws CardControlException;

}
