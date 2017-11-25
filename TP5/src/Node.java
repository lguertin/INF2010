
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
        enfant.parent = this;
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    public Node fusion(Node autre) throws DifferentOrderTrees {
    	if(autre == null)
    		return null;
    	
    	if(this.parent == null && autre.parent == null) {
    		if(this.getVal() < autre.getVal()) {
    			this.addEnfant(autre);
    			this.ordre++;
    			autre.parent = this;
    			return this;
    		} else {
    			autre.addEnfant(this);
    			autre.ordre++;
    			this.parent = autre;
    			return autre;
    		}
    		
    	}
        return null;
    }

    private void moveUp() {
    	Node tmpParent = parent;
    	Node grandParent = tmpParent.parent;
		if(tmpParent.parent != null) { // Gere le grand parent s il existe
			tmpParent.parent.addEnfant(this);
			grandParent.removeEnfant(tmpParent);
		}
		tmpParent.parent = this;
		this.parent = grandParent;
		tmpParent.removeEnfant(this);
		ArrayList<Node> lesEnfantsDuParent = tmpParent.enfants;
		tmpParent.enfants = enfants;
		enfants = lesEnfantsDuParent;
		enfants.add(tmpParent);
		
		for(Node node : tmpParent.enfants) // Gere les parents des enfants du Node this
			node.parent = tmpParent;

		tmpParent.ordre++;
		ordre--;
    }

    private void reduceOrderFromDelete(Node node){
    	for(Node nodeEnfant : node.enfants)
    		reduceOrderFromDelete(nodeEnfant);
    	this.ordre--;
    }
    
    public ArrayList<Node> delete() {
        while(parent != null)
        	moveUp();
        for(Node node : enfants)
        	node.parent = null;
        reduceOrderFromDelete(this);
        return enfants;
    }

    
    public void print(String tabulation) {
    	System.out.print(tabulation + valeur);
    	boolean foundOne = false;
        for(Node node : enfants){
        	if(!foundOne) {
        		foundOne = true;
        		node.print("\t");
        		System.out.println();
        	} else {
        		node.print(tabulation + "\t");
        	}       	
        }
    }
    
    public Node findValue(int valeur) {
    	Node temp = null;
        for(Node node: enfants){
        	if(node.valeur == valeur)
        		return node;
        	if(node.valeur < valeur) {
        		temp = node.findValue(valeur);
        		if(temp != null)
        			return temp;
        	}
        }
        return null;
    }
    
    public ArrayList<Integer> getElementsSorted() {
    	if (enfants.size() == 0)
    		return null;
    	
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	getElementsSortedRec(arr);
    	Collections.sort(arr);
    	return arr;
    }
    
    private void getElementsSortedRec(ArrayList<Integer> arr) {
    	for(Node node : enfants){
    		arr.add(node.valeur);
    		node.getElementsSortedRec(arr);
    	}
    }
}
