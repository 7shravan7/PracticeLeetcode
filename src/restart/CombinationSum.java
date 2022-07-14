package restart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinationList = new ArrayList<>();
        Arrays.sort(candidates);
        combinations(candidates, combinationList, new ArrayList<>(), target, 0,0);
        return combinationList;
    }

    private void combinations(int[] candidates, List<List<Integer>> combinationList,
                              List<Integer> list, int target, int sum, int index){
        if(target<sum){
            return;
        }
        if(target == sum) {
            combinationList.add(new ArrayList<>(list));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            int num = candidates[i];
            if(num<=target && sum+num<=target){
                list.add(num);
                combinations(candidates, combinationList, list, target, sum+num, i);
                list.remove(list.size()-1);
            }
        }
    }


    public static void printList(List<List<Integer>> resultList){
        resultList.forEach(list->{
            System.out.println();
            System.out.print("[");
            list.forEach(val->{
                System.out.print(val+",");
            });
            System.out.print("]");
            System.out.println();
        });
    }

    public static void main(String[] args) {
        CombinationSum sum = new CombinationSum();
        int[] canidates = {2,3,6,7};
        int target1=7;
        printList(sum.combinationSum(canidates, target1));
    }

}
