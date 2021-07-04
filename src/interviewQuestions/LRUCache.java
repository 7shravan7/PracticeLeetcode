package interviewQuestions;

import java.util.HashMap;
import java.util.Map;

/* **Medium**  146. LRU Cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

	Implement the LRUCache class:

	LRUCache(int capacity) 
			Initialize the LRU cache with positive size capacity.
	int get(int key) 
			Return the value of the key if the key exists, otherwise return -1.
	void put(int key, int value) 
			Update the value of the key if the key exists. 
			Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from
			this operation, evict the least recently used key.
	
	Follow up:
		Could you do get and put in O(1) time complexity?

	Example 1:
	Input["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
		 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    Output
		[null, null, null, 1, null, -1, null, -1, 3, 4]
	Explanation
		LRUCache lRUCache = new LRUCache(2);
		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		lRUCache.get(1);    // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		lRUCache.get(2);    // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		lRUCache.get(1);    // return -1 (not found)
		lRUCache.get(3);    // return 3
		lRUCache.get(4);    // return 4
	Constraints:
		1 <= capacity <= 3000
		0 <= key <= 3000
		0 <= value <= 104
		At most 3 * 104 calls will be made to get and put
 */
class LRUCache {
    
    class DoubleLinkedNode {
        int key;
        int val;
        DoubleLinkedNode prev;
        DoubleLinkedNode next;
        DoubleLinkedNode(int key, int val){
            this.key = key;
            this.val = val;
            prev = next = null;
        }
    }
    
    int maxSize = 0;
    
    int currSize = 0;
    
    Map<Integer,DoubleLinkedNode> memMap;
    
    DoubleLinkedNode head;
    
    DoubleLinkedNode tail;

    public LRUCache(int capacity) {
        maxSize = capacity;
        memMap = new HashMap<>();
        head = tail = null;
    }
    
    public int get(int key) {
    	DoubleLinkedNode dNode = memMap.get(key);
    	if(dNode == null) {
    		return -1;
    	}
    	moveToHead(dNode);
    	return dNode.val;
    }
    
    public void put(int key, int value) {
    	DoubleLinkedNode dNode = memMap.get(key);
    	if(dNode!=null) {
    		dNode.val = value;
    		memMap.put(key, dNode);
    		moveToHead(dNode);
    		return;
    	}
    	if(currSize>maxSize-1) { // size full
    		removeLeastUsedNode();
    	}
    	DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
    	addFirst(newNode);
    	memMap.put(key, newNode);
    	currSize++;
    }
    
    private void removeLeastUsedNode() {
    	int leastUsedKey = tail.key;
    	memMap.remove(leastUsedKey);
    	DoubleLinkedNode prevNode = tail.prev;
    	if(prevNode!=null) {
    		prevNode.next = null;
    	}
    	tail = prevNode;
    	currSize--;
    }
    
    private void addFirst(DoubleLinkedNode dNode) {
    	dNode.next = head;
    	dNode.prev = null;
    	if(head!=null) {
    		head.prev = dNode;
    	}
    	head = dNode;
    	if(tail==null) {
    		tail = head;
    	}
    }
    
    private void removeNode(DoubleLinkedNode dNode) {
    	DoubleLinkedNode prev = dNode.prev;
    	DoubleLinkedNode next = dNode.next;
    	if(prev!=null) {
    		prev.next = next;
    	} 
    	if(next!=null) {
    		next.prev = prev;
    	} else {
    		tail = prev;
    	}
    	if(prev==null) {
    		head = next;
    	}
    }
    
    private void moveToHead(DoubleLinkedNode dNode) {
    	removeNode(dNode);
    	addFirst(dNode);
    }
    
    
    
    public static void main(String[] args) {
    	// ["LRUCache","put","put","get","put","get","put","get","get","get"]
    	//[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
    	System.out.println("----------LRU 0----------------");
    	LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
    	lruCache.put(2, 2);
    	System.out.println(lruCache.get(1));
    	lruCache.put(3, 3);
    	System.out.println(lruCache.get(2));
    	lruCache.put(4, 4);
    	System.out.println(lruCache.get(1));
    	System.out.println(lruCache.get(3));
    	System.out.println(lruCache.get(4));
    	//["LRUCache","put","get","put","get","get"]
    	//[[1],[2,1],[2],[3,2],[2],[3]]
    	System.out.println("----------LRU 1----------------");
    	LRUCache lruCache1 = new LRUCache(1);
    	lruCache1.put(2, 1);
    	System.out.println(lruCache1.get(2));
    	lruCache1.put(3, 2);
    	System.out.println(lruCache1.get(2));
    	System.out.println(lruCache1.get(3));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
