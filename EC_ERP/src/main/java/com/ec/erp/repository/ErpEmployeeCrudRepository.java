package com.ec.erp.repository;

import org.springframework.stereotype.Component;
import com.ec.erp.model.ErpEmployee;
import org.springframework.data.repository.CrudRepository;

@Component
public interface ErpEmployeeCrudRepository extends CrudRepository<ErpEmployee, Long>{

}
