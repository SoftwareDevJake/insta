package com.insta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.DTO.Member;
import com.insta.DTO.ResultData;
import com.insta.Dao.MemberDao;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	
	public Member getMemberByLoginId(String loginId) {
		
		return memberDao.getMemberByLoginId(loginId);
	}

	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		memberDao.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		int aid = memberDao.getLastInsertId();
		
		return new ResultData("S-1", "회원가입이 완료되었습니다.", "loginId", loginId);
	}

	public Member getMemberById(int id) {
		
		return memberDao.getMemberById(id);
	}
	
}
