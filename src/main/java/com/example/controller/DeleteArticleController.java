package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.ArticleRepository;

@Controller
public class DeleteArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * 記事を削除する.
	 *
	 * @param articleId 削除する記事のID
	 * @return メインページへリダイレクト
	 */
	@RequestMapping("/deleteArticle")
	synchronized public String deleteArticle(Integer articleId) {
		articleRepository.deleteById(articleId);
		return "redirect:/";
	}
}