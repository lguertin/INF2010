public class ListMain
{
    final static int COUNT = 1013;

    public static void main(String[] args)
    {
        boolean queueTestResult = testQueueBehaviour();
        boolean stackTestResult = testStackBehaviour();
        boolean listTestResult = testListBehaviour();

        if (queueTestResult && stackTestResult && listTestResult) {
            System.out.print("DoublyLinkedList: It's all good!");
        }
    }

    private static boolean testQueueBehaviour()
    {
        DoublyLinkedList<Integer> queue = new DoublyLinkedList<Integer>();
        int pushCounter = 0;
        int popCounter = 0;

        for(int i = 0; i < COUNT * 2; i++) {
            queue.pushFront(pushCounter++);
        }

        if(queue.size() != COUNT * 2) {
            System.out.println("Erreur: La taille de la liste n'est pas égale à " + COUNT * 2 + " après avoir ajouté " + COUNT * 2 + " éléments.");
        }

        for(int i = 0; i < COUNT; i++) {
            try {
                queue.popBack();
                popCounter++;
            }
            catch (EmptyListException e) {
                e.printStackTrace();
                return false;
            }
        }

        for(int i = 0; i < COUNT * 2.5; i++) {
            queue.pushFront(pushCounter++);
        }

        for(int i = 0; i < COUNT * 3.5; i++) {
            if(queue.peekBack() != popCounter) {
                System.out.println("Erreur: l'ordre des éléments n'est pas respecté.");
                return false;
            }

            try {
                queue.popBack();
                popCounter++;
            }
            catch (EmptyListException e) {
                e.printStackTrace();
                return false;
            }
        }

        if(!queue.empty()) {
            System.out.println("Erreur: la liste devrait être vide, mais elle ne l'est pas.");
            return false;
        }

        if(queue.peekBack() != null) {
            System.out.println("Erreur: peekBack() doit retourner null lorsque la liste est vide.");
            return false;
        }

        try {
            queue.popBack();
            System.out.println("Erreur: popBack() doit lancer une exception lorsque la liste est vide.");
            return false;
        }
        catch (EmptyListException e){}

        return true;
    }

    private static boolean testStackBehaviour()
    {
        DoublyLinkedList<Integer> stack = new DoublyLinkedList<>();

        int pushCounter = 0;

        for (int i = 0; i < COUNT; i++) {
            stack.pushBack(++pushCounter);
        }

        if (stack.size() != COUNT) {
            System.out.println("Erreur: La taille de la liste n'est pas égale a " + COUNT + " après avoir ajouté " + COUNT + " éléments");
        }

        for (int i = 0; i < COUNT; i++) {
            try {
                if (stack.peekBack() != pushCounter) {
                    System.out.println("Erreur: l'ordre des éléments n'est pas respecté.");
                    return false;
                }
                stack.popBack();
                pushCounter--;

            }
            catch (EmptyListException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (!stack.empty()) {
            System.out.println("Erreur: la liste devrait être vide, mais elle ne l'est pas.");
            return false;
        }

        if (stack.peekBack() != null) {
            System.out.println("Erreur: peekBack() doit retourner null lorsque la liste est vide.");
            return false;
        }

        try {
            stack.popBack();
            System.out.println("Erreur: popBack() doit lancer une exception lorsque la liste est vide.");
            return false;
        }
        catch (EmptyListException e) {}

        return true;
    }

    private static boolean testListBehaviour()
    {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        for(int i = 0; i < COUNT; i++) {
            list.pushBack(i * 2);
        }

        if (list.size() != COUNT) {
            System.out.println("Erreur: La taille de la liste n'est pas égale à " + COUNT + " après avoir ajouté " + COUNT + " éléments");
        }

        try {
            list.insertAt(-1, 0);
            if (list.peekFront() != -1) {
                System.out.println("Erreur: insertAt() n'insere pas la valeur à la bonne position.");
                return false;
            }
            list.popFront();
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
        catch (EmptyListException e) {
            e.printStackTrace();
            return false;
        }

        try {
            for (int i = 0; i < COUNT; ++i) {
                if (list.getAt(i) != i * 2) {
                    System.out.println("Erreur: getAt() ne retourne pas la bonne valeur pour l'indice donné.");
                    return false;
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        try {
            list.removeAt(COUNT - 3);
            if (list.getAt(COUNT - 3) != (COUNT - 2) * 2 || list.size() != COUNT-1) {
                System.out.println("Erreur: removeAt() n'a pas bien retire l'element à l'indice donné.");
                return false;
            }
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        try {
            list.getAt(-1);
            System.out.println("Erreur: getAt() devrait lancer une exception lorsque l'indice est en dehors du tableau. ");
            return false;
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            list.getAt(COUNT + 1);
            System.out.println("Erreur: getAt() devrait lancer une exception lorsque l'indice est en dehors du tableau. ");
            return false;
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            list.removeAt(-1);
            System.out.println("Erreur: removeAt() devrait lancer une exception lorsque l'indice est en dehors du tableau. ");
            return false;
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            list.removeAt(COUNT + 1);
            System.out.println("Erreur: removeAt() devrait lancer une exception lorsque l'indice est en dehors du tableau. ");
            return false;
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            list.insertAt(0, -1);
            System.out.println("Erreur: insertAt() devrait lancer une exception lorsque l'indice est en dehors du tableau. ");
            return false;
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            list.insertAt(0, COUNT + 1);
            System.out.println("Erreur: insertAt() devrait lancer une exception lorsque l'indice est en dehors du tableau. ");
            return false;
        }
        catch (IndexOutOfBoundsException e) {}

        for (int i = 0; i < COUNT-1; ++i) {
            try {
                list.popFront();
            }
            catch (EmptyListException e) {
                e.printStackTrace();
                return false;
            }
        }

        if(!list.empty()) {
            System.out.println("Erreur: la liste devrait être vide, mais elle ne l'est pas.");
            return false;
        }

        if(list.peekBack() != null) {
            System.out.println("Erreur: peekBack() doit retourner null lorsque la liste est vide.");
            return false;
        }

        if(list.peekFront() != null) {
            System.out.println("Erreur: peekFront() doit retourner null lorsque la liste est vide.");
            return false;
        }

        try {
            list.popBack();
            System.out.println("Erreur: popBack() doit lancer une exception lorsque la liste est vide.");
            return false;
        }
        catch (EmptyListException e){}

        try {
            list.popFront();
            System.out.println("Erreur: popFront() doit lancer une exception lorsque la liste est vide.");
            return false;
        }
        catch (EmptyListException e){}

        return true;
    }

}
