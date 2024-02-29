package com.olta.fundManager.AdminApp.controller;

import com.olta.fundManager.AdminApp.exception.CustomException;
import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.model.ApiResponse;
import com.olta.fundManager.AdminApp.service.FundService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/admins/{adminId}")
    public ResponseEntity<ApiResponse<Object>> getFundByAdminId(@Nonnull @PathVariable Integer adminId) {
        List<Fund> funds= fundService.getFundByAdminId(adminId);
        if(funds.isEmpty())
            throw new CustomException("No funds found under the admin Id: "+adminId);
        return new ResponseEntity<>(ApiResponse.builder()
                .data(funds)
                .success(true).build(), HttpStatus.FOUND);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveFund(@Nonnull @RequestBody Fund fund) {
        if(null == fund.getAdminId()){
            throw new CustomException("Admin Id is mandatory to create a new fund.");
        }
        return new ResponseEntity<>(ApiResponse.builder()
                .data(fundService.saveFund(fund))
                .success(true).build(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{fundId}")
    public void deleteFund(@PathVariable Long fundId) {
        fundService.deleteFund(fundId);
    }
}
