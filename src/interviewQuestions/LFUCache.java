package interviewQuestions;

import java.util.HashMap;
import java.util.Map;

/* **Hard**   460. LFU Cache
 * 
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.

	Implement the LFUCache class:

	LFUCache(int capacity) 
			Initializes the object with the capacity of the data structure.
	int get(int key) 
			Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
	void put(int key, int value) 
			Update the value of the key if present, or inserts the key if not already present. 
			When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. 
			For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
			
	To determine the least frequently used key, a use counter is maintained for each key in the cache. 
	The key with the smallest use counter is the least frequently used key.

	When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). 
	The use counter for a key in the cache is incremented either a get or put operation is called on it.

	The functions get and put must each run in O(1) average time complexity.

	Example 1:
		Input
			["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
			[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
		Output
			[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

	Explanation
		// cnt(x) = the use counter for key x
		// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
		LFUCache lfu = new LFUCache(2);
		lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
		lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
		lfu.get(1);      // return 1
                 		 // cache=[1,2], cnt(2)=1, cnt(1)=2
		lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 		 // cache=[3,1], cnt(3)=1, cnt(1)=2
		lfu.get(2);      // return -1 (not found)
		lfu.get(3);      // return 3
                 	 	 // cache=[3,1], cnt(3)=2, cnt(1)=2
		lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 		 // cache=[4,3], cnt(4)=1, cnt(3)=2
		lfu.get(1);      // return -1 (not found)
		lfu.get(3);      // return 3
                 		 // cache=[3,4], cnt(4)=1, cnt(3)=3
		lfu.get(4);      // return 4
                 		 // cache=[3,4], cnt(4)=2, cnt(3)=3
	Constraints:
		0 <= capacity <= 104
		0 <= key <= 105
		0 <= value <= 109
		At most 2 * 105 calls will be made to get and put.
 * 
 */
public class LFUCache {
	
	Map<Integer, DlNode> cacheMap;
	
	Map<Integer, DoubleLinkedList> frequencyMap;
	
	int minFrequency;
	
	int capacity;
	
	int size;
	 
	
	public LFUCache(int capacity) {
		this.capacity = capacity;
		minFrequency = 0;
		cacheMap = new HashMap<>();
		frequencyMap = new HashMap<>();
	}
	
	/*
	 * Returns if key is there in map and also updates its frequency
	 */
	public int get(int key) {
		if(cacheMap.containsKey(key)) {
			DlNode node = cacheMap.get(key);
			updateFreq(node);
			return node.val;
		}
		return -1;
	}

	/*
	 * 1. If capacity is 0, return
	 * 2. If key is already present,updates its value and also its frequency
	 * 3. If Key is not there,
	 * 		3.1) If size is full, remove the last node which has MinFrequency (LFU-least frequently used) and decrease size
	 * 		3.2) If not, set minFreqency to 1 because of new node and add it to cache map and as well as frequency map
	 */
	public void put(int key, int value) {
		if(capacity==0) {
			return;
		}
		if(cacheMap.containsKey(key)) {
			DlNode node = cacheMap.get(key);
			node.val = value;
			cacheMap.put(key, node);
			updateFreq(node);
		} else {
			if(size == capacity) {
				DoubleLinkedList dList = frequencyMap.get(minFrequency);
				DlNode tailNode = dList.removeTail();
				cacheMap.remove(tailNode.key);
				size--;
			}
			minFrequency=1; // because new node will reset MinFreqency to 1
			DlNode newNode = new DlNode(key, value);
			cacheMap.put(key, newNode);
			DoubleLinkedList dList = frequencyMap.getOrDefault(minFrequency, new DoubleLinkedList());
			dList.addFirst(newNode);
			frequencyMap.put(minFrequency, dList);
			size++;
		}
	}
	
	/*
	 * UpdateFreq updates the frequency of the node and removes the node from prev frequency key 
	 * and adds it to new frequency key
	 * Also, if prev frequency key is same as MinFrequency and size of its list is zero after removing from map,
	 *  increase the minFrequency (!!!)
	 */
	private void updateFreq(DlNode node) {
		int frequency = node.freq;
		DoubleLinkedList dList = frequencyMap.get(frequency);
		dList.remove(node);
		if(frequency == minFrequency && dList.size==0) {
			minFrequency++;
		}
		frequency++;
		node.freq = frequency;
		DoubleLinkedList list = frequencyMap.getOrDefault(frequency, new DoubleLinkedList());
		list.addFirst(node);
		frequencyMap.put(frequency, list);
	}

	public static void main(String[] args) {
		/*int k=2;
		LFUCache lfu = new LFUCache(k);
		lfu.put(1, 1);
		lfu.put(2, 2);
		System.out.println(lfu.get(1));
		lfu.put(3, 3);
		lfu.get(2);
		System.out.println(lfu.get(3));
		lfu.put(4, 4);
		System.out.println(lfu.get(1));
		System.out.println(lfu.get(3));
		System.out.println(lfu.get(4));*/
		//["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
		//[[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]
		/*System.out.println("--------------------");
		int k1=3;
		LFUCache lfu1 = new LFUCache(k1);
		lfu1.put(2, 2);
		lfu1.put(1, 1);
		System.out.println(lfu1.get(2));
		System.out.println(lfu1.get(1));
		System.out.println(lfu1.get(2));
		lfu1.put(3, 3);
		lfu1.put(4, 4);
		System.out.println(lfu1.get(3));
		System.out.println(lfu1.get(2));
		System.out.println(lfu1.get(1));
		System.out.println(lfu1.get(4));*/
		//["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
		//[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
		int k2=10;
		LFUCache lfu2 = new LFUCache(k2);
		lfu2.put(10,13);
		lfu2.put(3,17);
		lfu2.put(6,11);
		lfu2.put(10,5);
		lfu2.put(9,10);
		System.out.println(lfu2.get(13));
		lfu2.put(2,19);
		System.out.println(lfu2.get(2));
		System.out.println(lfu2.get(3));
		lfu2.put(5,25);
		System.out.println(lfu2.get(8));
		lfu2.put(9,22);
		lfu2.put(5,5);
		lfu2.put(1,30);
		System.out.println(lfu2.get(11));
		lfu2.put(9,12);
		System.out.println(lfu2.get(7));
		System.out.println(lfu2.get(5));
		System.out.println(lfu2.get(8));
		System.out.println(lfu2.get(9));
		lfu2.put(4,30);
		lfu2.put(9,3);
		System.out.println(lfu2.get(9));
		System.out.println(lfu2.get(10));
		System.out.println(lfu2.get(10));
		lfu2.put(6,14);
		lfu2.put(3,1);
		System.out.println(lfu2.get(3));
		lfu2.put(10,11);
		System.out.println(lfu2.get(8));
		lfu2.put(2,14);
		//[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],
		System.out.println(lfu2.get(1));
		System.out.println(lfu2.get(5));
		System.out.println(lfu2.get(4));
		lfu2.put(11,4);
		lfu2.put(12,24);
		lfu2.put(5,18);
		System.out.println(lfu2.get(13));
		lfu2.put(7,23);
		System.out.println(lfu2.get(8));
		System.out.println(lfu2.get(12));
		lfu2.put(3,27);
		lfu2.put(2,12);
		System.out.println(lfu2.get(5));
		lfu2.put(2,9);
		lfu2.put(13,4);
		lfu2.put(8,18);
		lfu2.put(1,7);
		System.out.println(lfu2.get(6));
		lfu2.put(9,29);
		lfu2.put(8,21);
		System.out.println(lfu2.get(5));
		//[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],
		lfu2.put(6,30);
		lfu2.put(1,12);
		System.out.println(lfu2.get(10));
		lfu2.put(4,15);
		lfu2.put(7,22);
		lfu2.put(11,26);
		lfu2.put(8,17);
		lfu2.put(9,29);
		System.out.println(lfu2.get(5));
		lfu2.put(3,4);
		lfu2.put(11,30);
		System.out.println(lfu2.get(12));
		lfu2.put(4,29);
		System.out.println(lfu2.get(3));
		System.out.println(lfu2.get(9));
		System.out.println(lfu2.get(6));
		lfu2.put(3,4);
		System.out.println(lfu2.get(1));
		System.out.println(lfu2.get(10));
		lfu2.put(3,29);
		lfu2.put(10,28);
		//[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19]
		lfu2.put(1,20);
		lfu2.put(11,13);
		System.out.println(lfu2.get(3));
		lfu2.put(3,12);
		lfu2.put(3,8);
		lfu2.put(10,9);
		lfu2.put(3,26);
		System.out.println(lfu2.get(8));
		System.out.println(lfu2.get(7));
		System.out.println(lfu2.get(5));
		lfu2.put(13,17);
		lfu2.put(2,27);
		lfu2.put(11,15);
		System.out.println(lfu2.get(12));
		lfu2.put(9,19);
		lfu2.put(2,15);
		lfu2.put(3,16);
		System.out.println(lfu2.get(1));
		lfu2.put(12,17);
		lfu2.put(9,1);
		lfu2.put(6,19);
		//,[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]
	}
	
	class DlNode {
		
		int key;
		int val;
		int freq;
		DlNode prev;
		DlNode next;
		
		public DlNode(int key, int val) {
			this.key = key;
			this.val=val;
			prev=next=null;
			freq = 1;
		}
	}
	
	class DoubleLinkedList {
		
		DlNode head;
		DlNode tail;
		int size;
		
		public DoubleLinkedList() {
			head = tail = null;
			size = 0;
		}
		
		public void addFirst(DlNode node) {
			node.next = head;
			if(head!=null) {
				head.prev = node;
			}
			head = node;
			if(tail==null) {
				tail = head;
			}
			size++;
		}
		
		public void remove(DlNode node) {
			DlNode prev = node.prev;
			DlNode next = node.next;
			if(prev!=null) {
				prev.next= next;
			} else {
				head = next;
			}
			if(next!=null) {
				next.prev = prev;
			} else {
				tail = prev;
			}
			if(tail == null) {
				tail = head;
			}
			node.next = node.prev = null;  // update next and prev to null (!!to find this issue took time!!!)
			size--;
		}
		
		public DlNode removeTail() {
			if(size>0) {
				DlNode node = tail;
				/*DlNode prev = tail.prev;
				if(prev!=null) {
					prev.next = null;
				}
				tail = prev;*/
				remove(node);
				return node;
			} else {
				return null;
			}
		}
	}

}
