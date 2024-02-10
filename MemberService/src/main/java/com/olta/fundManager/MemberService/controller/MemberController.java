package com.olta.fundManager.MemberService.controller;

import com.olta.fundManager.MemberService.Service.MemberService;
import com.olta.fundManager.MemberService.entities.Member;
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

    @GetMapping("/{MemberId}")
    public Member getMemberById(@PathVariable Long MemberId) {
        return memberService.getMemberById(MemberId);
    }

    @PostMapping
    public Member saveMember(@RequestBody Member Member) {
        return memberService.saveMember(Member);
    }

    @DeleteMapping("/{MemberId}")
    public void deleteMember(@PathVariable Long MemberId) {
        memberService.deleteMember(MemberId);
    }
}
