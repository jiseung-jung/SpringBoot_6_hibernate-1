package com.jiseung.sb6.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("memberDelete")
	public String setMemberDelete(HttpSession session) throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberService.setMemberDelete(memberVO);
		session.invalidate();
		return "redirect:../";
		
	}
	
	
	
	@GetMapping("memberLogin")
	public void getMemberLogin() throws Exception{
	}
	
	
	@PostMapping("memberLogin")
	public String getMemberLogin(MemberVO memberVO, HttpSession session) throws Exception{
	      memberVO = memberService.getMemberLogin(memberVO);
	      
	      if(memberVO !=null) {
	         session.setAttribute("member", memberVO);
	      }
	      return "redirect:../";

	}
	

	
	@GetMapping("memberLogout")
	public String getmemberLogout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	
	
	@GetMapping("memberJoin")
	public ModelAndView setInsert(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/member/memberJoin");
		return mv;
	}
	
	@PostMapping("memberJoin")
	public void setInsert(MemberVO memberVO, MultipartFile file) throws Exception{
		memberService.save(memberVO);
	}
	
	
	@GetMapping("memberPage")
	public void getMemberPage() throws Exception {
	      
	}



}
