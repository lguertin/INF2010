
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import BST.Node;

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
    	if(parent != null) {
    		if(parent.parent != null) { // Gere le grand parent s il existe
    			parent.parent.addEnfant(this);
    			parent.parent.removeEnfant(parent);
    		}
    		parent.removeEnfant(this);
    		ArrayList<Node> lesEnfantsDuParent = parent.enfants;
    		parent.enfants = enfants;
    		enfants = lesEnfantsDuParent;
    		enfants.add(parent);
    		
    		for(Node node : parent.enfants) // Gere les parents des enfants du Node this
    			node.parent = parent;

    		parent.ordre++;
    		ordre--;
    		
    		Node grandParent = parent.parent;
    		parent.parent = this;
    		parent = grandParent;
    	}
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
    	if(this.valeur == valeur)
    		return this;
        for(Node node: enfants){
        	if(node.valeur < valeur) {
        		temp = node.findValue(valeur);
        		if(temp != null)
        			return temp;
        	}
        }
        return null;
    }
    
    public ArrayList<Integer> getElementsSorted() {
    	// à compléter
    	
    	return null;
    }
}
