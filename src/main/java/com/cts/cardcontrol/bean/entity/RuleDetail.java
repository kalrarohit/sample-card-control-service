package com.cts.cardcontrol.bean.entity;

import javax.persistence.Embeddable;

@Embeddable
public class RuleDetail {

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

	@Override
	public String toString() {
		return "RuleDetail [fieldId=" + fieldId + ", operator=" + operator + ", value=" + value + "]";
	}

}
