package com.insta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private int id;
	private String currentDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String cellphoneNo;
	private String email;
	private boolean delStatus;
	private String delDate;
}