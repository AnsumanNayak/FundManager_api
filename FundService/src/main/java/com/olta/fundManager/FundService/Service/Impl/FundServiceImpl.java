package com.olta.fundManager.FundService.Service.Impl;

import com.olta.fundManager.FundService.Repository.FundRepository;
import com.olta.fundManager.FundService.Service.FundService;
import com.olta.fundManager.FundService.entities.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundServiceImpl implements FundService {
    @Autowired
    private FundRepository fundRepository;

    @Override
    public List<Fund> getAllFunds() {
        return fundRepository.findAll();
    }

    @Override
    public Fund getFundById(Long fundId) {
        return fundRepository.findById(fundId).orElse(null);
    }

    @Override
    public List<Fund> getFundByAdminId(Integer adminId) {
        return fundRepository.findByAdminId(adminId);
    }

    @Override
    public Fund saveFund(Fund fund) {
        return fundRepository.save(fund);
    }

    @Override
    public void deleteFund(Long fundId) {
        fundRepository.deleteById(fundId);
    }
}
