package spreadsheetUpdates.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spreadsheetUpdates.store.ScheduledResultInterface;
import spreadsheetUpdates.util.FileDisplayInterface;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.Logger.DebugLevel;

/**
 * 
 * @author shali
 * This class consist of the the implementation of parsing the 
 * input from the file and implementation of spread sheet
 */
public class SpreadSheet implements SpreadSheetInterface {
	
	FileDisplayInterface fileProcessor = null;
	ScheduledResultInterface results = null;
	Map<String, ArrayList<String>> mapForCylceCheck = null;
	boolean flag = false;
    Map<Cell, ArrayList<Cell> > listOfObservers = null;
    
    /**
     * Constructor
     * @param fileProcessorIn
     * @param resultsIn
     */
	public SpreadSheet(FileDisplayInterface fileProcessorIn, ScheduledResultInterface resultsIn) {
		Logger.writeMessage("Constructor called: SpreadSheet Class ", DebugLevel.CONSTRUCTOR);
		fileProcessor = fileProcessorIn;
		results = resultsIn;
		mapForCylceCheck = new HashMap<>();
		listOfObservers = new HashMap<>();		
	}
	
	/**
	 * method used to check if the value is an Integer
	 * @param str
	 * @return boolean value
	 */
	public boolean isNumeric(String str)  
	
	{  
	  try  
	  {  
	   int i = Integer.parseInt(str);
	  }  
	  catch(NumberFormatException e)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	
	/**
	 * method to detect cycle in the given input
	 * @param result
	 * @param op1
	 * @param op2
	 */
	 public void cyclicCheck(String result, String op1, String op2) {
		 if ( mapForCylceCheck.containsKey(op1)) {
			 ArrayList<String> listnew =  mapForCylceCheck.get(op1);
		      if (listnew.contains(result)) {
		        //System.out.println("Cycle found ");
		        flag = true;
		        return;
		      }
		      for (String s : listnew)
		      {
		        cyclicCheck(result, s,null);
		      } 
		 }
		 
		 if ( mapForCylceCheck.containsKey(op2)) {
			 ArrayList<String> listnew =  mapForCylceCheck.get(op2);
		      if (listnew.contains(result)) {
		        //System.out.println("Cycle found ");
		        flag = true;
		        return;
		      }
		      for (String s : listnew)
		      {
		        cyclicCheck(result, s,null);
		      } 
		 }
	}
	
	/**
	 * method used for parsing and implementation of
	 * spread sheet
	 */
	public void spreadSheetInput(){
		
		String fileInput = null;	
		fileInput = fileProcessor.readFromFile();
		
		while(null != fileInput){
			ArrayList<String> listForCylce = null;
			ArrayList<Cell> list = null;
			int operand1 = 0,operand2 = 0, sum= 0;
			Cell observerCell = null;
			Cell subjectCell1 = null;
			Cell subjectCell2 = null;
			int oldValue = 0;
			list = new ArrayList<>();
			String[] part1 = fileInput.split("=");
			String[] part2 = part1[1].split("\\+");
			
			listForCylce = new ArrayList<String>();
			listForCylce.add(part2[0]);
			listForCylce.add(part2[1]);
			 mapForCylceCheck.put(part1[0], listForCylce);
			
			String result = part1[0];
		    String op1 = part2[0];
		    String op2 = part2[1];
		    flag = false;
		    cyclicCheck(result, op1, op2);
		    
		    if (flag)
		    	 mapForCylceCheck.remove(result);
		    
		    if(flag){
		    	Logger.writeMessage("Cycle Detected! at "+fileInput, DebugLevel.CYCLE_DETECTED);
		    	results.setCycleMessage("Cycle Detected! Ignoring line: "+fileInput);
				fileInput = fileProcessor.readFromFile();
		    }
		    
		    else{
		    	
		    	if(!results.getMapOfCells().containsKey(part1[0])){
		    		observerCell = new Cell(part1[0]);
		    		observerCell.setValue(sum);
		    		results.setMapOfCells(part1[0], observerCell);
				}
		    	else{
		    		observerCell = results.getMapOfCells().get(part1[0]);
		    		oldValue = observerCell.getValue(); 				
		    	}
			
		    	//remove observers from the list of observers
		    	if(isNumeric(part2[0]) && isNumeric(part2[1]))
		    	{
				
		    	}
		    	else
		    	{
		    		if(listOfObservers.containsKey(observerCell)){
		    			list = listOfObservers.get(observerCell);
							while(list.size()!=0){
								list.get(0).removeObserver(observerCell);
								listOfObservers.get(observerCell).remove(0);
						}	
		    		}
		    		else{
		    			listOfObservers.put(observerCell, list);
					}
		    	}
			
		    	if(isNumeric(part2[0])){
		    		operand1 = Integer.parseInt(part2[0]);
				
		    	}
		    	else{
		    		if(!results.getMapOfCells().containsKey(part2[0])){
		    			subjectCell1 = new Cell(part2[0]);
		    			subjectCell1.setValue(0);
		    			results.setMapOfCells(part2[0],subjectCell1);	
		    		}
		    		else{
		    			subjectCell1 = results.getMapOfCells().get(part2[0]);
		    		}	
				subjectCell1.registerObserver(observerCell);
				operand1 = subjectCell1.getValue();				
		    	}
				
		    	if(isNumeric(part2[1])){
		    		operand2 = Integer.parseInt(part2[1]);
		    	}
		    	else{
		    		if(!results.getMapOfCells().containsKey(part2[1])){
		    			subjectCell2 = new Cell(part2[1]);
		    			subjectCell2.setValue(0);
		    			results.setMapOfCells(part2[1],subjectCell2);	
		    		}
		    		else{
		    			subjectCell2 = results.getMapOfCells().get(part2[1]);
		    		}
				subjectCell2.registerObserver(observerCell);
				operand2 = subjectCell2.getValue();				
		    	}
			
		    	//add the observers to the list of observers
		    	if(isNumeric(part2[0]) && isNumeric(part2[1]))
		    	{
				
		    	}
		    	else if(isNumeric(part2[0]))
		    	{
		    		list.add(subjectCell2);
		    	}
		    	else if(isNumeric(part2[1]))
		    	{
		    		list.add(subjectCell1);
		    	}
		    	else
		    	{
		    		list.add(subjectCell1);
		    		list.add(subjectCell2);
		    	}
		    	
		    observerCell.add(operand1, operand2, oldValue);	
			fileInput = fileProcessor.readFromFile();		
		}	
		}			
	}
	
	/**
	 * @return String
	 * overridden method
	 */
	public String toString(){
		return "Spread Sheet Implementation";
		
	}
}