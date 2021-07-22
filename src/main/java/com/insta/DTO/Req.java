package com.insta.DTO;

public class Req {
	private Member loginedMember;
	
	public Req(Member loginedMember)
	{
		this.loginedMember = loginedMember;
	}
	
	public boolean isLogined()
	{
		return loginedMember != null;
	}
	
	public boolean isNotLogined()
	{
		return isLogined() == false;
	}
	
	public int getLoginedMemberId()
	{
		if(loginedMember == null)
		{
			return 0;
		}
		
		return loginedMember.getId();
		
	}
	
	public Member getLoginedMember()
	{
		return loginedMember;
	}
	
	public String getLoginedMemberNickname()
	{
		if(isNotLogined())
		{
			return "";
		}
		
		return loginedMember.getNickname();
	}
}
