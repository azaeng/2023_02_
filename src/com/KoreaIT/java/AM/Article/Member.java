package com.KoreaIT.java.AM.Article;

public class Member extends dto {
	public String Login_Id;
	public String Login_Pw;
	public String name;

	public Member(int id, String date, String Login_Id, String Login_Pw, String name) {
		this.id = id;
		this.date = date;
		this.Login_Id = Login_Id;
		this.Login_Pw = Login_Pw;
		this.name = name;
	}

}