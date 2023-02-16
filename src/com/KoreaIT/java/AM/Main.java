package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int articlesSize = 0;
		
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			
			System.out.printf("명령어	: ");
			String cmd = sc.nextLine().trim();
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
				
				Date now = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = formatter.format(now);
				
				Article article = new Article(id, titles, contents, date);
				articles.add(article);
				
				System.out.println(id + "번글이 생성되었습니다");
				articlesSize++;
			}
			else if(cmd.startsWith("article detail")) {
				String[] num = cmd.split(" ");
				if(num.length == 3) {
					if(isNumeric(num[2])) {
						int i = Integer.parseInt(num[2]) - 1;
						
						if(articles.size() == 0 || articles.size() < i) {
							System.out.println((i + 1) + "번 게시물은 존재하지 않습니다");					
						}
						else {
							Article article = articles.get(i);
							
							System.out.println("날짜	: " + article.date);
							System.out.println("번호	: " + article.id);
							System.out.println("제목	: " + article.titles);
							System.out.println("내용	: " + article.contents);
						}
					}
					else {
						System.out.println("존재하지 않는 명령어입니다");
					}
				}
				else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			else if(cmd.startsWith("article delete")) {
				String[] num = cmd.split(" ");
				if(num.length == 3) {
					if(isNumeric(num[2])) {
						int i = Integer.parseInt(num[2]) - 1;
						
						if(articles.size() == 0 || articles.size() < i) {
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
				else {
					System.out.println("존재하지 않는 명령어입니다");
				}
			}
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
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
	private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}

class Article {
	int id;
	String titles;
	String contents;
	String date;
	
	Article(int id, String titles, String contents, String date) {
		this.id = id;
		this.titles = titles;
		this.contents = contents;
		this.date = date;
	}
}