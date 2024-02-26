package com.olta.fundManager.MemberService.Service;


import com.olta.fundManager.MemberService.entities.Member;
import com.olta.fundManager.MemberService.model.MemberDTO;

import java.util.List;

//@Service
public interface MemberService {
    public List<Member> getAllMembers();
    public Member getMemberById(Long memberId);
    public List<Member> saveMember(List<MemberDTO> members);
    public void deleteMember(Long memberId);

    public List<Member> getMembersByFundId(Long fundId);

    boolean isExistingMember(MemberDTO memberDTO);
}
