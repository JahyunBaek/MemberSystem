package com.kakao.style.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalForm {
    private Long id;

    private String title;
    private String contents;
    private String kind;
    private String writer;
    private List<String> acclist;
}
