package demo;

import java.util.Scanner;
import java.util.Stack;

public class Main1 {

	static Stack<String> stack = new Stack<String>();

	public static String reverse(String[] array) {
		Main1.stack.clear();
		System.out.println(array.length);
		for (String string : array) {
			String xString = string.trim();
			if (xString.length() > 0) {
				stack.push(xString);
			}
		}
		System.out.println(stack.toString());
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < stack.size(); i++) {
			String xString = Main1.stack.pop();
			builder.append(xString).append(" ");
		}
		String result = builder.toString().trim();
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String line = scanner.nextLine().trim();
			String[] array = line.split(" ");
			String result = Main1.reverse(array);
			System.out.println(result);
		}
	}
}
