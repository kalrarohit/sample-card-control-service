package com.cts.cardcontrol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.cardcontrol.CardControlConstant;
import com.cts.cardcontrol.ResponseStatus;
import com.cts.cardcontrol.RuleLevel;
import com.cts.cardcontrol.RuleName;
import com.cts.cardcontrol.TransactionType;
import com.cts.cardcontrol.bean.dto.RuleDetailDto;
import com.cts.cardcontrol.bean.dto.RuleHeaderDto;
import com.cts.cardcontrol.bean.entity.CardRegistration;
import com.cts.cardcontrol.bean.entity.RuleDetail;
import com.cts.cardcontrol.bean.entity.RuleHeader;
import com.cts.cardcontrol.dao.BaseDao;
import com.cts.cardcontrol.exception.CardControlException;
import com.cts.cardcontrol.service.msg.RuleConfigurationRequestMsg;
import com.cts.cardcontrol.service.msg.RuleConfigurationResponseMsg;

@Transactional
@Service
public class RuleConfigurationService extends BaseService<RuleConfigurationResponseMsg, RuleConfigurationRequestMsg> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RuleConfigurationService.class);

	@Autowired
	private BaseDao<CardRegistration, String> cardregistrationDao;
	
	@Autowired
	private BaseDao<RuleHeader, String> rulesHeadersDao;

	public void preProcess(RuleConfigurationRequestMsg requestMessage) throws CardControlException, Exception {

	}

	public RuleConfigurationResponseMsg process(RuleConfigurationRequestMsg requestMessage) throws CardControlException, Exception {
		RuleConfigurationResponseMsg ruleConfResponse = new RuleConfigurationResponseMsg();
		CardRegistration card = cardregistrationDao.findById(requestMessage.getCardNumber());
		
		if (null != card && card.getStatus().equals(CardControlConstant.DB_STATUS_OPEN) && card.getCustomerId().equals(requestMessage.getCustomerId())){
			// Removing existing records
			card.getRuleHeaders().clear();
			card.setUpdatedBy(requestMessage.getCustomerId());
			List<RuleHeaderDto> controlGroups = requestMessage.getControlGroups();
			for(RuleHeaderDto controlGroup : controlGroups){
				RuleHeader ruleHeader = prepareRuleHeader(controlGroup, requestMessage.getCustomerId());
				card.addRuleHeader(ruleHeader);
			}
			// cardregistrationDao.merge(card);

			cardregistrationDao.merge(card);
			ruleConfResponse.addMessage("Rule Configured Successfully");
			ruleConfResponse.setStatus(ResponseStatus.SUCCESS);

		}else{
			ruleConfResponse.setStatus(ResponseStatus.FAILURE);
			ruleConfResponse.addMessage("Card Not registered yet or not belongs to the customer");
		}		
		
		return ruleConfResponse;
	}

	public RuleConfigurationResponseMsg postProcess(RuleConfigurationResponseMsg responseMessage)
			throws CardControlException, Exception {
		return responseMessage;
	}

	@Override
	public RuleConfigurationResponseMsg getResponseInstance() {
		return new RuleConfigurationResponseMsg();
	}
	
	
	private RuleHeader prepareRuleHeader(RuleHeaderDto ruleHeaderDto, String customerId) throws CardControlException{
		RuleName ruleName = RuleName.get(ruleHeaderDto.getGroupName());
		RuleHeader ruleHeader = new RuleHeader();
		if(null != ruleName){
			if(ruleHeaderDto.getControlDetails() != null && ruleHeaderDto.getControlDetails().size() != 0){
				ruleHeader.setRuleDescription(ruleName.getKey());
				ruleHeader.setRuleGroupID(ruleName.getValue());
				ruleHeader.setRuleLevel(RuleLevel.Card.toString());
				ruleHeader.setDecisionCode("05");
				ruleHeader.setActiveFlag("A");
				ruleHeader.setCreatedBy(customerId);
				// Preparing rule details with comma separated value for same fieldId  
				Map<String, RuleDetail> ruleDetailMap = new HashMap<>();
				for(RuleDetailDto ruleDetailDto : ruleHeaderDto.getControlDetails()){
					ControlValue controlValue = getControlValue(ruleName, ruleDetailDto);
					String fieldId = controlValue.getFieldId();
					RuleDetail ruleDetail = ruleDetailMap.get(fieldId);
					if(ruleDetail == null){
						ruleDetail = new RuleDetail();
						ruleDetail.setFieldId(fieldId);
						ruleDetail.setValue(controlValue.getValue());
					}else{
						ruleDetail.setValue(ruleDetail.getValue()+","+controlValue.getValue());
					}					
					ruleDetail.setOperator(controlValue.getOperator());
					ruleDetailMap.put(fieldId, ruleDetail);
					
				}
				for(Entry<String, RuleDetail> entry :ruleDetailMap.entrySet()){
					System.out.println("Rule Details "+entry.getKey());
					System.out.println("Rule Details "+entry.getValue());
					ruleHeader.addRuleDetail(entry.getValue());
					
				}
			}else{
				throw new CardControlException("Rule Details not present");
			}
			
		}else{
			throw new CardControlException("Invalid Rule Name/Description");
		}
		
		
		return ruleHeader;
	}
	
	private ControlValue getControlValue(RuleName ruleName, RuleDetailDto ruleDetailDto) {
		ControlValue controlValue = new ControlValue();

		switch (ruleName) {
		case Card_On_Off:{
			controlValue.setFieldId("1");
			controlValue.setOperator("EQ");
			controlValue.setValue(ruleDetailDto.getValue());
			break;
		}
		case Txn_Type_On_Off:
		case Pem_On_Off:
		case Mcc_On_Off:{
			System.out.println("Rule Details NAME :"+ruleDetailDto.getName());
			TransactionType transactionType = TransactionType.get(ruleDetailDto.getName());
			controlValue.setFieldId(transactionType.getFieldId());
			controlValue.setOperator(transactionType.getOperator());
			controlValue.setValue(transactionType.getMccCodeString());
			break;
		}
		case Txn_Limit_Single:
		case Txn_Limit_Weekly:
		case Txn_Limit_Daily:{
			controlValue.setFieldId("2");
			controlValue.setOperator("GT");
			controlValue.setValue(ruleDetailDto.getValue());
			break;
		}
		default:
			break;
		}

		return controlValue;
	}
	private class ControlValue {
		/**
		 * Field Id
		 */
		private String fieldId;
		/**
		 * Operator for this field
		 */
		private String operator;
		/**
		 * Value of this filed
		 */
		private String value;

		public String getFieldId() {
			return fieldId;
		}

		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public ControlValue(String fieldId, String operator, String value) {
			super();
			this.fieldId = fieldId;
			this.operator = operator;
			this.value = value;
		}
		public ControlValue(){
			super();
		}
	}

}