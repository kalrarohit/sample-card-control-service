package com.cts.cardcontrol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.cardcontrol.ResponseStatus;
import com.cts.cardcontrol.RuleName;
import com.cts.cardcontrol.TransactionType;
import com.cts.cardcontrol.bean.ControlGroup;
import com.cts.cardcontrol.bean.dto.CardRegistrationDto;
import com.cts.cardcontrol.bean.dto.RuleDetailDto;
import com.cts.cardcontrol.bean.dto.RuleHeaderDto;
import com.cts.cardcontrol.bean.entity.CardRegistration;
import com.cts.cardcontrol.bean.entity.RuleHeader;
import com.cts.cardcontrol.dao.BaseDao;
import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.service.msg.RuleQueryRequestMsg;
import com.cts.cardcontrol.service.msg.RuleQueryResponseMsg;

@Transactional
@Service
public class RuleQueryService extends BaseService<RuleQueryResponseMsg, RuleQueryRequestMsg> {

	@Autowired
	private BaseDao<CardRegistration, String> cardregistrationDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void preProcess(RuleQueryRequestMsg requestMessage) throws CardControlException, Exception {

	}

	@Override
	public RuleQueryResponseMsg process(RuleQueryRequestMsg requestMessage) throws CardControlException, Exception {

		RuleQueryResponseMsg defaultResponse = new RuleQueryResponseMsg();
		if (requestMessage instanceof RuleQueryRequestMsg) {
			CardRegistration card = cardregistrationDao.findById(requestMessage.getCardNumber());
			Map<String, RuleHeaderDto> ruleHeaderDtos = new HashMap<>();
			if (card != null) {
				CardRegistrationDto cardDto = convert(card, CardRegistrationDto.class);
				defaultResponse.setCardNumber(card.getId());
				defaultResponse.setCard(cardDto);
				for (Entry<String, RuleHeader> entry : card.getRuleHeaders().entrySet()) {
					RuleHeaderDto ruleHeaderDto = convert(entry.getValue(), RuleHeaderDto.class);
					ControlGroup controlGroup = new ControlGroup();
					controlGroup.setGroupName(entry.getKey());
					RuleName ruleName = RuleName.get(entry.getKey());
					List<Map<String, String>>controlDetails = new ArrayList<>() ;
					for (RuleDetailDto ruleDetailDto : ruleHeaderDto.getRuleDetails()) {
						populateControlDetails(controlDetails, ruleName, ruleDetailDto);
					}
					controlGroup.setControlDetails(controlDetails);
					defaultResponse.addControlGroup(controlGroup);
				}

				// defaultResponse.setRuleInfo(ruleHeaderDtos);
				defaultResponse.setStatus(ResponseStatus.SUCCESS);
				defaultResponse.addMessage("Card detail fetched successfully");
			} else {
				defaultResponse.setStatus(ResponseStatus.SUCCESS);
				defaultResponse.addMessage("Card Not Present");
			}
		}

		return defaultResponse;
	}

	@Override
	public RuleQueryResponseMsg postProcess(RuleQueryResponseMsg requestMessage)
			throws CardControlException, Exception {
		return requestMessage;
	}

	@Override
	public RuleQueryResponseMsg getResponseInstance() {
		return new RuleQueryResponseMsg();
	}

	private <T> T convert(Object source, Class<T> clz) {
		return modelMapper.map(source, clz);

	}
	
	private void populateControlDetails(List< Map<String,String>> controlDetails, RuleName ruleName, RuleDetailDto ruleDetailDto) {
		
		switch (ruleName) {
		case Card_On_Off:{
			Map<String,String> controlDetail = new HashMap<>();
			controlDetail.put("value", ruleDetailDto.getValue());
			controlDetail.put("name", "");
			controlDetails.add(controlDetail);
			break;
		}
		case Txn_Type_On_Off:
		case Pem_On_Off:
		case Mcc_On_Off:{
			List<String> added = new ArrayList<>();
			for (String value :ruleDetailDto.getValue().split(",")){
				Map<String,String> controlDetail = new HashMap<>();
				String uilable = TransactionType.searchByMccCode(value).getUiLabel();
				controlDetail.put("name", uilable);
				controlDetail.put("value","");
				if(!added.contains(uilable)){
					controlDetails.add(controlDetail);
					added.add(uilable);
				}
				
			}
			break;
		}
		case Txn_Limit_Single:
		case Txn_Limit_Weekly:
		case Txn_Limit_Daily:{
			Map<String,String> controlDetail = new HashMap<>();
			controlDetail.put("value", ruleDetailDto.getValue());
			controlDetail.put("name", "");
			controlDetails.add(controlDetail);
			break;
		}

		default:
			break;
		}


	}

}
