package com.olta.fundManager.AdminApp.service;

import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.model.FundDTO;

import java.util.List;
import java.util.Set;

//@Service
public interface FundService {
    public List<Fund> getAllFunds();
    public Fund getFundById(Long fundId);
    public List<FundDTO> getFundByAdminId(Integer adminId);
    public Set<Member> addMembersToFund(List<Member> members, Long fundId);
    public Fund saveFund(FundDTO fund);
    public void deleteFund(Long fundId);
}
