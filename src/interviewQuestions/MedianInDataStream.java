package interviewQuestions;

import java.util.PriorityQueue;

/* **Hard**    295. Find Median from Data Stream
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value
 *  and the median is the mean of the two middle values.
 *  
	For example, for arr = [2,3,4], the median is 3.
	For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
	
	Implement the MedianFinder class:
	MedianFinder() initializes the MedianFinder object.
	void addNum(int num) adds the integer num from the data stream to the data structure.
	double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be 
	accepted.
 
	Example 1:
	Input
		["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
		[[], [1], [2], [], [3], []]
	Output
		[null, null, null, 1.5, null, 2.0]
	Explanation
		MedianFinder medianFinder = new MedianFinder();
		medianFinder.addNum(1);    // arr = [1]
		medianFinder.addNum(2);    // arr = [1, 2]
		medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
		medianFinder.addNum(3);    // arr[1, 2, 3]
		medianFinder.findMedian(); // return 2.0
 
	Constraints:
		-105 <= num <= 105
		There will be at least one element in the data structure before calling findMedian.
		At most 5 * 104 calls will be made to addNum and findMedian.

	Follow up:
		If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
		If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?

 */
public class MedianInDataStream {
	
	PriorityQueue<Integer> maxHeap;
	
	PriorityQueue<Integer> minHeap;
	
    public MedianInDataStream() {
    	maxHeap = new PriorityQueue<>((a,b)->b-a);
    	minHeap = new PriorityQueue<>((a,b)->a-b);
    }
    
    public void addNum(int num) {
    	maxHeap.add(num);
    	minHeap.add(maxHeap.poll());
    	if(maxHeap.size()<minHeap.size()) {
    		maxHeap.add(minHeap.poll());
    	}
    }
    
    // using a/2.0 +b/2.0 rather than (a+b)/2.0 to avoid overflow
    public double findMedian() {
        return maxHeap.size() == minHeap.size() ? (maxHeap.peek())/2.0+(minHeap.peek())/2.0 : maxHeap.peek();
    }

	public static void main(String[] args) {
		MedianInDataStream medianInDataStream = new MedianInDataStream();
		int[] inputArr = {41, 35, 41, 5, 97, 9};
		for(int i=0;i<inputArr.length;i++) {
			medianInDataStream.addNum(inputArr[i]);
			System.out.println(medianInDataStream.findMedian());
		}
		System.out.println("------");
		int[] inputArr1 = {41, 35, 62, 5, 97, 9};
		for(int i=0;i<inputArr1.length;i++) {
			medianInDataStream.addNum1(inputArr[i]);
			System.out.println(medianInDataStream.findMedian1());
		}

	}

	int A[] = new int[101], n = 0;

	// O(1)
	public void addNum1(int num) {
		A[num]++;
		n++;
	}

	// O(100) = O(1)
	public double findMedian1() {

		// find 1st median number
		int count = 0, i = 0;
		while (count < (n+1)/2) count += A[i++];

		// find 2nd median number
		int j = i;
		while (count < n/2+1) count += A[j++];

		return (n%2 == 1) ? i-1 : (i-1+j-1) / 2.0;
	}

}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
