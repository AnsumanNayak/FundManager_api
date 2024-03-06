package com.olta.fundManager.AdminApp.controller;

import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.model.MemberDTO;
import com.olta.fundManager.AdminApp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/transactions/{memberId}")
    public List<MemberDTO> getMemberTransactionDetails(@PathVariable Long memberId) {
        return memberService.getMemberTransactionDetails(memberId);
    }

    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }
    @GetMapping("/funds/{fundId}")
    public Set<Member> getMembersByFundId(@PathVariable Long fundId) {
        return memberService.getMembersByFundId(fundId);
    }

    @PostMapping
    public Set<Member> saveMember(@RequestBody List<MemberDTO> members) {
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
