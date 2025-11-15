package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Graph {
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe

    public int order;
    public int upperBound;
    int edgeCardinality;
    private boolean[] isActive;
    public ArrayList<LinkedList<Edge>> incidency;
    public ArrayList<LinkedList<Arc>> inIncidency;
    public ArrayList<LinkedList<Arc>> outIncidency;

    public Graph(int upperBound) {
        // Au début, upperBound==order
        // Ensuite, on pourrait retirer des sommets du graphe.
        // Ainsi, on pourrait avoir upperBound > order
        // Cette modification de la classe devient nécessaire
        // si vous implémentez
        // ou l'algorithme de génération d'arbre couvrant
        // par suppression de sommet, ou l'opération de contraction d’arête.
        // Autrement, on pourra asssumer que upperBound==order.

        this.upperBound = upperBound;
        this.order = upperBound;
        this.edgeCardinality = 0;
        this.incidency = new ArrayList<>(upperBound);
        this.inIncidency = new ArrayList<>(upperBound);
        this.outIncidency = new ArrayList<>(upperBound);


        this.isActive = new boolean[upperBound];
        Arrays.fill(this.isActive, true);

        for (int i = 0; i < upperBound; i++) {
            incidency.add(new LinkedList<>());
            inIncidency.add(new LinkedList<>());
            outIncidency.add(new LinkedList<>());
        }
    }

    public boolean isVertex(int vertex) {
        if (vertex < 0 || vertex >= upperBound) {
            return false;
        }
        return isActive[vertex];
    }

    public void addVertex(int vertex) {
        if (vertex >= 0 && vertex < upperBound && !isActive[vertex]) {
            isActive[vertex] = true;
            order++;
        }
    }

    public void deleteVertex(int vertex){
        if (!isVertex(vertex)) {
            return; // Le sommet n'existe pas ou est déjà supprimé.
        }
        isActive[vertex] = false;
        order--;
        for (Edge edge : new ArrayList<>(incidency.get(vertex))) {
            int otherVertex = edge.oppositeExtremity(vertex);
            // Supprime l'arête de la liste de l'autre sommet
            if (isVertex(otherVertex)) { // Vérifie si l'autre sommet n'a pas été supprimé
                incidency.get(otherVertex).remove(edge);
            }
        }
        edgeCardinality -= incidency.get(vertex).size();
        incidency.get(vertex).clear(); // Vide la liste d'adjacence du sommet supprimé

        // 3. Supprimer tous les arcs sortants
        for (Arc arc : new ArrayList<>(outIncidency.get(vertex))) {
            int destVertex = arc.getDest();
            if (isVertex(destVertex)) {
                inIncidency.get(destVertex).remove(arc);
            }
        }
        outIncidency.get(vertex).clear();

        // 4. Supprimer tous les arcs entrants
        for (Arc arc : new ArrayList<>(inIncidency.get(vertex))) {
            int sourceVertex = arc.getSource();
            if (isVertex(sourceVertex)) {
                outIncidency.get(sourceVertex).remove(arc);
            }
        }
        inIncidency.get(vertex).clear();
    }

    public void ensureVertex(int vertex) {
        // Synonime de addVertex
        addVertex(vertex);
    }

    public void addArc(Arc arc) {
        if(isVertex(arc.getSource()) && isVertex(arc.getDest())){
            outIncidency.get(arc.getSource()).add(arc);
            inIncidency.get(arc.getDest()).add(arc);
        }
    }

    public void addEdge(Edge edge) {
        if(isVertex(edge.dest)&& isVertex(edge.source)){
            incidency.get(edge.dest).add(edge);
            incidency.get(edge.source).add(edge);
            edgeCardinality++;
            addArc(new Arc(edge, false));
            addArc(new Arc(edge, true));
        }
    }

    public Arc[] outEdges(int vertex) {
        // à modifier, si nécessaire

        // Pour la prochaine ligne voir
        // https://www.baeldung.com/java-collection-toarray-methods
        return outIncidency.get(vertex).toArray(new Arc[0]);
   }

}
