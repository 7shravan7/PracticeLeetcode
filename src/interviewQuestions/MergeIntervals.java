package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* **Medium**     56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, 
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * 
 * Example 1:
	Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

   Example 2:
	Input: intervals = [[1,4],[4,5]]
	Output: [[1,5]]
	Explanation: Intervals [1,4] and [4,5] are considered overlapping.

	Constraints:
		1 <= intervals.length <= 104
		intervals[i].length == 2
		0 <= starti <= endi <= 104
 */
public class MergeIntervals {
	
	/*
	 * Time Complexity  : O(nlogn) since we sorted by start asc
	 * Space Complexity : O(n)
	 */
	public int[][] merge(int[][] intervals) {
		int length = intervals.length;
		List<int[]> resultList = new ArrayList<>();
		Arrays.sort(intervals, (a,b)->a[0]-b[0]); // sort asc by start time
		int start = intervals[0][0];
		int end = intervals[0][1];
		for(int i=1;i<length;i++) {
			if(end>=intervals[i][0]) {
				end = Math.max(end, intervals[i][1]);
			} else {
				resultList.add(new int[] {start, end});
				start = intervals[i][0];
				end = intervals[i][1];
			}
		}
		resultList.add(new int[] {start, end}); // to add last (start,end) to result
//		int[][] resultInterval = new int[resultList.size()][2];
//		for(int i=0;i<resultList.size();i++) {
//			resultInterval[i] = resultList.get(i);
//		}
		int[][] resultInterval = resultList.toArray(new int[resultList.size()][]);
		return resultInterval;
	}
	
	private void printResult(int[][] mergedIntervals) {
		System.out.println("------------Merged Intervals------------");
		int len = mergedIntervals.length;
		for(int i =0; i<len; i++) {
			System.out.println("("+mergedIntervals[i][0]+","+mergedIntervals[i][1]+")");
		}
	}

	public static void main(String[] args) {
		MergeIntervals mergeIntervals = new MergeIntervals();
		int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
		int[][] mergedIntervals1 = mergeIntervals.merge(intervals1);
		mergeIntervals.printResult(mergedIntervals1);
		int[][] intervals2 = {{1,4},{5,5},{2,6}};
		int[][] mergedIntervals2 = mergeIntervals.merge(intervals2);
		mergeIntervals.printResult(mergedIntervals2);
	}

}
