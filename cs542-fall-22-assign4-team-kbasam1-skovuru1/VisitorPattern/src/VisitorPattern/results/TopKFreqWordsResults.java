package results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import util.MyLogger;

public class TopKFreqWordsResults implements Results {

	private String filename;
	private Map<String, Integer> result;
	private ArrayList<String> topFreq;
	FileWriter fileWrite;

	public TopKFreqWordsResults(String arg) {
		filename = arg;
		result = new HashMap<>();
	}

	public void addResult(Map<String, Integer> res) {
		result = res;
	}

	public void addTopFreq(ArrayList<String> topFq) {
		topFreq = topFq;
	}

	@Override
	public void writeToFile() {
		try {
			fileWrite = new FileWriter(filename, false);
			MyLogger.writeMessage("\nK most frequent words", null);
			for (String key : topFreq) {
				String Str_file = key + " : " + result.get(key);
				fileWrite.write(Str_file);
				fileWrite.write(System.lineSeparator());

				MyLogger.writeMessage(Str_file, null);

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
