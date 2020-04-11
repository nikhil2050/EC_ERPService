package com.ec.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ec.erp.model.ErpLeaveApplication;
import com.ec.erp.softdelete.BaseRepository;

@Repository
public interface ErpLeaveApplicationRepository 
		extends BaseRepository<ErpLeaveApplication, Long>, CrudRepository<ErpLeaveApplication, Long>, ErpLeaveApplicationRepositoryCustom{

	Page<ErpLeaveApplication> findByErpEmployee(Long empId, Pageable pageable);

}
