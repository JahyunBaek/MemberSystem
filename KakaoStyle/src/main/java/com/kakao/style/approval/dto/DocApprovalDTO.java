package com.kakao.style.approval.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kakao.style.approval.DocApproval;
import com.kakao.style.approval.DocApprovalStep;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocApprovalDTO {


    private Long sn;

    private String title;
    
    private String kind;
    
    private String contents;

    private String status;
    
    private String writer;
    
    private String position;

    private String count;
    
    private List<DocApprovalStep> approver;
    
    public DocApprovalDTO() {
    }

    public  DocApproval toEntity() {
    	return new DocApproval(sn,title,kind,contents,status,writer,position,count,approver);
    }
    
    //getter,setter 생략
}
