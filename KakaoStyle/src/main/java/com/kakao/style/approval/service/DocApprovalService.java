package com.kakao.style.approval.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kakao.style.approval.DocApproval;
import com.kakao.style.approval.DocApprovalOutMapping;
import com.kakao.style.approval.DocApprovalStep;
import com.kakao.style.approval.DocApprovalStepMapping;
import com.kakao.style.approval.dto.DocApprovalDTO;
import com.kakao.style.approval.dto.DocApprovalStepDTO;
import com.kakao.style.approval.repository.DocApprovalRepository;
import com.kakao.style.approval.repository.DocApprovalStepRepository;
import com.kakao.style.controller.ApprovalForm;
import com.kakao.style.controller.ApprovalInForm;
import com.kakao.style.controller.ApprovalStepForm;
import com.kakao.style.controller.ApprovalrsultForm;
import com.kakao.style.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
@Service
@RequiredArgsConstructor
public class DocApprovalService{
   
	private final DocApprovalRepository docRepository;
	private final DocApprovalStepRepository docStepRepository;
	
	@Transactional
    public Long save(final ApprovalForm params) {
		

		DocApproval dto = new DocApproval();
		
		dto.setContents(params.getContents());
		dto.setCount(Integer.toString(params.getAcclist().size()));
		dto.setPosition("1");
		dto.setKind(params.getKind());
		dto.setStatus("0");
		dto.setTitle(params.getTitle());
		dto.setWriter(params.getWriter());
		
		
		docRepository.save(dto);
		//dto.setDocAppList(null);
		
		for(int i=0;i<params.getAcclist().size();i++) {
			DocApprovalStep dtoStep = new DocApprovalStep();
			dtoStep.setApprover(params.getAcclist().get(i));
			dtoStep.setOrdernum(i+1);
			dtoStep.setResult("0");
			dtoStep.setDa(dto);
			docStepRepository.save(dtoStep);
		}
		
		
		
        //DocApproval entity = docRepository.save(dto.toEntity());
        return dto.getSn();
    }
	
	@Transactional
    public Long saveresult(final ApprovalrsultForm params) {
		
	
		long tL = Long.parseLong(params.getSn().trim());
		DocApproval dto = docRepository.findBySnLike(tL);
		if(params.getSelectbtn().equals("2")) {
			dto.setStatus("2");
		}else if(params.getSelectbtn().equals("1")) {
			if(dto.getPosition().equals(dto.getCount())) {
				dto.setStatus("1");
			}
		}
		String tempPo = dto.getPosition();
		int NtempPo = Integer.parseInt(tempPo) +1;
		dto.setPosition(Integer.toString(NtempPo));
		for(int i=0;i<dto.getDocAppList().size();i++) {
			DocApprovalStep tempDAS = dto.getDocAppList().get(i);
			if(tempDAS.getApprover().equals(params.getWriter())) {
				tempDAS.setInfotxt(params.getInfotxt());
				tempDAS.setResult(params.getSelectbtn());
			}
		}
		
		
		docRepository.save(dto);
		//dto.setDocAppList(null);
				
		
		
        //DocApproval entity = docRepository.save(dto.toEntity());
        return dto.getSn();
    }
	 /**
     * 서류 결제 OUTBOX
     */
    public List<DocApprovalOutMapping> findDocOUTBOX(String writer,String status) {
        List<DocApprovalOutMapping> list = docRepository.findDistinctByWriterAndStatus(writer,status);
        System.out.println(list.size());
		/*
		 * Set<DocApprovalOutMapping> set = new HashSet<DocApprovalOutMapping>(list);
		 * List<DocApprovalOutMapping> newList =new
		 * ArrayList<DocApprovalOutMapping>(set);
		 */


        return list;
    }
    public List<ApprovalInForm> findDocINBOX(String approver,String result) {
        List<DocApprovalStep> list = docStepRepository.findByApproverAndResult(approver, result);
        List<ApprovalInForm> returnList = new ArrayList<ApprovalInForm>();
        for(int i=0;i<list.size();i++) {
        	DocApprovalStep temp = list.get(i);
        	DocApproval DS = temp.getDa();
        	if(DS.getStatus().equals("0") && DS.getPosition().equals(Integer.toString(temp.getOrdernum()))) {
        		ApprovalInForm AIF = new ApprovalInForm();
        		AIF.setSn(DS.getSn());
        		AIF.setPosition(DS.getPosition());
        		AIF.setContents(DS.getContents());
        		AIF.setCount(DS.getCount());
        		AIF.setKind(DS.getKind());
        		AIF.setStatus(DS.getStatus());
        		AIF.setTitle(DS.getTitle());
        		AIF.setWriter(DS.getWriter());
        		returnList.add(AIF);
        	}
        }
        return returnList;
    }

    public List<ApprovalInForm> findDocARCHIVE(String approver,String result) {
        List<DocApprovalStep> list = docStepRepository.findByApprover(approver);
        List<ApprovalInForm> returnList = new ArrayList<ApprovalInForm>();
        for(int i=0;i<list.size();i++) {
        	DocApprovalStep temp = list.get(i);
        	DocApproval DS = temp.getDa();
        	if(!DS.getStatus().equals("0")) {
        		ApprovalInForm AIF = new ApprovalInForm();
        		AIF.setSn(DS.getSn());
        		AIF.setPosition(DS.getPosition());
        		AIF.setContents(DS.getContents());
        		AIF.setCount(DS.getCount());
        		AIF.setKind(DS.getKind());
        		AIF.setStatus(DS.getStatus());
        		AIF.setTitle(DS.getTitle());
        		AIF.setWriter(DS.getWriter());
        		returnList.add(AIF);
        	}
        }
        List<DocApprovalOutMapping> tempDOC =  docRepository.findByWriter(approver);
        for(int i=0;i<tempDOC.size();i++) {
        	if(!tempDOC.get(i).getStatus().equals("0")) {
        		ApprovalInForm AIF = new ApprovalInForm();
        		AIF.setSn(tempDOC.get(i).getSn());
        		AIF.setPosition(tempDOC.get(i).getPosition());
        		AIF.setContents(tempDOC.get(i).getContents());
        		AIF.setCount(tempDOC.get(i).getCount());
        		AIF.setKind(tempDOC.get(i).getKind());
        		AIF.setStatus(tempDOC.get(i).getStatus());
        		AIF.setTitle(tempDOC.get(i).getTitle());
        		AIF.setWriter(tempDOC.get(i).getWriter());
        		returnList.add(AIF);
        	}
        }
        
        
        return returnList;
    }
    public DocApprovalOutMapping findDocSn(String sn) {
    	long tL = Long.parseLong(sn.trim());
    	DocApprovalOutMapping form = docRepository.findBySn(tL);
        return form;
    }
    public List<ApprovalStepForm> findAppverSn(String sn) {
    	long tL = Long.parseLong(sn.trim());
    	DocApproval temp = docRepository.findBySnLike(tL);
    	List<ApprovalStepForm> ASF = new ArrayList<ApprovalStepForm>();
    	List<DocApprovalStep> list =  temp.getDocAppList();
		for(int i=0;i<list.size();i++) {
			DocApprovalStep DAS = list.get(i);
			ApprovalStepForm ASF_TEMP = new ApprovalStepForm();
			ASF_TEMP.setApprover(DAS.getApprover());
			ASF_TEMP.setId(DAS.getId());
			ASF_TEMP.setInfotxt(DAS.getInfotxt());
			ASF_TEMP.setOrdernum(DAS.getOrdernum());
			ASF_TEMP.setResult(DAS.getResult());
			ASF.add(ASF_TEMP);
		}
    	/*
		 * DocApprovalOutMapping form = findDocSn(sn); form.get long tL =
		 * Long.parseLong(sn.trim()); DocApprovalOutMapping form =
		 * docRepository.findBySn(tL);
		 */
        return ASF;
    }
	
}