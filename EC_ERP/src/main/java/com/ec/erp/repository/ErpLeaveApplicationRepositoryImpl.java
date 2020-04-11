package com.ec.erp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.ec.erp.model.ErpLeaveApplication;

public class ErpLeaveApplicationRepositoryImpl implements ErpLeaveApplicationRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

 // TODO Get Values in Map
    @Override
    @Transactional
    public List<ErpLeaveApplication> filter(String queryStr) {	
    	Query query = em.createQuery(queryStr);
    	List<ErpLeaveApplication> list = query.getResultList();
    	return list;
    }
    
}
