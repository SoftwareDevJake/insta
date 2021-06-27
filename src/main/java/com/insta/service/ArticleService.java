package com.insta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.DTO.Article;
import com.insta.DTO.Board;
import com.insta.DTO.ResultData;
import com.insta.Dao.ArticleDao;

// 좀더 디테일한 판단, 알림

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
//	public ArrayList<Article> showList()
//	{
//		return articleDao.showList();
//	}
//	
//	public Article showDetail(int aid)
//	{
//		return articleDao.showDetail(aid);
//	}
//	
	public ResultData doAdd(String title, String body)
	{
		int boardId = 3; //
		int memberId = 3; //
		articleDao.doAdd(boardId, memberId, title, body);
		int aid = articleDao.getLastInsertId(); //b 
		
		return new ResultData("S-1", "Added", "aid", aid);
	}	
	
	public ResultData doDelete(Integer aid)
	{
		Article article = articleDao.getArticleById(aid);
		if(isEmpty(article))
		{
			return new ResultData("F-1", aid + " doesnt exist");
		}
		articleDao.FindAndDeleteAid(aid);
		return new ResultData("S-1", aid + " is deleted", "aid", aid, "boardId", article.getBoardId());
		
	}
	
	private boolean isEmpty(Article article) {
		if(article == null)
		{
			return true;
		}
		else if(article.isDelStatus())
		{
			return true;
		}
		
		return false;
	}

	public ResultData doModify(Integer aid, String title, String body)
	{
		Article article = articleDao.getArticleById(aid);
		
		if(article == null)
		{
			return new ResultData("F-4", aid + " doesn't exist");
		}
		articleDao.doModify(aid, title, body);
		article = articleDao.getArticleById(aid);
		
		return new ResultData("S-1", aid + " is modified", "article", article);
	}

	public Board getBoardById(Integer aid) {
		return articleDao.getBoardById(aid);
	}

	 public int getArticlesTotalCount(Integer boardId, String searchKeywordType, String searchKeyword) {
		if(searchKeyword != null && searchKeyword.length() == 0)
		{
			searchKeyword = null;
		}
		return articleDao.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);
	}

	public List<Article> getForPrintArticles(Integer boardId, String searchKeywordType, String searchKeyword, int itemsCountInAPage, int page) {
		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		
		if(searchKeyword != null && searchKeyword.length() == 0)
		{
			searchKeyword = null;
		}
		
		return articleDao.getForPrintArticles(boardId, searchKeywordType, searchKeyword, limitFrom, limitTake);
	}
}
 