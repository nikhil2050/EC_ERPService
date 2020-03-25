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
@Table(name = "Category")
@Audited
@Where(clause = SoftDeletableEntity.SOFT_DELETED_CLAUSE)
public class Category extends SoftDeletableEntity
{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long categoryId;
	
	@NonNull
	@Column(unique=true,name="category_name")
	String categoryName;
	
	String categoryDescription;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
