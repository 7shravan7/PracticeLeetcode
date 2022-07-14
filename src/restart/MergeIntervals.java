package restart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        List<int[]> resultList = new ArrayList<>();
        StringBuilder sb= new StringBuilder();
        resultList.get(0);
        Arrays.sort(intervals, (a,b)->a[0]-b[0]); // sort based on start time
        int currStartTime = intervals[0][0];
        int currEndTime = intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(currEndTime>=intervals[i][0]){
                currEndTime = Math.max(currEndTime, intervals[i][1]);
            } else {
                resultList.add(new int[]{currStartTime, currEndTime});
                currStartTime = intervals[i][0];
                currEndTime = intervals[i][1];
            }
        }
        resultList.add(new int[]{currStartTime, currEndTime});
        return resultList.toArray(new int[resultList.size()][]);
    }

    public static void main(String[] args) {

    }
}
