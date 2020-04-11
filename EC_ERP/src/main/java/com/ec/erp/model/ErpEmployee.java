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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import com.ec.erp.model.userroles.User;
import com.ec.erp.softdelete.SoftDeletableEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NonNull;

@Entity
@Table(name="ErpEmployee")
//@Audited
@Where(clause= SoftDeletableEntity.SOFT_DELETED_CLAUSE)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt","hibernateLazyInitializer", "handler" }, allowGetters = true)
public class ErpEmployee extends SoftDeletableEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employeeId", updatable=false, nullable=false)
	private Long employeeId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdDate;

	@NonNull
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
//	@JoinColumn(name="userId",nullable=false)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@NotFound(action=NotFoundAction.IGNORE)
	private User user;
	
	private String employeeName;
	private String mobileNo;
	private String emergencyContactNo;
	private String address;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	
	private String department;

	@JsonGetter(value="employeeId")
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@JsonGetter(value="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@JsonGetter(value="user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonGetter(value="employeeName")
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@JsonGetter(value="mobileNo")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@JsonGetter(value="emergencyContactNo")
	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}

	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}

	@JsonGetter(value="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonGetter(value="birthDate")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@JsonGetter(value="joiningDate")
	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@JsonGetter(value="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "ErpEmployee [employeeId=" + employeeId + ", createdDate=" + createdDate + ", user=" + user
				+ ", employeeName=" + employeeName + ", mobileNo=" + mobileNo + ", emergencyContactNo="
				+ emergencyContactNo + ", address=" + address + ", birthDate=" + birthDate + ", joiningDate="
				+ joiningDate + ", department=" + department + "]";
	}

	
}
