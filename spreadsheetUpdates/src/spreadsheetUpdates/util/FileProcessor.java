package spreadsheetUpdates.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/** 
 * @author shali 
 * This Class contains the operation related to File Processor.
 */
public class FileProcessor implements FileDisplayInterface {
	FileInputStream fileInputStream = null;
	BufferedReader bufferedReader = null;
	FileOutputStream fileOutputStream = null;
	BufferedWriter bufferedWriter = null;

	/**
	 * 
	 * @param fileInputStreamIn
	 * @param fileIn
	 * @throws FileNotFoundException
	 * 
	 */
	public FileProcessor(FileInputStream fileInputStreamIn, String fileIn) throws FileNotFoundException {
		Logger.writeMessage("Constructor called: FileProcessor class", Logger.DebugLevel.CONSTRUCTOR);
		fileInputStream = new FileInputStream(fileIn);
		bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
	}

	/**
	 * 
	 * @param fileOutputStreamIn
	 * @param fileIn
	 * @throws FileNotFoundException
	 */
	public FileProcessor(FileOutputStream fileOutputStreamIn, String fileIn) throws FileNotFoundException {
		Logger.writeMessage("Constructor called: FileProcessor class", Logger.DebugLevel.CONSTRUCTOR);
		fileOutputStream = new FileOutputStream(fileIn);
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
	}

	/**
	 * 
	 * @return fileInputStream
	 */
	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	/**
	 * 
	 * @param fileInputStreamIn
	 */
	public void setFileInputStream(FileInputStream fileInputStreamIn) {
		fileInputStream = fileInputStreamIn;
	}

	/**
	 * 
	 * @return bufferedReader
	 */
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	/**
	 * 
	 * @param bufferedReaderIn
	 */
	public void setBufferedReader(BufferedReader bufferedReaderIn) {
		bufferedReader = bufferedReaderIn;
	}

	/**
	 * method called for reading from file
	 * @return String
	 */
	public synchronized String readFromFile() {
		String line = null;
		try {
			line = bufferedReader.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * method to write to file
	 * @return String
	 */
	public synchronized void writeToFile(String line) {
		try {
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return FileOutputStream
	 */
	public FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

	/**
	 * 
	 * @param fileOutputStream
	 * @return void
	 */
	public void setFileOutputStream(FileOutputStream fileOutputStream) {
		this.fileOutputStream = fileOutputStream;
	}

	/**
	 * 
	 * @return bufferedWriter
	 */
	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}

	/**
	 * 
	 * @param bufferedWriter
	 *            return void
	 */
	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}
	
	/**
	 * @return String
	 * overridden toString method
	 */
	public String toString() {
		return "FileProcessor -> input file and output file";
	}

}
