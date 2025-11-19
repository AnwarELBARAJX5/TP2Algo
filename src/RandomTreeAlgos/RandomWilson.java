package RandomTreeAlgos;
import Graph.*;

import java.util.ArrayList;
import java.util.Random;
public class RandomWilson {
    public static ArrayList<Edge> generateTree(Graph graph) {
        ArrayList<Edge> finalTree = new ArrayList<>();
        boolean[] inTree=new boolean[graph.order];
        Random gen=new Random();
        inTree[0]=true;
        int[] next=new int[graph.order]; // next[u] = v signifie "dans ma marche actuelle, je suis allé de u vers v"
        for(int i=0;i< graph.order;i++){
            if(inTree[i])continue;
            int u=i;
            while(!inTree[u]){
                Arc[] neighbours=graph.outEdges(u);
                int randomId= gen.nextInt(neighbours.length);
                int v=neighbours[randomId].getDest();
                next[u]=v;
                u=v;
            }
            int curr = i;
            while (!inTree[curr]) {
                int neighbor = next[curr]; // On récupère où on était allé
                // On ajoute l'arête à l'arbre final
                finalTree.add(new Edge(curr, neighbor, 0));
                inTree[curr] = true; // Le sommet fait maintenant partie de l'arbre
                curr = neighbor;
            }
        }
        return finalTree;
    }
}


