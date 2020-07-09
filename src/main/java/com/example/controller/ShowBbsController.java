package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/")
public class ShowBbsController {

	@Autowired
	private ArticleRepository articleRepository;

	@ModelAttribute
	public ArticleForm setupArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setupCommentForm() {
		return new CommentForm();
	}

	/**
	 * 記事一覧画面表示.
	 *
	 * @return 記事一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAllWithComments();
		model.addAttribute("articleList", articleList);

		return "index";
	}
}
