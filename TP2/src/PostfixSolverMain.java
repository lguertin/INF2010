import java.io.*;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
        String s1 = "0 1 or";
        String s2 = "0 0 or";
        String s3 = "1 1 or";
        String s4 = "1 0 or";

        try {
            if (!solve(s1) || solve(s2) || !solve(s3) || !solve(s4)) {
                System.out.println("Erreur : résultat de l'opération or invalide.");
                return;
            }
        }
        catch (ParsingErrorException ex) {
            System.out.println("Erreur : le solveur a rencontré un problème.");
        }

        String s5 = "0 0 and";
        String s6 = "0 1 and";
        String s7 = "1 0 and";
        String s8 = "1 1 and";

        try {
            if (solve(s5) || solve(s6) || solve(s7) || !solve(s8)) {
                System.out.println("Erreur : résultat de l'opération and invalide.");
                return;
            }
        }
        catch (ParsingErrorException ex) {
            System.out.println("Erreur : le solveur a rencontré un problème.");
        }

        String s9 = "0 not";
        String s10 = "1 not";

        try {
            if (!solve(s9) || solve(s10)) {
                System.out.println("Erreur : résultat de l'opération not invalide.");
                return;
            }
        }
        catch (ParsingErrorException ex) {
            System.out.println("Erreur : le solveur a rencontré un problème.");
        }

        System.out.print("PostfixSolver: It's all good");
     }
	 
	 public static boolean solve(String input) throws ParsingErrorException
     {
        boolean val1;
        boolean val2;
        boolean result;
        ArrayStack<Boolean> stack = new ArrayStack<>();
        //L'expression est séparée en tokens selon les espaces.
        for (String token : input.split("\\s")) {
        	switch(token) {
        	case "and":
        		val2 = stack.pop();
        		val1 = stack.pop();		// On prend les deux elements a faire un et
        		result = val1 & val2;
        		stack.push(result);		// On remet le resultat dans le stack
        		break;
        	case "or":
        		val2 = stack.pop();
        		val1 = stack.pop();		// On prend les deux elements a faire un ou
        		result = val1 | val2;
        		stack.push(result);		// On remet le resultat dans le stack
        		break;
        	case "not":
        		val1 = stack.pop();		// On prend l'element a faire un non
        		result = !val1;
        		stack.push(result);		// On remet le resultat dans le stack
        		break;
        	case "1":
        		stack.push(Boolean.TRUE);
        		break;
        	case "0":
        		stack.push(Boolean.FALSE);
        		break;
        	default:
        		break;
        	}
        	
        }
        return stack.pop();
    }
}
