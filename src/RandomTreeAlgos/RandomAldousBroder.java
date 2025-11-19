package RandomTreeAlgos;

import Graph.Edge;
import Graph.*;

import java.util.ArrayList;
import java.util.Random;

public class RandomAldousBroder {
    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> finalTree = new ArrayList<>();
        boolean[] visited = new boolean[graph.order];
        Random gen = new Random();
        int startVertex = 0;
        visited[startVertex] = true;
        int visitedCount = 1;
        int currentVertex = startVertex;
        while (visitedCount < graph.order) {
            Arc[] neighbors = graph.outEdges(currentVertex);
            int randomIndex = gen.nextInt(neighbors.length);
            Arc randomArc = neighbors[randomIndex];
            int nextVertex = randomArc.getDest();
            if (!visited[nextVertex]) {
                finalTree.add(randomArc.support);
                visited[nextVertex] = true;
                visitedCount++;
            }
            currentVertex = nextVertex;
        }
        return finalTree;
    }
}
