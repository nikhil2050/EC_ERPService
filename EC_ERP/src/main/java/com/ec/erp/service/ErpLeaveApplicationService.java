package com.ec.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.erp.exception.ErpEmployeeNotFoundException;
import com.ec.erp.model.ErpLeaveApplication;
import com.ec.erp.model.request.Filter;
import com.ec.erp.model.request.FilterPayload;
import com.ec.erp.repository.ErpLeaveApplicationRepository;

@Service
public class ErpLeaveApplicationService {

	@Autowired
	private ErpLeaveApplicationRepository erpLeaveApplicationRepository;
	
	public List<ErpLeaveApplication> filter(FilterPayload filterPayload) {
		StringBuilder queryStr = new StringBuilder("SELECT la FROM ErpLeaveApplication la WHERE "); // TODO
		int idx = 0;
		for(Filter filter : filterPayload.getFilters()) {
			queryStr.append(idx==0?"":" and ").append("la.").append(filter.getColumn()).append(" ");
			queryStr.append(filter.getOperator()).append(" '");
			queryStr.append(filter.getValue()).append("'");
			idx++;
		}
		List<ErpLeaveApplication> la = erpLeaveApplicationRepository.filter(queryStr.toString());
		return la;
	}

}
