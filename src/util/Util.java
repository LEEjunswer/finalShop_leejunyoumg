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
			System.out.println(msg);
			try {
				int input = scan.nextInt();
				if (input < start || input > end) {
					System.out.println("잘못입력하셧습니다");
						continue;
				}
				scan.nextLine();
				return input;
			} catch (InputMismatchException e) {
				System.out.println("숫자만입력하세요");
			}
		}
	}

	public static String getValueS(String msg) { 
		System.out.println(msg);
		String id =scan.next();
		return id;
	}
	}