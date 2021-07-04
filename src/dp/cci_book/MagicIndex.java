package dp.cci_book;

public class MagicIndex {
	
	// Brute force method
	public int getMagicIndexBruteForce(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==i) {
				return i;
			}
		}
		return 0;
	}
	
	//Binary Search way since it is sorted distinct arr
	public int getMagicIndexBS(int[] arr) {
		return getMagicIndexBS(arr, 0, arr.length-1);
	}

	private int getMagicIndexBS(int[] arr, int start, int end) {
		if(start>end) {
			return 0;
		}
		int mid = start + (end -start)/2;
		if(arr[mid]==mid) {
			return mid;
		}
		if(arr[mid]>mid) {
			return getMagicIndexBS(arr, start, mid-1);
		} else {
			return getMagicIndexBS(arr, mid+1, end);
		}
	}
	
	//what if elements are non unique sorted array
	public int getMagicIndexNonUnique(int[] arr) {
		return getMagicIndexNonUnique(arr, 0, arr.length-1);
	}

	private int getMagicIndexNonUnique(int[] arr, int start, int end) {
		if(start>end) {
			return 0;
		}
		int mid = start + (end-start)/2;
		if(arr[mid] == mid) {
			return mid;
		}
		int leftMinValue = Math.min(arr[mid], mid-1);
		int left = getMagicIndexNonUnique(arr, start, leftMinValue);
		if(left>0) {
			return left;
		}
		int rightMaxValue = Math.max(arr[mid], mid+1);
		int right = getMagicIndexNonUnique(arr, rightMaxValue, end);
		if(right>0) {
			return right;
		}
		return 0;
	}

	public static void main(String[] args) {
		MagicIndex magicIndex = new MagicIndex();
		int[] arr = {-40,-20,-1,1,2,3,5,7,9,12,13};
		// index    {  0,  1, 2,3,4,5,6,7,8, 9,10}
		int[] arr1 = {-10,-5,2,2,2,3,4,7,9,12,13};
		// index    {  0,  1,2,3,4,5,6,7,8, 9,10}
		int mIndex = magicIndex.getMagicIndexBruteForce(arr);
		System.out.println("Magic Index Brute Force : "+mIndex);
		int mIndex1 = magicIndex.getMagicIndexBS(arr);
		System.out.println("Magic Index Binary Search : "+mIndex1);
		// non unique sorted array
		int mIndex2 = magicIndex.getMagicIndexBS(arr1);
		System.out.println("Magic Index Binary Search : "+mIndex2);
		int mIndex3 = magicIndex.getMagicIndexNonUnique(arr1);
		System.out.println("Magic Index Non Unique : "+mIndex3);
		// check for normal one too
		int mIndex4 = magicIndex.getMagicIndexNonUnique(arr);
		System.out.println("Magic Index Non Unique : "+mIndex4);
	}

}
