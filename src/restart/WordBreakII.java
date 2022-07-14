package restart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBreakII {

    public static ArrayList<String> wordBreak(String s, ArrayList<String> dictionary) {
        // Write your code here.
        ArrayList<String> resultList = new ArrayList<>();
        Set<String> dictionarySet = new HashSet<>(dictionary);
        List<String> wordList = new ArrayList<>();
        wordBreak(s, dictionarySet, wordList, resultList);
        return resultList;
    }

    private static void wordBreak(String s, Set<String> dictionarySet, List<String> wordList,ArrayList<String> resultList){
        if(s.isEmpty()){
            resultList.add(wordList.stream().collect(Collectors.joining(" ")));
            return;
        }
        for(int i=0;i<s.length();i++){
            String str = s.substring(0,i+1);
            if(dictionarySet.contains(str)){
                wordList.add(str);
                wordBreak(s.substring(i+1), dictionarySet, wordList, resultList);
                wordList.remove(wordList.size()-1);
            }
        }
    }

    public static void print(ArrayList<String> resultList){
        for(String s:resultList){
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        String str = "godisnowherenowhere";
        ArrayList<String> dictList = new ArrayList<>();
        dictList.add("god");
        dictList.add("is");
        dictList.add("now");
        dictList.add("no");
        dictList.add("where");
        dictList.add("here");
        ArrayList<String> resultList = WordBreakII.wordBreak(str, dictList);
        print(resultList);
    }
}
