package com.ec.gateway.bean.auth.Data;

import java.util.List;

import org.springframework.data.domain.Page;

public class UsersWithRoleNameListData 
{

	List<String> roles;
	Page<User> users;
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public Page<User> getUsers() {
		return users;
	}
	public void setUsers(Page<User> users) {
		this.users = users;
	}
	
	
}
