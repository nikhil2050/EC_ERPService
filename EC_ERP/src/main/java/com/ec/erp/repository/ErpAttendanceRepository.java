package com.ec.erp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ec.erp.model.ErpAttendance;
import com.ec.erp.softdelete.BaseRepository;

@Repository
public interface ErpAttendanceRepository 
		extends BaseRepository<ErpAttendance, Long>, CrudRepository<ErpAttendance, Long>, ErpAttendanceRepositoryCustom{

}
