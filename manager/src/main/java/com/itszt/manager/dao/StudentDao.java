package com.itszt.manager.dao;

import com.itszt.manager.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {
    public List<Student> findStudetBy(@Param("studentId") Integer studentId, @Param("name")String name,  @Param("identifyNo")String identifyNo);
}

