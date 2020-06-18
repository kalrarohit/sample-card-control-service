package com.cts.cardcontrol.rest.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.rest.client.BaseRestClient;

@Component
public class  CardRegistrationRestClient extends BaseRestClient  {

@Autowired public  CardRegistrationRestClient(RestOperations restClient, @Value("${base24auth.service.url}") String baseUrl){
super(restClient, baseUrl);
}


protected <U> void preExecute(U request) throws CardControlException {

}


protected <T> T postExecute(T restResponse) throws CardControlException {
return restResponse;
}


}