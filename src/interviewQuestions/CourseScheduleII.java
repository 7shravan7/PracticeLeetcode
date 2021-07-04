package interviewQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* **Medium**   210. Course Schedule II
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first 
 * if you want to take course ai.

   For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
   Return the ordering of courses you should take to finish all courses. If there are many valid answers, 
   return any of them. If it is impossible to finish all courses, return an empty array.

   Example 1:
		Input: numCourses = 2, prerequisites = [[1,0]]
		Output: [0,1]
		Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
					 So the correct course order is [0,1].
					 
   Example 2:
		Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
		Output: [0,2,1,3]
		Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
		Both courses 1 and 2 should be taken after you finished course 0.
		So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
		
	Example 3:
		Input: numCourses = 1, prerequisites = []
		Output: [0]

	Constraints:
		1 <= numCourses <= 2000
		0 <= prerequisites.length <= numCourses * (numCourses - 1)
		prerequisites[i].length == 2
		0 <= ai, bi < numCourses
		ai != bi
		All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {
	
	private boolean topologicalSortKahnsAlgo(int numCourses, int[][] prerequisites, int[] orderArr) {
		List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
		for(int i=0;i<numCourses;i++) {
			adjacencyList.add(new ArrayList<>());
		}
		int[] inDegCountArr = new int[numCourses];
		for(int i=0;i<prerequisites.length;i++) {
			//prerequisites[i][0] = dependent , prerequisites[i][1] = src
			adjacencyList.get(prerequisites[i][1]).add(prerequisites[i][0]);
			inDegCountArr[prerequisites[i][0]]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		for(int i=0;i<numCourses;i++) {
			if(inDegCountArr[i] == 0) {
				queue.add(i);
			}
		}
		if(queue.isEmpty()) { // there exists cycle
			return true;
		}
		int index=0;
		while(!queue.isEmpty()) {
			int courseNum = queue.poll();
			orderArr[index++] = courseNum;
			adjacencyList.get(courseNum).forEach(course->{
				inDegCountArr[course]--;
				if(inDegCountArr[course]==0) {
					queue.add(course);
				}
			});
		}
		if(index != numCourses) { // there exists cycle
			return true; 
		}
		return false;
	}

	/*
	 * Time Complexity : O(v+e) v- vertices and e- edges
	 * Space Complexity: O(v+e)
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] orderArr = new int[numCourses];
		boolean isCycle = topologicalSortKahnsAlgo(numCourses, prerequisites, orderArr);
		if(isCycle) {
			return  new int[0];
		}
		return orderArr;
	}
	
	public static void printResult(int[] orderArr) {
		System.out.print("[");
		for(int i=0;i<orderArr.length;i++) {
			System.out.print(orderArr[i]);
			if(i!=orderArr.length-1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		CourseScheduleII courseSchedule = new CourseScheduleII();
		int numCourses1 = 2;
		int[][] prerequisites1 = {{1,0}};
		printResult(courseSchedule.findOrder(numCourses1, prerequisites1));
		int numCourses2 = 4;
		int[][] prerequisites2 = {{1,0},{2,0},{3,1},{3,2}};
		printResult(courseSchedule.findOrder(numCourses2, prerequisites2));
		int numCourses3 = 1;
		int[][] prerequisites3 = {};
		printResult(courseSchedule.findOrder(numCourses3, prerequisites3));
		int numCourses4 = 4;
		int[][] prerequisites4 = {{1,0},{3,1},{2,3},{0,2},{3,0}};
		printResult(courseSchedule.findOrder(numCourses4, prerequisites4));
		int numCourses5 = 4;
		int[][] prerequisites5 = {{1,0},{3,1},{0,3},{3,2}};
		printResult(courseSchedule.findOrder(numCourses5, prerequisites5));
	}

}
