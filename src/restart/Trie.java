package restart;

public class Trie {

    class TrieNode {

        char ch;

        TrieNode[] children;

        boolean isWord;

        int count;

        public TrieNode(char ch) {
            this.ch = ch;
            children = new TrieNode[26];
            isWord = false;
            count = 0;
        }

        public TrieNode insert(char ch){
            TrieNode newNode = new TrieNode(ch);
            children[ch-'a'] = newNode;
            return newNode;
        }

        public boolean containsChar(char ch) {
            return children[ch-'a']!=null;
        }

        public TrieNode getNode(char ch){
            return children[ch-'a'];
        }

        public boolean isWord(){
            return isWord;
        }

        public void markEnd() {
            isWord = true;
        }

        public boolean isEmpty() {
            for(int i=0;i<26;i++){
                if(children[i]!=null) {
                    return false;
                }
            }
            return true;
        }

        public int getCount(){
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

    TrieNode root;

    public Trie() {
        root = new TrieNode('.');
    }

    public void insert(String word) {
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
        int wordCount = node.getCount();
        node.setCount(wordCount+1);
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = getTrieNode(word);
        return node == null ? 0 : node.getCount();
    }

    public int countWordsStartingWith(String word) {
        TrieNode node = getTrieNode(word);
        if(node == null){
            return 0;
        }
        return getChildrenCount(node);
    }

    public void erase(String word) {
        erase(word, 0, root);
    }

    private TrieNode getTrieNode(String word) {
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            node = node.getNode(ch);
            if(node == null){
                return null;
            }
        }
        return node;
    }

    private int getChildrenCount(TrieNode node){
        int count = node.getCount();
        for(int i=0;i<26;i++){
            if(node.children[i]!=null){
                count += getChildrenCount(node.children[i]);
            }
        }
        return count;
    }

    private TrieNode erase(String word, int index, TrieNode node){
        if(index == word.length()){
            int count = node.getCount();
            node.setCount(count-1);
            if(count-1 == 0 && node.isEmpty() && node.isWord()){
                return null;
            }
            return node;
        }
        char ch = word.charAt(index);
        TrieNode chNode = node.getNode(ch);
        node.children[ch-'a'] = erase(word, index+1, chNode);
        if(node.isEmpty() && !node.isWord()){
            node = null;
        }
        return node;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("coding");
        trie.insert("ninja");
        trie.insert("ninjaaa");
        trie.insert("ninja");
        System.out.println(trie.countWordsEqualTo("ninja"));
        trie.erase("ninja");
        System.out.println(trie.countWordsEqualTo("ninja"));
        System.out.println(trie.countWordsStartingWith("ninj"));
        trie.erase("ninja");
        System.out.println(trie.countWordsEqualTo("ninja"));
        System.out.println(trie.countWordsStartingWith("ninj"));
     }
}

