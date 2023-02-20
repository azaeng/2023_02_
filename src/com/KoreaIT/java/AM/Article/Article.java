package com.KoreaIT.java.AM.Article;

public class Article {
	public int id;
	public String titles;
	public String contents;
	public String date;
	public int view;
	
	public Article(int id, String date, String titles, String contents) {
		this(id, date, titles, contents, 0);
	}
	
	public Article(int id, String titles, String contents, String date, int view) {
		this.id = id;
		this.titles = titles;
		this.contents = contents;
		this.date = date;
		this.view = view;
	}
	
	public void increaseView() {
		this.view++;
	}
}
