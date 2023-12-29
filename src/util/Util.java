package util;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	private static Scanner scan = new Scanner(System.in);

	public static void closeScanner() {
		scan.close();
	}

	public static int getValueI(String msg, int start, int end) {
		while (true) {// 숫자 입력
			System.out.printf(msg + "[%d ~ %d]", start, end);
			try {
				int input = scan.nextInt();
				scan.nextLine();
				if (input < start || input > end) {
					System.out.println("잘못입력하셧습니다");
					continue;
				}
				return input;
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("숫자만입력하세요");
			}
		}
	}


	public static String getValueS(String msg) {
		System.out.println(msg);
		String id = scan.next();
		return id;
	}

	public static int password(String msg) {
		System.out.println(msg);
		int input = scan.nextInt();
		return input;
	}
}