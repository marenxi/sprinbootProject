package com.itszt.manager.serviceImpl;

import com.itszt.manager.dao.StudentDao;
import com.itszt.manager.entity.Student;
import com.itszt.manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDao service;


    @Override
    public List<Student> findStudetBy(Integer studentId, String name, String identifyNo) {
        return service.findStudetBy(studentId,name,identifyNo);
    }
}
