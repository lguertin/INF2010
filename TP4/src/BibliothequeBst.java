import java.util.ArrayList;

// Implémentation de l'interface IBibliotheque
// à l'aide d'un arbre de recherche binaire.
public class BibliothequeBst implements IBibliotheque
{
    private BST<String> livres = new AvlTree<String>();

    // Complexité: O(log(n))
    // Explication: L'ajout dans un arbre binaire de recherche est toujours en log(n) lors qu'il est balance
    //				Il ne doit pas tout parcourir les elements, mais que log(n) elements	
    public void ajouterLivre(String livre)
    {
    	livres.insert(livre);
    }

    // Complexité: O(log(n))
    // Explication: La recherche dans un arbre binaire de recherche est toujours en log(n) lorsqu'il est balance
    //				Il ne doit pas tout parcourir les elements, mais que log(n) elements	
    public boolean contientLivre(String livre)
    {
    	return livres.contains(livre);
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre/ascendant.
    public String afficherLivresAlpha()
    {
    	String result = "";

        for(String str : livres.traverseInOrder())
            result += str + "\n";

        return result;
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre inverse/descendant.
    public String afficherLivresAlphaInverse()
    {
    	String result = "";

        for(String str : livres.traverseReverseOrder())
            result += str + "\n";

        return result;
    }
}