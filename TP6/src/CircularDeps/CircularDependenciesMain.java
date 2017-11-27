package CircularDeps;

/**
 * Created by Rémi Pelletier on 2017-11-16.
 *
 * Exercice sur la recherche de cycle dans un graphe dirigé.
 * Ici le graphe est composé de fichiers de code source ayant
 * chacun des dépendances sur d'autres fichiers. On désire
 * alors trouver s'il existe des dépendances circulaires
 * dans le groupe de fichiers.
 */
public class CircularDependenciesMain
{
    public static void main(String[] args)
    {
        boolean isDirectCircularDepsCorrect = testDirectCircularDependency();
        boolean isIndirectCircularDepsCorrect = testIndirectCircularDependency();
        boolean isNoCircularDepsCorrect = testNoCircularDependency();

        if (isDirectCircularDepsCorrect
         && isIndirectCircularDepsCorrect
         && isNoCircularDepsCorrect) {
            System.out.println("Circular dependencies : Tous les tests passent!");
        }
    }

    private static boolean testDirectCircularDependency()
    {
        // Arrange
        SourceFile carFile = new SourceFile("car.lel");
        SourceFile carPhysicsFile = new SourceFile("car-physics.lel");
        SourceFile engineFile = new SourceFile("engine.lel");
        SourceFile wheelFile = new SourceFile("wheel.lel");
        SourceFile windowFile = new SourceFile("window.lel");

        carFile.addDependency(engineFile);
        carFile.addDependency(wheelFile);
        carFile.addDependency(windowFile);
        carFile.addDependency(carPhysicsFile);
        carPhysicsFile.addDependency(carFile);

        SourceFile[] files = {engineFile, wheelFile, windowFile, carFile, carPhysicsFile};
        CodeBase codeBase = new CodeBase(files);

        boolean expectedHasCircularDeps = true;

        // Act
        boolean actualHasCircularDeps = codeBase.hasCircularDependencies();

        // Assert
        boolean isCorrect = actualHasCircularDeps == expectedHasCircularDeps;
        if (!isCorrect) {
            System.out.println("ERREUR (CircularDependencies): Le graphe comporte une dépendance circulaire directe mais elle n'a pas été détectée.");
        }

        return isCorrect;
    }

    private static boolean testIndirectCircularDependency()
    {
        // Arrange
        SourceFile carFile = new SourceFile("car.lel");
        SourceFile carPhysicsFile = new SourceFile("car-physics.lel");
        SourceFile engineFile = new SourceFile("engine.lel");
        SourceFile wheelFile = new SourceFile("wheel.lel");
        SourceFile windowFile = new SourceFile("window.lel");

        carFile.addDependency(engineFile);
        carFile.addDependency(wheelFile);
        carFile.addDependency(windowFile);
        carPhysicsFile.addDependency(carFile);
        wheelFile.addDependency(carPhysicsFile);

        SourceFile[] files = {engineFile, wheelFile, windowFile, carFile, carPhysicsFile};
        CodeBase codeBase = new CodeBase(files);

        boolean expectedHasCircularDeps = true;

        // Act
        boolean actualHasCircularDeps = codeBase.hasCircularDependencies();

        // Assert
        boolean isCorrect = actualHasCircularDeps == expectedHasCircularDeps;
        if (!isCorrect) {
            System.out.println("ERREUR (CircularDependencies): Le graphe comporte une dépendance circulaire indirecte mais elle n'a pas été détectée.");
        }

        return isCorrect;
    }

    private static boolean testNoCircularDependency()
    {
        // Arrange
        SourceFile carFile = new SourceFile("car.lel");
        SourceFile carPhysicsFile = new SourceFile("car-physics.lel");
        SourceFile engineFile = new SourceFile("engine.lel");
        SourceFile wheelFile = new SourceFile("wheel.lel");
        SourceFile windowFile = new SourceFile("window.lel");

        carFile.addDependency(engineFile);
        carFile.addDependency(wheelFile);
        carFile.addDependency(windowFile);
        carPhysicsFile.addDependency(carFile);

        SourceFile[] files = {engineFile, wheelFile, windowFile, carFile, carPhysicsFile};
        CodeBase codeBase = new CodeBase(files);

        boolean expectedHasCircularDeps = false;

        // Act
        boolean actualHasCircularDeps = codeBase.hasCircularDependencies();

        // Assert
        boolean isCorrect = actualHasCircularDeps == expectedHasCircularDeps;
        if (!isCorrect) {
            System.out.println("ERREUR (CircularDependencies): Le graphe ne comporte pas de dépendance circulaire mais une a été détectée.");
        }

        return isCorrect;
    }
}
