import java.util.ArrayList;
import java.util.Collections;

// Implémentation de l'interface IBibliotheque
// à l'aide d'une liste.
public class BibliothequeListe implements IBibliotheque
{
    private ArrayList<String> livres = new ArrayList<String>();

    // Complexité: O(1)
    // Explication: On ne fait qu'ajouter le livre
    //              à la fin de la liste alors cela
    //              se fait en temps constant.
    public void ajouterLivre(String livre)
    {
        livres.add(livre);
    }

    // Complexité: O(n)
    // Explication: Afin de savoir si le livre se trouve
    //              dans notre liste de livres, il faut
    //              vérifier chaque livre un à un.
    public boolean contientLivre(String livre)
    {
        boolean livreTrouve = false;
        for (int i = 0; i < livres.size() && !livreTrouve; ++i) {
            livreTrouve = livres.get(i) == livre;
        }
        return livreTrouve;
    }

    // Complexité: O(n*log(n))
    // Explication: Afin d'afficher les livres en ordre,
    //              il faut tout d'abord les trier. Le
    //              minimum théorique pour un tri par
    //              comparaison (tel qu'avec Merge sort)
    //              est O(n*log(n)).
    public String afficherLivresAlpha()
    {
        Collections.sort(livres);
        String livresStr = "";
        for (String livre : livres) {
            livresStr += livre + "\n";
        }
        return livresStr;
    }

    // Complexité: O(n*log(n))
    // Explication: Afin d'afficher les livres en ordre
    //              inverse, il faut tout d'abord les trier
    //              en ordre inverse. On procède alors de
    //              la même façon que pour un tri normal.
    public String afficherLivresAlphaInverse()
    {
        Collections.sort(livres, Collections.reverseOrder());
        String livresStr = "";
        for (String livre : livres) {
            livresStr += livre + "\n";
        }
        return livresStr;
    }
}