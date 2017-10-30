// Interface représentant une blibothèque très
// simple où il est possible de manipuler des
// livres. Ces derniers étant représentés par
// leur titre (donné sous forme de String).
interface IBibliotheque
{
    // Ajoute le livre donné à la bibliothèque.
    void ajouterLivre(String livre);

    // Indique si le livre donné est déjà
    // contenu dans la bibliothèque.
    boolean contientLivre(String livre);

    // Retourne une chaîne de charactères
    // affichant les livres de la bibliothèque
    // en ordre alphabétique. Chaque livre
    // se trouvant sur une nouvelle ligne.
    String afficherLivresAlpha();

    // Retourne une chaîne de charactères
    // affichant les livres de la bibliothèque
    // en ordre alphabétique inverse. Chaque
    // livre se trouvant sur une nouvelle ligne.
    String afficherLivresAlphaInverse();
}