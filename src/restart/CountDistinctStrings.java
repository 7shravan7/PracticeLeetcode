package restart;

public class CountDistinctStrings {

    public static int countDistinctSubstrings(String s) {
        TrieNode root = new TrieNode();
        int count=1; //including empty string
        for(int i=0;i<s.length();i++) {
            TrieNode node = root;
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (!node.containsChar(ch)) {
                    node.put(ch);
                    count++;
                }
                node = node.getNode(ch);
            }
        }
        return count;
    }

}

class TrieNode {

    TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[26];
    }

    public boolean containsChar(char ch) {
        return children[ch-'a']!=null;
    }

    public TrieNode getNode(char ch){
        return children[ch-'a'];
    }

    public void put(char ch){
        children[ch-'a'] = new TrieNode();
    }
}
