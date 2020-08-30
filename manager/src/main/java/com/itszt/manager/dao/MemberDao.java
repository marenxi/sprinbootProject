package com.itszt.manager.dao;



import com.itszt.manager.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}
