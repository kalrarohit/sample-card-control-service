package com.cts.cardcontrol.bean.dto;

public class RuleDetailDto {
	/**
	 * Field id(Base25 field id)
	 */
	private String fieldId;
	/**
	 * Operator
	 */
	private String operator;
	/**
	 * Value
	 */
	private String value;
	/**
	 * Name of the control
	 */
	private String name;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "RuleDetailDto [fieldId=" + fieldId + ", operator=" + operator + ", value=" + value + "]";
	}
	
	

}
