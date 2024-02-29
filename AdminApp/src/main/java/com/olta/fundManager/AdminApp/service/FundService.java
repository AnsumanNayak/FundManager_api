package com.olta.fundManager.AdminApp.service;

import com.olta.fundManager.AdminApp.entities.Fund;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface FundService {
    public List<Fund> getAllFunds();
    public Fund getFundById(Long fundId);
    public List<Fund> getFundByAdminId(Integer adminId);
    public Fund saveFund(Fund fund);
    public void deleteFund(Long fundId);
}
