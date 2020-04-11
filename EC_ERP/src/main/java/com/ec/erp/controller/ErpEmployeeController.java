package com.ec.erp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ec.erp.exception.ErpEmployeeNotFoundException;
import com.ec.erp.exception.ResourceNotFoundException;
import com.ec.erp.model.ErpEmployee;
import com.ec.erp.repository.ErpEmployeeRepository;
import com.ec.erp.service.ErpEmployeeService;
import com.ec.erp.util.ErpEncryptionUtils;

@RestController
@RequestMapping("ec/erp")
public class ErpEmployeeController {
	
//	@Autowired
//	private ErpEmployeeService erpEmployeeService;
	
	@Autowired
	private ErpEmployeeRepository erpEmployeeRepository;
	
	@Autowired
	private ErpEncryptionUtils erpEncryptionUtils;
	
	@PostMapping("/employee/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ErpEmployee createEmployee(@RequestBody ErpEmployee payload) throws Exception {
		payload.getUser().setPassword(erpEncryptionUtils.bCryptPassword("Green@123"));
		return erpEmployeeRepository.save(payload);
	}
	
	@GetMapping("/employee/id={empId}")
	public ErpEmployee findEmployeeById(@PathVariable("empId") Long employeeId) {
		return erpEmployeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("ErpEmployee", "employeeId", employeeId));
	}
	
	@GetMapping("/employee/list")
	public Iterable<ErpEmployee> getAllErpEmployees(){
		return erpEmployeeRepository.findAll();
	}
	
	@PutMapping("/employee/id={empId}")
	public ErpEmployee updateEmployee(@PathVariable("empId") Long employeeId, @RequestBody ErpEmployee payload) {

		return erpEmployeeRepository.findById(employeeId)
				.map(erpEmployeeToUpdate -> {
					
//					erpEmployeeToUpdate.getUser().setUserName(payload.getUser().getUserName());
					erpEmployeeToUpdate.getUser().setPassword(erpEncryptionUtils.bCryptPassword(payload.getUser().getPassword()));
					erpEmployeeToUpdate.getUser().setStatus(payload.getUser().isStatus());
					erpEmployeeToUpdate.getUser().setPasswordExpired(payload.getUser().isPasswordExpired());
					erpEmployeeToUpdate.getUser().setRoles(payload.getUser().getRoles());

					erpEmployeeToUpdate.setEmployeeName(payload.getEmployeeName());
					erpEmployeeToUpdate.setMobileNo(payload.getMobileNo());
					erpEmployeeToUpdate.setEmergencyContactNo(payload.getEmergencyContactNo());
					erpEmployeeToUpdate.setAddress(payload.getAddress());
//					erpEmployeeToUpdate.setBirthDate(payload.getBirthDate());
//					erpEmployeeToUpdate.setJoiningDate(payload.getJoiningDate());
					erpEmployeeToUpdate.setDepartment(payload.getDepartment());
					
					return erpEmployeeRepository.save(erpEmployeeToUpdate);
				}).orElseThrow(ErpEmployeeNotFoundException::new );
	}
	
	@DeleteMapping("/employee/id={empId}")
	public Object deleteEmployee(@PathVariable("empId") Long employeeId) {
		erpEmployeeRepository.softDeleteById(employeeId);
		
		Map<String, String> response = new HashMap<>();
		response.put("success","Entity deleted");
		return response;
	}
}
