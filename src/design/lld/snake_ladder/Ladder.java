package design.lld.snake_ladder;

public class Ladder {
	
	private int startPoint;
	
	private int endPoint;
	
	public Ladder(int startPoint, int endPoint) {
		if(endPoint<startPoint) {
			throw new IllegalArgumentException("Ladder's End Point should be greater than its Start Point");
		}
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public int getEndPoint() {
		return endPoint;
	}
	
}
