package com.ers.utils;

public class Decryption {

	public static String decrypt(String encryptedPassword) {

		int key = Integer.parseInt(System.getenv("ERS_EncryptionKey"));
		StringBuilder stringBuilder = new StringBuilder();
		char[] characters = encryptedPassword.toCharArray();
		
		for(char c : characters) {
			c -= key;
			stringBuilder.append(c);
		}
		
		String decryptedPassword = stringBuilder.toString();
		return decryptedPassword;
		
	}
	
}
