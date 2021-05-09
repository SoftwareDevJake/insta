package com.insta.Dao;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.insta.Util;
import com.insta.DTO.Article;

// 기능 구현

@Component
public class ArticleDao {
	private Util util = new Util();
	
	private ArrayList<Article> articles;
	private int articleLastId = 0;
	private String updateDate;
	private String today = util.date();
	
	public ArticleDao()
	{
		articles = new ArrayList<>();
		updateDate = util.date();
		
		articles.add(new Article(++articleLastId, today, "title 1", "body 1", updateDate));
		articles.add(new Article(++articleLastId, today, "title 2", "body 2", updateDate));
		articles.add(new Article(++articleLastId, today, "title 3", "body 3", updateDate));
		
	}

	public ArrayList<Article> showList() {
		return articles;
	}

	public Article showDetail(int aid) {
		return articles.get(aid - 1);
	}
	
	public Article doAdd(String title, String body)
	{
		today = util.date();
		updateDate = util.date();
		articles.add(new Article(++articleLastId, today, title, body, updateDate));
		return articles.get(articleLastId - 1);
	}
	
	public Article doModify(Integer aid, String title, String body)
	{
		for(Article article : articles)
		{
			if(aid == article.getId())
			{
				article.setTitle(title);
				article.setBody(body);
				updateDate = util.date();
				article.setUpdateDate(updateDate);

				return article;
			}
		}
		
		return null;	
	}
	
	public boolean FindAndDeleteAid(Integer aid)
	{
		for(Article article : articles)
		{
			if(aid == article.getId())
			{
				articles.remove(article);
				return true; 
			}
		}
		return false;
	}
}
