package dp.cci_book;

import java.util.Arrays;
import java.util.List;

public class StackOfBoxes {
	
	public int findMaxmimumHeight(List<Box> boxList) {
		boxList.sort((a,b)->b.height-a.height); // sort desc by height
		return findMaxmimumHeight(boxList, null, 0);
	}

	private int findMaxmimumHeight(List<Box> boxList, Box bottom, int offset) {
//		if(bottom!=null) {
//			System.out.println("offset:"+offset+" box height:"+bottom.height);
//		}
		System.out.println("1");
		if(offset>=boxList.size()) {
			return 0;
		}
		Box currBox = boxList.get(offset);
		int heightWithBottomIncluded = 0;
		if(bottom == null || currBox.canPlaceAbove(bottom)) {
			heightWithBottomIncluded = currBox.height + findMaxmimumHeight(boxList, currBox, offset+1);
		}
		int heightWithoutBottomIncluded = findMaxmimumHeight(boxList, bottom, offset+1);
		return Math.max(heightWithBottomIncluded, heightWithoutBottomIncluded);
	}
	
	public int findMaxHeightWithMemoization(List<Box> boxList) {
		boxList.sort((a,b)->b.height-a.height); // sort desc by height
		int[] mem = new int[boxList.size()];
		return findMaxHeightWithMemoization(boxList, null, 0, mem);
	}
	
	private int findMaxHeightWithMemoization(List<Box> boxList, Box bottom, int offset, int[] mem) {
		System.out.println("2");
		if(offset>=boxList.size()) {
			return 0;
		}
		Box currBox = boxList.get(offset);
		int heightWithBottomIncluded = 0;
		if(bottom == null || currBox.canPlaceAbove(bottom)) {
			if(mem[offset]==0) {
				mem[offset] = currBox.height + findMaxHeightWithMemoization(boxList, currBox, offset+1, mem);
			}
			heightWithBottomIncluded = mem[offset];
		}
		int heightWithoutBottomIncluded = findMaxHeightWithMemoization(boxList, bottom, offset+1, mem);
		return Math.max(heightWithBottomIncluded, heightWithoutBottomIncluded);
	}

	public static void main(String[] args) {
		StackOfBoxes sb = new StackOfBoxes();
		Box b1 = new Box(2,10,10);
		Box b2 = new Box(5,6,6);
		Box b3 = new Box(1,2,5);
		Box b4 = new Box(8,4,4);
		int noOfWays = sb.findMaxmimumHeight(Arrays.asList(b1,b2,b4));
		System.out.println("Max height when stacked : "+noOfWays);
		int noOfWaysMem = sb.findMaxHeightWithMemoization(Arrays.asList(b1,b2,b4));
		System.out.println("Max height when stacked with memoization: "+noOfWaysMem);
	}

}
class Box {
	int height;
	int width;
	int depth;
	
	Box(int h, int w, int d){
		height = h;
		width = w;
		depth = d;
	}
	
	boolean canPlaceAbove(Box b) {
		return this.height<b.height;
	}
}