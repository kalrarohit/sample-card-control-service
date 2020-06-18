package com.cts.cardcontrol;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RuleName {
	Card_On_Off("Card-On-Off", "10"), 
	Txn_Type_On_Off("TXN-type-On-Off", "20"),
	Pem_On_Off("PEM-On-Off", "40"),
	Mcc_On_Off("MCC-On-Off", "30"),
	Txn_Limit_Single("TXN-Limit-Single", "50"), 
	Txn_Limit_Daily("TXN-Limit-Daily", "60"), 
	Txn_Limit_Weekly("TXN-Limit-Weekly", "70");

	private String key;
	private String value;

	private RuleName(String key, String value) {
		this.key = key;
		this.value = value;
	}

	// Reverse-lookup map for getting a RuleName from a key
	private static final Map<String, RuleName> lookup = new HashMap<String, RuleName>();

	static {
		for (RuleName ruleName : RuleName.values()) {
			lookup.put(ruleName.getKey(), ruleName);
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return getKey();
	}

	public static RuleName get(String key) {
		return lookup.get(key);
	}
}
