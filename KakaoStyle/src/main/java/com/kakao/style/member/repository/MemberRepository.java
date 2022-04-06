package com.kakao.style.member.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kakao.style.approval.DocApprovalMapping;
import com.kakao.style.member.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByAccount(String account);
    List<DocApprovalMapping> findByAccountNot(String account);
}