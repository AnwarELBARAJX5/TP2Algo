package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomKruskal {
    public static ArrayList<Edge> generateTree(Graph graph){
        ArrayList<Edge> allEdges = new ArrayList<>();
        Random gen = new Random();
        for (int u = 0; u < graph.upperBound; u++) {
            // Si vous gérez la suppression de sommets, vérifiez graph.isVertex(u)
            if (!graph.isVertex(u)) continue;
            // Parcourez les voisins
            for (Edge e : graph.incidency.get(u)) {

                // Astuce pour ne prendre chaque arête qu'une fois
                if (u < e.oppositeExtremity(u)) {

                    // 1. Générez le poids aléatoire [0, 1]
                    double randomWeight = gen.nextDouble();

                    // 2. Ajoutez une NOUVELLE arête avec ce poids
                    allEdges.add(new Edge(e.getSource(), e.getDest(), randomWeight));
                }
            }
        }
        Collections.sort(allEdges);
        ArrayList<Edge> finalTree = new ArrayList<>();
        for(Edge edge:allEdges){

        }
    }
}
