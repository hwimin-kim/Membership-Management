package org.crm;

import java.sql.SQLException;
import java.util.Scanner;

public class User {

	public static Scanner sc = new Scanner(System.in);
	// ȸ������ parameter
	private static String name;
	private static String id;
	private static String pwd;
	private static int age;
	private static String area;
	// ȸ����ȸ parameter
	private static int menuSearchIdx;
	private static String searchId;

	public static void scanListInput() throws SQLException, ClassNotFoundException {
		boolean menuChk = false;
		do {
			System.out.println("���Ͻô� �޴��� �������ּ���!");
			System.out.println("1. ��� ȸ������ ���");
			System.out.println("2. ���� ȸ������ ���");

			// ȸ����ȸ(�޴�����)
			menuSearchIdx = sc.nextInt();
			switch (menuSearchIdx) {
			case 1:
				searchId = null;
				menuChk = true;
				break;
			case 2:
				// ���̵���ȸ�ؾ���
				System.out.println("ID�� �Է��� �ּ���.");
				searchId = sc.next();
				int cnt = DatabaseSelect.userId(searchId);
				if (cnt > 0) {
					System.out.println(searchId + "�� ��ȸ�ϴ� ���Դϴ�.");
					menuChk = true;
				} else {
					System.out.println(searchId + "�� �������� �ʴ� ���̵��Դϴ�..");
				}
				break;
			default:
				System.out.println("�޴��� �߸� �����Ͽ����ϴ�.");
				break;
			}
		} while (menuChk == false);
	}

	public static void scanInsInput() throws SQLException, ClassNotFoundException {
		String[] areaAry = { "����", "���", "��õ", "����", "���", "�泲", "����", "����", "���", "�泲", "�λ�", "���", "�뱸", "����", "����" };
		int areaAryIdx = 1;
		int areaChoice = 0;
		boolean idChk = false;
		boolean areaChx = false;

		// �̸� �Է�
		System.out.println("�̸��� �Է����ּ���");
		name = sc.next();

		// ���̵� �Է� �� �ߺ� üũ
		do {
			System.out.println("���̵� �Է����ּ���");
			id = sc.next();
			int cnt = DatabaseSelect.userId(id);
			if (cnt > 0) {
				System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
			} else {
				System.out.println(id + "�� ��밡���� ���̵� �Դϴ�.");
				idChk = true;
			}
		} while (idChk == false);

		// ��й�ȣ �Է�
		System.out.println("��й�ȣ�� �Է����ּ���");
		pwd = sc.next();

		// ���� �Է�
		System.out.println("���̸� �Է����ּ���");
		age = sc.nextInt();

		// ���� �Է�
		do {
			System.out.println("������ �������ּ���");
			// ���� �޴� ǥ��
			for (String e : areaAry) {
				System.out.println(areaAryIdx + ". " + e);
				areaAryIdx++;
			}
			areaAryIdx = 1;
			// ���� üũ
			areaChoice = sc.nextInt();
			if (areaChoice > 0 && areaChoice <= areaAry.length) {
				area = areaAry[areaChoice - 1];
				areaChx = true;
			} else {
				System.out.println("������ �߸� �����ϼ̽��ϴ�.");
			}

		} while (areaChx == false);
	}

	public static void main(String[] args) {
		int menuIdx = 1;
		int menuChoice = 0;
		// ����ȭ�� ���
		System.out.println("ȸ������ �ý��ۿ� ���Ű� ȯ���մϴ�.");
		System.out.println("���Ͻô� �޴��� �������ּ���!");
		String[] menu = { "ȸ�� ���", "ȸ�� ��ȸ", "�ý��� ����" };
		for (String element : menu) {
			System.out.println(menuIdx + ". " + element);
			menuIdx++;
		}
		// ����ȭ��(�޴�����)
		menuChoice = sc.nextInt();
		switch (menuChoice) {
		case 1:
			// ȸ�����
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
			// ȸ�� ��ȸ
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
			// �ý��� ����
			System.out.println("ȸ������ �ý����� �����մϴ�.");
			System.exit(0);

		default:
			System.out.println("�޴��� �߸� �����Ͽ����ϴ�.");
			break;
		}
		// ����ȭ��(�޴�����) �ٽ�ȣ��
		main(new String[] { "" });

	}
}
