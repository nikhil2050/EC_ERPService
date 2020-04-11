package com.ec.erp.repository;

import java.util.List;

import com.ec.erp.model.ErpLeaveApplication;
import com.ec.erp.model.request.Filter;
import com.ec.erp.model.request.FilterPayload;

public interface ErpLeaveApplicationRepositoryCustom {
	
	public List<ErpLeaveApplication> filter(String query);

}
