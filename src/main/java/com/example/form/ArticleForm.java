package com.example.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * /insertArticle で受け取るフォームデータ.
 *
 * @author kanta.fujiwara
 */
public class ArticleForm {

	/** 名前 */
	@NotBlank(message = "投稿者名を入力してください")
	@Length(max = 50, message = "投稿者名は50文字以内で入力してください")
	private String name;

	/** コメント */
	@NotBlank(message = "投稿内容を入力してください")
	private String content;

	public ArticleForm() {
	}

	public ArticleForm(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
