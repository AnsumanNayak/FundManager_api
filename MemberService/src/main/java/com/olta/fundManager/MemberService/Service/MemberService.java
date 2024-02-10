package com.olta.fundManager.MemberService.Service;


import com.olta.fundManager.MemberService.entities.Member;

import java.util.List;

//@Service
public interface MemberService {
    public List<Member> getAllMembers();
    public Member getMemberById(Long memberId);
    public Member saveMember(Member member);
    public void deleteMember(Long memberId);
}
