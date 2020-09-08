package com.itszt.manager.service;

import com.itszt.manager.entity.User;

public interface UserService {
    public User findUserByUserName(String username);
}
