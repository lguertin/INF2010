
import java.util.EmptyStackException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maitr
 */
public class StackMain
{
    final static int COUNT = 1013;

    public static void main(String[] args)
    {
        ArrayStack<Integer> stack = new ArrayStack<>();

        int pushCounter = 0;

        for (int i = 0; i < COUNT; ++i) {
            stack.push(++pushCounter);
        }

        if (stack.size() != COUNT) {
            System.out.println("Erreur: La taille de la pile n'est pas égale à " + COUNT + " après avoir ajouté " + COUNT + " éléments");
        }

        for (int i = 0; i < COUNT; ++i) {
            try {
                if (stack.peek() != pushCounter || stack.pop() != pushCounter) {
                    System.out.println("Erreur: l'ordre de sortie(LIFO) n'est pas respecté.");
                    return;
                }
                --pushCounter;

            }
            catch (EmptyStackException e) {
                e.printStackTrace();
                return;
            }
        }

        if (!stack.empty()) {
            System.out.println("Erreur: la pile devrait être vide, mais elle ne l'est pas.");
            return;
        }

        if (stack.peek() != null) {
            System.out.println("Erreur: peek() doit retourner null lorsque la pile est vide.");
            return;
        }

        try {
            stack.pop();
            System.out.println("Erreur: pop() doit lancer une exception lorsque la pile est vide.");
            return;
        }
        catch (EmptyStackException e) {
        }

        // Exercice 2
        String phrase = "Ceci est une phrase";
        String phraseInverse = reverseString(phrase);

        if (!phraseInverse.equals("phrase une est Ceci")) {
            System.out.println("Erreur : la phrase retournée n'est pas correcte.");
            return;
        }

        System.out.print("ArrayStack: It's all good!");

    }

    // Utilise une ArrayStack<String> pour inverser l'ordre des mots dans la phrase
    // de départ.
    public static String reverseString(String input) {
        String[] words = input.split("\\s");
        String output = "";

        ArrayStack<String> stack = new ArrayStack<>();
         // À compléter
		 
        return output;
    }

}
