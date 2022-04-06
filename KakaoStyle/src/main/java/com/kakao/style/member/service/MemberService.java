package com.kakao.style.member.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kakao.style.approval.DocApprovalMapping;
import com.kakao.style.member.Member;
import com.kakao.style.member.dto.MemberDTO;

public interface MemberService extends UserDetailsService {
    Integer save(MemberDTO memberTO);
    List<DocApprovalMapping> getMemberNot(String account);
}