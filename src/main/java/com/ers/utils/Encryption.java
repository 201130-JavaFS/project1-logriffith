package com.ers.utils;

public class Encryption {
		
	public static String encrypt(String password) {
		
		int key = Integer.parseInt(System.getenv("ERS_EncryptionKey"));
		StringBuilder stringBuilder = new StringBuilder();
		char[] characters = password.toCharArray();
		
		for (char c : characters) {
			c += key;
			stringBuilder.append(c);
		}
		
		String encryptedPassword = stringBuilder.toString();
		return encryptedPassword;
	}
	
//	public static void main(String[] args) {
//		String password = "This is my super-secret password!";
//		System.out.println("Password: " + password);
//		
//		String encryptedPassword = encrypt(password);
//		System.out.println("Encrypted Password is: " + encryptedPassword);
//		
//		if(password.equals(Decryption.decrypt(encryptedPassword))) {
//			System.out.println("Encryption and Decryption work as desired.");
//		}else {
//			System.out.println("Something went wrong with either Encryption or Decryption.");
//		}
//		
////		//encrypt new password
////		String newPassword = "newPassword";
////		System.out.println(encrypt(newPassword));
//		
//	}

}
