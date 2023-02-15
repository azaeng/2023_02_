package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int articlesSize = 0;
		String[] titles = new String[1000];
		String[] contents = new String[1000];
		
		while(true) {	
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("article list")) {
					System.out.println("게시글이 없습니다");
			}
			
			else if(cmd.equals("article write")) {
				System.out.printf("제목 : ");
				titles[articlesSize] = sc.nextLine();
				System.out.printf("내용 : ");
				contents[articlesSize] = sc.nextLine();
				articlesSize++;
				System.out.printf("%d번글이 생성되었습니다\n", articlesSize);
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