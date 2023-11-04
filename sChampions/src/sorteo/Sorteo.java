package sorteo;

import java.util.Iterator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import sorteo.Probabilidades;

public class Sorteo {
    DirectedGraph<Entry<Integer, Team>, Integer> posible = new DirectedAdjacencyListGraph<>();
    HashTableMap<Integer, Vertex<Entry<Integer, Team>>> teams = new HashTableMap<>();
    Integer[][] gmat;
    String fase;
    PositionList<HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>>> prev;

    /**
     * Construct a graph out of a series of vertices and an adjacency matrix. There
     * are 'len' vertices. A negative number means no connection. A non-negative
     * number represents distance between nodes.
     *
     */
    public Sorteo(Team[] teams, Integer[][] gmat, String fase) {
        prev = new NodePositionList<>();
        this.gmat = gmat;
        this.fase = fase;
        for (int i = 0; i < teams.length; i++) {
            this.teams.put(i, posible.insertVertex(new EntryImpl<>(i, teams[i])));
        }
        for (int i = 0; i < gmat.length; i++) {
            for (int j = 0; j < gmat[i].length; j++) {
                if (gmat[i][j] != -1)
                    posible.insertDirectedEdge(this.teams.get(i), this.teams.get(j), gmat[i][j]);
            }
        }
    }

    /**
     * @return Just return the graph that was constructed
     */
    public DirectedGraph<Entry<Integer, Team>, Integer> getGraph() {
        return posible;
    }

    /**
     * search a Hamiltonian path for the stored graph
     *
     * @return Return a Hamiltonian path for the stored graph, or null if there is
     *         none. The list containts a series of vertices, with no repetitions
     *         (even if the path can be expanded to a cycle).
     */
    public HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> sortear() {
        HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> tour;
        HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> cuadro = new HashTableMap<>();
        int i = randomVertexID();
        Vertex<Entry<Integer, Team>> v0 = teams.get(i);
        tour = walkarraundG(v0, cuadro, i) ;
        return tour;
    }

    /**
     * @return Return a random vertex ID
     */
    private <E, K, V> boolean member(HashTableMap<K, V> map, E elem) {
        boolean member = map.containsKey(elem);
        for (Entry<K, V> cursor : map.entries())
            member |= cursor.getValue() == elem;
        return member;
    }
    private boolean member1(HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> map, Entry<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> e) {
        boolean member = false;
        for (Entry<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> cursor : map.entries()) {
            member |= (cursor.getKey().element().getKey() == e.getKey().element().getKey() && cursor.getValue().element().getKey() == e.getValue().element().getKey());
            if (member) break;
        }
        return member;
    }
    private boolean member2(HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> map,int t1,int t2) {
        boolean member = false;
        for (Entry<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> cursor : map.entries()) {
            member |= (cursor.getKey().element().getKey() == t1 && cursor.getValue().element().getKey() == t2);
            if (member) break;
        }
        return member;
    }
    public boolean getSorteos() {
        HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> tour = sortear();
        boolean esta = false;
        for (HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> e1 : prev) {
            boolean res = true;
            for(Entry<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> e : tour) {
                res &= member1(e1,e);
                if (!res) break;
            }
            esta |= res;
            if (esta) break;
        }
        if(!esta) prev.addLast(tour);
        return esta ;
    }
    public int getEmparejamiento(int t1 , int t2) {
        int cont = 0;
        for(HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> e : prev) {
            if (member2(e,t1,t2))cont++;
        }
        return cont;
    }

    public void print(HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> sorteo) {
        int i = 1;
        for (Entry<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> e1 : sorteo.entries()) {
            System.out.println("\n" + i++ + "º Eliminatoria : " + e1.getKey().element().getValue() + " vs "
                    + e1.getValue().element().getValue());
        }
    }

    /**
     * @return Sorteo
     */
    public String toString() {
        return "Sorteo";
    }

    public double prob(int t1,int t2) {
        double totales = Probabilidades.casos("", "");
        double favorables = Probabilidades.casos(t1%8+"", t2%8+"");
        return favorables/totales;
    }
    /**
     * this method goes in to map searching a path with all vertex that are been
     * keeped in memoria meanwhile, if there is no such path it deletes the memoria
     * and goes to the next vertex
     *
     * @param vertex
     * @param sorteados
     * @param memoria
     * @return A position list with the all the vertex in the path, else return an
     *         empty position list
     */
    private HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> walkarraundG(
            Vertex<Entry<Integer, Team>> vertex,
            HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> memory, int i) {
        Set<Integer> called = new HashTableMapSet<>();
        Iterator<Edge<Integer>> edges = posible.outgoingEdges(vertex).iterator();
        while (edges.hasNext() && memory.size() != teams.size() / 2) {
            edges.next();
            int next = randomAdjacentVertexID(posible, vertex, called);
            called.add(next);
            Vertex<Entry<Integer, Team>> nextVertex = teams.get(next);
            if (!member(memory, nextVertex)&& !member(memory,vertex)) {
                HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> previousStep = copia(memory);
                if (canBeMatched(vertex, nextVertex)) {
                    memory.put(vertex, nextVertex);
                    i = whileHasBeenSorted(i,memory);
                    HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> nextStep = walkarraundG(teams.get(i), memory, i);
                    if (nextStep.size() == teams.size() / 2) {
                        memory = nextStep;
                    } else {
                        memory = previousStep;
                    }
                }
            }
        }
        return memory;
    }

    /**
     * look for a j different for i
     *
     * @param i
     * @param memory
     * @param j
     * @param vertex
     */
    private int whileHasBeenSorted(int i, HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> memory) {
        int res = randomVertexID();
        if (memory.size() == teams.size()/2)
            return i;
        else {
            while (member(memory,teams.get(res))) {
                res = randomVertexID();
            }
        }
        return res;
    }


    /**
     * Compare the Elem keeped in two vertex(nation, position, group)
     *
     * @param vertex
     * @param nextVertex
     * @return true or false depending of teams can match each other
     */
    private boolean canBeMatched(Vertex<Entry<Integer, Team>> vertex, Vertex<Entry<Integer, Team>> nextVertex) {
        if (fase.equals("CUARTOS")) {
            return true;
        }
        return !vertex.element().getValue().getNation().equals(nextVertex.element().getValue().getNation())
                && !vertex.element().getValue().getPos().equals(nextVertex.element().getValue().getPos())
                && !vertex.element().getValue().getGroup().equals(nextVertex.element().getValue().getGroup());
    }

    /**
     * copy all the elements of iter in a HashTableMap
     *
     * @param iter
     * @return A copy of iter
     */
    private HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> copia(HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> memory) {
        HashTableMap<Vertex<Entry<Integer, Team>>, Vertex<Entry<Integer, Team>>> copia = new HashTableMap<>();
        for (Vertex<Entry<Integer, Team>> elem : memory.keys()) {
            copia.put(elem, memory.get(elem));
        }
        return copia;
    }

    /**
     * return a random number within the positions of the graph
     *
     * @return Integer
     */
    private int randomVertexID() {
        //primero estan los primeros
        return !fase.equals("CUARTOS") ? (int) (Math.random() * (teams.size()/2) + teams.size()/2) : (int) (Math.random() * teams.size());
    }

    private static int randomAdjacentVertexID(DirectedGraph<Entry<Integer, Team>, Integer> posibles, Vertex<Entry<Integer, Team>> v,Set<Integer> called) {
        int degree = posibles.outDegree(v);
        int[] next = new int[degree];
        int cont = 0;
        for (Edge<Integer> e : posibles.outgoingEdges(v)) {
            next[cont++] = e.element();
        }
        int r = (int) (Math.random() * degree);
        if (called.size() == degree)
            return r;
        else {
            while (called.contains(r)) {
                r = (int) (Math.random() * degree);
            }
        }
        return next[r];
    }
}
