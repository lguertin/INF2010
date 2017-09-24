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

    private int size = 0;		 // Nombre d'éléments dans la liste.
    private Node<AnyType> front; // Premier élément de la liste.
    private Node<AnyType> back;  // Dernier élément de la liste.

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

    // Retourne l'élément à la fin de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekBack()
    {
        // À compléter
    }

    // Retourne l'élément au début de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekFront()
    {
        // À compléter
    }

    // Retourne le noeud à l'indice donné.
    // Complexité asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
        // À compléter
    }

    // Retourne l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException
    {
        // À compléter
    }

    // Retire l'élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
        // À compléter
    }

    // Retire l'élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void popFront() throws EmptyListException
    {
        // À compléter
    }

    // Retire l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public void removeAt(int index) throws IndexOutOfBoundsException
    {
        // À compléter
    }

    // Ajoute un élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void pushBack(AnyType item)
    {
        // À compléter
    }

    // Ajoute un élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void pushFront(AnyType item)
    {
        // À compléter
    }

    // Ajoute un élément à l'indice donné.
    // Complexité asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {
        // À compléter
    }
}