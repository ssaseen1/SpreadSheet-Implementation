package spreadsheetUpdates.observer;

/**
 * 
 * @author shali
 * This interface is implemented by class Cell
 */
public interface Subject {
	public void registerObserver(Listener l);
	public void removeObserver(Listener l);
	public void notifyObservers(int updateDiff);
}
