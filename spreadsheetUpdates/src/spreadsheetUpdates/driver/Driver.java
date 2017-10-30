package spreadsheetUpdates.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import spreadsheetUpdates.observer.SpreadSheet;
import spreadsheetUpdates.observer.SpreadSheetInterface;
import spreadsheetUpdates.store.Results;
import spreadsheetUpdates.store.ScheduledResultInterface;
import spreadsheetUpdates.util.FileDisplayInterface;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.StdoutDisplayInterface;

/**
 * 
 * @author shali
 * This is the Driver class.
 *
 */
public class Driver {
	
	/**
	 * main method
	 * @param args
	 * @return void
	 */
	public static void main(String args[]){
		/**
		 * Validate the correct number of arguments
		 */
		if(args.length != 3){
			System.err.println("Enter the correct number of command line arguments");
			System.exit(1);	
		}
		
		int debugValue = -1; 
		String input_file = null;
		
		System.out.println("args[0] : input file "+args[0] );
		System.out.println("args[1] : output file "+args[1]);
		System.out.println("args[2] : DEBUG_VALUE "+args[2]);
		
		input_file = args[0];
		
		/**
		 * Validate the number format exception,
		 * expected command line argument is an Integer
		 */
		try{
			debugValue = Integer.parseInt(args[2]);
		}
		catch (NumberFormatException e ){
			System.err.println("A numerical value is expected");
			e.printStackTrace();
			System.exit(1);
		}
		
		/**
		 * Validate the DEBUG_VALUE is between 0 and 4.
		 */
		if(debugValue < 0 || debugValue > 4){
			System.err.println("The value of DEBUG_VALUE should be between 0 and 4");
			System.exit(1);
		}
		
		FileInputStream fileInputStream = null;
		FileDisplayInterface fileProcessor = null;
		
		
		File f1 = new File(input_file);
		if (f1.exists() && !f1.isDirectory()) {
			long f1Size = f1.length();
			if (f1Size == 0) {
				System.err.println("input.txt file is empty");
				System.exit(1);
			}
			try {
				Logger.setDebugValue(debugValue);
				fileInputStream = new FileInputStream(input_file);
				fileProcessor = new FileProcessor(fileInputStream, input_file);
				
				ScheduledResultInterface results = new Results();
				
				SpreadSheetInterface input = new SpreadSheet(fileProcessor, results);
				input.spreadSheetInput();
				
				((FileDisplayInterface)results).writeToFile(args[1]);
				((StdoutDisplayInterface)results).writeScheduleToStdout();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println("File not found");
				e.printStackTrace();
				System.exit(1);
			} finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.exit(1);
					} finally {

					}
				}
			}
		} 
		else{
			System.err.println("File does not exist");
			System.exit(1);
		}	
	}
	
	/**
	 * @return String
	 */
	public String toString(){
		return "Driver Class";
	}
}
