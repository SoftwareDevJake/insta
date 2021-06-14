package com.insta.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.insta.DTO.Article;
import com.insta.DTO.Board;

// 기능 구현

@Mapper
public interface ArticleDao {

	void doAdd(@Param("boardId") int boardId,@Param("memberId")  int memberId, @Param("title") String title, @Param("body") String body);
	boolean doModify(@Param("aid") Integer aid, @Param("title") String title, @Param("body") String body);
	boolean FindAndDeleteAid(@Param("aid") Integer aid);
	Article getArticleById(@Param("aid") Integer aid);
	int getLastInsertId();
	Board getBoardById(@Param("aid") Integer aid);
}
