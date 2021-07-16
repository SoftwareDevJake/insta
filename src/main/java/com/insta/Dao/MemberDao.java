package com.insta.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.insta.DTO.Member;

@Mapper
public interface MemberDao {

	Member getMemberByLoginId(@Param("loginId") String loginId);
	Member getMemberById(@Param("id") int id);
	void join(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name, @Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);
	int getLastInsertId();
}
