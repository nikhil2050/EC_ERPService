package com.ec.gateway.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ec.gateway.bean.auth.Data.Role;
import com.ec.gateway.repository.RoleRepo;

@Service
public class RoleService 
{

	@Autowired
	RoleRepo roleRepo;
	
	public Page<Role> findAll(Pageable pageable)
	{
		return roleRepo.findAll(pageable);
    }
	
	public Role createRole(Role payload) throws Exception 
	{
		roleRepo.save(payload);
		return payload;	
    }
}
