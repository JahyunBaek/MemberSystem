package com.kakao.style.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.style.approval.DocApproval;
import com.kakao.style.approval.DocApprovalMapping;
import com.kakao.style.approval.DocApprovalOutMapping;
import com.kakao.style.approval.service.DocApprovalService;
import com.kakao.style.member.Member;
import com.kakao.style.member.service.MemberService;

@RestController
@RequestMapping("/api")
public class RestAPIController {
	  @Autowired
	  private DocApprovalService docService;
	  
	  @Autowired
	  private MemberService memberService;
	  
	  @GetMapping("/outbox/{name}")
	  public List<DocApprovalOutMapping> getOUTBOX(@PathVariable("name") String account,HttpSession session) {
		System.out.print(account);
	    return this.docService.findDocOUTBOX(account, "0");
	  }
	  @GetMapping("/inbox/{name}")
	  public List<ApprovalInForm> getINBOX(@PathVariable("name") String account,HttpSession session) {
		 
	    return this.docService.findDocINBOX(account, "0");
	  }
	  @GetMapping("/archive/{name}")
	  public List<ApprovalInForm> getARCHIVE(@PathVariable("name") String account,HttpSession session) {
		 
	    return this.docService.findDocARCHIVE(account, "0");
	  }
	  @GetMapping("/memlist/{name}")
	  public List<DocApprovalMapping> getApprover(@PathVariable("name") String account,HttpSession session) {
		
	    return this.memberService.getMemberNot(account);
	  }
	  @GetMapping("/applist/{sn}")
	  public List<ApprovalStepForm> getApproverList(@PathVariable("sn") String sn,HttpSession session) {
		  
	    return this.docService.findAppverSn(sn);
	  }
	
}
