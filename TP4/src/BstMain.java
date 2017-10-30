import jdk.nashorn.internal.runtime.ECMAException;

import java.util.Arrays;

public class BstMain
{
    public static void main(String[] args)
    {
        testRemainsValid();
        testHeight();
        testContains();
        testPostOrderTraversal();
        testPreOrderTraversal();
        testInOrderTraversal();
        testReverseOrderTraversal();
        testLevelOrderTraversal();
    }


    private static void testRemainsValid()
    {
        BST<Integer> tree = new BST<>();
        int[] arr = new int[] {13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr) {
            tree.insert(val);
            if (!tree.isValid()) {
                System.out.println("ERREUR: Les critères d'un arbre binaire de recherche ne sont pas toujours respectés.");
                return;
            }
        }
        System.out.println("Tous les tests sur les critères de validité de l'arbre ont passé!");
    }


    private static boolean isValidHeight(BST tree, int expectedHeight)
    {
        int actualHeight = tree.getHeight();
        boolean isValid = actualHeight == expectedHeight;
        if (!isValid) {
            System.out.println("ERREUR: Hauteur de l'arbre incorrecte. \nAttendu: " +
                               + expectedHeight + "\nReçu: " + actualHeight);
        }
        return isValid;
    }

    private static void testHeight()
    {
        // Test 1
        BST<Integer> tree1 = new BST<>();
        int[] arr1 = new int[] {13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr1) {
            tree1.insert(val);
        }
        if (!isValidHeight(tree1, 5)) {
            return;
        }

        // Test 2
        BST<Integer> tree2 = new BST<>();
        tree2.insert(10);
        if (!isValidHeight(tree2, 0)) {
            return;
        }

        // Test 3
        BST<Integer> tree3 = new BST<>();
        int[] arr3 = new int[] {10, 5, 15};
        for (int val : arr3) {
            tree3.insert(val);
        }
        if (!isValidHeight(tree3, 1)) {
            return;
        }

        System.out.println("Tous les tests de hauteur ont passé!");
    }


    private static boolean isValidContains(BST tree, int value, boolean expectedContains)
    {
        boolean actualContains = tree.contains(value);
        boolean isValid = actualContains == expectedContains;
        if (!isValid) {
            System.out.println("ERREUR: La recherche d'un élément est incorrecte.\nAttendu: "
                               +  value +  (expectedContains ? " est contenue." : " n'est pas contenue.")
                               + "\nReçu: " +  value +  (actualContains ? " est contenue." : " n'est pas contenue."));
        }
        return isValid;
    }

    private static void testContains()
    {
        BST<Integer> tree = new BST<>();
        Integer[] arr1 = new Integer[] {13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        Integer[] arr2 = new Integer[] {14, 1, 3, 30, 50, -1};
        for (int val : arr1) {
            tree.insert(val);
        }

        for (int val: arr1) {
            if (!isValidContains(tree, val, true)) {
                return;
            }
        }

        for (int val: arr2) {
            if (!isValidContains(tree, val, false)) {
                return;
            }
        }

        System.out.println("Tous les tests de recherche d'un élément ont passé!");
    }


    private static boolean isValidOrder(Integer[] actualOrder, Integer[] expectedOrder, String orderName)
    {
        String actualOrderStr = Arrays.toString(actualOrder);
        String expectedOrderStr = Arrays.toString(expectedOrder);
        boolean isValid = actualOrderStr.equals(expectedOrderStr);
        if (!isValid) {
            System.out.println("ERREUR: Parcours " + orderName + " non respecté. \nAttendu: "
                               + expectedOrderStr + "\nReçu: " + actualOrderStr);
        }
        return isValid;
    }

    private static void testPreOrderTraversal() {
        // Test 1
        BST<Integer> tree1 = new BST<>();
        Integer[] arr1 = new Integer[]{13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr1) {
            tree1.insert(val);
        }
        Integer[] expectedOrder1 = new Integer[]{13, 10, 7, 4, 5, 6, 8, 11, 12, 20, 15, 22, 21, 23};
        Integer[] actualOrder1 = tree1.traversePreOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder1, expectedOrder1, "pré-ordre")) {
            return;
        }

        // Test 2
        BST<Integer> tree2 = new BST<>();
        Integer[] expectedOrder2 = new Integer[0];
        Integer[] actualOrder2 = tree2.traversePreOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder2, expectedOrder2, "pré-ordre")) {
            return;
        }

        // Test 3
        BST<Integer> tree3 = new BST<>();
        Integer[] arr3 = new Integer[]{5, 10, 12, 9};
        for (int val : arr3) {
            tree3.insert(val);
        }
        Integer[] expectedOrder3 = new Integer[]{5, 10, 9, 12};
        Integer[] actualOrder3 = tree3.traversePreOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder3, expectedOrder3, "pré-ordre")) {
            return;
        }

        System.out.println("Tous les tests de parcours en pré-ordre ont passé!");
    }


    private static void testPostOrderTraversal()
    {
        // Test 1
        BST<Integer> tree1 = new BST<>();
        Integer[] arr1 = new Integer[] {13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr1) {
            tree1.insert(val);
        }
        Integer[] expectedOrder1 = new Integer[] {6, 5, 4, 8, 7, 12, 11, 10, 15, 21, 23, 22, 20, 13};
        Integer[] actualOrder1 = tree1.traversePostOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder1, expectedOrder1, "post-ordre")) {
            return;
        }

        // Test 2
        BST<Integer> tree2 = new BST<>();
        Integer[] expectedOrder2 = new Integer[0];
        Integer[] actualOrder2 = tree2.traversePostOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder2, expectedOrder2, "post-ordre")) {
            return;
        }

        // Test 3
        BST<Integer> tree3 = new BST<>();
        Integer[] arr3 = new Integer[] {5, 10, 12, 9};
        for (int val : arr3) {
            tree3.insert(val);
        }
        Integer[] expectedOrder3 = new Integer[] {9, 12, 10, 5};
        Integer[] actualOrder3 = tree3.traversePostOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder3, expectedOrder3, "post-ordre")) {
            return;
        }

        System.out.println("Tous les tests de parcours en post-ordre ont passé!");
    }


    private static void testInOrderTraversal()
    {
        // Test 1
        BST<Integer> tree1 = new BST<>();
        Integer[] arr1 = new Integer[] {13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr1) {
            tree1.insert(val);
        }
        Integer[] expectedOrder1 = new Integer[] {4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 20, 21, 22, 23};
        Integer[] actualOrder1 = tree1.traverseInOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder1, expectedOrder1, "en ordre")) {
            return;
        }

        // Test 2
        BST<Integer> tree2 = new BST<>();
        Integer[] expectedOrder2 = new Integer[0];
        Integer[] actualOrder2 = tree2.traverseInOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder2, expectedOrder2, "en ordre")) {
            return;
        }

        // Test 3
        BST<Integer> tree3 = new BST<>();
        Integer[] arr3 = new Integer[] {5, 10, 12, 9};
        for (int val : arr3) {
            tree3.insert(val);
        }
        Integer[] expectedOrder3 = new Integer[] {5, 9, 10, 12};
        Integer[] actualOrder3 = tree3.traverseInOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder3, expectedOrder3, "en ordre")) {
            return;
        }

        System.out.println("Tous les tests de parcours en ordre ont passé!");
    }


    private static void testReverseOrderTraversal()
    {
        // Test 1
        BST<Integer> tree1 = new BST<>();
        Integer[] arr1 = new Integer[] {13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr1) {
            tree1.insert(val);
        }
        Integer[] expectedOrder1 = new Integer[] {23, 22, 21, 20, 15, 13, 12, 11, 10, 8, 7, 6, 5, 4};
        Integer[] actualOrder1 = tree1.traverseReverseOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder1, expectedOrder1, "en ordre inverse")) {
            return;
        }

        // Test 2
        BST<Integer> tree2 = new BST<>();
        Integer[] expectedOrder2 = new Integer[0];
        Integer[] actualOrder2 = tree2.traverseReverseOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder2, expectedOrder2, "en ordre inverse")) {
            return;
        }

        // Test 3
        BST<Integer> tree3 = new BST<>();
        Integer[] arr3 = new Integer[] {5, 10, 12, 9};
        for (int val : arr3) {
            tree3.insert(val);
        }
        Integer[] expectedOrder3 = new Integer[] {12, 10, 9, 5};
        Integer[] actualOrder3 = tree3.traverseReverseOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder3, expectedOrder3, "en ordre inverse")) {
            return;
        }

        System.out.println("Tous les tests de parcours en ordre inverse ont passé!");
    }


    private static void testLevelOrderTraversal()
    {
        // Test 1
        BST<Integer> tree1 = new BST<>();
        Integer[] arr1 = new Integer[] {13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr1) {
            tree1.insert(val);
        }
        Integer[] expectedOrder1 = new Integer[] {13, 10, 20, 7, 11, 15, 22, 4, 8, 12, 21, 23, 5, 6};
        Integer[] actualOrder1 = tree1.traverseLevelOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder1, expectedOrder1, "par niveau")) {
            return;
        }

        // Test 2
        BST<Integer> tree2 = new BST<>();
        Integer[] expectedOrder2 = new Integer[0];
        Integer[] actualOrder2 = tree2.traverseLevelOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder2, expectedOrder2, "par niveau")) {
            return;
        }

        // Test 3
        BST<Integer> tree3 = new BST<>();
        Integer[] arr3 = new Integer[] {5, 10, 12, 9};
        for (int val : arr3) {
            tree3.insert(val);
        }
        Integer[] expectedOrder3 = new Integer[] {5, 10, 9, 12};
        Integer[] actualOrder3 = tree3.traverseLevelOrder().toArray(new Integer[0]);
        if (!isValidOrder(actualOrder3, expectedOrder3, "par niveau")) {
            return;
        }

        System.out.println("Tous les test de parcours par niveau ont passé!");
    }
}