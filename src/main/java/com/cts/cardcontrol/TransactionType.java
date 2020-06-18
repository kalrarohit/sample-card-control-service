package com.cts.cardcontrol;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {

	ECOMMERCE("Ecommerce", "3", "EQ", Arrays.asList("09", "81")), 
	CASH_WITHDRAWAL("Cash Withdrawal", "5", "EQ",Arrays.asList("01")), 
	GAMBLING("Gambling", "4", "EQ", Arrays.asList("7995", "9754")), 
	LIQUOR_STORE("Liquor Store", "4", "EQ",Arrays.asList("5921")), 
	MONEY_TRANSFER("Money Transfer", "4", "EQ", Arrays.asList("4829", "6051"));

	private String uiLabel;
	private String fieldId;
	private String operator;
	private List<String> mccCodes;

	// Reverse-lookup map for getting a TransactionType from a value
	private static final Map<String, TransactionType> lookup = new HashMap<String, TransactionType>();

	static {
		for (TransactionType transactionType : TransactionType.values()) {
			lookup.put(transactionType.getUiLabel(), transactionType);
		}
	}

	private TransactionType(String value, String fieldId, String operator, List<String> mccCode) {
		this.uiLabel = value;
		this.fieldId = fieldId;
		this.operator = operator;
		this.mccCodes = mccCode;
	}

	public String getUiLabel() {
		return uiLabel;
	}

	public String getFieldId() {
		return fieldId;
	}

	public List<String> getMccCodes() {
		return Collections.unmodifiableList(mccCodes);
	}

	public String getMccCodeString(){
		return String.join(",", getMccCodes());
	}
	public String getOperator() {
		return operator;
	}

	/*@Override
	@JsonValue
	public String toString() {
		return getUiLabel();
	}*/

	public static TransactionType get(String key) {
		return lookup.get(key);
	}
	
	public static TransactionType searchByMccCode(String mccCdoe) {
		for (Entry<String, TransactionType> entry : lookup.entrySet()) {
			if (entry.getValue().getMccCodes().contains(mccCdoe)) {
				return entry.getValue();
			}
		}
		return null;

	}
	
	
}
