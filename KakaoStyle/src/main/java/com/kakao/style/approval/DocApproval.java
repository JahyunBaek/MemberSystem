package com.kakao.style.approval;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "docapproval")
public class DocApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sn")
    private Long sn;

    @Column(length = 100, nullable = false)
    private String title;
    
    @Column(length = 255, nullable = false)
    private String kind;
    
    @Column(length = 500, nullable = false)
    private String contents;

    @Column(length = 10, nullable = false)
    private String status;
    
    @Column(length = 255, nullable = false)
    private String writer;
    
    @Column(length = 255, nullable = false)
    private String position;

    @Column(length = 255, nullable = false)
    private String count;
    
    @OneToMany(mappedBy = "da",fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    List<DocApprovalStep> docAppList = new ArrayList<>();

    
    public DocApproval() {
    }

    public DocApproval(Long sn, String title, String kind, String contents, String status, String writer, String position, String count,List<DocApprovalStep> docAppList) {
        this.sn = sn;
        this.title = title;
        this.kind = kind;
        this.contents = contents;
        this.status = status;
        this.writer = writer;
        this.position = position;
        this.count = count;
        this.docAppList = docAppList;
    }
    
    //getter,setter 생략
}
