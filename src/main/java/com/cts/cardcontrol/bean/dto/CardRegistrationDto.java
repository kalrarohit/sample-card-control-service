package com.cts.cardcontrol.bean.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardRegistrationDto {
	@JsonProperty("cardNumber")
	private String id;
	/**
	 * Customer Id
	 */
	private String customerId;
	/**
	 * The name present on the card
	 */
	private String nameOnCard;
	/**
	 * Registration status of the card
	 */
	private String status;
	/**
	 * Expiry date of the card
	 */
	private String expiryDate;
	/**
	 * Card Limit
	 */
	private Long cardLimit;
	/**
	 * Cash withdrawal limit
	 */
	private Long cashLimit;
	/**
	 * POS Purchase Limit
	 */
	private Long pospurchaseLimit;
	/**
	 * Enrollment date
	 */
	private Date enrolledDate = new Date();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Long getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(Long cardLimit) {
		this.cardLimit = cardLimit;
	}
	public Long getCashLimit() {
		return cashLimit;
	}
	public void setCashLimit(Long cashLimit) {
		this.cashLimit = cashLimit;
	}
	public Long getPospurchaseLimit() {
		return pospurchaseLimit;
	}
	public void setPospurchaseLimit(Long pospurchaseLimit) {
		this.pospurchaseLimit = pospurchaseLimit;
	}
	public Date getEnrolledDate() {
		return enrolledDate;
	}
	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}
	
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	@Override
	public String toString() {
		return "CardRegistrationDto [id=" + id + ", customerId=" + customerId + ", status=" + status + ", expiryDate="
				+ expiryDate + ", cardLimit=" + cardLimit + ", cashLimit=" + cashLimit + ", pospurchaseLimit="
				+ pospurchaseLimit + ", enrolledDate=" + enrolledDate + "]";
	}
	
	
}
