package com.itszt.manager.dao;

import com.itszt.manager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
   public User findUserByUserName(String username);
}
