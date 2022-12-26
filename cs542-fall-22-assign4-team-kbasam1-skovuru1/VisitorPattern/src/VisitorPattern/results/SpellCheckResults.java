package results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.MyLogger;

public class SpellCheckResults implements Results {

	private String filename;
	FileWriter fileWrite;
	List<String> caseSensitive;
	List<String> caseInSensitive;

	public SpellCheckResults(String arg) {
		filename = arg;
		caseSensitive = new ArrayList<>();
		caseInSensitive = new ArrayList<>();
	}

	public void setCaseSensitive(List<String> res) {
		caseSensitive = res;
	}

	public void setCaseInSensitive(List<String> resIn) {
		caseInSensitive = resIn;
	}

	@Override
	public void writeToFile() {
		try {
			fileWrite = new FileWriter(filename, false);
			fileWrite.write("Input Text after Case Sensitive Check: \n");
			MyLogger.writeMessage("\n\nInput Text after Case Sensitive Check: \n", null);
			for (String str : caseSensitive) {
				fileWrite.write(str + ". ");
				MyLogger.writeMessage(str + ".", null);
			}
			fileWrite.write("\n \n");

			fileWrite.write("\n\nInput Text after non case InSensitive Check: \n");
			MyLogger.writeMessage("\n \nInput Text after non case Sensitive Check: \n", null);
			for (String str : caseInSensitive) {
				fileWrite.write(str + ". ");
				MyLogger.writeMessage(str + ".", null);
			}

			fileWrite.close();
		} catch (NullPointerException | IOException e) {
			System.err.println("Exception: ");
			System.out.println("Exception: " + e.getMessage().getClass().getName());
			e.printStackTrace();
		} finally {
			try {
			fileWrite.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
	}
}
