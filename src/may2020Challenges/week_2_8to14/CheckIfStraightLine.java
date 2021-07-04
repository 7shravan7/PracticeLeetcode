package codes.LeetCode.may2020Challenges.week_2_8to14;
/*
 * you are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a 
 * point. Check if these points make a straight line in the XY plane.
 * Example 1
 * 		Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
		Output: true
	Example 2
		Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
		Output: false
	Constraints:
		2 <= coordinates.length <= 1000
		coordinates[i].length == 2
		-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
		coordinates contains no duplicate point.
 */
public class CheckIfStraightLine {
	
	public static boolean checkStraightLine(int[][] coordinates) {
		int len = coordinates.length;
		if(len==2) {
			return true;
		} else {
			int x = coordinates[0][0];
			int y = coordinates[0][1];
			int x1 = coordinates[1][0];
			int y1 = coordinates[1][1];
			int diffX = x1-x;
			int diffY = y1-y;
			for(int i=2; i<len; i++) {
				if(coordinates[i][0]-coordinates[i-1][0]!=diffX ||
						coordinates[i][1]-coordinates[i-1][1]!=diffY) {
					return false;
				}
			}
			return true;
		}
    }

	public static void main(String[] args) {
		int[][] coordinates = {{1,2},{2,3},{3,4},{4,5}};
		System.out.println(CheckIfStraightLine.checkStraightLine(coordinates));
	}

}
