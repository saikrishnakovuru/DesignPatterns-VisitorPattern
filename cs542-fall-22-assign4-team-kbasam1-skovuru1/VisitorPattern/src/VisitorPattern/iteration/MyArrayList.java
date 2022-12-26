package iteration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myVisitor.Visitor;
import util.FileProcessor;

public class MyArrayList implements Container {

	private List<String> arrayList;
	private FileProcessor fp;
	private Iterator it;

	public MyArrayList(Builder builder) {
		this.fp = builder.fp;
		this.arrayList = builder.arrayList;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Iterator getIterator() {
		return new ElementIterator();
	}

	private class ElementIterator implements Iterator {
		int index = 0;

		@Override
		public boolean hasNext() {
			if (index < arrayList.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (this.hasNext()) {
				return arrayList.get(index++);
			}
			return null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static class Builder {
		private FileProcessor fp;
		private List<String> arrayList;

		public Builder() {
			arrayList = new ArrayList<>();
		}

		public Builder setFp(FileProcessor fp) {
			this.fp = fp;
			return this;
		}

		public Builder withFileProcessor(FileProcessor fileProcessor) {
			setFp(fileProcessor);
			return this;
		}

		public MyArrayList build() {
			try {
				String line = fp.poll();
				String[] sentences = line.trim().split("\\.\\s*");
				for (String s : sentences) {
					arrayList.add(s);
				}
				fp.close();
			} catch (IOException e) {
				System.err.println("Exception: ");
				System.out.println("Exception: " + e.getMessage().getClass().getName());
				e.printStackTrace();
			}
			return new MyArrayList(this);
		}
	}
}
