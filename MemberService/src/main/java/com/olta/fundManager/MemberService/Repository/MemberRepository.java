package com.olta.fundManager.MemberService.Repository;

import com.olta.fundManager.MemberService.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByFundId(Long fundId);

    List<Member> findByNameAndDob(String name, LocalDate dob);
}
