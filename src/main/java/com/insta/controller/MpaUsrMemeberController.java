package com.insta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insta.Util;
import com.insta.DTO.Member;
import com.insta.DTO.ResultData;
import com.insta.service.MemberService;

import lombok.extern.slf4j.Slf4j;

// 간단한 업무, 알림

@Controller
@Slf4j
public class MpaUsrMemeberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/mpaUsr/member/join")
	public String showJoin()
	{
		
		return "/mpaUsr/member/join";
	}
	
	@RequestMapping("/mpaUsr/member/doJoin")
	public String doJoin(HttpServletRequest req,String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email)
	{
		Member oldMember = memberService.getMemberByLoginId(loginId);
		
		if(oldMember != null)
		{
			return Util.msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인 아이디 입니다.");
		}
		
		ResultData joinRd = memberService.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		if(joinRd.isFail())
		{
			return Util.msgAndBack(req, joinRd.getMsg());
		}
		
		return Util.msgAndReplace(req, joinRd.getMsg(), "/");
	}
	
}
