package com.itszt.manager.dao;



import com.itszt.manager.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface MemberMapper {
    //根据id查询会员信息
    public List<Member> findById(Integer id);

}
