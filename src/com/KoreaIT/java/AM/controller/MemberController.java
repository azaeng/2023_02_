package com.KoreaIT.java.AM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Article.Member;
import com.KoreaIT.java.AM.Util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String cmd;
	private String actionMethodName;

	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}

	int Member_Id_Size = 0;
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "join":
			doJoin();
			break;
		}
	}

	public void doJoin() {
		int id = Member_Id_Size + 1;
		String date = Util.NowDate();
		String Login_Id = null;

		while (true) {

			System.out.printf("로그인 아이디 : ");
			Login_Id = sc.nextLine();

			if (isJoinable_ID(Login_Id) == false) {
				System.out.println("이미 사용중인 아이디입니다");
				continue;
			}

			break;
		}

		String Login_Pw = null;
		String Pw_Confirm = null;
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			Login_Pw = sc.nextLine();
			System.out.printf("로그인 비밀번호 재확인: ");
			Pw_Confirm = sc.nextLine();

			if (Login_Pw.equals(Pw_Confirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, date, Login_Id, Login_Pw, name);
		members.add(member);

		System.out.println(id + "번 회원이 가입되었습니다");
		Member_Id_Size++;

	}

	private boolean isJoinable_ID(String Login_Id) {

		int index = getMemberIndexByLogin_Id(Login_Id);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLogin_Id(String Login_Id) {
		int i = 0;
		for (Member member : members) {
			if (member.Login_Id.equals(Login_Id)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}