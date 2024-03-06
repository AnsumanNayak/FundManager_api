package com.olta.fundManager.AdminApp.service;

import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

//@Service
public interface FundService {
    public List<Fund> getAllFunds();
    public Fund getFundById(Long fundId);
    public List<Fund> getFundByAdminId(Integer adminId);
    public Set<Member> addMembersToFund(List<Member> members, Long fundId);
    public Fund saveFund(Fund fund);
    public void deleteFund(Long fundId);
}
