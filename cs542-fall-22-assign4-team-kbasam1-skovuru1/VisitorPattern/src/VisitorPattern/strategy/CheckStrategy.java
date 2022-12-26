package strategy;

import java.util.List;
import java.util.Map;

import iteration.MyArrayList;

public interface CheckStrategy {
	public List<String> performSpellCheck(MyArrayList myArray, Map<String, String> usToUkmap);
}
