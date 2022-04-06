package com.kakao.style.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kakao.style.member.Member;
import com.kakao.style.member.repository.MemberRepository;

@Component
public class SomethingComponent {
    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            for(int i = 0; i < 11; i++) {
            	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                  String pwd = passwordEncoder.encode("pwd"+i);
                Member member = new Member(i, "name"+i, "account"+i, pwd);
                memberRepository.save(member);
            }
        };
    }   
}