package design.lld.snake_ladder;

public class Snake {

	private int startPoint;
	
	private int endPoint;
	
	public Snake(int startPoint, int endPoint) {
		if(endPoint>startPoint) {
			throw new IllegalArgumentException("Snake's End Point should be lesser than its Start Point");
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
