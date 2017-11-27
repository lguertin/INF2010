package PolyNet;

import java.util.HashSet;
import java.util.PriorityQueue;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes)
    {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength()
    {
        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();

        visitedNodes.add(nodes[0]);
        for (PolyNetConnection connections : nodes[0].getConnections())
        	knownConnections.add(connections);
        
        while(!knownConnections.isEmpty()) {
        	PolyNetConnection connection = knownConnections.poll();
        	
    		if(!visitedNodes.contains(connection.getConnectedNode())) {
                visitedNodes.add(connection.getConnectedNode());

                for (PolyNetConnection connections : connection.getConnectedNode().getConnections())
                	knownConnections.add(connections);
                totalCableLength+=connection.getDistance();
            }
        }
        
        return totalCableLength;
    }
}
