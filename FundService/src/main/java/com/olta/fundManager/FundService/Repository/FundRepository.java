package com.olta.fundManager.FundService.Repository;


import com.olta.fundManager.FundService.entities.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundRepository extends JpaRepository<Fund,Long> {
    List<Fund> findByAdminId(Integer adminId);
}
