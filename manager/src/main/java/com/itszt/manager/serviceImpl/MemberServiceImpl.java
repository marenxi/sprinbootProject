package com.itszt.manager.serviceImpl;


import com.itszt.manager.dao.MemberDao;
import com.itszt.manager.entity.Member;
import com.itszt.manager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberMapper;

    @Override
    public List<Member> findById(Integer id) {
        return memberMapper.findById(id);
    }


    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public void deleteMemberById(Integer id) {
        memberMapper.deleteMemberById(id);
    }

    @Override
    public boolean insertMember(Member member) {
        memberMapper.insertMember(member);
        return true;
    }

    @Override
    public int updateMemberById(Member member) {
        int rows= memberMapper.updateMemberById(member);
        System.out.println(rows);
        return rows;
    }

    @Override
    public List<Member> findByManyConditions(String startDate, String endDate, String name, String workType, String telephone, Integer age,Integer pageStart, Integer pageEnd) {
        return memberMapper.findByManyConditions(startDate,endDate,name,workType,telephone,age,pageStart,pageEnd);
    }

    @Override
    public Integer countManyConditions(String startDate, String endDate, String name, String workType, String telephone, Integer age, Integer pageStart, Integer pageEnd) {
        return memberMapper.countManyConditions(startDate,endDate,name,workType,telephone,age,pageStart,pageEnd);
    }

    @Override
    public Member findMemberById(Integer id) {
        return memberMapper.findMemberById(id);
    }
}
