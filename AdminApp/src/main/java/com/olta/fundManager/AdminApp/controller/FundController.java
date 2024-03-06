package com.olta.fundManager.AdminApp.controller;

import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.exception.CustomException;
import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.service.FundService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/funds")
public class FundController {

    @Autowired
    private FundService fundService;

    @GetMapping
    public List<Fund> getAllFunds() {
        return fundService.getAllFunds();
    }

    @GetMapping("/{fundId}")
    public Fund getFundById(@Nonnull @PathVariable Long fundId) {
        return fundService.getFundById(fundId);
    }

    @GetMapping("/admins")
    public List<Fund> getFundByAdminId(@RequestParam("adminId") Integer adminId) {
        return fundService.getFundByAdminId(adminId);
    }
    @PostMapping("/{fundId}/members")
    public Set<Member> addMembersToFund(@Nonnull @RequestBody List<Member> members,@PathVariable Long fundId){
        return fundService.addMembersToFund(members,fundId);
    }
    @PostMapping
    public Fund saveFund(@Nonnull @RequestBody Fund fund) {
        if(null == fund.getAdminId()){
            throw new CustomException("Admin Id is mandatory to create a new fund.");
        }
        return fundService.saveFund(fund);
    }

    @DeleteMapping("/{fundId}")
    public void deleteFund(@PathVariable Long fundId) {
        fundService.deleteFund(fundId);
    }
}
