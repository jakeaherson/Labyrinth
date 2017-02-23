package cs2321;

public class Walkway {
	private String mName;
	private int mDistance;
	private String status = "Unvisited";

	public Walkway( String aName, int aDistance ) {
		mName = aName;
		mDistance = aDistance;
	}

	public String getName() {
		return mName;
	}

	public int getDistance() {
		return mDistance;
	}

	public void setName( String aName ) {
		mName = aName;
	}

	public void setDistance( int aDistance ) {
		mDistance = aDistance;
	}
	
	public void setVisited() {
		status = "Visited";
	}
	
	public void setInProgress() {
		status = "InProgress";
	}
	
	public String getStatus() {
		return status;
	}
}
