package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.Article.Member;

public abstract class Controller {
	
	public static Member LoginedMember = null;
	
	public boolean isLogined() {
		return LoginedMember != null;
	}
	
	public abstract void doAction(String cmd, String actionMethodName);
	
	public abstract void makeTestData();
}