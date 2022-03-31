package org.crm;

import java.sql.SQLException;
import java.util.Scanner;

public class User {

	public static Scanner sc = new Scanner(System.in);
	// 회원가입 parameter
	private static String name;
	private static String id;
	private static String pwd;
	private static int age;
	private static String area;
	// 회원조회 parameter
	private static int menuSearchIdx;
	private static String searchId;

	public static void scanListInput() throws SQLException, ClassNotFoundException {
		boolean menuChk = false;
		do {
			System.out.println("원하시는 메뉴를 선택해주세요!");
			System.out.println("1. 모든 회원정보 출력");
			System.out.println("2. 개인 회원정보 출력");

			// 회원조회(메뉴선택)
			menuSearchIdx = sc.nextInt();
			switch (menuSearchIdx) {
			case 1:
				searchId = null;
				menuChk = true;
				break;
			case 2:
				// 아이디조회해야함
				System.out.println("ID를 입력해 주세요.");
				searchId = sc.next();
				int cnt = DatabaseSelect.userId(searchId);
				if (cnt > 0) {
					System.out.println(searchId + "를 조회하는 중입니다.");
					menuChk = true;
				} else {
					System.out.println(searchId + "는 존재하지 않는 아이디입니다..");
				}
				break;
			default:
				System.out.println("메뉴를 잘못 선택하였습니다.");
				break;
			}
		} while (menuChk == false);
	}

	public static void scanInsInput() throws SQLException, ClassNotFoundException {
		String[] areaAry = { "서울", "경기", "인천", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "부산", "울산", "대구", "광주", "제주" };
		int areaAryIdx = 1;
		int areaChoice = 0;
		boolean idChk = false;
		boolean areaChx = false;

		// 이름 입력
		System.out.println("이름을 입력해주세요");
		name = sc.next();

		// 아이디 입력 및 중복 체크
		do {
			System.out.println("아이디를 입력해주세요");
			id = sc.next();
			int cnt = DatabaseSelect.userId(id);
			if (cnt > 0) {
				System.out.println("이미 존재하는 아이디입니다.");
			} else {
				System.out.println(id + "는 사용가능한 아이디 입니다.");
				idChk = true;
			}
		} while (idChk == false);

		// 비밀번호 입력
		System.out.println("비밀번호를 입력해주세요");
		pwd = sc.next();

		// 나이 입력
		System.out.println("나이를 입력해주세요");
		age = sc.nextInt();

		// 지역 입력
		do {
			System.out.println("지역을 선택해주세요");
			// 지역 메뉴 표시
			for (String e : areaAry) {
				System.out.println(areaAryIdx + ". " + e);
				areaAryIdx++;
			}
			areaAryIdx = 1;
			// 지역 체크
			areaChoice = sc.nextInt();
			if (areaChoice > 0 && areaChoice <= areaAry.length) {
				area = areaAry[areaChoice - 1];
				areaChx = true;
			} else {
				System.out.println("지역을 잘못 선택하셨습니다.");
			}

		} while (areaChx == false);
	}

	public static void main(String[] args) {
		int menuIdx = 1;
		int menuChoice = 0;
		// 시작화면 출력
		System.out.println("회원관리 시스템에 오신걸 환영합니다.");
		System.out.println("원하시는 메뉴를 선택해주세요!");
		String[] menu = { "회원 등록", "회원 조회", "시스템 종료" };
		for (String element : menu) {
			System.out.println(menuIdx + ". " + element);
			menuIdx++;
		}
		// 시작화면(메뉴선택)
		menuChoice = sc.nextInt();
		switch (menuChoice) {
		case 1:
			// 회원등록
			try {
				scanInsInput();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			try {
				DatabaseInsert.userIns(name, id, pwd, age, area);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			// 회원 조회
			try {
				scanListInput();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			try {
				DatabaseSelect.userList(menuSearchIdx, searchId);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			// 시스템 종료
			System.out.println("회원관리 시스템을 종료합니다.");
			System.exit(0);

		default:
			System.out.println("메뉴를 잘못 선택하였습니다.");
			break;
		}
		// 시작화면(메뉴선택) 다시호출
		main(new String[] { "" });

	}
}
