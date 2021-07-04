package interviewQuestions;

import java.util.PriorityQueue;

/* **Hard**  23. Merge k Sorted Lists
 * 
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

   Merge all the linked-lists into one sorted linked-list and return it.

	Example 1:
		Input: lists = [[1,4,5],[1,3,4],[2,6]]
		Output: [1,1,2,3,4,4,5,6]
		Explanation: The linked-lists are:
			[
  				1->4->5,
  				1->3->4,
  				2->6
			]
			merging them into one sorted list:
				1->1->2->3->4->4->5->6
				
	Example 2:
		Input: lists = []
		Output: []
			
	Example 3:
		Input: lists = [[]]
		Output: []

	Constraints:
		k == lists.length
		0 <= k <= 10^4
		0 <= lists[i].length <= 500
		-10^4 <= lists[i][j] <= 10^4
		lists[i] is sorted in ascending order.
		The sum of lists[i].length won't exceed 10^4.
 */
public class MergeKSortedLists {

	public ListNode mergeKLists1(ListNode[] lists) {
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b)->a.val-b.val);
		ListNode resultNodeHead = new ListNode(0);
		ListNode resultTemp = resultNodeHead;
		for(ListNode node : lists) {
			while(node!=null) {
				minHeap.add(node);
				node = node.next;
			}
		}
		while(!minHeap.isEmpty()) {
			ListNode node = minHeap.poll();
			node.next = null;
			resultTemp.next = node;
			resultTemp = resultTemp.next;
			
		}
		return resultNodeHead.next;
	}
	
	public ListNode mergeKLists(ListNode[] lists) {
		int size = lists.length;
		return merge(lists, 0 , size-1);
	}
	
	private ListNode merge(ListNode[] lists, int start, int end) {
		if(start == end) {
			return lists[start];
		}
		int mid = start + (end - start)/2;
		return merge(merge(lists, start, mid), merge(lists, mid+1, end));
	}
	
	private ListNode merge(ListNode a, ListNode b) {
		ListNode head = new ListNode();
		ListNode temp = head;
		while(a!=null && b!=null) {
			if(a.val<=b.val) {
				temp.next = a;
				a = a.next;
			} else {
				temp.next = b;
				b = b.next;
			}
			temp = temp.next;
		}
		while(a!=null) {
			temp.next = a;
			a = a.next;
			temp = temp.next;
		}
		while(b!=null) {
			temp.next = b;
			b = b.next;
			temp = temp.next;
		}
		return head.next;
	}

	public static void main(String[] args) {
		MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
		ListNode node1 = new ListNode(1);
		node1.next = new ListNode(4);
		node1.next.next = new ListNode(5);
		ListNode node2 = new ListNode(1);
		node2.next = new ListNode(3);
		node2.next.next = new ListNode(4);
		ListNode node3 = new ListNode(2);
		node3.next = new ListNode(6);
		ListNode[] nodeList = {node1,node2,node3};
		ListNode resultListNode = mergeKSortedLists.mergeKLists(nodeList);
		mergeKSortedLists.printResultList(resultListNode);
		ListNode node4 = new ListNode(-2);
		node4.next = new ListNode(-1);
		node4.next.next = new ListNode(-1);
		node4.next.next.next = new ListNode(-1);
		ListNode node5 = null;
		ListNode[] nodeList1 = {node4,node5};
		ListNode resultListNode1 = mergeKSortedLists.mergeKLists(nodeList1);
		mergeKSortedLists.printResultList(resultListNode1);
	}
	
	private void printResultList(ListNode resultNode) {
		System.out.println();
		ListNode temp = resultNode;
		while(temp!=null) {
			System.out.print(temp.val);
			temp = temp.next;
			if(temp!=null) {
				System.out.print("->");
			}
		}
		System.out.println();
	}
	
}

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
