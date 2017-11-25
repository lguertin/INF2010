
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
    public void fusion(Monceau autre) {
        int ordreMax = 0;
        for(Node node : arbres)
        	Math.max(ordreMax, node.ordre);
        for(Node node : autre.arbres)
        	Math.max(ordreMax, node.ordre);
        
        Node retenue = null;
        ArrayList<Node> newMonceauTrees = new ArrayList<Node>();
        for ( int j = 0; j < ordreMax; j++) {
        	ArrayList <Node> foundTrees = new ArrayList<Node>();
        	for(Node node : arbres) {
        		if(node.ordre == j) {
        			foundTrees.add(node);
        			arbres.remove(node);
        		}
        	}
        	for(Node node : autre.arbres) {
        		if(node.ordre == j) {
        			foundTrees.add(node);
        			autre.arbres.remove(node);
        		}
        	}
        	
        	if(retenue != null && retenue.ordre == j){
    			foundTrees.add(retenue);
    		}
    		
    		if(foundTrees.size() == 1)
    			newMonceauTrees.add(foundTrees.get(0));
    		else if(foundTrees.size() == 2) {
				try {
					retenue = foundTrees.get(0).fusion(foundTrees.get(1));
					ordreMax = Math.max(ordreMax, retenue.ordre);
				} catch (DifferentOrderTrees e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        	else if(foundTrees.size() == 3){
        		try {
					retenue = foundTrees.get(0).fusion(foundTrees.get(1));
					ordreMax = Math.max(ordreMax, retenue.ordre);
				} catch (DifferentOrderTrees e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				newMonceauTrees.add(foundTrees.get(2));
        	}	
    	}
    	arbres = newMonceauTrees;
    }
    
    public void insert(int val) {
    	Monceau aMonceau = new Monceau();
    	aMonceau.arbres.add(new Node(val));
    	fusion(aMonceau);
    }
    
    public boolean delete (int val) {
    	boolean foundOne = false;
        for(Node aNode : arbres) {
        	Node tmp = aNode.findValue(val);
        	if (tmp != null) {
        		aNode.delete();
        		foundOne = true;
        	}
        } 
        return foundOne;
    }
    
    public void print() {
        for(Node aNode : arbres) {
        	System.out.println("Arbre d'ordre : " + aNode.ordre);
        	aNode.print("  ");
        }
    }
    
}
