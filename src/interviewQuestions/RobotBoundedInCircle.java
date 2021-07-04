package interviewQuestions;

/* **Medium**  1041. Robot Bounded In Circle
 * 
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
	"G": go straight 1 unit;
	"L": turn 90 degrees to the left;
	"R": turn 90 degrees to the right.
	The robot performs the instructions given in order, and repeats them forever.
	Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

	Example 1:
		Input: instructions = "GGLLGG"
		Output: true
		Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
		When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.

	Example 2:
		Input: instructions = "GG"
		Output: false
		Explanation: The robot moves north indefinitely.
		
	Example 3:
		Input: instructions = "GL"
		Output: true
		Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 
	Constraints:
		1 <= instructions.length <= 100
		instructions[i] is 'G', 'L' or, 'R'
 */
public class RobotBoundedInCircle {

	public boolean isRobotBounded(String instructions) {
		// 0->north,1->left(east),2->south,3->right(west)
		int startX=0;
		int startY=0;
		int direction = 0;
		for(char c: instructions.toCharArray()) {
			if(c == 'L') {
				direction = (direction+1)%4;
			} else if (c == 'R') {
				direction = (direction+3)%4;
			} else {
				switch(direction) {
					case 0:
						startY++;
						break;
					case 1:
						startX--;
						break;
					case 2:
						startY--;
						break;
					default:
						startX++;
						break;
				}
			}
		}
		return direction!=0 || (startX==0 && startY==0);
	}

	public static void main(String[] args) {
		RobotBoundedInCircle robot = new RobotBoundedInCircle();
		System.out.println("is there is a circle GGLLGG : "+robot.isRobotBounded("GGLLGG"));
		System.out.println("is there is a circle GG :" +robot.isRobotBounded("GG"));
		System.out.println("is there is a circle GL : "+robot.isRobotBounded("GL"));
		System.out.println("is there is a circle GGLGGL : "+robot.isRobotBounded("GGLGGL"));
		System.out.println("is there is a circle GGLGGR : "+robot.isRobotBounded("GGLGGR"));
		System.out.println("is there is a circle R : "+robot.isRobotBounded("R"));
	}

}
