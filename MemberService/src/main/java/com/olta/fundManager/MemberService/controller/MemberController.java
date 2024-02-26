package com.olta.fundManager.MemberService.controller;

import com.olta.fundManager.MemberService.Service.MemberService;
import com.olta.fundManager.MemberService.entities.Member;
import com.olta.fundManager.MemberService.model.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }
    @GetMapping("/funds/{fundId}")
    public List<Member> getMembersByFundId(@PathVariable Long fundId) {
        return memberService.getMembersByFundId(fundId);
    }

    @PostMapping
    public List<Member> saveMember(@RequestBody List<MemberDTO> members) {
        return memberService.saveMember(members);
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long MemberId) {
        memberService.deleteMember(MemberId);
    }

    @PostMapping("/memberCheck")
    public boolean isExistingMember(@RequestBody MemberDTO memberDTO){
        return memberService.isExistingMember(memberDTO);
    }

}
