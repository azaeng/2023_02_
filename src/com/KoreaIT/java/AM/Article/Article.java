package com.KoreaIT.java.AM.Article;

public class Article extends dto {
	public String titles;
	public String contents;
	public int view;
	public int memberId;
	
	public Article(int id, String date, int memberId, String titles, String contents) {
		this(id, date, memberId, titles, contents, 0);
	}
	
	public Article(int id, String date, int memberId, String titles, String contents, int view) {
		this.id = id;
		this.date = date;
		this.memberId = memberId;
		this.titles = titles;
		this.contents = contents;
		this.view = view;
	}
	
	public void increaseView() {
		this.view++;
	}
}
