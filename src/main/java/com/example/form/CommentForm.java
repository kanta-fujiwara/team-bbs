package com.example.form;

/**
 * コメント用フォーム.
 * 
 * @author ren.akase
 *
 */
public class CommentForm {

	/** 名前*/
	private String name;
	/** コメント*/
	private  String content;
	/** 記事ID*/
	private String articleId;;

	
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}
}
