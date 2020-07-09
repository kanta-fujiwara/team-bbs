package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;

@RequestMapping("/")
public class InsertCommentController {
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * コメント作成.
	 * 
	 * @param commentForm:コメント入力内容
	 * @return　記事一覧画面
	 */
	@RequestMapping("/insertCom")
	public String insertComment(CommentForm commentForm) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		commentRepository.insert(comment);
		
		return "redirect:/bbs/index";
		
	}
}
