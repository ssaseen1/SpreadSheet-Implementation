package spreadsheetUpdates.observer;

/**
 * @author shali
 * This interface is implemented by class SpreadSheet
 */
public interface SpreadSheetInterface {
	 public boolean isNumeric(String str);
	 public void cyclicCheck(String result, String op1, String op2);
	 public void spreadSheetInput();
}
