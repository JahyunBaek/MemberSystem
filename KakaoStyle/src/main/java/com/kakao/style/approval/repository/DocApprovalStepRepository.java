package com.kakao.style.approval.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kakao.style.approval.DocApproval;
import com.kakao.style.approval.DocApprovalStep;
import com.kakao.style.member.Member;

public interface DocApprovalStepRepository extends JpaRepository<DocApprovalStep, Long> {
    Optional<DocApprovalStep> findByApproverAndDa(String approver,DocApproval docapproval);
    List<DocApprovalStep> findByApproverAndResult(String approver,String result);
    List<DocApprovalStep> findByApprover(String approver);
}