package restart;

import java.util.ArrayList;

public class MedianInMatrix {

    public int findMedian(ArrayList<ArrayList<Integer>> A) {
        int rowSize = A.size();
        int colSize = A.get(0).size();
        if(rowSize == 1 && colSize == 1){
            return A.get(0).get(0);
        }
        int lowVal = A.get(0).get(0);
        int highVal = 0;
        for(ArrayList<Integer> list: A){
            lowVal = Math.min(lowVal, list.get(0));
            highVal = Math.max(highVal, list.get(colSize-1));
        }
        int medianCount = (rowSize*colSize)/2;
        while(lowVal<=highVal){
            int mid = lowVal+(highVal-lowVal)/2;
            int count = getCountLessThanMid(A, mid, rowSize, colSize);
            if(count>medianCount){
                highVal = mid-1;
            } else{
                lowVal = mid+1;
            }
        }
        return lowVal;
    }

    private int getCountLessThanMid(ArrayList<ArrayList<Integer>> A, int val,
                                    int rowSize, int colSize){
        int minCount = 0;
        for(ArrayList<Integer> list: A){
            int low = 0;
            int high = colSize-1;
            while(low<=high){
                int mid = low+(high-low)/2;
                if(list.get(mid)>val){
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
            minCount += low;
        }
        return minCount;
    }

    public static void main(String[] args) {
        MedianInMatrix medianInMatrix = new MedianInMatrix();
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(3);
        row1.add(3);
        A.add(row1);
        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(2);
        row2.add(6);
        row2.add(9);
        A.add(row2);
        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(3);
        row3.add(6);
        row3.add(9);
        A.add(row3);
        System.out.println(medianInMatrix.findMedian(A));
    }
}
