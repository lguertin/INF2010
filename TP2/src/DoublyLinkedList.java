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
            return next;
        }

        public AnyType getValue()
        {
            return value;
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

    // Retourne l'element a  la fin de la liste.
    // Retourne null si la liste est vide.
    // Complexite asymptotique: O(1)
    public AnyType peekBack()
    {
        if(size == 0)
        	return null;
        //return this.back;
    }

    // Retourne l'element au debut de la liste.
    // Retourne null si la liste est vide.
    // Complexite asymptotique: O(1)
    public AnyType peekFront()
    {
    	if(size == 0)
        	return null;
        //return this.front;
    }

    // Retourne le noeud a  l'indice donne.
    // Complexite asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
        // a€ completer
    }

    // Retourne l'element a  l'indice donne.
    // Complexite asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException
    {
        // a€ completer
    }

    // Retire l'element a  la fin de la liste.
    // Complexite asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
        // a€ completer
    }

    // Retire l'element au debut de la liste.
    // Complexite asymptotique: O(1)
    public void popFront() throws EmptyListException
    {
        // a€ completer
    }

    // Retire l'element a  l'indice donne.
    // Complexite asymptotique: O(n)
    public void removeAt(int index) throws IndexOutOfBoundsException
    {
        // a€ completer
    }

    // Ajoute un element a  la fin de la liste.
    // Complexite asymptotique: O(1)
    public void pushBack(AnyType item)
    {
        // a€ completer
    }

    // Ajoute un element au debut de la liste.
    // Complexite asymptotique: O(1)
    public void pushFront(AnyType item)
    {
        // a€ completer
    }

    // Ajoute un element a  l'indice donne.
    // Complexite asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {
        // a€ completer
    }
}