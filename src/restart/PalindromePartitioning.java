package restart;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public List<List<String>> partitionDpBackTrack(String s) {
        List<List<String>> resultList = new ArrayList<>();
        int strLen = s.length();
        boolean[][] dp = new boolean[strLen][strLen];
        getPartition(s, new ArrayList<>(), resultList, 0);
        return resultList;
    }

    private void getPartition(String s, List<String> list, List<List<String>> resultList, int start,
                              boolean[][] dp){
        if(start>s.length()-1){
            resultList.add(new ArrayList<>(list));
            return;
        }
        for(int end=start;end<s.length();end++){
            if(s.charAt(start)==s.charAt(end) && (end-start==0 || dp[start+1][end-1])){
                dp[start][end] = true;
                list.add(s.substring(start,end+1));
                getPartition(s, list, resultList, end+1);
                list.remove(list.size()-1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> resultList = new ArrayList<>();
        getPartition(s, new ArrayList<>(), resultList, 0);
        return resultList;
    }

    private void getPartition(String s, List<String> list, List<List<String>> resultList, int start){
        if(start>s.length()-1){
            resultList.add(new ArrayList<>(list));
            return;
        }
        for(int end=start;end<s.length();end++){
            if(isPalindrome(s, start,end)){
                list.add(s.substring(start,end+1));
                getPartition(s, list, resultList, end+1);
                list.remove(list.size()-1);
            }
        }
    }

    private boolean isPalindrome(String str, int start, int end){
        while(start<end){
            if(str.charAt(start)!=str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void printList(List<List<String>> resultList){
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
        PalindromePartitioning pp = new PalindromePartitioning();
        String str = "aababa";
        printList(pp.partitionDpBackTrack(str));
    }
}
