package com.kakao.style.member.service;




import com.kakao.style.approval.DocApprovalMapping;
import com.kakao.style.member.Member;
import com.kakao.style.member.repository.MemberRepository;
import com.kakao.style.member.dto.MemberDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberRepository.findByAccount(account);
        Member memberEntity = memberEntityWrapper.orElse(null);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        System.out.println(memberEntity.getAccount());
        System.out.println(memberEntity.getPassword());
        return new User(memberEntity.getAccount(), memberEntity.getPassword(), authorities);
    }

    @Transactional
    @Override
    public Integer save(MemberDTO memberTO) {
        Member member = memberTO.toEntity();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        //System.out.println(member.getPassword());
        return memberRepository.save(member).getId();
    }
    
    @Transactional
    @Override
    public List<DocApprovalMapping> getMemberNot(String account) {
        return memberRepository.findByAccountNot(account);
    }
}
