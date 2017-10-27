package probleme2;

public class DoubleHashingTable<AnyType> {
	private static final int DEFAULT_TABLE_SIZE = 13;
	private HashEntry<AnyType>[] array;
	private int currentSize;
	private int R;
	
	/**
	 * Constructeur par defaut
	 */
	public DoubleHashingTable() {
		this(DEFAULT_TABLE_SIZE);
	}
	/**
	 * Constructeur par parametre
	 * @param size : taille du tableau a allouer
	 */
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
			this.element = element;
			this.isActive = active;
		}
	}
	
	/**
	 * Methode qui permet d'allouer de l'espace au array
	 * @param size : la taille du tableau a allouer
	 */
	@SuppressWarnings("unchecked")
	private void allocateArray(int size) {
		array = new HashEntry[size];
	}
	
	/**
	 * Methode qui permet de vider le array en affectant tous les elements a null
	 */
	public void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}
	
	/**
	 * Methode qui permet de savoir si l'element a une case est actif
	 * @param currentPos : indice de l'element qui va etre verifie
	 */
	private boolean isActive(int currentPos) {
		if(array[currentPos] == null)
			return false;
		else
			return array[currentPos].isActive;
	}

	/**
	 * Methode qui permet d'obtenir la valeur du hash d'un element (H1 dans la formule)
	 * @param x : l'element a hasher
	 * @return : La valeur du hash de l'element
	 */
	private int myHash(AnyType x) {
		int hashVal = x.hashCode();
		hashVal %= array.length;
		
		if(hashVal < 0)
			hashVal += array.length;
		return hashVal;
	}

	/**
	 * Methode qui permet d'obtenir la cle du hashing de l'element
	 * @param x : l'element a hasher
	 * @return : position valide de la cle pour l'element x
	 */
	private int getKey(AnyType x) {
		int offset = 0;
		int pos = myHash(x);
		int N = array.length;

		while (this.array[pos] != null && !this.array[pos].element.equals(x)) {
			int H1 = myHash(x);
			int H2 = (R - (x.hashCode() % R));
			pos = (H1 + offset * H2) % N;
			offset++;
		}

		return pos;
	}

	/**
	 * Methode pour avoir un element
	 * @param x : element a aller chercher
	 * @return : l'element trouve
	 */
	public AnyType get(AnyType x) {
		int pos = getKey(x);

		if (array[pos] != null)
			return array[pos].element;

		return null;
	}
	
	/**
	 * Methode qui enleve un element du tableau s'il est actif en le desactivant
	 * @param x : Element a retirer
	 */
	public void remove(AnyType x) {
		int pos = this.getKey(x);

		if (this.isActive(pos))
			this.array[pos].isActive = false;
	}
	/**
	 * Insere un element dans notre tableau
	 * @param element : element qui doit etre inserer dans le tableau 
	 */
	public void insert(AnyType x) {
		int pos = getKey(x);

		if (isActive(pos))
			return;

		array[pos] = new HashEntry<AnyType>(x, true);

		if (++currentSize > array.length/2)
			rehash();
	}
	/**
	 * Methode qui retourne le nombre d'element dans le tableau
	 */
	public int nbElement() {
		return this.currentSize;
	}
	
	/**
	 * Methode de rehashage de notre tableau. Alloue un nouveau tableau plus grand
	 */
	private void rehash() {
		HashEntry<AnyType>[] oldArray = array;
		R = array.length;
		
		allocateArray(nextPrime(oldArray.length*2));
		currentSize = 0;

		for (int pos = 0; pos < oldArray.length; pos++)
			if (oldArray[pos] != null && oldArray[pos].isActive)
				insert(oldArray[pos].element);
	}
	/**
	 * Methode  qui determine le prochain entier premier
	 * @param n : Valeur a partir de laquelle on doit trouver le prochain entier
	 */
	private static int nextPrime(int n) {
		if (n <= 0) n = 3;
		if (n % 2 == 0) n++;
		for (; !isPrime(n); n += 2);
		return n;
	}
	/**
	 * Methode qui determine si un entier est premier
	 * @param n : Valeur qui va etre tester
	 */
	private static boolean isPrime(int n) {
		if (n == 1 || n == 2 || n == 3 || n % 2 == 0) return true;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}
	/**
	 * Methode qui permet d'aller chercher un element a partir d'une cle
	 * @param key : cle a laquelle nous voulons avoir un objet
	 * @return : retourne l'objet a la cle
	 */
	public AnyType getElementFromKey(int key) {
		if(key > 0 && key < currentSize && array[key].element != null && array[key].isActive)
			return array[key].element;
		return null;
	}
}