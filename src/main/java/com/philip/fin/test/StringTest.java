package com.philip.fin.test;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = new String();
		a=String.format("%03d", 1);
		System.out.println(a);
		a=String.format("%03d", 21);
		System.out.println(a);
		a=String.format("%07d", 321);
		System.out.println(a);
		a=String.format("%07d", 1234);
		System.out.println(a);
	}

}
