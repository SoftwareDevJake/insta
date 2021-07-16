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
			return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
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
	
	@RequestMapping("/mpaUsr/article/detail")
	public String showDetail(HttpServletRequest req, @RequestParam(defaultValue = "1") Integer aid)
	{
		Article article = articleService.getForPrintArticleById(aid);
		if(article == null)
		{
			return Util.msgAndBack(req, aid + "번 게시물이 존재하지 않습니다.");
		}
		
		Board board = articleService.getBoardById(article.getBoardId());
		
		req.setAttribute("article", article);
		
		log.debug("article : " + article);
		
		req.setAttribute("board", board);
		
		return "/mpaUsr/article/detail";
	}
	
	@RequestMapping("/mpaUsr/article/write")
	public String showWrite(HttpServletRequest req, @RequestParam(defaultValue = "1") Integer boardId)
	{
		Board board = articleService.getBoardById(boardId);
		
		if(Util.isEmpty(board))
		{
			return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		req.setAttribute("board", board);
		
		return "/mpaUsr/article/write";
	}
	
	@RequestMapping("/mpaUsr/article/doAdd")
	public String doAdd(HttpServletRequest req, int boardId,  String title, String body)
	{
		if(Util.isEmpty(title))
		{
			return Util.msgAndBack(req, "제목을 입력해주세요.");
		}
		
		if(Util.isEmpty(body))
		{
			return Util.msgAndBack(req, "내용을 입력해주세요.");
		}
		
		int memberId = 3; // 임시 갚
		
		ResultData writeArticleRd = articleService.doAdd(boardId, memberId, title, body);
		
		if(writeArticleRd.isFail())
		{
			return Util.msgAndBack(req, writeArticleRd.getMsg());
		}
		
		String replaceUrl = "detail?aid=" + writeArticleRd.getBody().get("aid");
		
		return Util.msgAndReplace(req, writeArticleRd.getMsg(), replaceUrl);
	}
	
	@RequestMapping("/mpaUsr/article/doDelete")
	public String doDelete(HttpServletRequest req, Integer aid)
	{
		if(Util.isEmpty(aid))
		{
			return Util.msgAndBack(req, "aid를 입력해 주세요");
		}
		
		ResultData rd = articleService.doDelete(aid);
		if(rd.isFail())
		{
			return Util.msgAndBack(req, rd.getMsg());
		}
		
		String redirectUrl = "../article/list?boardId=" + rd.getBody().get("boardId");
		
		return Util.msgAndReplace(req, rd.getMsg(), redirectUrl);
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
