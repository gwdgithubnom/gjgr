package edu.study.base.java.lang;

import tools.SystemTools;

public class IntegerTest {

	public static void main(String []args) {
		Integer a = 127;
		Integer b = 127;
		Integer c = 200;
		Integer d = 200;
		int e=128;
		//For the cache scope:[-128-127]. this is about 8 bits. out (-~,-128)||(127,+~) the integer would be a object.
		System.out.println(a == b);//true
		System.out.println(c == d);//false
		System.out.println(c == 200);//true
		System.out.println(a==e);//true
	}
}
