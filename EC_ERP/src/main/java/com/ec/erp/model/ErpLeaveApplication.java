package com.ec.erp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import com.ec.erp.softdelete.SoftDeletableEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NonNull;

@Entity
@Table(name = "ErpLeaveApplication")
//@Audited
@Where(clause = SoftDeletableEntity.SOFT_DELETED_CLAUSE)
public class ErpLeaveApplication extends SoftDeletableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveApplicationId;

	@Column
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@NonNull
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="employeeId",nullable=false)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@NotFound(action=NotFoundAction.IGNORE)
	private ErpEmployee erpEmployee;

	@Column(name = "leaveAccrued")
	private Integer leaveAccrued;

	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	private Date toDate;

	@Column(length = 128)
	private String reason;

	private Integer leaveBalance;

	private String leaveStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveApproveTimestamp;

	@NonNull
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="leaveApproverEmployeeId",nullable=true)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@NotFound(action=NotFoundAction.IGNORE)
	private ErpEmployee leaveApproverEmployee;
	
	@JsonGetter(value="leaveApplicationId")
	public Long getLeaveApplicationId() {
		return leaveApplicationId;
	}

	public void setLeaveApplicationId(Long leaveApplicationId) {
		this.leaveApplicationId = leaveApplicationId;
	}

	@JsonGetter(value="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@JsonGetter(value="employerId")
	public ErpEmployee getErpEmployee() {
		return erpEmployee;
	}

	public void setErpEmployee(ErpEmployee erpEmployee) {
		this.erpEmployee = erpEmployee;
	}

	@JsonGetter(value="leaveAccrued")
	public Integer getLeaveAccrued() {
		return leaveAccrued;
	}

	public void setLeaveAccrued(Integer leaveAccrued) {
		this.leaveAccrued = leaveAccrued;
	}

	@JsonGetter(value="fromDate")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@JsonGetter(value="toDate")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@JsonGetter(value="reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@JsonGetter(value="leaveBalance")
	public Integer getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(Integer leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	@JsonGetter(value="leaveStatus")
	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	@JsonGetter(value="leaveApprovedTimestamp")
	public Date getLeaveApproveTimestamp() {
		return leaveApproveTimestamp;
	}

	public void setLeaveApproveTimestamp(Date leaveApproveTimestamp) {
		this.leaveApproveTimestamp = leaveApproveTimestamp;
	}

	@JsonGetter(value="leaveApproverEmployee")
	public ErpEmployee getLeaveApproverEmployee() {
		return leaveApproverEmployee;
	}

	public void setLeaveApproverEmployee(ErpEmployee leaveApproverEmployee) {
		this.leaveApproverEmployee = leaveApproverEmployee;
	}


}
