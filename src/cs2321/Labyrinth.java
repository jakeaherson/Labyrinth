package cs2321;

import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
import net.datastructures.*;

/* CS2321 Project: The Labyrinth
 * 
 * Do NOT change the setupLabyrinth function.
 *         
 * Implement the dfsPath, bfsPath, shortestPath, and totalPathDistance functions below.
 *
 */
public class Labyrinth {

	public static final int WALL = 1;
	public static final String PARSE_CHARACTER = ",";

	private Graph<RoomCoordinate, Walkway> mGraph;
	private int mWidth = -1;
	private int mHeight = -1;


	public Labyrinth( String aFileName ) {
		mGraph = setupLabyrinth( aFileName );
		// TODO: Add other necessary code to constructor
	}

	/*
	 * Width of the labyrinth (# of squares, not pixels)
	 */
	public int getWidth() {
		return mWidth;
	}

	/*
	 * Height of the labyrinth (# of squares, not pixels)
	 */
	public int getHeight() {
		return mHeight;
	}

	/*
	 * Create the graph based on the maze specification in the input file
	 * !!! Don't Change this method !!!
	 */
	@SuppressWarnings("unchecked")
	public Graph<RoomCoordinate, Walkway> setupLabyrinth( String aFileName ) {
		Scanner lFile = null;
		try
		{
			lFile = new Scanner( new File( aFileName ) );
			lFile.useDelimiter( ",\n" );
		}
		catch ( FileNotFoundException eException )
		{
			System.out.println( eException.getMessage() );
			eException.printStackTrace();
			System.exit( -1 );
		}

		Graph<RoomCoordinate, Walkway> lGraph = new AdjListGraph<RoomCoordinate, Walkway>(false);

		try
		{
			int lXSize = 0;
			int lYSize = 0;
			if ( lFile.hasNext() )
			{
				String[] lDimensions = lFile.nextLine().split( PARSE_CHARACTER );
				lXSize = Integer.parseInt( lDimensions[0] );
				lYSize = Integer.parseInt( lDimensions[1] );
			}

			mWidth = lXSize;
			mHeight = lYSize;

			/* Create all the room coordinates */
			Vertex<?>[][] lVertices = new Vertex<?>[lXSize][lYSize];
			for ( int lYIndex = 0; lYIndex < lYSize; lYIndex++ )
			{
				for ( int lXIndex = 0; lXIndex < lXSize; lXIndex++ )
				{
					RoomCoordinate lNextRoomCoordinate = new RoomCoordinate(
							lXIndex, lYIndex );
					Vertex<RoomCoordinate> lNextRoom = lGraph
							.insertVertex( lNextRoomCoordinate );
					lVertices[lXIndex][lYIndex] = lNextRoom;
				}
			}

			for ( int lYIndex = 0; lYIndex < lYSize; lYIndex++ )
			{
				String[] lWalls = lFile.nextLine().split( PARSE_CHARACTER );
				for ( int lXIndex = 0; lXIndex < lXSize; lXIndex++ )
				{
					if ( Integer.parseInt( lWalls[lXIndex] ) != WALL )
					{
						Vertex<RoomCoordinate> lVertex1 = ( Vertex<RoomCoordinate> )lVertices[lXIndex][lYIndex];
						Vertex<RoomCoordinate> lVertex2 = ( Vertex<RoomCoordinate> )lVertices[lXIndex][lYIndex - 1];

						Walkway lNewWalkway = new Walkway( lVertex1.getElement()
								.toString() + lVertex2.getElement().toString(),  Integer.parseInt( lWalls[lXIndex] ) );
						lGraph.insertEdge( lVertex1, lVertex2, lNewWalkway );
					}
				}
			}

			for ( int lYIndex = 0; lYIndex < lYSize; lYIndex++ )
			{
				String[] lWalls = lFile.nextLine().split( PARSE_CHARACTER );
				for ( int lXIndex = 0; lXIndex < lXSize; lXIndex++ )
				{
					if ( Integer.parseInt( lWalls[lXIndex] ) != WALL )
					{
						Vertex<RoomCoordinate> lVertex1 = ( Vertex<RoomCoordinate> )lVertices[lXIndex][lYIndex];
						Vertex<RoomCoordinate> lVertex2 = ( Vertex<RoomCoordinate> )lVertices[lXIndex - 1][lYIndex];

						Walkway lNewWalkway = new Walkway( lVertex1.getElement()
								.toString() + lVertex2.getElement().toString(),  Integer.parseInt( lWalls[lXIndex] ) );
						lGraph.insertEdge( lVertex1, lVertex2, lNewWalkway );
					}
				}
			}
		}
		catch ( Exception eException )
		{
			System.out.println( eException.getMessage() );
			eException.printStackTrace();
			System.exit( -1 );
		}

		return lGraph;
	}


	/* 
	 * Complete the dfsPath function by implementing a Depth First Search algorithm
	 * to find a path from start to end.
	 * RETURN VALUES:
	 *    + If there is NO path, do NOT return null.  Return an empty sequence.
	 *    + If there is a path, return the sequence of edges traversed in order
	 *      to get from the start to the finish locations.
	 */
	@TimeComplexity("O(n+e)")
	@SpaceComplexity("O(1)")
	public Iterable<Edge<Walkway>> dfsPath( RoomCoordinate start, RoomCoordinate end ) {

		DFS dfs = new DFS(mGraph, start, end);
		dfs.findStartAndEnd();
		Vertex<RoomCoordinate> starter = dfs.findVertex(start);	
		Iterable<Edge<Walkway>> path = dfs.dfsPath(starter);
		return path;
	}

	/* 
	 * Complete the bfsPath function by implementing a Breadth First Search algorithm
	 * to find a path from start to end.
	 * RETURN VALUES:
	 *    + If there is NO path, do NOT return null.  Return an empty sequence.
	 *    + If there is a path, return the sequence of edges traversed in order
	 *      to get from the start to the finish locations.
	 */
	@TimeComplexity("O(n+e)")
	@SpaceComplexity("O(n)")
	public Iterable<Edge<Walkway>> bfsPath( RoomCoordinate start, RoomCoordinate end ) {
		// #TODO: Complete and correct bfsPath() */
		return dfsPath(start,end);
	}

	/* 
	 * Complete the shortestPath function by implementing Dijkstra's Algorithm
	 * to find a path from start to end.
	 * RETURN VALUES:
	 *    + If there is NO path, do NOT return null.  Return an empty sequence.
	 *    + If there is a path, return the sequence of edges traversed in order
	 *      to get from the start to the finish locations.
	 */
	@TimeComplexity("O(n^2)")
	@SpaceComplexity("O(n)")
	public Iterable<Edge<Walkway>> shortestPath( RoomCoordinate start, RoomCoordinate end ) {
		// #TODO: Complete and correct shortestPath() */
		return dfsPath(start,end);
	}

	/* 
	 * Complete the totalPathDistance function, which calculates how far the given
	 * path traverses.
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(1)")
	public static double totalPathDistance( Iterable<Edge<Walkway>> path ) {
		int totalPath = 0;
		Iterator<Edge<Walkway>> it = path.iterator();
		while (it.hasNext()) {
			totalPath = totalPath + it.next().getElement().getDistance();
		}
		return totalPath;
	}

	public static void main( String[] aArguments ) {
		new Labyrinth( "SmallLabyrinth.txt" );

		// TODO: Testing
	}

}
