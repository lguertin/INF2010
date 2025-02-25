public class DoublyLinkedList<AnyType>
{
    // Un noeud de la liste.
    @SuppressWarnings("hiding")
    private class Node<AnyType>
    {
        private AnyType value;
        private Node prev;
        private Node next;

        public Node(AnyType value, Node prev, Node next)
        {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public void setPrev(Node prev) { this.prev = prev; }

        public Node<AnyType> getPrev() { return this.prev; }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Node<AnyType> getNext()
        {
            return this.next;
        }

        public AnyType getValue()
        {
            return this.value;
        }
    }

    private int size = 0;		 // Nombre d'elements dans la liste.
    private Node<AnyType> front; // Premier element de la liste.
    private Node<AnyType> back;  // Dernier element de la liste.

    // Indique si la liste est vide.
    public boolean empty()
    {
        return size == 0;
    }

    // Retourne la taille de la liste.
    public int size()
    {
        return size;
    }

    // Retourne l'element a� la fin de la liste.
    // Retourne null si la liste est vide.
    // Complexite asymptotique: O(1)
    public AnyType peekBack()
    {
        if(size == 0)
        	return null;
        return back.getValue();
    }

    // Retourne l'element au debut de la liste.
    // Retourne null si la liste est vide.
    // Complexite asymptotique: O(1)
    public AnyType peekFront()
    {
    	if(size == 0)
        	return null;
    	return front.getValue();
    }

    // Retourne le noeud a� l'indice donne.
    // Complexite asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
        Node<AnyType> temp = front;
        int i = 0;
        while(i != index) {
        	temp = temp.getNext();
        	i++;
        }
        return temp;
    }

    // Retourne l'element a� l'indice donne.
    // Complexite asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException
    {
    	if(index<0 || index > size)
    		throw new IndexOutOfBoundsException("L'index non valide");
    	
    	Node<AnyType> temp = front;
        int i = 0;
        while(i != index) {
        	temp = temp.getNext();
        	i++;
        }
        return temp.getValue();
    }

    // Retire l'element a� la fin de la liste.
    // Complexite asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
    	if(empty())
    		throw new EmptyListException("Liste Vide");
    	
    	else if(size ==1){	//Si la taille est 1 alors mettre faire pointer les deux elements vers un nullptr
        		size--;
        		back = null;
        		front = null;
        }
        else{
        	back.prev.next = null;
        	back = back.prev;
        	size--;
        }
    }

    // Retire l'element au debut de la liste.
    // Complexite asymptotique: O(1)
    public void popFront() throws EmptyListException
    {	
    	if(empty())
    		throw new EmptyListException("Liste vide");
    	
    	else if(size ==1){	//Si la taille est 1 alors mettre faire pointer les deux elements vers un nullptr
        	size--;
        	back = null;
        	front = null;
        }
    	else{
    		front.next.prev = null;
    		front = front.next;
    		size--;
    	}
    }

    // Retire l'element a� l'indice donne.
    // Complexite asymptotique: O(n)
    public void removeAt(int index) throws IndexOutOfBoundsException
    {
    	if(index<0 || index > size)
    		throw new IndexOutOfBoundsException("L'index non valide");
    	
    	Node<AnyType> temp = front;
        int i = 0;
        while(i != index) {
        	temp = temp.next;
        	i++;
        }
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;
        temp.next = temp.prev = null;
        temp = null;
        size--;
    }

    // Ajoute un element a� la fin de la liste.
    // Complexite asymptotique: O(1)
    public void pushBack(AnyType item)
    {
        if(size == 0) {
        	front = new Node<AnyType>(item, null, null);
        	back = front;
        }
        else {
        	back = new Node<AnyType>(item, back, null);
        	back.prev.next = back;
        }
        size++;
    }

    // Ajoute un element au debut de la liste.
    // Complexite asymptotique: O(1)
    public void pushFront(AnyType item)
    {
    	if(size == 0) {
        	front = new Node<AnyType>(item, null, null);
        	back = front;
        }
        else {
        	front = new Node<AnyType>(item, null, front);
        	front.next.prev = front;
        }
    	size++;
    }

    // Ajoute un element a� l'indice donne.
    // Complexite asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {	
    	if(index<0 || index > size)
    		throw new IndexOutOfBoundsException("L'index non valide");
	    if(index==0)
	    		pushFront(item);
	    else if (index==size)
	    		pushBack(item);
	    else{
	        Node<AnyType> temp = getNodeAt(index);
	        Node<AnyType> newNode = new Node<AnyType>(item, temp.prev, temp.next);
	        newNode.prev.next = newNode;
	        newNode.next.prev = newNode;
	        size++;
	    }
    }
}