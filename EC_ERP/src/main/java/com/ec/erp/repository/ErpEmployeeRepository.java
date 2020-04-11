package com.ec.erp.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ec.erp.model.ErpEmployee;
import com.ec.erp.softdelete.BaseRepository;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ErpEmployeeRepository extends CrudRepository<ErpEmployee, Long>, BaseRepository<ErpEmployee, Long>{

}
