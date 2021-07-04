package interviewQuestions;

import java.util.Arrays;
import java.util.PriorityQueue;

/* **Medium**    253. Meeting Rooms II
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number 
 * of conference rooms required.

	Example 1:
		Input: intervals = [[0,30],[5,10],[15,20]]
		Output: 2
		
	Example 2:
		Input: intervals = [[7,10],[2,4]]
		Output: 1

	Constraints:
		1 <= intervals.length <= 104
		0 <= starti < endi <= 106
 */
public class MeetingRooms2 {
	
	/*
	 * Time Complexity 	: O(nlogn)
	 * Space Complexity : O(n)
	 */
	public int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, (a,b)->a[0]-b[0]);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->a-b);
		minHeap.add(intervals[0][1]); // add first end time
		for(int i=1;i<intervals.length;i++) {
			if(intervals[i][0]>=minHeap.peek()) { // if min end time <= interval's start time then room will become free
				minHeap.poll();
			}
			minHeap.add(intervals[i][1]);
		}
		return minHeap.size();
	}

	public static void main(String[] args) {
		MeetingRooms2 meetingRooms2 = new MeetingRooms2();
		int[][] intervals1 = {{0,30},{5,10},{15,20}};
		System.out.println(meetingRooms2.minMeetingRooms(intervals1));
		int[][] intervals2 = {{7,10},{2,4}};
		System.out.println(meetingRooms2.minMeetingRooms(intervals2));
	}

}
