package googleInterview_Sep2021;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
/*
     Round 2 happened on Sep 14, 2021 9.30-10.15pm IST
 */
public class Round2 {
    /*


Q: User watched a Youtube video. Given a log of minutes watched. Find the number of unique minutes watched.
Input: [0, 5], [8, 10], [1, 6], [12, 18]
          5.      2.       1.       6
Output: 14

My Initial approach :
1. Sort the input -- [0,5] [1,6] [8,10] [12, 18]
2. Merge the intervals -- [0,6] [8, 10] [12, 18]
3. Add all times (endTime-startTime)


int[] -> interval ; [0] - startTime ; [1] - endTime */

    public int findUniqueMinutes(List<int[]> intervalList) {
        if(Objects.isNull(intervalList) || intervalList.isEmpty()){ // base case)
            return 0;
        }
        int uniqueMinutes = 0;
        // sort the interval list based on start time of intervals in asc
        Collections.sort(intervalList, (a, b)->a[0]-b[0]);
        // find and merge intervals if they overlap
        int[] interval = intervalList.get(0);
        int startTime = interval[0];
        int endTime = interval[1];
        for(int i=1; i<intervalList.size(); i++) {
            int[] currInterval = intervalList.get(i);
            if(endTime>=currInterval[0]){ // overlap
                endTime = Math.max(endTime, currInterval[1]);  // update end time of interval (merging the interval)
            } else {
                uniqueMinutes += endTime - startTime;  // calculating unqiue minutes
                startTime = currInterval[0];
                endTime = currInterval[1];
            }
        }
        return uniqueMinutes + (endTime - startTime);
    }

/*
Q:  BigO? O(n) where n is size of the intervalList

Q: How would you test this solution?
- Create a unit test

Q: Please list all the test cases you would consider for your unit tests
  TC1 : what if intervalList is empty
  TC2 : what if intervalList is null
  TC3 : Consider 2 intervals of i/p [0,2][10,15] ; o/p =7
  TC4 : Consider 3 intervals of i/p [0,5] [1,6] [8,10] [12, 18] ; o/p=14
  TC5 : Consider 3 intervals of i/p [0,10] [1,7] [2,10] ; o/p=10
  TC6 : Conisder 1 intervals of i/p [1,6] ;o/p=5
  TC7 : Consider 3 intervals of i/p [0, 5], [8, 10], [1, 6], [12, 18] ; o/p=14

Q: Please implement TC3 as a jUnit
@Test
public void testCase3 () {
  List<int[]> intervalList = new ArrayList<>();
  int[] interval1 = {0,2};
  int[] interval2 = {10,15};
  intervalList.add(interval1);
  intervalList.add(interval2);
  assertEquals("Test case 3 scenario Failed because o/p doesn't match", 7, findUniqueMinutes(intervalList));
}
[0, 12], [0, 5]*/
}
