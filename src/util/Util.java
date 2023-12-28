package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	private static Scanner sc = new Scanner(System.in);

	public static void closeScanner() {
		sc.close();
	}

	public static int getIntVal(String str, int start, int end) {
		while (true) {
			int val = 0;
			try {
				System.out.print("▶" + str + "[" + start + "-" + end + "] >>");
				val = sc.nextInt();
				sc.nextLine();
				if (val < start || val > end) {
					System.out.println("다시 입력하세요.");
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				sc.nextLine();
				continue;
			}
			return val;
		}
	}

	public static String getStrVal(String showStr) {
		while (true) {
			String val = "";
			try {
				System.out.print("▶"+showStr+" >>");
				val = sc.next();
				sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
				sc.nextLine();
				continue;
			}
			return val;
		}
	}
	
	
}
