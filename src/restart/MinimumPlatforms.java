package restart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumPlatforms {

    //Function to find the minimum number of platforms required at the
    //railway station such that no train waits.
    static int findPlatform(int arr[], int dep[], int n) {
        List<int[]> timingsList = new ArrayList<>();
        for(int i=0;i<n;i++){
            timingsList.add(new int[]{arr[i],dep[i]});
        }
        Collections.sort(timingsList, (a, b)->a[0]-b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b)->a-b);
        minHeap.offer(timingsList.get(0)[1]);
        for(int i=1;i<n;i++){
            int[] currTime = timingsList.get(i);
            if(minHeap.peek()<currTime[0]){
                minHeap.poll();
            }
            minHeap.offer(currTime[1]);
        }
        return minHeap.size();
    }

    public static void main(String[] args) {
        int n = 6;
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(findPlatform(arr,dep,n));
    }
}
