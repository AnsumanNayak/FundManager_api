package com.olta.fundManager.AdminApp.repository;


import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FundRepository extends JpaRepository<Fund,Long> {
    List<Fund> findByAdminId(Integer adminId);
}
