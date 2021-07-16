package com.insta.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insta.Util;

import lombok.extern.slf4j.Slf4j;

// 간단한 업무, 알림

@Controller
@Slf4j
public class MpaUsrMemeberController {
	
	@RequestMapping("/mpaUsr/member/join")
	public String showJoin()
	{
		
		return "/mpaUsr/member/join";
	}
	
	@RequestMapping("/mpaUsr/member/doJoin")
	@ResponseBody
	public Map doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email)
	{
		return Util.mapOf("loginId", loginId, "loginPw", loginPw, "name", name, "nickname", nickname, "cellphoneNo", cellphoneNo, "email", email);
//		return "/mpaUsr/member/doJoin";
	}
	
}
