package com.olta.fundManager.MemberService.Repository;

import com.olta.fundManager.MemberService.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
