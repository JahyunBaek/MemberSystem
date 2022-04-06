package com.kakao.style.approval.dto;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kakao.style.approval.DocApproval;
import com.kakao.style.approval.DocApprovalStep;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocApprovalStepDTO {

    private Long id;

    private DocApproval sn;
    
    private int orderNum;
    
    private String approver;

    @Column(length = 10, nullable = false)
    private String result;
    
    @Column
    private String infotxt;
    
    
    public DocApprovalStepDTO() {
    }

	
    public  DocApprovalStep toEntity() {
    	return new DocApprovalStep(id,sn,orderNum,approver,result,infotxt);
    }
	 
    
    //getter,setter 생략
}
