package spreadsheetUpdates.observer;

import java.util.ArrayList;

import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.Logger.DebugLevel;

/**
 * 
 * @author shali
 * The Cell Class implements Subject and Listener
 */
public class Cell implements Subject, Listener{
	private String name = null;
	private int value;
	private ArrayList<Listener> listOfObservers = null;
	
	/**
	 * Constructor
	 * @param nameIn
	 */
	public Cell(String nameIn){
		Logger.writeMessage("Constructor called: Cell class", DebugLevel.CONSTRUCTOR);
		name = nameIn; 
		listOfObservers = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return listOfObservers
	 */
	public ArrayList<Listener> getListOfCells() {
		return listOfObservers;
	}
	

	/**
	 * @return void
	 * @param listOfCells
	 */
	public void setListOfCells(ArrayList<Listener> listOfCells) {
		this.listOfObservers = listOfCells;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return void
	 * @param part1
	 */
	public void setName(String part1) {
		String name1 = String.valueOf(part1);
		this.name = name1;
	}
	
	/**
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return void
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * return void
	 * @param x
	 * @param y
	 * @param oldValue
	 */
	public void add(int x, int y, int oldValue){
		value = x + y;
		int updateDiff = value - oldValue;
		if(!listOfObservers.isEmpty()){
			notifyObservers(updateDiff);
		}
	}
	
	/**
	 * method from interface Listener
	 * @param updateDiff
	 */
	@Override
	public void update(int updateDiff) {
		// TODO Auto-generated method stub
		value = value + updateDiff;
	}
	
	/**
	 * @return void
	 * @param listener
	 */
	@Override
	public void registerObserver(Listener listener) {
		// TODO Auto-generated method stub
		String nameListener = ((Cell)listener).getName();
		Logger.writeMessage("Subject "+this.name+" added listener "+nameListener+" to the list", DebugLevel.REGISTEROBSERVER_METHOD);

		listOfObservers.add(listener);
		
	}
	/**
	 * @return void
	 * @param listener
	 */
	@Override
	public void removeObserver(Listener listener) {
		// TODO Auto-generated method stub
		String nameListener = ((Cell)listener).getName();
		Logger.writeMessage("Subject "+this.name+" removed listener "+nameListener+" from the list", DebugLevel.REMOVEOBSERVER_METHOD);
		listOfObservers.remove(listener);
	}
	
	/**
	 * @return void
	 * @param updateDiff
	 */
	@Override
	public void notifyObservers(int updateDiff) {
		
		// TODO Auto-generated method stub
		for(Listener l: listOfObservers ){
			((Subject)l).notifyObservers(updateDiff);
			l.update(updateDiff);
		}
	}
	/**
	 * @return string
	 * toString method overridden
	 */
	public String toString(){
		return this.name + " " + this.value+ " "+listOfObservers.toString();
	}
	
}
