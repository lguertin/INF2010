import com.sun.corba.se.impl.orb.ParserTable;

public class BibliothequeMain
{
    public static void main(String[] args)
    {
        testBibliothequeListe();
        testBibliothequeBst();
    }

    private static boolean isValidContient(IBibliotheque biblio, String livre, boolean expectedContient)
    {
        boolean actualContient = biblio.contientLivre(livre);
        boolean isValid = actualContient == expectedContient;
        if (!isValid) {
            System.out.println("ERREUR: La recherche d'un élément est incorrecte.\nAttendu: "
                    +  livre +  (expectedContient ? " est contenue." : " n'est pas contenue.")
                    + "\nReçu: " +  livre +  (actualContient ? " est contenue." : " n'est pas contenue."));
        }
        return isValid;
    }

    private static boolean testContientLivre(IBibliotheque biblio)
    {
        String[] livres = new String[] {"PolyPepe", "My Little Poly", "PolyJam", "PolyHx"};
        String[] livresNonPresent = new String[] {"PolyPhoto", "PolyTV"};
        for (String livre : livres) {
            biblio.ajouterLivre(livre);
        }

        for (String livre : livres) {
            if (!isValidContient(biblio, livre, true)) {
                return false;
            }
        }

        for (String livre : livresNonPresent) {
            if (!isValidContient(biblio, livre, false)) {
                return false;
            }
        }

        return true;
    }

    private static boolean testAfficherLivresAlpha(IBibliotheque biblio)
    {
        String[] livres = new String[] {"PolyPepe", "My Little Poly", "PolyJam", "PolyHx"};
        for (String livre : livres) {
            biblio.ajouterLivre(livre);
        }

        String expectedLivresAlpha = "My Little Poly\nPolyHx\nPolyJam\nPolyPepe\n";
        String actualLivresAlpha = biblio.afficherLivresAlpha();
        boolean isValidAfficherAlpha = expectedLivresAlpha.equals(actualLivresAlpha);
        if (!isValidAfficherAlpha) {
            System.out.println("ERREUR: L'affichage en ordre alphabétique est incorrecte. \nAttendu: "
                               + expectedLivresAlpha + "\nReçu: " + actualLivresAlpha);
        }

        return isValidAfficherAlpha;
    }

    private static boolean testAfficherLivresAlphaInverse(IBibliotheque biblio)
    {
        String[] livres = new String[] {"PolyPepe", "My Little Poly", "PolyJam", "PolyHx"};
        for (String livre : livres) {
            biblio.ajouterLivre(livre);
        }

        String expectedLivresAlphaInv = "PolyPepe\nPolyJam\nPolyHx\nMy Little Poly\n";
        String actualLivresAlphaInv = biblio.afficherLivresAlphaInverse();
        boolean isValidAfficherAlphaInv = expectedLivresAlphaInv.equals(actualLivresAlphaInv);
        if (!isValidAfficherAlphaInv) {
            System.out.println("ERREUR: L'affichage en ordre alphabétique inverse est incorrecte. \nAttendu: "
                    + expectedLivresAlphaInv + "\nReçu: " + actualLivresAlphaInv);
        }

        return isValidAfficherAlphaInv;
    }

    private static void testBibliothequeListe()
    {
        IBibliotheque biblio1 = new BibliothequeListe();
        if (!testContientLivre(biblio1)) {
            return;
        }

        IBibliotheque biblio2 = new BibliothequeListe();
        if (!testAfficherLivresAlpha(biblio2)) {
            return;
        }

        IBibliotheque biblio3 = new BibliothequeListe();
        if (!testAfficherLivresAlphaInverse(biblio3)) {
            return;
        }

        System.out.println("Tous les tests de BibliothequeListe ont passé!");
    }

    private static void testBibliothequeBst()
    {
        IBibliotheque biblio1 = new BibliothequeBst();
        if (!testContientLivre(biblio1)) {
            return;
        }

        IBibliotheque biblio2 = new BibliothequeBst();
        if (!testAfficherLivresAlpha(biblio2)) {
            return;
        }

        IBibliotheque biblio3 = new BibliothequeBst();
        if (!testAfficherLivresAlphaInverse(biblio3)) {
            return;
        }

        System.out.println("Tous les tests de BibliothequeBst ont passé!");
    }
}