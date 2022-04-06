package com.kakao.style.approval.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kakao.style.approval.DocApproval;
import com.kakao.style.approval.DocApprovalOutMapping;
import com.kakao.style.controller.ApprovalInForm;
import com.kakao.style.member.Member;

public interface DocApprovalRepository extends JpaRepository<DocApproval, Long> {
    List<DocApprovalOutMapping> findDistinctByWriterAndStatus(String writer,String status);
    List<DocApprovalOutMapping> findByWriter(String writer);
    List<DocApproval> findByWriterAndSn(String writer,Long sn);
    DocApprovalOutMapping findBySn(Long sn);
    DocApproval findBySnLike(Long sn);
}