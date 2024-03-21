package com.olta.fundManager.AdminApp.repository;

import com.olta.fundManager.AdminApp.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Set<Member> findByNameContainingAndDob(String name, LocalDate dob);
}
