package RandomTreeAlgos;

import Graph.Edge;
import Graph.*;
import java.util.ArrayList;
import java.util.Collections;

public class RandomEdgeInsertion {
    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> allEdges = new ArrayList<>();
        ArrayList<Edge> spanningTree = new ArrayList<>();
        for (int u = 0; u < graph.upperBound; u++) {
            if (!graph.isVertex(u)) continue;
            for (Edge e : graph.incidency.get(u)) {
                if (u < e.oppositeExtremity(u)) {
                    allEdges.add(e);
                }
            }
        }
        Collections.shuffle(allEdges);
        UnionFind uf = new UnionFind(graph.order);
        for (Edge e : allEdges) {
            int rootU = uf.find(e.getSource());
            int rootV = uf.find(e.getDest());
            if (rootU != rootV) {
                spanningTree.add(e);
                uf.union(rootU, rootV);
                if (spanningTree.size() == graph.order - 1) {
                    break;
                }
            }
        }

        return spanningTree;
    }
}