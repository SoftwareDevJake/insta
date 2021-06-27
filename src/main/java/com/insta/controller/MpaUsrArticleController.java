package com.insta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insta.Util;
import com.insta.DTO.Article;
import com.insta.DTO.Board;
import com.insta.DTO.ResultData;
import com.insta.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

// 간단한 업무, 알림

@Controller
@Slf4j
public class MpaUsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	// 5:30 메인화면 바꾸기, 공통 레이아웃
	
	private String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "common/redirect";
	}
		
	private String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("msg", msg);
		req.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}
	
	@RequestMapping("/mpaUsr/article/list")
	public String showList(HttpServletRequest req, @RequestParam(defaultValue = "1") Integer boardId, String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page)
	{
		Board board = articleService.getBoardById(boardId);
		
		if(Util.isEmpty(searchKeywordType))
		{
			searchKeywordType = "titleAndBody";
		}
		
		log.debug("searchKeyword : " + searchKeyword);
		
		if(Util.isEmpty(board))
		{
			return msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		req.setAttribute("board", board);
		
		int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);
		
		req.setAttribute("totalItemsCount", totalItemsCount);
		
		int itemsCountInAPage = 20;
		int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsCountInAPage);
		
		req.setAttribute("totalPage", totalPage);
		
		List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword, itemsCountInAPage, page);
		
		req.setAttribute("articles", articles);
		req.setAttribute("page", page);
		
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
	public String doDelete(HttpServletRequest req, Integer aid)
	{
		if(Util.isEmpty(aid))
		{
			return msgAndBack(req, "aid를 입력해 주세요");
		}
		
		ResultData rd = articleService.doDelete(aid);
		if(rd.isFail())
		{
			return msgAndBack(req, rd.getMsg());
		}
		
		String redirectUrl = "../article/list?boardId=" + rd.getBody().get("boardId");
		
		return msgAndReplace(req, rd.getMsg(), redirectUrl);
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
