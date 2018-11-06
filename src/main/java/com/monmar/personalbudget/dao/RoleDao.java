package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.Role;

public interface RoleDao {

	Role findRoleByName(String theRoleName);
	
}
