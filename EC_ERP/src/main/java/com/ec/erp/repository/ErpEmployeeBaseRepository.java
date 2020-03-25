package com.ec.erp.repository;

import org.springframework.stereotype.Component;
import com.ec.erp.model.ErpEmployee;
import com.ec.erp.softdelete.BaseRepository;

@Component
public interface ErpEmployeeBaseRepository extends BaseRepository<ErpEmployee, Long>{

}
