package _02_File_Encrypt_Decrypt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	// Create a program that opens the file created by FileEncryptor and display
	// the decrypted message to the user in a JOptionPane.
	
	public static void main(String[] args) {
		
		
		try {
			FileReader reader = new FileReader("src/_02_File_Encrypt_Decrypt/encrypted.txt");
			int c = reader.read();
			String decrypted = "";
			while(c != -1){
				decrypted += (char) (c - 5);
				c = reader.read();
			}
			JOptionPane.showMessageDialog(null, decrypted);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
