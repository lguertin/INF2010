import java.util.EmptyStackException;

public class ArrayStack<AnyType>
{
    private static final int INITIAL_SIZE = 10;
    private static final int DEFAULT_RESIZE_FACTOR = 2;

    private int size = 0; // Nombre d'elements dans la pile.
    private AnyType[] table;

    // Initialisation de la pile.
    @SuppressWarnings("unchecked")
	public ArrayStack()
    {
    	table = (AnyType[])new Object[INITIAL_SIZE];
    }

    // Enlève l'element au sommet de la pile et le renvoie.
    // Complexité asymptotique: O(1)
    public AnyType pop() throws EmptyStackException
    {
    	if(empty())
    		throw new EmptyStackException();
    	
    	AnyType element = table[size-1];
    	size--;								// Reduit la taille du tableau
        return element;
    }

    // Ajoute un element au sommet de la pile.
    // Augmente la taille de la pile si nécessaire (utiliser la fonction resize définie plus bas).
    // Complexité asymptotique: O(1) (O(N) lorsqu'un redimensionnement est nécessaire)
    public void push(AnyType element)
    {
        if(size == table.length)
        	resize(DEFAULT_RESIZE_FACTOR);	// Augmente la taille du tableau s'il est plein
        
        table[size] = element;
        size++;
    }

    // Renvoie l'element au sommet de la pile sans l'enlever.
    // Retourne null si la pile est vide.
    // Complexité asymptotique: O(1)
    public AnyType peek()
    {
    	if(empty())
    		return null;
    	
        return table[size-1];
    }

    // Renvoie le nombre d'elements dans la pile.
    public int size() { return size; }

    // Indique si la pile est vide.
    public boolean empty() { return size == 0; }

    // Redimensionne la pile. La capacité est multipliée par un facteur de resizeFactor.
    // Complexité asymptotique: O(N)
    @SuppressWarnings("unchecked")
    private void resize(int resizeFactor)
    {
        AnyType[] tableTemp = (AnyType[])new Object[table.length*resizeFactor];
        
        for(int i = 0; i < this.size; i++){
        	tableTemp[i] = table[i];		//Copie des elements
        }
        
        table = tableTemp;
    }
}
