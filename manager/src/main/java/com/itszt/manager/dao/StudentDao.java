package com.itszt.manager.dao;

import com.itszt.manager.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {
    public List<Student> findStudetBy(Integer studentId,String name,String identifyNo);
}

