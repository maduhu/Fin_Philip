package com.philip.fin.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtil {
	public static byte[] getSalt() throws Exception {
		SecureRandom random = new SecureRandom();
		
		return random.generateSeed(10);
	}
	
	public static String encrypt(String password, String salt) throws NoSuchAlgorithmException{

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest((password + salt).getBytes());
		
		return byte2HexStr(b);
	}
	
	public static String byte2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder();
		String s = null;
		for (int i=0; i<b.length; i++) {
			s = Integer.toHexString(b[i] & 0xFF );
			if (s.length() == 1) {
				sb.append("0");
			}
			
			sb.append(s.toUpperCase());
		}
		
		return sb.toString();
	}
}
