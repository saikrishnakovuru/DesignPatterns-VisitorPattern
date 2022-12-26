package iteration;

import java.util.ArrayList;

public class ArrayListIterator implements Iterator {

	private ArrayList<String> Words_list;
	private int position;

	public ArrayListIterator(ArrayList<String> Words_listIn) {
		Words_list = Words_listIn;
	}

	public boolean hasNext() {
		if (position >= Words_list.size()) {
			return false;
		}
		return true;
	}

	public String next() {
		String s = Words_list.get(position);
		position = position + 1;
		return s;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
