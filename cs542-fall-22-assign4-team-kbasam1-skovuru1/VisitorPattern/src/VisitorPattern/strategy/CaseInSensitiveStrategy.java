package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import iteration.Iterator;
import iteration.MyArrayList;

public class CaseInSensitiveStrategy implements CheckStrategy {
	List<String> arrayList = new ArrayList<>();

	@Override
	public List<String> performSpellCheck(MyArrayList myArray, Map<String, String> inSensitiveMap) {
		StringBuilder sb;
		Iterator it = myArray.getIterator();
		while (it.hasNext()) {
			sb = new StringBuilder();
			String[] words = getWordsFromElement(it.next());
			for (String w : words) {
				if (inSensitiveMap.containsKey(w.toLowerCase())) {
					sb.append(inSensitiveMap.get(w.toLowerCase()));
				} else {
					sb.append(w);
				}
				sb.append(" ");
			}

			arrayList.add(sb.toString().trim());
		}

		return arrayList;
	}

	private String[] getWordsFromElement(Object object) {
		String[] temp = ((String) object).split("\\ |\\,");
		return temp;
	}

}