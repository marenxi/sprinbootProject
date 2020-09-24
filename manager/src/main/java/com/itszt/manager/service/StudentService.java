package com.itszt.manager.service;

import com.itszt.manager.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findStudetBy(Integer studentId, String name, String identifyNo);
}
