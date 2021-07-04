package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* **Hard**    759. Employee Free Time
 * 
 * We are given a list schedule of employees, which represents the working time for each employee.

   Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

   Return the list of finite intervals representing common, positive-length free time for all employees, also in 
   sorted order.

   (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
    For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  
    Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

   	Example 1:
		Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
		Output: [[3,4]]
		Explanation: There are a total of three employees, and all common
					 free time intervals would be [-inf, 1], [3, 4], [10, inf].
					 We discard any intervals that contain inf as they aren't finite.
					 
	Example 2:
		Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
		Output: [[5,6],[7,9]]

	Constraints:
		1 <= schedule.length , schedule[i].length <= 50
		0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class EmployeeFreeTime {

	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
		List<Interval> resultList = new ArrayList<>();
		List<Interval> intervalList = new ArrayList<>();
		schedule.forEach(interval->intervalList.addAll(interval)); // add all intervals to new list
		Collections.sort(intervalList, (a,b)->a.start-b.start); // sort based on interval's start time
		Interval compInterval = intervalList.get(0);
		for(int i=1;i<intervalList.size();i++) {
			Interval currInterval = intervalList.get(i);
			// compare prev start time with curr endTime 
			//          if it is greater then there is gap and add it to list and update prev interval
			if(compInterval.end<currInterval.start) {  
				resultList.add(new Interval(compInterval.end, currInterval.start));
				compInterval = currInterval;
			} else {
				compInterval = compInterval.end<currInterval.end ? currInterval: compInterval;
			}
		}
		return resultList;
	}
	
	public static void printResult(List<Interval> resultList) {
		System.out.println();
		for(Interval interval: resultList) {
			System.out.print("["+interval.start+","+interval.end+"]");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		EmployeeFreeTime empFreeTime = new EmployeeFreeTime();
		List<List<Interval>> schedule1 = new ArrayList<>();
		schedule1.add(Arrays.asList(new Interval(1,2),new Interval(5,6)));
		schedule1.add(Arrays.asList(new Interval(1,3)));
		schedule1.add(Arrays.asList(new Interval(4,10)));
		List<Interval> employeeFreeTime1 = empFreeTime.employeeFreeTime(schedule1);
		printResult(employeeFreeTime1);
		List<List<Interval>> schedule2 = new ArrayList<>();
		schedule2.add(Arrays.asList(new Interval(1,3),new Interval(6,7)));
		schedule2.add(Arrays.asList(new Interval(2,4)));
		schedule2.add(Arrays.asList(new Interval(2,5),new Interval(9,12)));
		List<Interval> employeeFreeTime2 = empFreeTime.employeeFreeTime(schedule2);
		printResult(employeeFreeTime2);
	}
}

class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
}