package probleme2;

public class DoubleHashingTable<AnyType> {
	private static final int DEFAULT_TABLE_SIZE = 13;
	private HashEntry<AnyType>[] array;
	private int currentSize;
	private int R;

	public DoubleHashingTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public DoubleHashingTable(int size) {
		R = 3;
		allocateArray(size);
		makeEmpty();
	}

	public static class HashEntry<AnyType> {
		public AnyType element;
		public boolean isActive;

		public HashEntry(AnyType element) {
			this(element, true);
		}

		public HashEntry(AnyType element, boolean active) {
			element = element;
			isActive = active;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void allocateArray(int arraySize) {
		array = (HashEntry<AnyType>[]) new Object[arraySize];
	}

	public void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}

	private boolean isActive(int currentPos) {
		return (array[currentPos].isActive && array[currentPos] != null);
	}

	private int myHash(AnyType element) {
		int x = element.hashCode();
		x %= array.length;
		
		if(x < 0)
			x += array.length;
		return x;
	}

	private int getKey(AnyType x) {
		int offset = 0;
		int currentPos = myHash(x);
		int n = array.length;

		while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
			int H1 = myHash(x);
			int H2 = (R - (x.hashCode() % R));
			currentPos = (H1 + offset * H2) % n;
			offset++;
		}

		return currentPos;
	}

	public AnyType get(AnyType x) {
		int pos = getKey(x);

		if (array[pos] != null)
			return array[pos].element;

		return null;
	}

	public AnyType getElementAtKey(int key) {
		if (this.array[key] != null && this.array[key].isActive)
			return this.array[key].element;
		return null;
	}

	public void remove(AnyType element) {
		int key = this.getKey(element);

		if (this.isActive(key))
			this.array[key].isActive = false;
	}

	public void insert(AnyType element) {
		int key = this.getKey(element);

		if (isActive(key))
			return;

		this.array[key] = new HashEntry<AnyType>(element, true);
		this.currentSize++;

		if (this.currentSize > this.array.length / 2)
			rehash();
	}

	public int nbElement() {
		return this.currentSize;
	}

	private void rehash() {
		HashEntry<AnyType>[] oldArray = this.array;
		R = this.array.length;

		int nextPrime = nextPrime(2 * oldArray.length);
		allocateArray(nextPrime);
		this.currentSize = 0;

		for (int key = 0; key < oldArray.length; key++)
			if (oldArray[key] != null && oldArray[key].isActive)
				insert(oldArray[key].element);
	}

	private static int nextPrime(int n) {
		if (n <= 0) n = 3;
		if (n % 2 == 0) n++;
		for (; !isPrime(n); n += 2);
		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 1 || n == 2 || n == 3 || n % 2 == 0) return true;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}
}