package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

@Controller
public class InsertArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@ModelAttribute
	public ArticleForm setupArticleForm() {
		return new ArticleForm();
	}

	/**
	 * 記事を挿入する.
	 *
	 * @param form   リクエストで受け取ったフォームデータ
	 * @param result formへのバインディングの結果
	 * @param model  indexに渡すモデル
	 * @return メインページへリダイレクト
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(@Validated ArticleForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "forward:/";
		}

		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);

		return "redirect:/";
	}
}
