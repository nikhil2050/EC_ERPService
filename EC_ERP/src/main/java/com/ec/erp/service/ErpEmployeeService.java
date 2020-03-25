package com.ec.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.erp.exception.ErpEmployeeNotFoundException;
import com.ec.erp.model.ErpEmployee;
import com.ec.erp.repository.ErpEmployeeCrudRepository;

@Service
public class ErpEmployeeService {
	
	@Autowired
	ErpEmployeeCrudRepository erpEmployeeRepository;
	
	@Autowired
	private ErpEmployeeCrudRepository erpEmployeeCrudRepository;
	
//	public ErpEmployee createErpEmployee(ErpEmployee payload) throws Exception {
//		ErpEmployee erpEmployee = payload;
//		erpEmployeeRepository.save(erpEmployee);
//		return erpEmployee;
//	}

	public ErpEmployee updateEmployee(Long employeeId, ErpEmployee erpEmployee) {		
		ErpEmployee erpEmployeeToUpdate = erpEmployeeCrudRepository.findById(employeeId)
				.orElseThrow(ErpEmployeeNotFoundException::new);

		erpEmployeeToUpdate.setEmployeeName(erpEmployee.getEmployeeName());
		erpEmployeeToUpdate.setMobileNo(erpEmployee.getMobileNo());
		erpEmployeeToUpdate.setEmergencyContactNo(erpEmployee.getEmergencyContactNo());
		erpEmployeeToUpdate.setEmployeeName(erpEmployee.getEmployeeName());
		erpEmployeeToUpdate.setAddress(erpEmployee.getAddress());
		erpEmployeeToUpdate.setBirthDate(erpEmployee.getBirthDate());
		erpEmployeeToUpdate.setJoiningDate(erpEmployee.getJoiningDate());
		erpEmployeeToUpdate.setDepartment(erpEmployee.getDepartment());
		
		return erpEmployeeCrudRepository.save(erpEmployeeToUpdate);

	}
	
}
