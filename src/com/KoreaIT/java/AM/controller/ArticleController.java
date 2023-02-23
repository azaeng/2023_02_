package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Article.Article;
import com.KoreaIT.java.AM.Util.Util;

public class ArticleController extends Controller {
	
	private List<Article> articles;
	private Scanner sc;
	private String cmd;
	private String actionMethodName;
	
	public ArticleController(Scanner sc) {
		this.articles = new ArrayList<>();
		this.sc = sc;
	}
	
	int Article_Id_Size = 3;
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "list":
			dolist();
			break;
		case "write":
			if (isLogined() == false) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			dowrite();
			break;
		case "detail":
			dodetail();
			break;
		case "delete":
			if (isLogined() == false) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			dodelete();
			break;
		case "modify":
			if (isLogined() == false) {
				System.out.println("로그인 후 이용해주세요");
				return;
			}
			domodify();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 게시물을 생성합니다");
		for(int i = 1; i <= 3; ++i) {
			articles.add(new Article(i, Util.NowDate(), i, "제목" + i, "내용" + i, 0));
		}
	}
	
	private void dolist() {
		if(articles.size() == 0) {
			System.out.println("게시글이 없습니다");
		}
		else {
			System.out.println("  번호	/  제____목   /  조회   /   작성자");
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article article = articles.get(i);
				System.out.printf("%4d	/ %7s   / %4d   / %4d\n", article.id, article.titles, article.view, article.memberId);
			}
		}
	}
	
	private void dowrite() {
		int id = Article_Id_Size + 1;
		String date = Util.NowDate();
		System.out.printf("제목	: ");
		String titles = sc.nextLine();
		System.out.printf("내용	: ");
		String contents = sc.nextLine();
		
		Article article = new Article(id, date, LoginedMember.id, titles, contents);
		articles.add(article);
		
		System.out.println(id + "번글이 생성되었습니다");
		Article_Id_Size++;
	}
	
	private void dodetail() {
		String[] num = cmd.split(" ");
		if(num.length == 3) {
			int id = Integer.parseInt(num[2]);
			Article foundArticle = getArticleById(id);
			while(true) {
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				foundArticle.increaseView();
				
				System.out.println("번호	: " + foundArticle.id);
				System.out.println("날짜	: " + foundArticle.date);
				System.out.println("작성자	: " + foundArticle.memberId);
				System.out.println("제목	: " + foundArticle.titles);
				System.out.println("내용	: " + foundArticle.contents);
				System.out.println("조회	: " + foundArticle.view);
				
				break;
			}
			return;
		}
		else {
			System.out.println("명령어를 확인해주세요");
					}
	}
	
	private void dodelete() {
		String[] num = cmd.split(" ");
		if(num.length == 3) {
			int id = Integer.parseInt(num[2]);
			int foundIndex = getArticleIndexById(id);
			while(true) {
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				articles.remove(foundIndex);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);
				break;
			}
		}
		else {
			System.out.println("명령어를 확인해주세요");
		}
	}
	
	private void domodify() {
		String[] num = cmd.split(" ");
		if(num.length == 3) {
			int id = Integer.parseInt(num[2]);
			Article foundArticle = getArticleById(id);
			while(true) {
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.printf("제목 : ");
				String titles = sc.nextLine();
				System.out.printf("내용 : ");
				String contents = sc.nextLine();

				foundArticle.titles = titles;
				foundArticle.contents = contents;

				System.out.printf("%d번 글을 수정했습니다.\n", id);
				break;
			}
		}
		else {
			System.out.println("명령어를 확인해주세요");
		}		
	}
	
	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
}