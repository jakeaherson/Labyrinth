package cs2321;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Position;
import net.datastructures.Vertex;

public class InnerEdge<E, V> implements Edge<E> {
	private E element;
	private Position<Edge<E>> pos;
	private Vertex<V>[] endpoints;

	@SuppressWarnings({"unchecked"})
	/** Constructs InnerEdge instance from u to v, storing the given element. */
	public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
		element = elem;
		endpoints = (Vertex<V>[]) new Vertex[]{u,v};  // array of length 2
	}

	/** Returns the element associated with the edge. */
	public E getElement() { return element; }

	/** Returns reference to the endpoint array. */
	public Vertex<V>[] getEndpoints() { return endpoints; }

	/** Validates that this edge instance belongs to the given graph. */
	public boolean validate(Graph<V,E> graph) {
		return (pos != null);
	}

	/** Stores the position of this edge within the graph's vertex list. */
	public void setPosition(Position<Edge<E>> p) { pos = p; }

	/** Returns the position of this edge within the graph's vertex list. */
	public Position<Edge<E>> getPosition() { return pos; }
}
