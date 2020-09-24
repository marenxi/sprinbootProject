package com.itszt.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    /*现有表结构：学号(studentId),学生姓名(NAME)，
    身份证号(identifyNo)，专业(profess)，班级(class)，入学时间(enroTime)，联系电话(telNo)，已修学分(credit)*/

    private Integer studentId;
    private String name;//NAME
    private String identifyNo;
    private String profess;
    private String enroTime;
    private String telNo;
    private Integer credit;
    private String studentClass;//class
}
