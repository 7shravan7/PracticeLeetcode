package interviewQuestions;


public class HeapImplementation {
	
	HeapNode[] heapArr;
	
	int currIndex;
	
	boolean isMinHeap;
	
	boolean isSortByFreq;
	
	public HeapImplementation(int size, boolean isMinHeap, boolean isSortByFreq) {
		heapArr = new HeapNode[size];
		this.isMinHeap = isMinHeap;
		currIndex = 0;
		this.isSortByFreq = isSortByFreq;
	}
	
	public int getLeftChildIndex(int parentIndex) {
		return 2*parentIndex+1;
	}
	
	public int getRightChildIndex(int parentIndex) {
		return 2*parentIndex+2;
	}
	
	public int getParentIndex(int childIndex) {
		return (childIndex-1)/2;
	}
	
	private void swap(int index1, int index2) {
		HeapNode temp = heapArr[index1];
		heapArr[index1] = heapArr[index2];
		heapArr[index2] = temp;
	}
	
	private int getComparingHeapNodeValue(int index) {
		HeapNode heapNode = heapArr[index];
		return isSortByFreq? heapNode.frequency : heapNode.value;
	}
	
	private boolean comparator(int srcIndex, int compToIndex) {
		int srcNodeVal = getComparingHeapNodeValue(srcIndex);
		int compToNodeVal = getComparingHeapNodeValue(compToIndex);
		return isMinHeap ? srcNodeVal>compToNodeVal : compToNodeVal>srcNodeVal;
	}
	
	public void insert(int value, int frequency) {
		if(currIndex>=heapArr.length) {
			return;
		}
		HeapNode newHeapNode = new HeapNode(value, frequency);
		heapArr[currIndex] = newHeapNode;
		int tempIndex = currIndex;
		while(tempIndex!=0 && !comparator(tempIndex, getParentIndex(tempIndex))) {
			int parentIndex = getParentIndex(tempIndex);
			swap(tempIndex, parentIndex);
			tempIndex = parentIndex;
		}
		currIndex++;
	}
	
	public int remove() {
		if(currIndex==0) {
			return -1;
		}
		int value = heapArr[0].value;
		swap(currIndex-1, 0);
		currIndex--;
		heapify(0);
		return value;
	}
	
	private void heapify(int parentIndex) {
		int tempIndex = parentIndex;
		int lastNodeParentIndex = getParentIndex(currIndex);
		while(tempIndex<lastNodeParentIndex) {
			int leftChildIndex = getLeftChildIndex(tempIndex);
			int rightChildIndex = getRightChildIndex(tempIndex);
			int smallestIndex = tempIndex;
			if(comparator(tempIndex, leftChildIndex)) {
				smallestIndex = leftChildIndex;
			}
			if (rightChildIndex<=currIndex && comparator(smallestIndex, rightChildIndex)) {
				smallestIndex = rightChildIndex;
			}
			if(smallestIndex == tempIndex) {
				break;
			} else {
				swap(tempIndex, smallestIndex);
				tempIndex = smallestIndex;
			}
		}
	}
	
	public void printHeap() {
		System.out.println();
		for(int i=0;i<currIndex;i++) {
			System.out.print(heapArr[i].value+":"+heapArr[i].frequency);
			if(i!=currIndex-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}
	

	public static void main(String[] args) {
		HeapImplementation heap = new HeapImplementation(10, true, true); //(size, isMinheap, isSortByFre)
		int[] inputValArr = {5,3,8,1,6,4};
		int[] inputFreqArr = {1,6,2,4,5,3};
		for(int i=0;i<inputValArr.length;i++) {
			heap.insert(inputValArr[i], inputFreqArr[i]);
		}
		heap.printHeap();
		heap.remove();
		heap.printHeap();
		heap.remove();
		heap.printHeap();
		heap.remove();
		heap.printHeap();
	}

}

class HeapNode {
	int value;
	int frequency;
	public HeapNode(int value, int frequency) {
		this.value = value;
		this.frequency = frequency;
	}
}