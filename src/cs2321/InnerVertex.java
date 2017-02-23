package cs2321;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Map;
import net.datastructures.Position;
import net.datastructures.Vertex;

public class InnerVertex<V, E> implements Vertex<V> {
	private V element;
	private Position<Vertex<V>> pos;
	private Map<Vertex<V>, Edge<E>> outgoing, incoming;

	/** Constructs a new InnerVertex instance storing the given element. */
	public InnerVertex(V elem, boolean graphIsDirected) {
		element = elem;
		outgoing = new ProbeHashMap<Vertex<V>, Edge<E>>();
		if (graphIsDirected)
			incoming = new ProbeHashMap<Vertex<V>, Edge<E>>();
		else
			incoming = outgoing;    // if undirected, alias outgoing map
	}

	/** Validates that this vertex instance belongs to the given graph. */
	public boolean validate(Graph<V,E> graph) {
		return (pos != null);
	}

	/** Returns the element associated with the vertex. */
	public V getElement() { return element; }

	/** Stores the position of this vertex within the graph's vertex list. */
	public void setPosition(Position<Vertex<V>> p) { pos = p; }

	/** Returns the position of this vertex within the graph's vertex list. */
	public Position<Vertex<V>> getPosition() { return pos; }

	/** Returns reference to the underlying map of outgoing edges. */
	public Map<Vertex<V>, Edge<E>> getOutgoing() { return outgoing; }

	/** Returns reference to the underlying map of incoming edges. */
	public Map<Vertex<V>, Edge<E>> getIncoming() { return incoming; }
}
