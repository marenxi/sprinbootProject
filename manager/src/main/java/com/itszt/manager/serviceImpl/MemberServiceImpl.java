package com.itszt.manager.serviceImpl;


import com.itszt.manager.dao.MemberDao;
import com.itszt.manager.entity.Member;
import com.itszt.manager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberMapper;

    @Override
    public List<Member> findById(Integer id) {
        return memberMapper.findById(id);
    }
}
