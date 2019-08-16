package edu.study.base.java.lang;

import org.apache.log4j.Logger;

public class IntAndTest {
	private static Logger logger=Logger.getLogger(IntAndTest.class);
	public static void test() {
		int a = 1 , b = 1 , c = 1 , d = 1;
		
		a++;
		++b;
		c = c++;
		d = ++d;
		
		System.out.println(a + "\t" + b + "\t" + c + "\t" + d);
	}
	
	public static void main(String []args) {
		test();
	}
}
