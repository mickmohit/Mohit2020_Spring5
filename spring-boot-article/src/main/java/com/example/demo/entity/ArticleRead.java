package com.example.demo.entity;

public class ArticleRead {

	private String articleId;
	private TimeToRead timeread;
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public TimeToRead getTimeread() {
		return timeread;
	}
	public void setTimeread(TimeToRead timeread) {
		this.timeread = timeread;
	}
	@Override
	public String toString() {
		return "ArticleRead [articleId=" + articleId + ", timeread=" + timeread + "]";
	}
		
	
}
