package com.itszt.manager.controller;

import com.itszt.manager.entity.DataResponse;
import com.itszt.manager.entity.Student;
import com.itszt.manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class StudentController {
/*    1.入参为学号(studentId),学生姓名(NAME)，身份证号(identifyNo)，实现三个字段的模糊查询(三个字段都不是必传，按实际传递的参数查询)；
            2.在1的基础上做分页查询：要求按入学日期排序并且分页每页3条；*/
    @Autowired
    private StudentService service;

    @RequestMapping("findStudentBy/{studentId}")
    @ResponseBody
    public List<Student> findStudentBy(@PathVariable Integer studentId, String name, String identifyNo){
       // DataResponse response=new DataResponse();
        List<Student> list = service.findStudetBy(studentId, name, identifyNo);
        /*response.setData(list);
        response.setCount(list.size());
        response.setMsg("ok");*/
        return list;
    }
}
