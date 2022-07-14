package restart;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        minHeap.offer(intervals[0]);
        for(int i=1;i< intervals.length;i++){
            int[] minInterval = minHeap.peek();
            if(minInterval[1]<=intervals[i][0]){
                minHeap.poll();
            }
            minHeap.offer(intervals[i]);
        }
        return minHeap.size();
    }

    public static void main(String[] args) {
        MeetingRoomsII meetingRoomsII = new MeetingRoomsII();
        int[][] intervals1= {{0,30},{5,10},{15,20}};
        System.out.println(meetingRoomsII.minMeetingRooms(intervals1));
        int[][] intervals2 = {{7,10},{2,4}};
        System.out.println(meetingRoomsII.minMeetingRooms(intervals2));
    }
}
