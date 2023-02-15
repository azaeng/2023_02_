package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int articlesSize = 0;
		
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			
			System.out.printf("명령어	: ");
			String cmd = sc.nextLine();
			if(cmd.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다");					
				}
				else {
					System.out.println("번호	/	제목");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.println(article.id + "	/	" + article.titles);
					}
				}
			}
			
			else if(cmd.equals("article write")) {
				int id = articlesSize + 1;
				System.out.printf("제목	: ");
				String titles = sc.nextLine();
				System.out.printf("내용	: ");
				String contents = sc.nextLine();
				
				Article article = new Article(id, titles, contents);
				articles.add(article);
				
				System.out.println(id + "번글이 생성되었습니다");
				articlesSize++;
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
}

class Article {
	int id;
	String titles;
	String contents;

	Article(int id, String titles, String contents) {
		this.id = id;
		this.titles = titles;
		this.contents = contents;
	}
}