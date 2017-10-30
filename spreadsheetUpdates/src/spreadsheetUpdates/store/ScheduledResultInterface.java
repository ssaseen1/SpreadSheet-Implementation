package spreadsheetUpdates.store;

import java.util.List;
import java.util.Map;

import spreadsheetUpdates.observer.Cell;

/**
 * 
 * @author shali
 * Class Results implements ScheduledResultInterface 
 */
public interface ScheduledResultInterface {
	
	public Map<String, Cell> getMapOfCells();
	public void setMapOfCells(String name, Cell cell);
	public List<String> getCycleMessage();
	public void setCycleMessage(String message);
	
}

