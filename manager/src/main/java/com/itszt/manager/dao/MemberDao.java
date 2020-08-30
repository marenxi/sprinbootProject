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

}
