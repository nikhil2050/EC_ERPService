package com.ec.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.erp.exception.ErpEmployeeNotFoundException;
import com.ec.erp.exception.ResourceNotFoundException;
import com.ec.erp.model.ErpLeaveApplication;
import com.ec.erp.model.request.Filter;
import com.ec.erp.model.request.FilterPayload;
import com.ec.erp.repository.ErpEmployeeRepository;
import com.ec.erp.repository.ErpLeaveApplicationRepository;
//import com.ec.erp.service.ErpLeaveApplicationService;
import com.ec.erp.service.ErpLeaveApplicationService;

/*
 * Refer : https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
 */
@RestController
@RequestMapping("ec/erp/leaveApplication/")
public class ErpLeaveApplicationController {

	@Autowired
	private ErpLeaveApplicationRepository erpLeaveApplicationRepository;

	@Autowired
	private ErpLeaveApplicationService erpLeaveApplicationService;

	@Autowired
	private ErpEmployeeRepository erpEmployeeRepository;
	
	@PostMapping("/create/employeeId/{empId}")
	public ErpLeaveApplication createLeaveApplication(@PathVariable("empId") Long employeeId, 
			@RequestBody ErpLeaveApplication payload) {

		return erpEmployeeRepository.findById(employeeId)
				.map(employee -> {
					payload.setErpEmployee(employee);
					payload.setLeaveApproverEmployee(erpEmployeeRepository.findById(payload.getLeaveApproverEmployee().getEmployeeId())
							.orElseThrow(() -> new ResourceNotFoundException("ErpEmployee", "employeeId", employeeId)));
					return erpLeaveApplicationRepository.save(payload);
				})
				.orElseThrow(() -> new ResourceNotFoundException("ErpEmployee", "employeeId", employeeId));
	}

	@GetMapping("/id={leaveApplicationId}")
	public ErpLeaveApplication findLeaveApplicationById(@PathVariable("leaveApplicationId") Long leaveApplicationId) {
		return erpLeaveApplicationRepository.findById(leaveApplicationId)
				.orElseThrow(() -> new ResourceNotFoundException("ErpLeaveApplication", "leaveApplicationId", leaveApplicationId));
	}

//	@GetMapping("/employee/{empId}/leaveApplication/list")
//	public Page<ErpLeaveApplication> getAllLeaveApplicationsByEmployeeId(@PathVariable("empId") Long empId, Pageable pageable) {
//		return erpLeaveApplicationRepository.findByErpEmployee(empId, pageable);
//	}
	
	@PutMapping("/employee/{empId}/leaveApplication/{leaveApplicationId}")
	public ErpLeaveApplication updateLeaveApplication(
			@PathVariable("empId") Long empId, 
			@PathVariable("leaveApplicationId") Long leaveApplicationId, 
			@Valid @RequestBody ErpLeaveApplication erpLeaveApplication) {

		if(!erpEmployeeRepository.existsById(empId)) {
			throw new ResourceNotFoundException("ErpEmployee", "employeeId", empId);
		}

		return erpLeaveApplicationRepository.findById(leaveApplicationId)
				.map(erpLeaveApplicationToUpdate -> {
//					erpLeaveApplicationToUpdate.setErpEmployee(erpLeaveApplication.getErpEmployee());					
					erpLeaveApplicationToUpdate.setErpEmployee(erpEmployeeRepository.findById(
							erpLeaveApplication.getErpEmployee().getEmployeeId())
							.orElseThrow(() -> new ResourceNotFoundException("ErpEmployee", "employeeId", erpLeaveApplication.getErpEmployee().getEmployeeId())));
					
					erpLeaveApplicationToUpdate.setLeaveAccrued(erpLeaveApplication.getLeaveAccrued());
					erpLeaveApplicationToUpdate.setFromDate(erpLeaveApplication.getFromDate());
					erpLeaveApplicationToUpdate.setToDate(erpLeaveApplication.getToDate());
					erpLeaveApplicationToUpdate.setReason(erpLeaveApplication.getReason());
					erpLeaveApplicationToUpdate.setLeaveBalance(erpLeaveApplication.getLeaveBalance());

					erpLeaveApplicationToUpdate.setLeaveStatus(erpLeaveApplication.getLeaveStatus());
					erpLeaveApplicationToUpdate.setLeaveApproveTimestamp(erpLeaveApplication.getLeaveApproveTimestamp());

//					erpLeaveApplicationToUpdate.setLeaveApproverEmployee(erpLeaveApplication.getLeaveApproverEmployee());
					erpLeaveApplicationToUpdate.setLeaveApproverEmployee(erpEmployeeRepository.findById(
							erpLeaveApplication.getLeaveApproverEmployee().getEmployeeId())
							.orElseThrow(() -> new ResourceNotFoundException("ErpEmployee", "leaveApproverEmployee", erpLeaveApplication.getLeaveApproverEmployee().getEmployeeId())));
					
					return erpLeaveApplicationRepository.save(erpLeaveApplicationToUpdate);
				}).orElseThrow(() -> new ResourceNotFoundException("ErpLeaveApplication", "leaveApplicationId", leaveApplicationId) );

//		return erpLeaveApplicationService.updateLeaveApplication(leaveApplicationId, erpLeaveApplication);
	}

	@DeleteMapping("/id={leaveApplicationId}")
	public Object deleteLeaveApplication(@PathVariable("leaveApplicationId") Long leaveApplicationId) {
		erpLeaveApplicationRepository.softDeleteById(leaveApplicationId);

		Map<String, String> response = new HashMap<>();
		response.put("success","Entity deleted");
		return response;
	}

	@PostMapping("/filter")
	public Object filter(@RequestBody FilterPayload filterPayload) {
		List<ErpLeaveApplication> la = erpLeaveApplicationService.filter(filterPayload);
		return la;
	}	
}
