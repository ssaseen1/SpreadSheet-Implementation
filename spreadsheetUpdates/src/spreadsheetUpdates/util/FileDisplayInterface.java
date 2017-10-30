package spreadsheetUpdates.util;



/**
 * @author shali 
 * Class {@link Results} and {@link FileProcessor} implements {@link FileDisplayInterface}
 */
public interface FileDisplayInterface {

	public String readFromFile();
	public void writeToFile(String line);

}
