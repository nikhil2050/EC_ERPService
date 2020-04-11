package com.ec.erp.repository;

import java.util.List;
import java.util.Map;

import com.ec.erp.model.ErpAttendance;
import com.ec.erp.model.request.DateRangePojo;

public interface ErpAttendanceRepositoryCustom {

	public List<ErpAttendance> filter(String query);
	
	public List<Map<String, Object>> fetchLatecomerCount(DateRangePojo dateRangePojo);

}
