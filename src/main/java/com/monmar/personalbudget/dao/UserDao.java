package com.monmar.personalbudget.dao;

import com.monmar.personalbudget.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
