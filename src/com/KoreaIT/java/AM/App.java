package com.KoreaIT.java.AM;

import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;

public class App {
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		
		Controller controller;
		
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
			
			String[] num = cmd.split(" ");
			String controllerName = num[0];
			
			if (num.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}
			
			String actionMethodName = num[1];
			
			controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;
			}
			else if(controllerName.equals("sign")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
			
			String cmdCombine = controllerName + "/" + actionMethodName;
			
			switch (cmdCombine) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "sign/out":
				if (Controller.isLogined() == false) {
					System.out.println("로그인 후 이용해주세요");
					continue;
				}

				break;
			}

			switch (cmdCombine) {
			case "sign/in":
			case "sign/up":
				if (Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요");
					continue;
				}

				break;
			}
			
			controller.doAction(cmd, actionMethodName);
			
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
}