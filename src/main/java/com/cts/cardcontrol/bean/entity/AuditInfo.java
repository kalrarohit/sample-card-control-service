package com.cts.cardcontrol.bean.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
/// @Embeddable
public abstract class AuditInfo {
	/**
	 * Creation Date
	 */
	@JsonIgnore
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	/**
	 * Update date
	 */
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	/**
	 * Created by
	 */
	@JsonIgnore
	@Column(updatable = false)
	private String createdBy;
	/**
	 * Updated by
	 */
	@JsonIgnore
	private String updatedBy;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@PrePersist
	void onCreate() {
		this.setCreationDate(new Date());
	}

	@PreUpdate
	void onUpdate() {
		this.setUpdateDate(new Date());
	}

}
