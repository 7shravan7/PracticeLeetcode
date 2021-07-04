package interviewQuestions;

/* **Medium**     2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in 
 * reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a
 *  linked list.

   You may assume the two numbers do not contain any leading zero, except the number 0 itself.
   
   Example 1:
 	Input: l1 = [2,4,3], l2 = [5,6,4]
	Output: [7,0,8]
	Explanation: 342 + 465 = 807.
	
   Example 2:
	Input: l1 = [0], l2 = [0]
	Output: [0]
	
   Example 3:
	Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
	Output: [8,9,9,9,0,0,0,1]

	Constraints:
		The number of nodes in each linked list is in the range [1, 100].
		0 <= Node.val <= 9
		It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {
	
	public ListNode addTwoNumbersOptimized(ListNode l1, ListNode l2) {
		ListNode l3 = new ListNode(0); // dummy head Node
		ListNode temp =l3;
		int sum = 0;
		int carry = 0;
		while(l1!=null || l2!=null) {
			sum = carry;
			if(l1!=null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if(l2!=null) {
				sum += l2.val;
				l2 = l2.next;
			}
			carry = (sum > 9) ? 1 : 0;
			temp.next = new ListNode(sum%10);
			temp = temp.next;
		}
		if(carry>0) {
			temp.next = new ListNode(carry);
		}
		return l3.next;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l3 = new ListNode();
		ListNode temp = l3;
		int carry = 0;
		while(l1!=null && l2!=null) {
			int sum = l1.val + l2.val +carry;
			carry = sum>9 ? 1 : 0;
			temp.next = new ListNode(sum%10);
			l1 = l1.next;
			l2 = l2.next;
			temp = temp.next;
		}
		if(l1!=null) {
			while(l1!=null) {
				int sum = l1.val +carry;
				carry = sum>9 ? 1 : 0;
				temp.next = new ListNode(sum%10);
				l1 = l1.next;
				temp = temp.next;
			}
		}
		if(l2!=null) {
			while(l2!=null) {
				int sum = l2.val +carry;
				carry = sum>9 ? 1 : 0;
				temp.next = new ListNode(sum%10);
				l2 = l2.next;
				temp = temp.next;
			}
		}
		if(carry>0) {
			temp.next = new ListNode(carry);
		}
		return l3.next;
	}
	
	public static void main(String[] args) {
		AddTwoNumbers addNumbers = new AddTwoNumbers();
		int[] inputArr1 = {9,9,9,9};
		int[] inputArr2 = {9,9,9,9,9,9,9};
		ListNode l1 = addNumbers.getLinkedListInput(inputArr1);
		ListNode l2 = addNumbers.getLinkedListInput(inputArr2);
		addNumbers.printLinkedList(addNumbers.addTwoNumbers(l1, l2));
		System.out.println();
		addNumbers.printLinkedList(addNumbers.addTwoNumbersOptimized(l1, l2));
		System.out.println();
	}
	
	public ListNode getLinkedListInput(int[] inputArr) {
		ListNode l1 = new ListNode(inputArr[0]);
		ListNode temp = l1;
		for(int i=1;i<inputArr.length;i++) {
			temp.next = new ListNode(inputArr[i]);
			temp = temp.next;
		}
		return l1;
	}
	
	public void printLinkedList(ListNode listNode) {
		while(listNode!=null) {
			System.out.print(listNode.val);
			listNode = listNode.next;
			if(listNode!=null) {
				System.out.print("->");
			}
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
	 

}
