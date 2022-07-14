package restart;

public class CompleteString {

    class TrieNode {

        char ch;
        TrieNode[] children;
        boolean isWord;

        public TrieNode(char ch) {
            this.ch = ch;
            children = new TrieNode[26];
            isWord = false;
        }

        public TrieNode insert(char ch){
            TrieNode newNode = new TrieNode(ch);
            children[ch-'a'] = newNode;
            return newNode;
        }

        public boolean containsChar(char ch){
            return children[ch-'a']!=null;
        }

        public TrieNode getNode(char ch){
            return children[ch-'a'];
        }

        public void markEnd(){
            isWord = true;
        }

        public boolean isWord() {
            return isWord;
        }

        public TrieNode[] getChildren() {
            return children;
        }

        public boolean isEmpty() {
            for(int i=0;i<26;i++){
                if(children[i]!=null){
                    return false;
                }
            }
            return true;
        }
    }

    class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode('.');
        }

        public void insertWord(String word){
            TrieNode node = root;
            for(int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if(node.containsChar(ch)){
                    node = node.getNode(ch);
                } else {
                    node = node.insert(ch);
                }
            }
            node.markEnd();
        }

        public boolean checkIfAllPrefixExists(String word){
            TrieNode node = root;
            for(int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if(node.containsChar(ch)){
                    node = node.getNode(ch);
                    if(!node.isWord()){
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for(String str : a){
            trie.insertWord(str);
        }
        String completeStr = "";
        TrieNode node = trie.root;
        for(String str : a){
            if(trie.checkIfAllPrefixExists(str)){
                if(completeStr.length()<str.length()){
                    completeStr = str;
                } else if (completeStr.length() == str.length() &&
                        completeStr.compareTo(str)<0){
                    completeStr = str;
                }
            }
        }
        return completeStr;
    }

    public static void main(String[] args) {
        CompleteString cs = new CompleteString();
        String[] a = {"g", "a", "ak", "szhkb" ,"hy"};
        System.out.println(cs.completeString(5, a));
    }

}
