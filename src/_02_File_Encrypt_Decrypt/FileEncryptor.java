package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptor {
	// Create a program that takes a message from the user.
	// Use the methods in the String and Character classes to save
	// an encrypted form of the message to a file
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter text to be encrypted: ");
		String tbe = s.nextLine(); //tbe = to be encrypted
		
		try {
			FileWriter writer = new FileWriter("src/_02_File_Encrypt_Decrypt/encrypted.txt");
			writer.write(encrypt(tbe));
			
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	static String encrypt(String tbe) {
		String encrypted = "";
		
		for(int i = 0; i < tbe.length(); i++) {
			encrypted += (char) (tbe.charAt(i) + 5);
		}
		
		return encrypted;
	}
}