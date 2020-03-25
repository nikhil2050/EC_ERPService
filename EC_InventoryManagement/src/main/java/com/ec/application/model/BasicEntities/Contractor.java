package com.ec.application.model.BasicEntities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.lang.NonNull;

import com.ec.application.SoftDelete.SoftDeletableEntity;

@Entity
@Table(name = "Contractor")
@Audited
@Where(clause = SoftDeletableEntity.SOFT_DELETED_CLAUSE)
public class Contractor extends SoftDeletableEntity
{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long contractorId;
	
	@NonNull
	@Column(unique=true,name="contractor_name")
	String contractorName;
	
	String contractorDescription;
	
	public Long getContractorId() {
		return contractorId;
	}
	public void setContractorId(Long contractorId) {
		this.contractorId = contractorId;
	}
	
	
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public String getContractorDescription() {
		return contractorDescription;
	}
	public void setContractorDescription(String contractorDescription) {
		this.contractorDescription = contractorDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
