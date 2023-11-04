package aed.individual6;

import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;

import java.util.Iterator;

import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.map.HashTableMap;


public class Suma {
	
	public static <E> Map<Vertex<Integer>,Integer> sumVertices(DirectedGraph<Integer,E> g) {
		Map<Vertex<Integer>,Integer> sumaVertices = new HashTableMap<>();
		Iterator<Vertex<Integer>> it = g.vertices().iterator();
		while (it.hasNext()) {
			Vertex<Integer> vertex = it.next(); 
			int suma = vertex.element();
			Map<Vertex<Integer>,Boolean> memoria = new HashTableMap<>();
			memoria.put(vertex,true);
			suma = recorreGrafo(g,vertex,memoria ,suma);
			sumaVertices.put(vertex, suma);
		}
		return sumaVertices;
	}
	
	/**
	 * recorre todos los caminos desde un vertice y los va sumando de manera recursiva
	 * @param <E>
	 * @param g
	 * @param vertex
	 * @param memoria
	 * @param suma
	 * @return la suma de todos los vertices del camino hasta el final
	 */
	private static <E> int recorreGrafo (DirectedGraph<Integer,E> g,Vertex<Integer> vertex,Map<Vertex<Integer>,Boolean> memoria,int suma) {
			Iterator<Edge<E>> edges = g.outgoingEdges(vertex).iterator();
			while (edges.hasNext()) {
				Edge<E> edge = edges.next();
				Vertex<Integer> nextVertex = g.endVertex(edge);
				if(!memoria.containsKey(nextVertex)) {
					memoria.put(nextVertex,true);
					suma += nextVertex.element();
					suma = recorreGrafo(g,nextVertex,memoria,suma);
				}
			}
		return suma;
	}
}
