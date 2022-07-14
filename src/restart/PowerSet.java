package restart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PowerSet {

    private void powerSet(int index, String s, List<String> resultList, StringBuilder sb) {
        if(index == s.length()){
            if(sb.length()>0) {
                resultList.add(sb.toString());
            }
            return;
        }
        sb.append(s.charAt(index));
        powerSet(index+1, s, resultList, sb);
        sb.deleteCharAt(sb.length()-1);
        powerSet(index+1, s, resultList, sb);
    }

    public List<String> AllPossibleStrings(String s) {
        List<String> resultList = new ArrayList<>();
        powerSet(0,s, resultList, new StringBuilder());
        Collections.sort(resultList);
        return resultList;
    }

    public static void printResult(List<String> resultList) {
        for(String str : resultList){
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        PowerSet ps = new PowerSet();
        String a = "cab";
        printResult(ps.AllPossibleStrings(a));
    }
}

class TrieNode1 {

    char ch;

    TrieNode1[] children;

    int count;

    public TrieNode1(char ch){
        this.ch = ch;
        children = new TrieNode1[26];
        count = 0;
    }

    public boolean containsChar(char ch){
        return children[ch-'a'] != null;
    }

    public TrieNode1 getNode(char ch){
        return children[ch-'a'];
    }

    public void put(char ch){
        TrieNode1 newNode = new TrieNode1(ch);
        //newNode.count = 1;
        children[ch-'a'] = newNode;
    }

    public int getCount(){
        return count;
    }

    public void increaseCount() {
        count += 1;
    }

    public boolean isEmpty() {
        for(int i=0;i<26;i++){
            if(children[i]!=null){
                return false;
            }
        }
        return true;
    }

    public char getChar() {
        return ch;
    }
}
