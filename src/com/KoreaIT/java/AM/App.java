package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Article.Article;
import com.KoreaIT.java.AM.Article.Member;
import com.KoreaIT.java.AM.Util.Util;

public class App {
	
	public static List<Article> articles;
	public static List<Member> members;
	
	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");
				
		makeTestData();

		Scanner sc = new Scanner(System.in);
		
		int Id_Size = 3;
		int membersSize = 0;
		
		while(true) {
			System.out.printf("명령어	: ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			if (cmd.equals("system exit")) {
				break;
			}
			
			if(cmd.equals("sign up")) {
				int id = membersSize + 1;
				String date = Util.NowDate();
				String Login_ID = null;
				while(true) {
					System.out.printf("로그인 아이디	: ");
					Login_ID = sc.nextLine();
					
					if(isJoinable_ID(Login_ID) == false) {
						System.out.println("이미 사용중인 아이디입니다");
						continue;
					}
					break;
				}
				
				String Login_PW = null;
				String PW_Confirm = null;
				while(true) {
					System.out.printf("로그인 비밀번호	: ");
					Login_PW = sc.nextLine();
					System.out.printf("비밀번호 재확인	: ");
					PW_Confirm = sc.nextLine();
					if (Login_PW.equals(PW_Confirm) == false) {
						System.out.println("비밀번호를 다시 입력해주세요");
						continue;
					}
					break;
				}
				System.out.printf("이름	: ");
				String name = sc.nextLine();
				
				Member member = new Member(id, date, Login_ID, Login_PW, name);
				members.add(member);
				
				System.out.println(id + "번 회원이 가입되었습니다");
				membersSize++;
			}
			
			else if(cmd.equals("article list")) {
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
				int id = Id_Size + 1;
				String date = Util.NowDate();
				System.out.printf("제목	: ");
				String titles = sc.nextLine();
				System.out.printf("내용	: ");
				String contents = sc.nextLine();
				
				Article article = new Article(id, titles, contents, date);
				articles.add(article);
				
				System.out.println(id + "번글이 생성되었습니다");
				Id_Size++;
			}
			
			else if(cmd.startsWith("article detail")) {
				String[] num = cmd.split(" ");
				int id = Integer.parseInt(num[2]);
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				foundArticle.increaseView();
				
				System.out.println("번호	: " + foundArticle.id);
				System.out.println("제목	: " + foundArticle.titles);
				System.out.println("내용	: " + foundArticle.contents);
				System.out.println("날짜	: " + foundArticle.date);
				System.out.println("조회	: " + foundArticle.view);
			}
			
			else if(cmd.startsWith("article delete")) {
				String[] num = cmd.split(" ");
				int id = Integer.parseInt(num[2]);
				int foundIndex = getArticleIndexById(id);
				
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				articles.remove(foundIndex);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);
			}
			
			else if(cmd.startsWith("article modify")) {
				String[] num = cmd.split(" ");
				int id = Integer.parseInt(num[2]);
				Article foundArticle = getArticleById(id);
				
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
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
	private boolean isJoinable_ID(String loginId) {

		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String Login_Id) {
		int i = 0;
		for (Member member : members) {
			if (member.Login_Id.equals(Login_Id)) {
				return i;
			}
			i++;
		}
		return -1;
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
	
	public static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");
		for(int i = 1; i <= 3; ++i) {
			articles.add(new Article(i, "test_titles_" + i, "test_contents_" + i, Util.NowDate(), 0));
		}
	}
}