package com.ec.application.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ec.application.SoftDelete.BaseRepository;
import com.ec.application.model.MachineryOnRent;
import com.ec.application.model.BasicEntities.Product;

@Repository
public interface MachineryOnRentRepo extends BaseRepository<MachineryOnRent, Long>
{
	@Query(value="SELECT count(*) from MachineryOnRent m where m.machinery.machineryId=:machineryId")
	int machineryUsageCount(Long machineryId);

	//@Query(value="SELECT count(*) from MachineryOnRent m where m.contractor.contractorId=:contractorId")
	//int contractorUsageCount(Long contractorId);
	
	@Query(value="SELECT count(*) from MachineryOnRent m where m.usageLocation.locationId=:locationId")
	int locationUsageCount(Long locationId);


}

