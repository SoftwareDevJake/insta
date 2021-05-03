package com.insta.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insta.Util;
import com.insta.DTO.Article;
import com.insta.DTO.ResultData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class MpaUsrArticleController {

	Util util = new Util();
	
	private ArrayList<Article> articles;
	private int articleLastId = 0;
	private String updateDate;
	private String today = util.date();
	
	public MpaUsrArticleController()
	{
		articles = new ArrayList<>();
		updateDate = util.date();
		
		articles.add(new Article(++articleLastId, today, "title 1", "body 1", updateDate));
		articles.add(new Article(++articleLastId, today, "title 2", "body 2", updateDate));
		articles.add(new Article(++articleLastId, today, "title 3", "body 3", updateDate));
		
	}
	
	@RequestMapping("/mpaUsr/article/list")
	@ResponseBody
	public ArrayList<Article> showList()
	{
		return articles;
	}
	
	@RequestMapping("/mpaUsr/article/detail")
	@ResponseBody
	public Article showDetail(int aid)
	{
		return articles.get(aid - 1);
	}
	
	@RequestMapping("/mpaUsr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body)
	{
		today = util.date();
		updateDate = util.date();
		articles.add(new Article(++articleLastId, today, title, body, updateDate));
		
	
		return new ResultData("S-1", articleLastId + " article is uploaded", "article", articles.get(articleLastId - 1));
	}
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int aid)
	{
		
		if(FindAndDeleteAid(aid))
		{			
			return new ResultData("S-1", articleLastId + " article is deleted");
		}
		
		return new ResultData("F-1", articleLastId + " article doesn't exist");
		
	}
	
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(int aid, String title, String body)
	{
	
		for(Article article : articles)
		{
			if(aid == article.getId())
			{
				article.setTitle(title);
				article.setBody(body);
				updateDate = util.date();
				article.setUpdateDate(updateDate);

				return new ResultData("S-1", article.getId() + " is modified", "article", articles.get(aid-1));
			}
		}
		
		return new ResultData("F-1", aid + " doesn't exist");
		
	}
	
	public boolean FindAndDeleteAid(int aid)
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
