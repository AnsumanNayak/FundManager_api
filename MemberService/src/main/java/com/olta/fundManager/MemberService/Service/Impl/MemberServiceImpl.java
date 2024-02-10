package com.olta.fundManager.MemberService.Service.Impl;


import com.olta.fundManager.MemberService.Repository.MemberRepository;
import com.olta.fundManager.MemberService.Service.MemberService;
import com.olta.fundManager.MemberService.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
