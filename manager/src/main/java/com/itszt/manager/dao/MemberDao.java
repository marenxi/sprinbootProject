package com.itszt.manager.dao;



import com.itszt.manager.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface MemberDao {
    //根据id查询会员信息
    public List<Member> findById(Integer id);
    //查询所有的会员信息
    public  List<Member> findAll();
    //根基id删除会员信息
    public void deleteMemberById(Integer id);
    //新增会员信息
    public void insertMember(Member member);

    //1.1会员信息更新页面的跳转（需要根据id查询显示出对应的会员信息）
    public  Member findMemberById(Integer id);

    //1.2会员信息的更新
    public  int updateMemberById(Member member);

    //根据时间段和任意会员的字段进行查询
    public  List<Member> findByManyConditions(@Param("startDate") String startTime, @Param("endDate") String endTime, @Param("name") String name, @Param("workType") String workType,
                                              @Param("telephone") String telephone, @Param("age") Integer age);

}
