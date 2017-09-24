import java.util.Stack;

public class StackQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0; // Nombre d'éléments dans la file.
	private Stack<AnyType> inStack;
	private Stack<AnyType> outStack;
   
	@SuppressWarnings("unchecked")
	public StackQueue()
	{
        // À compléter
	}
	
	// Indique si la file est vide.
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	// Retourne la taille de la file.
	public int size() 
	{ 
		return size; 
	}
	
	// Retourne l'élément en tête de file.
	// Retourne null si la file est vide.
	// Complexité asymptotique: O(1) (ammorti)
	public AnyType peek()
    {
        // À compléter
	}
	
	// Retire l'élément en tête de file.
	// Complexité asymptotique: O(1) (ammorti)
	public void pop() throws EmptyQueueException
	{
        // À compléter
	}
	
	// Ajoute un élément a la fin de la file.
	// Complexité asymptotique: O(1)
	public void push(AnyType item)
	{
        // À compléter
	}
}

