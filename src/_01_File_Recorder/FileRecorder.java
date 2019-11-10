package _01_File_Recorder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Write stuff:");
		String stuff = s.nextLine();
		
		try {
			FileWriter writer = new FileWriter("src/_01_File_Recorder/stuff.txt");
			writer.write(stuff);
				
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
