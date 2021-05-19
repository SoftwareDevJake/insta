package com.insta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		int aid = 1; //
		
		return new ResultData("S-1", "Added", "aid", aid);
	}	
	
	public ResultData doDelete(Integer aid)
	{
		if(articleDao.FindAndDeleteAid(aid))
		{
			return new ResultData("S-1", aid + " is deleted", "aid", aid);
		}
		
		return new ResultData("F-2", aid + " article doesn't exist");
	}
	
//	public ResultData doModify(Integer aid, String title, String body)
//	{
//		Article article = articleDao.doModify(aid, title, body);
//		
//		if(article == null)
//		{
//			return new ResultData("F-4", aid + " doesn't exist");
//		}
//		return new ResultData("S-1", aid + " is modified", "article", article);
//	}
}
 