package com.cts.cardcontrol.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.cardcontrol.CardControlConstant;
import com.cts.cardcontrol.RegistrationStatus;
import com.cts.cardcontrol.ResponseStatus;
import com.cts.cardcontrol.bean.dto.CardRegistrationDto;
import com.cts.cardcontrol.bean.entity.CardRegistration;
import com.cts.cardcontrol.dao.BaseDao;
import com.cts.cardcontrol.dao.impl.CardRegistrationDao;
import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.service.msg.CardInfoRequestMsg;
import com.cts.cardcontrol.service.msg.CardInfoResponseMsg;
import com.cts.cardcontrol.service.msg.CardRegistrationRequestMsg;
import com.cts.cardcontrol.service.msg.CardRegistrationResponseMsg;

@Transactional
@Service
public class CardRegistrationService extends BaseService<CardRegistrationResponseMsg, CardRegistrationRequestMsg> {

	
	@Autowired
	private BaseDao<CardRegistration, String> cardregistrationDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public void preProcess(CardRegistrationRequestMsg requestMessage) throws CardControlException, Exception {
		// requestMessage.validateRequest();

	}

	public CardRegistrationResponseMsg process(CardRegistrationRequestMsg requestMessage)
			throws CardControlException, Exception {

		CardRegistrationResponseMsg defaultResponse = new CardRegistrationResponseMsg();
		// If request for CardInfo
		if (requestMessage instanceof CardInfoRequestMsg) {
			CardInfoResponseMsg cardInfo = new CardInfoResponseMsg();
			List<CardRegistration> cards = ((CardRegistrationDao) cardregistrationDao).findByCustomerId(requestMessage.getCustomerId());
			List<CardRegistrationDto> cardsDto = new ArrayList<>();
			for(CardRegistration card : cards){
				CardRegistrationDto dto = convert(card, CardRegistrationDto.class);
				cardsDto.add(dto);
				System.out.println("Card Registration DTO :"+dto);
			}
			
			
			cardInfo.setCards(cardsDto);
			cardInfo.addMessage("Card Info retrieved successfully");
			cardInfo.setStatus(ResponseStatus.SUCCESS);
			defaultResponse = cardInfo;
		// If request for card registration
		} else if (requestMessage instanceof CardRegistrationRequestMsg) {
			if (isValidCard(requestMessage)) {
				CardRegistration entity = getPopulatedEntity(requestMessage);
				cardregistrationDao.merge(entity);
				defaultResponse.addMessage("Registration/De-Registration completed");
				defaultResponse.setStatus(ResponseStatus.SUCCESS);
			} else {
				defaultResponse.addMessage("Invalid Card");
				defaultResponse.setStatus(ResponseStatus.FAILURE);
			}
			// If request for card details
		}
		

		return defaultResponse;
	}

	public CardRegistrationResponseMsg postProcess(CardRegistrationResponseMsg responseMessage)
			throws CardControlException, Exception {
		return responseMessage;
	}

	@Override
	public CardRegistrationResponseMsg getResponseInstance() {
		return new CardRegistrationResponseMsg();
	}

	/**
	 * Check the validity of the requested card 
	 * This should call the BASE24 API but now 
	 * only checking with in internal data base for basic validation
	 * @param requestMsg
	 * @return
	 */
	private boolean isValidCard(CardRegistrationRequestMsg requestMsg) {
		RegistrationStatus requestType = RegistrationStatus.valueOf(RegistrationStatus.class, requestMsg.getRequestType());
		CardRegistration card = cardregistrationDao.findById(requestMsg.getCardNumber());
		if(requestType.equals(RegistrationStatus.register)){
			if(card != null && CardControlConstant.DB_STATUS_OPEN.equals(card.getStatus()) && !requestMsg.getCustomerId().equals(card.getCustomerId())){
				return false;
			}
		}else{
			if(card == null){
				return false;
			} else if(CardControlConstant.DB_STATUS_CLOSE.equals(card.getStatus()) ||  !requestMsg.getCustomerId().equals(card.getCustomerId())){
				return false;
			}
		}
		
		return true;

	}

	private CardRegistration getPopulatedEntity(CardRegistrationRequestMsg registrationRequestMsg) {
		CardRegistration entity = new CardRegistration();
		entity.setId(registrationRequestMsg.getCardNumber());
		entity.setExpiryDate(registrationRequestMsg.getExpiryDate());
		entity.setCustomerId(registrationRequestMsg.getCustomerId());
		entity.setCreatedBy(registrationRequestMsg.getCustomerId());
		entity.setNameOnCard(registrationRequestMsg.getNameOnCard());
		// Populating with dummy values
		entity.setCardLimit(10000l);
		entity.setCashLimit(3000l);
		entity.setPospurchaseLimit(7000l);
		entity.setStatus(RegistrationStatus.valueOf(RegistrationStatus.class, registrationRequestMsg.getRequestType())
				.getValue());
		return entity;

	}
	
	private <T> T convert(Object source, Class<T> clz){
		return modelMapper.map(source, clz);
		
	}

}