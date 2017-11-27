package PolyNet;

public class PolyNetMain {
    public static void main(String[] args)
    {
        boolean isTest1Correct = test1();
        boolean isTest2Correct = test2();
        boolean isTest3Correct = test3();

        if (isTest1Correct && isTest2Correct && isTest3Correct) {
            System.out.println("PolyNet : Tous les tests passent!");
        }
    }

    private static void connect(PolyNetNode node1, PolyNetNode node2, int distance)
    {
        node1.addConnection(node2, distance);
        node2.addConnection(node1, distance);
    }

    private static boolean test1()
    {
        // Arrange
        PolyNetNode montrealNode = new PolyNetNode();
        PolyNetNode newYorkNode = new PolyNetNode();
        PolyNetNode ottawaNode = new PolyNetNode();
        PolyNetNode quebecNode = new PolyNetNode();
        PolyNetNode sherbrookeNode = new PolyNetNode();
        PolyNetNode torontoNode = new PolyNetNode();
        PolyNetNode troisRivieresNode = new PolyNetNode();

        connect(montrealNode, newYorkNode, 11);
        connect(montrealNode, ottawaNode, 2);
        connect(montrealNode, quebecNode, 3);
        connect(montrealNode, sherbrookeNode, 2);
        connect(montrealNode, torontoNode, 7);
        connect(montrealNode, troisRivieresNode, 2);
        connect(newYorkNode, sherbrookeNode, 10);
        connect(newYorkNode, torontoNode, 5);
        connect(ottawaNode, torontoNode, 6);
        connect(quebecNode, sherbrookeNode, 5);
        connect(quebecNode, troisRivieresNode, 2);

        PolyNetNode[] nodes = {montrealNode, newYorkNode, ottawaNode,
                               quebecNode, sherbrookeNode, torontoNode, troisRivieresNode};

        PolyNet network = new PolyNet(nodes);

        int expectedCableLength = 19;

        // Act
        int actualCableLength = network.computeTotalCableLength();

        // Asset
        boolean isCorrect = actualCableLength == expectedCableLength;
        if (!isCorrect) {
            System.out.println("ERREUR (PolyNet) - Test #1: La longueur de cable attendue est de "
                               + expectedCableLength + " mais la valeur obtenue est " + actualCableLength);
        }

        return isCorrect;
    }

    private static boolean test2()
    {
        // Arrange
        PolyNetNode montrealNode = new PolyNetNode();
        PolyNetNode newYorkNode = new PolyNetNode();
        PolyNetNode ottawaNode = new PolyNetNode();
        PolyNetNode quebecNode = new PolyNetNode();
        PolyNetNode sherbrookeNode = new PolyNetNode();
        PolyNetNode torontoNode = new PolyNetNode();
        PolyNetNode troisRivieresNode = new PolyNetNode();

        connect(montrealNode, newYorkNode, 8);
        connect(montrealNode, ottawaNode, 2);
        connect(montrealNode, quebecNode, 4);
        connect(montrealNode, sherbrookeNode, 2);
        connect(montrealNode, torontoNode, 6);
        connect(montrealNode, troisRivieresNode, 3);
        connect(newYorkNode, sherbrookeNode, 10);
        connect(newYorkNode, torontoNode, 9);
        connect(ottawaNode, torontoNode, 7);
        connect(quebecNode, sherbrookeNode, 6);
        connect(quebecNode, troisRivieresNode, 4);

        PolyNetNode[] nodes = {montrealNode, newYorkNode, ottawaNode,
                quebecNode, sherbrookeNode, torontoNode, troisRivieresNode};

        PolyNet network = new PolyNet(nodes);

        int expectedCableLength = 25;

        // Act
        int actualCableLength = network.computeTotalCableLength();

        // Asset
        boolean isCorrect = actualCableLength == expectedCableLength;
        if (!isCorrect) {
            System.out.println("ERREUR (PolyNet) - Test #2: La longueur de cable attendue est de "
                               + expectedCableLength + " mais la valeur obtenue est " + actualCableLength);
        }

        return isCorrect;
    }

    private static boolean test3()
    {
        // Arrange
        PolyNetNode montrealNode = new PolyNetNode();
        PolyNetNode newYorkNode = new PolyNetNode();
        PolyNetNode ottawaNode = new PolyNetNode();
        PolyNetNode quebecNode = new PolyNetNode();
        PolyNetNode sherbrookeNode = new PolyNetNode();
        PolyNetNode torontoNode = new PolyNetNode();
        PolyNetNode troisRivieresNode = new PolyNetNode();

        connect(montrealNode, ottawaNode, 15);
        connect(montrealNode, quebecNode, 10);
        connect(newYorkNode, ottawaNode, 6);
        connect(newYorkNode, torontoNode, 8);
        connect(ottawaNode, torontoNode, 3);
        connect(quebecNode, sherbrookeNode, 7);
        connect(quebecNode, troisRivieresNode, 2);
        connect(sherbrookeNode, troisRivieresNode, 5);

        PolyNetNode[] nodes = {montrealNode, newYorkNode, ottawaNode,
                quebecNode, sherbrookeNode, torontoNode, troisRivieresNode};

        PolyNet network = new PolyNet(nodes);

        int expectedCableLength = 41;

        // Act
        int actualCableLength = network.computeTotalCableLength();

        // Asset
        boolean isCorrect = actualCableLength == expectedCableLength;
        if (!isCorrect) {
            System.out.println("ERREUR (PolyNet) - Test #3: La longueur de cable attendue est de "
                               + expectedCableLength + " mais la valeur obtenue est " + actualCableLength);
        }

        return isCorrect;
    }
}
