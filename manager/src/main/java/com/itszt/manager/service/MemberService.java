package com.itszt.manager.service;



import com.itszt.manager.entity.Member;

import java.util.Date;
import java.util.List;


public interface MemberService {
    //根据id查询会员信息
    public List<Member> findById(Integer id);

    //查询所有会员信息
    public  List<Member> findAll();

    //根基id删除会员信息
    public void deleteMemberById(Integer id);

    //新增会员信息
    public boolean insertMember(Member member);

    //1.1会员信息更新页面的跳转（需要根据id查询显示出对应的会员信息）
    public  Member findMemberById(Integer id);

    //1.2会员信息的更新
    public  int  updateMemberById(Member member);

    //根据时间段和任意会员的字段进行查询
    public  List<Member> findByManyConditions(String startDate, String endDate, String name, String workType, String telephone, Integer age,Integer pageStart, Integer pageEnd);
    public  Integer countManyConditions(String startDate, String endDate, String name, String workType, String telephone, Integer age,Integer pageStart, Integer pageEnd);
}
