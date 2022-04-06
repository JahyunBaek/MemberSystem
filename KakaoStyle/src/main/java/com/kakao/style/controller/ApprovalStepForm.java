package com.kakao.style.controller;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kakao.style.approval.DocApproval;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalStepForm {
    private Long id;
    
    private int ordernum;
    
    private String approver;

    private String result;
    
    private String infotxt;
}
