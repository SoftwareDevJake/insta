package com.insta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

	private int id;
	private String currentDate;
	private int boardId;
	private int memberId;
	private String title;
	private String body;
	private String updateDate;
	private boolean blindStatus;
	private String blindDate;
	private boolean delStatus;
	private String delDate;
	private int likeCount;
	private int dislikeCount;
	private int hitCount;
	private int repliesCount;

}
