package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProcessUSToUKFile {
	private Map<String, String> sensitiveMap = new HashMap<>();
	private Map<String, String> insensitiveMap = new HashMap<>();

	public ProcessUSToUKFile(String args, FileWriter fw) {
		File us_ukI = new File(args);

		try (Scanner sc2 = new Scanner(us_ukI)) {
			while (sc2.hasNextLine()) {
				if (sc2.hasNextLine()) {
					String s[] = sc2.nextLine().split("\\:");
					sensitiveMap.put(s[0], s[1]);
					insensitiveMap.put(s[0].toLowerCase(), s[1]);
				} else {
					MyLogger.writeMessage("inout format is wrong / inut file is empty", null);
					fw.write("inout format is wrong / inut file is empty");
					fw.flush();
				}
			}

		} catch (Exception e) {
			try {
				fw.write("input error / file is empty in Us_uk file");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public Map<String, String> getUSUKWords_caseSensitive() {
		return sensitiveMap;
	}

	public Map<String, String> getUSUKWords_caseInSensitive() {
		return insensitiveMap;
	}
}
