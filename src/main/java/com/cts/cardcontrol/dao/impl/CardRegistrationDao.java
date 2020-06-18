package com.cts.cardcontrol.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cts.cardcontrol.bean.entity.CardRegistration;
import com.cts.cardcontrol.dao.BaseDao;

@Repository
public class CardRegistrationDao extends BaseDao<CardRegistration, String> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CardRegistrationDao.class);
	
	private static String FIND_BY_CUST_ID ="Select cr from CardRegistration cr where cr.customerId =:customerId";
		
	public List<CardRegistration> findByCustomerId(String custId) {
		return createQuery(FIND_BY_CUST_ID).setParameter("customerId", custId).getResultList();
		
	}


}
