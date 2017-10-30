
package spreadsheetUpdates.store;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import spreadsheetUpdates.observer.Cell;
import spreadsheetUpdates.util.FileDisplayInterface;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.Logger.DebugLevel;
import spreadsheetUpdates.util.StdoutDisplayInterface;

/**
 * 
 * @author shali
 * Results class to store the data to the data structure
 * and print the output to the output.txt file
 *
 */
public class Results implements StdoutDisplayInterface, FileDisplayInterface, ScheduledResultInterface {
	
	Map<String, Cell> mapOfCells = null;
	List<String> cycleMessage = null;
	int totalSum = 0;
	
	/**
	 * Constructor
	 */
	public Results() {
		Logger.writeMessage("Constructor called: Results Class", DebugLevel.CONSTRUCTOR);
		mapOfCells = new HashMap<String, Cell>();
		cycleMessage = new ArrayList<>();
	}
	
	/**
	 * @return cycleMessage
	 */
	public List<String> getCycleMessage() {
		return cycleMessage;
	}

	/**
	 * @param message
	 */
	public void setCycleMessage(String message) {
		cycleMessage.add(message);
	}

	/**
	 * 
	 * @return totalSum
	 */
	public int getTotalSum() {
		return totalSum;
	}

	/**
	 * 
	 * @param totalSum
	 */
	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}

	/**
	 * @return mapOfCells
	 */
	public Map<String, Cell> getMapOfCells() {
		return mapOfCells;
	}
	
	/**
	 * method to add the content to the data structure
	 * @param name
	 * @param cell
	 */
	public void setMapOfCells(String name, Cell cell) {
		//Logger.writeMessage("Entry "+name+" is added to data structure", DebugLevel.ADD_TO_DATASTRUCTURE);
		mapOfCells.put(name, cell);
	}
	
	/**
	 * method to write the output to the screen
	 */
	public void writeScheduleToStdout() {
		Logger.writeMessage("", DebugLevel.OUTPUT);
    	
  	
	}	
	
	/**
	 * method to print Data structure
	 */
	public void printDS(){
		for(Entry<String, Cell> name: mapOfCells.entrySet()){
			System.out.println(name.getValue().getName()+" "+name.getValue().getValue());
		}
	}
	
	/**
	 * method to calculate the total
	 */
	public void calculateSum(){

		for(Entry<String, Cell> name: mapOfCells.entrySet()){
			totalSum = totalSum + name.getValue().getValue();
		}

	}
	   
	/**
	 * method to write the output to the file
	 * @param String output filename
	 */
	@Override
	public void writeToFile(String outFileName){
		
		FileOutputStream fileOutputStream = null;
		FileProcessor fileProcessor = null;
		try {
			fileOutputStream = new FileOutputStream(outFileName);
			fileProcessor = new FileProcessor(fileOutputStream, outFileName);
			calculateSum();
			for(String printMessage: cycleMessage){
				fileProcessor.writeToFile(printMessage);
			}
			fileProcessor.writeToFile("The sum of all cell values is: "+totalSum);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method to read From File
	 */
	@Override
	public String readFromFile() {
	// TODO Auto-generated method stub
	return null;
	}
	
	/**
	 * overridden toString method
	 * @return String 
	 */
	public String toString(){
		return mapOfCells.keySet().toString();	
	}

}
