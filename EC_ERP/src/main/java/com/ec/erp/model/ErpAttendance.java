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
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import com.ec.erp.softdelete.SoftDeletableEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NonNull;

@Entity
@Table(name = "ErpAttendance")
//@Audited
@JsonIgnoreProperties(value = { "createdAt", "updatedAt","hibernateLazyInitializer", "handler" }, allowGetters = true)
@Where(clause = SoftDeletableEntity.SOFT_DELETED_CLAUSE)
public class ErpAttendance extends SoftDeletableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;

	@Column(nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@NonNull
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="employeeId",nullable=false)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@NotFound(action=NotFoundAction.IGNORE)
	private ErpEmployee erpEmployee;

	@Temporal(TemporalType.DATE)
	private Date punchDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/India")
	private Date punchInTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/India")
	private Date punchOutTimestamp;

	@Column(nullable = false)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ErpEmployee getErpEmployee() {
		return erpEmployee;
	}

	public void setErpEmployee(ErpEmployee erpEmployee) {
		this.erpEmployee = erpEmployee;
	}

	public Date getPunchDate() {
		return punchDate;
	}

	public void setPunchDate(Date punchDate) {
		this.punchDate = punchDate;
	}

	public Date getPunchInTimestamp() {
		return punchInTimestamp;
	}

	public void setPunchInTimestamp(Date punchInTimestamp) {
		this.punchInTimestamp = punchInTimestamp;
	}

	public Date getPunchOutTimestamp() {
		return punchOutTimestamp;
	}

	public void setPunchOutTimestamp(Date punchOutTimestamp) {
		this.punchOutTimestamp = punchOutTimestamp;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}	

}
