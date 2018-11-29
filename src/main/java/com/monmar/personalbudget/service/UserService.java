package com.monmar.personalbudget.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.monmar.personalbudget.entity.User;
import com.monmar.personalbudget.user.CrmUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);

	void updateUser(User user);
}
