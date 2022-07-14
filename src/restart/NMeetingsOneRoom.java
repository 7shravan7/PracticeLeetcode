package restart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NMeetingsOneRoom {

    //Function to find the maximum number of meetings that can
    //be performed in a meeting room.
    public static int maxMeetings(int start[], int end[], int n)
    {
        List<int[]> meetingList= new ArrayList<>();
        for(int i=0;i<start.length;i++){
            meetingList.add(new int[]{start[i],end[i]});
        }
        Collections.sort(meetingList, (a, b)->a[1]-b[1]);
        int endLimit = meetingList.get(0)[1];
        int meetingsCount = 1;
        for(int i=1;i<start.length;i++){
            if(meetingList.get(i)[0]>endLimit){
                meetingsCount++;
                endLimit = meetingList.get(i)[1];
            }
        }
        return meetingsCount;
    }

    public static void main(String[] args) {

    }
}
