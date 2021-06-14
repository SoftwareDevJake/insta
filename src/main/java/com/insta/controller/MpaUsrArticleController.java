package com.insta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insta.Util;
import com.insta.DTO.Board;
import com.insta.DTO.ResultData;
import com.insta.service.ArticleService;

// 간단한 업무, 알림

@Controller
public class MpaUsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	// 5:30 메인화면 바꾸기, 공통 레이아웃
	
	@RequestMapping("/mpaUsr/article/list")
	public String showList(Integer boardId)
	{
		Board board = articleService.getBoardById(boardId);
		
		if(Util.isEmpty(board))
		{
			return "Board does not exist";
		}
		
		return "/mpaUsr/article/list";
	}
	
//	@RequestMapping("/mpaUsr/article/detail")
//	@ResponseBody
//	public Article showDetail(int aid)
//	{
//		return articleService.showDetail(aid);
//	}
	
	@RequestMapping("/mpaUsr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body)
	{
		if(Util.isEmpty(title))
		{
			return new ResultData("F-1", "please write down the title");
		}
		
		if(Util.isEmpty(body))
		{
			return new ResultData("F-2", "please write down the body");
		}
		
		return articleService.doAdd(title, body);
		
	}
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer aid)
	{
		if(Util.isEmpty(aid))
		{
			return new ResultData("F-1", "please write down the aid");
		}
		
		return articleService.doDelete(aid);
	}
	
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer aid, String title, String body)
	{
		if(Util.isEmpty(aid))
		{
			return new ResultData("F-1", "please write down the aid");
		}
		
		if(Util.isEmpty(title))
		{
			return new ResultData("F-2", "please write down the title");
		}
		
		if(Util.isEmpty(body))
		{
			return new ResultData("F-3", "please write down the body");
		}
		
		return articleService.doModify(aid, title, body);
		
	}
	
	
}
