package it.unibo.iss.olivi.ipc.demo.fifo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

public class FifoReader {

	private static final String FIFO_PATH 	= "fifoDir" + FileSystems.getDefault().getSeparator() + "fifo";
	private static final int BUF_SIZE 		= 1000;  
	private static char[] buffer 			= new char[BUF_SIZE];  
	
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(FIFO_PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		int readChars;
		try {
			while((readChars = reader.read(buffer)) != -1) {
				System.out.println("Received: " + new String(buffer, 0, readChars));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("bye bye");
	}

}
