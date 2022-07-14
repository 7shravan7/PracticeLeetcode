package restart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubsetSum {

    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        // code here
        ArrayList<Integer> subsetSumList = new ArrayList<>();
        getSum(arr, subsetSumList, 0, 0);
        Collections.sort(subsetSumList);
        return subsetSumList;
    }

    void getSum(ArrayList<Integer> arr,ArrayList<Integer> result, int i, int sum){
        if(i == arr.size()){
            result.add(sum);
            return;
        }
        getSum(arr, result, i+1, sum+arr.get(i));
        getSum(arr, result, i+1, sum);
    }

    ArrayList<Integer> subsetSumsCombinatorics(ArrayList<Integer> arr, int N){
        // code here
        ArrayList<Integer> subsetSumList = new ArrayList<>();
        int subsetLength = 1 << N;
        for(int i=0;i<subsetLength;i++){
            int sum = 0;
            int index=0;
            for(int val=i;val>0;val=val>>1){
                if((val&1) == 1){
                    sum += arr.get(index);
                }
                index++;
            }
            subsetSumList.add(sum);
        }
        Collections.sort(subsetSumList);
        return subsetSumList;
    }

    public static void printList(ArrayList<Integer> arr) {
        arr.forEach(val->System.out.print(val+","));
    }

    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(2,4,16,8,1));
        printList(ss.subsetSums(arr1, 5));
        System.out.println("---------");
        printList(ss.subsetSumsCombinatorics(arr1, 5));
    }
}
