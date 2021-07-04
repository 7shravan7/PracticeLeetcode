package interviewQuestions;

import java.util.PriorityQueue;

/* **Medium**    973. K Closest Points to Origin
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 *  return the k closest points to the origin (0, 0).

	The distance between two points on the X-Y plane is the Euclidean distance (i.e., sqrt((x1 - x2)^2 + (y1 - y2)^2)).
	
	You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
	
	Example 1:
		Input: points = [[1,3],[-2,2]], k = 1
		Output: [[-2,2]]
		Explanation:
			The distance between (1, 3) and the origin is sqrt(10).
			The distance between (-2, 2) and the origin is sqrt(8).
			Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
		We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
		
	Example 2:
		Input: points = [[3,3],[5,-1],[-2,4]], k = 2
		Output: [[3,3],[-2,4]]
		Explanation: The answer [[-2,4],[3,3]] would also be accepted.

	Constraints:
		1 <= k <= points.length <= 104
		-104 < xi, yi < 104
 */
public class KClosestPointsToOrigin {
	
	private double getEuclideanDistanceFromOrigin(int[] point) {
		return Math.sqrt((point[0]*point[0])+(point[1]*point[1]));
	}

	public int[][] kClosestMinHeap(int[][] points, int k) {
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(k,(point1,point2)->{
			double dist1 = getEuclideanDistanceFromOrigin(point1);
			double dist2 = getEuclideanDistanceFromOrigin(point2);
			if(dist2>dist1) {
				return -1;
			} else {
				return 1;
			}
		});
		int[][] closestPoints = new int[k][2];
		int noOfPoints = points.length;
		for(int i=0;i<noOfPoints;i++) {
			minHeap.add(points[i]);
		}
		for(int i=0;i<k;i++) {
			closestPoints[i] = minHeap.poll();
		}
		return closestPoints;
	}
	
	public int[][] kClosestMaxHeap(int[][] points, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>(k,(point1,point2)->{
			double dist1 = getEuclideanDistanceFromOrigin(point1);
			double dist2 = getEuclideanDistanceFromOrigin(point2);
			if(dist2>dist1) {
				return 1;
			} else {
				return -1;
			}
		});
		int[][] closestPoints = new int[k][2];
		int noOfPoints = points.length;
		for(int i=0;i<noOfPoints;i++) {
			maxHeap.add(points[i]);
			if(maxHeap.size()>k) {
				maxHeap.poll();
			}
		}
		for(int i=0;i<k;i++) {
			closestPoints[i] = maxHeap.poll();
		}
		return closestPoints;
	}
	
	private int getEuclideanDistanceFromOrigin1(int[] point) {
		return (point[0]*point[0])+(point[1]*point[1]);
	}
	
	public int[][] kClosestMaxHeap1(int[][] points, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>(k,(point1,point2)->{
			int dist1 = getEuclideanDistanceFromOrigin1(point1);
			int dist2 = getEuclideanDistanceFromOrigin1(point2);
			return dist2-dist1;
		});
		int[][] closestPoints = new int[k][2];
		int noOfPoints = points.length;
		for(int i=0;i<noOfPoints;i++) {
			maxHeap.add(points[i]);
			if(maxHeap.size()>k) {
				maxHeap.poll();
			}
		}
		for(int i=0;i<k;i++) {
			closestPoints[i] = maxHeap.poll();
		}
		return closestPoints;
	}
	
	public void printPoints(int[][] points) {
		int rowCount = points.length;
		for(int i=0;i<rowCount;i++) {
			System.out.println("("+points[i][0]+","+points[i][1]+")");
		}
	}

	public static void main(String[] args) {
		KClosestPointsToOrigin kClosestPoints = new KClosestPointsToOrigin();
		int[][] points1 = {{1,3},{-2,2}};
		int k1 = 1;
		kClosestPoints.printPoints(kClosestPoints.kClosestMinHeap(points1, k1));
		kClosestPoints.printPoints(kClosestPoints.kClosestMaxHeap(points1, k1));
		kClosestPoints.printPoints(kClosestPoints.kClosestMaxHeap1(points1, k1));
		int[][] points2 = {{3,3},{5,-1},{-2,4}};
		int k2 = 2;
		kClosestPoints.printPoints(kClosestPoints.kClosestMinHeap(points2, k2));
		kClosestPoints.printPoints(kClosestPoints.kClosestMaxHeap(points2, k2));
		kClosestPoints.printPoints(kClosestPoints.kClosestMaxHeap1(points2, k2));
	}

}
