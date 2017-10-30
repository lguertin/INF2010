
public class AvlMain
{
    public static void main(String[] args)
    {
        testRemainsBalanced();
        testRemainsValid();
    }

    private static void testRemainsBalanced()
    {
        AvlTree<Integer> tree1 = new AvlTree<>();
        Integer[] arr1 = new Integer[]{13, 10, 20, 15, 22, 23, 21, 11, 7, 8, 12, 4, 5, 6};
        for (int val : arr1) {
            tree1.insert(val);
            if (!tree1.isBalanced()) {
                System.out.println("ERREUR: Le balancement de l'arbre n'est pas maintenu.");
                return;
            }
        }
        System.out.println("Tous les tests du maintient du balancement ont passé!");
    }

    private static void testRemainsValid()
    {
        AvlTree<Integer> tree = new AvlTree<>();
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
}
