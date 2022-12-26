package myVisitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import iteration.Iterator;
import iteration.MyArrayList;
import results.Results;
import results.TopKFreqWordsResults;

public class TopKMostFreqVisitor implements Visitor {
	private int k;
	private Results topKFreqWordsResults;
	private Map<String, Integer> result;
	private ArrayList<String> topFreq;

	public TopKMostFreqVisitor(String arg, Results topKFreqWordsResultsIn) {
		k = Integer.parseInt(arg);
		topKFreqWordsResults = topKFreqWordsResultsIn;
	}

	@Override
	public void visit(MyArrayList myArray) {
		calculateTopKFreqWords(myArray);
		((TopKFreqWordsResults) topKFreqWordsResults).addResult(result);
		((TopKFreqWordsResults) topKFreqWordsResults).addTopFreq(topFreq);
	}

	private String[] getWordsFromElement(Object object) {
		String[] temp = ((String) object).split("\\ |\\,");
		return temp;
	}

	public void calculateTopKFreqWords(MyArrayList myArray) {
		result = new HashMap<>();
		topFreq = new ArrayList<>();
		Iterator it = myArray.getIterator();
		while (it.hasNext()) {
			String[] words = getWordsFromElement(it.next());

			for (String w : words) {
				result.put(w.trim().toLowerCase(), result.getOrDefault(w.trim().toLowerCase(), 0) + 1);
			}
		}

		PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
			public int compare(String word1, String word2) {
				int freq1 = result.get(word1);
				int freq2 = result.get(word2);
				if (freq1 == freq2) {
					return word2.compareTo(word1);
				}
				return freq2 - freq1;
			}
		});

		for (Map.Entry<String, Integer> entry : result.entrySet()) {
			pq.add(entry.getKey());
		}

		for (int i = 0; i < k; i++) {
			topFreq.add(pq.poll());
		}
	}
}
