package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomKruskal {
    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> allEdges = new ArrayList<>();
        Random gen = new Random();
        for (int u = 0; u < graph.upperBound; u++) {
            if (!graph.isVertex(u)) continue;
            for (Edge e : graph.incidency.get(u)) {
                if (u < e.oppositeExtremity(u)) { //pour ne prendre chaque arête qu'une fois, sinon on va ajouter l'arête deux fois car le graphe est non orienté.
                    double randomWeight = gen.nextDouble();
                    allEdges.add(new Edge(e.getSource(), e.getDest(), randomWeight));
                }
            }
        }
        Collections.sort(allEdges);
        ArrayList<Edge> finalTree = new ArrayList<>();
        UnionFind uf = new UnionFind(graph.order);
        for (Edge edge : allEdges) {
            int rootU = uf.find(edge.getSource());
            int rootV = uf.find(edge.getDest());
            if (rootU != rootV) {//pas de cycles
                finalTree.add(edge);
                uf.union(rootU, rootV);  // On unit les deux ensembles
                if (finalTree.size() == graph.order - 1) {
                    break;
                }
            }
        }
        return finalTree;
    }
}
