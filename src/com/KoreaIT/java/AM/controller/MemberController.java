package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Article.Article;
import com.KoreaIT.java.AM.Article.Member;
import com.KoreaIT.java.AM.Util.Util;

public class MemberController extends Controller {
	
	private List<Member> members;
	private Scanner sc;
	private String cmd;
	private String actionMethodName;
	
	private Member LoginedMember = null;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	int Member_Id_Size = 0;
	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "up":
			doUp();
			break;
		case "in":
			doIn();
			break;
		case "out":
			doOut();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다");
		for(int i = 1; i <= 3; ++i) {
			members.add(new Member(i, Util.NowDate(), "id_" + i, "pw_" + i, "회원" + i));
		}
	}
	
	private void doUp() {
		int id = Member_Id_Size + 1;
		String date = Util.NowDate();
		String Login_Id = null;

		while (true) {

			System.out.printf("로그인 아이디	: ");
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
			System.out.printf("로그인 비밀번호	: ");
			Login_Pw = sc.nextLine();
			System.out.printf("비밀번호 재확인	: ");
			Pw_Confirm = sc.nextLine();

			if (Login_Pw.equals(Pw_Confirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.printf("사용자 이름		: ");
		String name = sc.nextLine();

		Member member = new Member(id, date, Login_Id, Login_Pw, name);
		members.add(member);

		System.out.println(id + "번 회원이 가입되었습니다");
		Member_Id_Size++;

	}
	
	private void doIn() {
		if(LoginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		System.out.printf("로그인 아이디	: ");
		String Login_Id = sc.nextLine();
		
		System.out.printf("로그인 비밀번호	: ");
		String Login_Pw = sc.nextLine();
		
		Member member =SignIn_Id(Login_Id);
		
		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다");
			return;
		}
		
		if(member.Login_Pw.equals(Login_Pw) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}

		LoginedMember = member;
		
		System.out.println(member.name + "님이 로그인되었습니다");

	}
	
	private void doOut() {
		LoginedMember = null;
				
		System.out.println("로그아웃 되었습니다");
	}

	private Member SignIn_Id(String Login_Id) {
		int index = getMemberIndexByLogin_Id(Login_Id);
		
		if (index == -1) {
			return null;			
		}
		
		return members.get(index);
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

