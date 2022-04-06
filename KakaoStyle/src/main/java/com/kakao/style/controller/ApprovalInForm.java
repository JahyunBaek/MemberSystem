package com.kakao.style.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalInForm {
    private Long sn;

    private String title;
    private String contents;
    private String kind;
    private String writer;
    private String status;
    private String position;
    private String count;
}
