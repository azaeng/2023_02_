package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Article.Article;
import com.KoreaIT.java.AM.Util.Util;

public class App {
	
	public static List<Article> articles;
	
	static {
		articles = new ArrayList<>();
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");
				
		makeTestData();

		Scanner sc = new Scanner(System.in);
		
		int articlesSize = 3;
		
		while(true) {
			System.out.printf("명령어	: ");
			String cmd = sc.nextLine().trim();
			if(cmd.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다");
				}
				else {
					System.out.println("번호	/	제목	/	조회");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.println(article.id + "	/	" + article.titles.substring(0, 3) + "	/	" + article.view);
					}
				}
			}
			
			else if(cmd.equals("article write")) {
				int id = articlesSize + 1;
				System.out.printf("제목	: ");
				String titles = sc.nextLine();
				System.out.printf("내용	: ");
				String contents = sc.nextLine();
				String date = Util.NowDate();
				int view = 0;
				
				Article article = new Article(id, titles, contents, date, view);
				articles.add(article);
				
				System.out.println(id + "번글이 생성되었습니다");
				articlesSize++;
			}
			else if(cmd.startsWith("article detail")) {
				String[] num = cmd.split(" ");
				int i = Integer.parseInt(num[2]) - 1;
				if(isNull(cmd)) {
					if(articles.size() == 0 || articles.size() < i + 1) {
						System.out.println((i + 1) + "번 게시물은 존재하지 않습니다");					
					}
					else {
						Article article = articles.get(i);
							
						System.out.println("날짜	: " + article.date);
						System.out.println("번호	: " + article.id);
						System.out.println("제목	: " + article.titles);
						System.out.println("내용	: " + article.contents);
						article.view++;
					}
				}
				else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}
			else if(cmd.startsWith("article delete")) {
				String[] num = cmd.split(" ");
				int i = Integer.parseInt(num[2]) - 1;
				if(isNull(cmd)) {
					if(articles.size() == 0 || articles.size() < i + 1) {
						System.out.println((i + 1) + "번 게시물은 존재하지 않습니다");					
					}
					else {
						articles.remove(i);
						for (int j = articles.size() - 1; j >= 0; j--) {
							Article article = articles.get(j);
							article.id = j + 1;
						}
						System.out.println((i + 1) + "번 게시물이 삭제되었습니다 ");
					}
				}
				else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}
			else if(cmd.startsWith("article modify")) {
				String[] num = cmd.split(" ");
				int i = Integer.parseInt(num[2]) - 1;
				if(isNull(cmd)) {
					if(articles.size() == 0 || articles.size() < i + 1) {
					System.out.println((i + 1) + "번 게시물은 존재하지 않습니다");					
					}
					else {
						Article article = articles.get(i);
						System.out.printf("제목	: ");
						article.titles = sc.nextLine();
						System.out.printf("내용	: ");
						article.contents = sc.nextLine();
						System.out.println((i + 1) + "번 게시물이 수정되었습니다 ");
					}
				}
				else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}
			else if(cmd.equals("")) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			else if(cmd.equals("system exit")) {
				break;
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
	
	static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");
		for(int i = 1; i <= 3; ++i) {
			
			articles.add(new Article(i, "test_titles_" + i, "test_contents_" + i, Util.NowDate(), 0));
		}
	}
	
	private static boolean isNull(String str){
		String[] num = str.split(" ");
		return (num[2] != null && num[2].matches("[0-9.]+")) && (num.length == 3);
	}
}