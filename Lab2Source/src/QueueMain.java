public class QueueMain
{
	final static int COUNT = 1013;
	
	public static void main(String[] args) 
	{
		Queue<Integer> stackQueue = new StackQueue<Integer>();
		int pushCounter = 0;
		int popCounter = 0;
		
		for(int i = 0; i < COUNT * 2; i++) {
			stackQueue.push(pushCounter++);
		}
		
		if(stackQueue.size() != COUNT * 2) {
			System.out.println("Erreur: La taille de la file n'est pas égale à " + COUNT * 2 + " après avoir ajouté " + COUNT * 2 + " éléments.");
		}
		
		for(int i = 0; i < COUNT; i++) {
			try {
				stackQueue.pop();
				popCounter++;
			} 
			catch (EmptyQueueException e) {
				e.printStackTrace();
				return;
			}
		}
		
		for(int i = 0; i < COUNT * 2.5; i++) {
			stackQueue.push(pushCounter++);
		}
		
		for(int i = 0; i < COUNT * 3.5; i++) {
			if(stackQueue.peek() != popCounter) {
				System.out.println("Erreur: l'ordre de sortie(FIFO) n'est pas respecté.");
				return;
			}
			
			try {
				stackQueue.pop();
				popCounter++;
			} 
			catch (EmptyQueueException e) {
				e.printStackTrace();
				return;
			}
		}
		
		if(!stackQueue.empty()) {
			System.out.println("Erreur: la file devrait être vide, mais elle ne l'est pas.");
			return;
		}
		
		if(stackQueue.peek() != null) {
			System.out.println("Erreur: peek() doit retourner null lorsque la file est vide.");
			return;
		}
		
		try {
			stackQueue.pop();
			System.out.println("Erreur: pop() doit lancer une exception lorsque la file est vide.");
			return;
		} 
		catch (EmptyQueueException e){}		
		
		System.out.print("StackQueue: It's all good!");
	}
}
