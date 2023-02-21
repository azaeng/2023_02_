package com.KoreaIT.java.AM.Article;

public class Article extends dto {
	public String titles;
	public String contents;
	public int view;
	
	public Article(int id, String date, String titles, String contents) {
		this(id, date, titles, contents, 0);
	}
	
	public Article(int id, String date, String titles, String contents, int view) {
		this.id = id;
		this.date = date;
		this.titles = titles;
		this.contents = contents;
		this.view = view;
	}
	
	public void increaseView() {
		this.view++;
	}
}
