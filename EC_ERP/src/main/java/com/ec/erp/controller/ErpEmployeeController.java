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
import com.ec.erp.model.ErpEmployee;
import com.ec.erp.repository.ErpEmployeeBaseRepository;
import com.ec.erp.repository.ErpEmployeeCrudRepository;
import com.ec.erp.service.ErpEmployeeService;

@RestController
@RequestMapping("ec/erp/employee")
public class ErpEmployeeController {
	
	@Autowired
	private ErpEmployeeService erpEmployeeService;
	
	@Autowired
	private ErpEmployeeCrudRepository erpEmployeeCrudRepository;
	
	@Autowired
	private ErpEmployeeBaseRepository erpEmployeeBaseRepository;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ErpEmployee createEmployee(@RequestBody ErpEmployee payload) throws Exception {
		return erpEmployeeCrudRepository.save(payload);
	}
	
	@GetMapping("/id={empId}")
	public ErpEmployee findEmployeeByEmpId(@PathVariable("empId") Long employeeId) {
		return erpEmployeeCrudRepository.findById(employeeId)
//				.orElseThrow(() -> new ResourceNotFoundException("ErpEmployee", "employeeId", employeeId));
				.orElseThrow(ErpEmployeeNotFoundException::new);
	}
	
	@GetMapping
	public Iterable<ErpEmployee> getAllErpEmployees(){
		return erpEmployeeCrudRepository.findAll();
	}
	
	@PutMapping("/id={empId}")
	public ErpEmployee updateEmployee(@PathVariable("empId") Long employeeId, @RequestBody ErpEmployee erpEmployee) {
		return erpEmployeeService.updateEmployee(employeeId, erpEmployee);
	}
	
	@DeleteMapping("/id={empId}")
	public Object deleteEmployee(@PathVariable("empId") Long employeeId) {
		erpEmployeeBaseRepository.softDeleteById(employeeId);
		
		Map<String, String> response = new HashMap<>();
		response.put("success","Entity deleted");
		return response;
	}
}
