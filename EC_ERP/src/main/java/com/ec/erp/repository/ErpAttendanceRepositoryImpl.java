package com.ec.erp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.ec.erp.model.ErpAttendance;
import com.ec.erp.model.request.DateRangePojo;

public class ErpAttendanceRepositoryImpl implements ErpAttendanceRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    private static final String COMPANY_IN_TIME = "14:00:00";
    private static final String COMPANY_OUT_TIME = "23:00:00";
    
 // TODO Get Values in Map
    @Override
    @Transactional
    public List<ErpAttendance> filter(String queryStr) {	
    	Query query = em.createQuery(queryStr);
    	List<ErpAttendance> list = query.getResultList();
    	return list;
    }
    
    @Override
    @Transactional	// Check by commenting
    public List<Map<String, Object>> fetchLatecomerCount(DateRangePojo dateRangePojo) {

    	Query query = em.createNativeQuery("select employee_id,COUNT(employee_id) count from erp_attendance where " + 
    			"cast(punch_in_timestamp as time)>=? and cast(punch_in_timestamp as time)<=? " + 
    			"and punch_date>=? and punch_date<=? " + 
    			"group by employee_id having COUNT(employee_id)>0 order by COUNT(employee_id) desc ");
    	query.setParameter(1, COMPANY_IN_TIME);
    	query.setParameter(2, COMPANY_OUT_TIME);
    	query.setParameter(3, dateRangePojo.getFromDate());
    	query.setParameter(4, dateRangePojo.getToDate());

    	List<Map<String, Object>> responseList = new ArrayList<>();
    	for (Object[] obj : (List<Object[]>) query.getResultList()) {
			Map<String, Object> map = new HashMap<>();
			map.put("employee_id", String.valueOf(obj[0]));
			map.put("lateCount", Integer.parseInt(String.valueOf(obj[1])));
			responseList.add(map);
		}
    	return responseList;
    }
}
