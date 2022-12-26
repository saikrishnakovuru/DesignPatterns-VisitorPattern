package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import iteration.Iterator;
import iteration.MyArrayList;

public class CaseSensitiveStrategy implements CheckStrategy {

	List<String> arrayList = new ArrayList<>();

	@Override
	public List<String> performSpellCheck(MyArrayList myArray, Map<String, String> sensitiveMap) {
		StringBuilder sb;
		Iterator it = myArray.getIterator();
		while (it.hasNext()) {
			sb = new StringBuilder();
			String[] words = getWordsFromElement(it.next());
			for (String w : words) {
				if (sensitiveMap.containsKey(w)) {
					sb.append(sensitiveMap.get(w));
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
