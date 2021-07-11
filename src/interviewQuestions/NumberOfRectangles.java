package interviewQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *        .  .  .
 *        .  .  .
 */
public class NumberOfRectangles {
	
	public int numberOfRect(List<Point> pointList) {
		int noOfRectangles = 0;
		Map<String, Integer> pointCountMap = new HashMap<>();
		for(Point point1: pointList) {
			for(Point point2: pointList) {
				if(point1.y == point2.y && point2.x>point1.x) {
					System.out.println("Point1:"+point1.x+","+point1.y+"::"+"Point2:"+point2.x+","+point2.y);
					//Point p = new Point(point1.y,point2.y);
					//int[] p = new int[] {point1.x,point2.x};
					String p=point1.x+","+point2.x;
					int count = pointCountMap.getOrDefault(p, 0);
					noOfRectangles += count;
					pointCountMap.put(p, ++count);
				}
			}
		}
		return noOfRectangles;
	}

	public static void main(String[] args) {
		NumberOfRectangles noOfRectangles = new NumberOfRectangles();
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(0, 2);
		Point p4 = new Point(1, 0);
		Point p5 = new Point(1, 1);
		Point p6 = new Point(1, 2);
		List<Point> pointList = Arrays.asList(p1,p2,p3,p4,p5,p6);
		System.out.println(noOfRectangles.numberOfRect(pointList));
	}

}
class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result += prime * x;
		result += prime * y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if(x == other.y && y == other.x) {
			return true;
		}
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}