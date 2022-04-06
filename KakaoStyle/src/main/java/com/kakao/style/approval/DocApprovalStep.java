package com.kakao.style.approval;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "docapprovalstep")
public class DocApprovalStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    @JoinColumn(name = "docapproval_sn")
    private DocApproval da;
    
    @Column(length = 255, nullable = false)
    private int ordernum;
    
    @Column(length = 500, nullable = false)
    private String approver;

    @Column(length = 10, nullable = false)
    private String result;
    
    @Column
    private String infotxt;
    
    
    public DocApprovalStep() {
    }

	
	  public DocApprovalStep(Long id, DocApproval da, int order, String approver,
	  String result, String infotxt) 
	  { this.id = id; 
	  this.da = da;
	  this.ordernum = order; 
	  this.approver = approver; 
	  this.result = result;
	  this.infotxt = infotxt; }
	 
    
    //getter,setter 생략
}
