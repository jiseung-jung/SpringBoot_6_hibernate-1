package com.jiseung.sb6.member;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	public void setMemberDelete(MemberVO memberVO) throws Exception{
		memberRepository.deleteById(memberVO.getId());
	}
	
	
	public void save(MemberVO memberVO) throws Exception{
		memberRepository.save(memberVO);
	}
	
	public MemberVO getMemberLogin(MemberVO memberVO) throws Exception{
		
	      memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
		
//		Optional<MemberVO> vo = memberRepository.findById(memberVO.getId());
		
		
//		if(vo.isPresent()) {
//			System.out.println("ID는 있는 사용자");
//			 MemberVO memberVOCheck = vo.get();

			
//			if(memberVO.getPw().equals(memberVOCheck.getPw())) {
//				memberVO = memberVOCheck;
//			}else {
//				memberVO = null;
//			}
				
//		}else {
//			System.out.println("없는 사용자");
//			memberVO = null;
//		}
		
		return memberVO;
	}

}
