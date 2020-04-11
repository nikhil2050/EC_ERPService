package com.ec.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.erp.exception.ResourceNotFoundException;
import com.ec.erp.model.ErpAttendance;
import com.ec.erp.model.ErpLeaveApplication;
import com.ec.erp.model.request.DateRangePojo;
import com.ec.erp.model.request.FilterPayload;
import com.ec.erp.repository.ErpAttendanceRepository;
import com.ec.erp.repository.ErpEmployeeRepository;
import com.ec.erp.repository.ErpLeaveApplicationRepository;
import com.ec.erp.service.ErpAttendanceService;
import com.ec.erp.service.ErpLeaveApplicationService;

@RestController
@RequestMapping("ec/erp/attendance")
public class ErpAttendanceController {

	@Autowired
	private ErpAttendanceRepository erpAttendanceRepository;

	@Autowired
	private ErpEmployeeRepository erpEmployeeRepository;
	
	@Autowired
	private ErpAttendanceService erpAttendanceService;

	@PostMapping("/create/employeeId/{empId}")
	public ErpAttendance createAttendance(@PathVariable("empId") Long employeeId, 
			@RequestBody ErpAttendance payload) {
		return erpEmployeeRepository.findById(employeeId)
				.map(employee -> {
					payload.setErpEmployee(employee);
					return erpAttendanceRepository.save(payload);
				})
				.orElseThrow(() -> new ResourceNotFoundException("ErpEmployee", "employeeId", employeeId));

	}

	@GetMapping("/id={attendanceId}")
	public ErpAttendance findAttendanceById(@PathVariable("attendanceId") Long attendanceId) {
		return erpAttendanceRepository.findById(attendanceId)
				.orElseThrow(() -> new ResourceNotFoundException("ErpAttendance", "attendanceId", attendanceId));
	}

	@PutMapping("/id={attendanceId}")
	public ErpAttendance updateAttendanceById(@PathVariable("attendanceId") Long attendanceId, @RequestBody ErpAttendance erpAttendance) {
		return erpAttendanceService.updateAttendance(attendanceId, erpAttendance);
	}

	@DeleteMapping("/id={attendanceId}")
	public Object deleteAttendance(@PathVariable("attendanceId") Long attendanceId) {
		erpAttendanceRepository.softDeleteById(attendanceId);

		Map<String, String> response = new HashMap<>();
		response.put("success","Entity deleted");
		return response;
	}

	@PostMapping("/filter")
	public Object filter(@RequestBody FilterPayload filterPayload) {
		List<ErpAttendance> la = erpAttendanceService.filter(filterPayload);
		return la;
	}

	@PostMapping("/latecomerCount")
	public Object latecomerCount(@RequestBody DateRangePojo dateRangePojo) {
		Map<String, Object> response = new HashMap<>();
		Object responseList = erpAttendanceService.getLatecomerCount(dateRangePojo);
		response.put("latecomers", responseList);
		return response;
	}
}
