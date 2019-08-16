package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		String xString = "";
		String parent = "";
		String child = "";
		boolean flag = true;
		List<String> pathList = new ArrayList<String>();
		if (n > 0) {
			pathList.add("0");
			xString = scanner.nextLine();
			while (flag) {
				if (xString != null && xString.trim().length() > 0) {
					String[] nums = xString.split(" ");
					parent = nums[0];
					child = nums[1];
					List<String> aList = new ArrayList<>();
					for (String string : pathList) {
						if (string != null && string.endsWith(parent)) {
							String str = string + child;
							aList.add(str);
						}
					}
					pathList.addAll(aList);
					xString = scanner.nextLine();
					if (xString == null || xString.trim().length() == 0) {
						flag = false;
						break;
					}
				}
				
			}
			int max = 0;
			for (String string : pathList) {
				if (string.length() > max) {
					max = string.length();
				}
			}
			System.out.println(max);
		} else {
			System.out.println("0");
		}
	}
}
