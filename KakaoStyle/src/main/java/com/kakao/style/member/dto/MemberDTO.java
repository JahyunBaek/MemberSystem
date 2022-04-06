package com.kakao.style.member.dto;

import java.time.LocalDateTime;

import com.kakao.style.member.Member;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MemberDTO {

    private Integer id;

    private String name;

    private String account;

    private String password;


    public Member toEntity() {
        return new Member(id, name, account, password);
    }
//getter,setter 생략
}