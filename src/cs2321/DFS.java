package cs2321;

import java.util.ArrayList;
import java.util.Iterator;
import net.datastructures.*;

public class DFS {

	Graph<RoomCoordinate, Walkway> mGraph;
	RoomCoordinate start;
	RoomCoordinate end;
	public Vertex<RoomCoordinate> starter = null;
	public Vertex<RoomCoordinate> ender = null;
	public ArrayList<Edge<Walkway>> dfs = new ArrayList<Edge<Walkway>>();

	DFS(Graph<RoomCoordinate, Walkway> graph, RoomCoordinate start, RoomCoordinate end) {
		mGraph = graph;
		this.start = start;
		this.end = end;
	}

	public void findStartAndEnd() {
		Vertex<RoomCoordinate> flavs = null;
		Iterator<Vertex<RoomCoordinate>> flavoursIter = mGraph.vertices().iterator();
		while (flavoursIter.hasNext()){
			flavs = flavoursIter.next();
			if(start.compareTo(flavs.getElement()) == 0){
				starter = flavs;
			}	
			if(end.compareTo(flavs.getElement()) == 0){
				ender = flavs;
			}
			else {
				if(starter!=null && ender!=null){
					break;
				}
			}
		}
	}

	public Vertex<RoomCoordinate> findVertex(RoomCoordinate vertex) {
		Vertex<RoomCoordinate> flavs = null;
		Vertex<RoomCoordinate> point = null;
		Iterator<Vertex<RoomCoordinate>> flavoursIter = mGraph.vertices().iterator();
		while (flavoursIter.hasNext()){
			flavs = flavoursIter.next();
			if(vertex.compareTo(flavs.getElement()) == 0){
				point = flavs;
				point.getElement().setX(flavs.getElement().getX());
				point.getElement().setY(flavs.getElement().getY());
				return point;
			}
		}
		return null;
	}

	public ArrayList<Edge<Walkway>> incidentWalkways(Vertex<RoomCoordinate> vertex) {
		Iterator<Edge<Walkway>> edges = mGraph.edges().iterator();
		Edge<Walkway> current = null;
		ArrayList<Edge<Walkway>> incidentWalkways = new ArrayList<Edge<Walkway>>();
		while(edges.hasNext()) {
			current = edges.next();
			String coords = current.getElement().getName();
			char x1 = coords.charAt(1);
			char y1 = coords.charAt(3);
			char x2 = coords.charAt(6);
			char y2 = coords.charAt(8);
			RoomCoordinate x = new RoomCoordinate(x1, y1);
			RoomCoordinate y = new RoomCoordinate(x2, y2);
			if((vertex.getElement().compareTo(x) == 0) || (vertex.getElement().compareTo(y) == 0)) {
				incidentWalkways.add(current);
			}
		}
		return incidentWalkways;
	}

	public Iterable<Edge<Walkway>> dfsPath(Vertex<RoomCoordinate> start) {
		ArrayList<Edge<Walkway>> walkways = incidentWalkways(start);
		if(end.compareTo(start.getElement()) == 0) {
			return dfs;
		}
		else {
			for(Edge<Walkway> neighbor : walkways) {
				if(neighbor.getElement().getStatus().equals("Unvisited")) {
					String coords = neighbor.getElement().getName();
					char x1 = coords.charAt(1);
					char y1 = coords.charAt(3);
					char x2 = coords.charAt(6);
					char y2 = coords.charAt(8);
					RoomCoordinate one = new RoomCoordinate(Character.getNumericValue(x1), Character.getNumericValue(y1));
					RoomCoordinate two = new RoomCoordinate(Character.getNumericValue(x2), Character.getNumericValue(y2));
					Vertex<RoomCoordinate> first = findVertex(one);
					Vertex<RoomCoordinate> second = findVertex(two);
					if(start.getElement().compareTo(one) == 0) {
						dfs.add(neighbor);
						neighbor.getElement().setVisited();
						dfsPath(second);
					}
					else {
						dfs.add(neighbor);
						neighbor.getElement().setVisited();
						dfsPath(first);
					}
				}
			}
		}
		return dfs;
	}


}
