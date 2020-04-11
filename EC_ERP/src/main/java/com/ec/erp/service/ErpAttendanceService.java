package com.ec.erp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.erp.exception.ErpEmployeeNotFoundException;
import com.ec.erp.exception.ResourceNotFoundException;
import com.ec.erp.model.ErpAttendance;
import com.ec.erp.model.ErpLeaveApplication;
import com.ec.erp.model.request.DateRangePojo;
import com.ec.erp.model.request.Filter;
import com.ec.erp.model.request.FilterPayload;
import com.ec.erp.repository.ErpAttendanceRepository;
import com.ec.erp.repository.ErpLeaveApplicationRepository;

@Service
public class ErpAttendanceService {

	@Autowired
	private ErpAttendanceRepository erpAttendanceRepository;
	
	public ErpAttendance updateAttendance(Long attendanceId, ErpAttendance erpAttendance) {
		ErpAttendance erpAttendanceToUpdate = erpAttendanceRepository.findById(attendanceId)
				.orElseThrow(() -> new ResourceNotFoundException("ErpAttendance", "attendanceId", attendanceId));

		if(null!=erpAttendance.getErpEmployee())
			erpAttendanceToUpdate.setErpEmployee(erpAttendance.getErpEmployee());
		if(null!=erpAttendance.getPunchDate())
			erpAttendanceToUpdate.setPunchDate(erpAttendance.getPunchDate());
		if(null!=erpAttendance.getPunchInTimestamp())
			erpAttendanceToUpdate.setPunchInTimestamp(erpAttendance.getPunchInTimestamp());
		if(null!=erpAttendance.getPunchOutTimestamp())
			erpAttendanceToUpdate.setPunchOutTimestamp(erpAttendance.getPunchOutTimestamp());

		return erpAttendanceRepository.save(erpAttendanceToUpdate);

	}
	
	public List<ErpAttendance> filter(FilterPayload filterPayload) {
		StringBuilder queryStr = new StringBuilder("SELECT la FROM ErpAttendance la WHERE "); // TODO
		int idx = 0;
		for(Filter filter : filterPayload.getFilters()) {
			queryStr.append(idx==0?"":" and ").append("la.").append(filter.getColumn()).append(" ");
			queryStr.append(filter.getOperator()).append(" '");
			queryStr.append(filter.getValue()).append("'");
			idx++;
		}
		List<ErpAttendance> la = erpAttendanceRepository.filter(queryStr.toString());
		return la;
	}

	public List<Map<String, Object>> getLatecomerCount(DateRangePojo dateRangePojo) {
		return erpAttendanceRepository.fetchLatecomerCount(dateRangePojo);
		
	}

}
