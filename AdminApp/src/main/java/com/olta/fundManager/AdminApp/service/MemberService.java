package com.olta.fundManager.AdminApp.service;


import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.model.MemberDTO;

import java.util.List;
import java.util.Set;

//@Service
public interface MemberService {
    public List<Member> getAllMembers();
    public Member getMemberById(Long memberId);
    public Set<Member> saveMember(List<MemberDTO> members);
    public void deleteMember(Long memberId);

    public Set<Member> getMembersByFundId(Long fundId);

    boolean isExistingMember(MemberDTO memberDTO);
}
