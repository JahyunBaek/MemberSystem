package com.kakao.style.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.style.approval.DocApprovalOutMapping;
import com.kakao.style.approval.service.DocApprovalService;
import com.kakao.style.member.dto.MemberDTO;
import com.kakao.style.member.service.MemberService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private DocApprovalService docService;
    
    @GetMapping("/")
    public String homeView() {
    	System.out.println("homeView");
        return "pages/home";
    }

    @GetMapping("/login")
    public String loginView() {
    	System.out.println("login");
        return "pages/login";
    }

    @GetMapping("/signup")
    public String signupView() {
    	System.out.println("signup");
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDTO memberDTO) {
    	System.out.println("signup2");
        memberService.save(memberDTO);
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/member/approval")
    public String approvalView() {
        return "pages/approval";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/member/newdoc")
    public String approvalPopupView() {
        return "pages/newdoc";
    }
    
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/member/docview/{sn}")
    public String approvaldocView(@PathVariable("sn") String sn,Model model) {
    	DocApprovalOutMapping temp = docService.findDocSn(sn);
    	model.addAttribute("ApprovalInForm", temp);
        return "pages/docView";
    }
    

    @GetMapping("/denied")
    public String deniedView() {
        return "pages/denied";
    }
    @PostMapping("/create")
    public String create(final ApprovalForm form) {
    	docService.save(form);
        return "redirect:/member/approval";
    }
    @PostMapping("/savestatus")
    public String savestatus(final ApprovalrsultForm form) {
    	docService.saveresult(form);
        return "redirect:/member/approval";
    }
}