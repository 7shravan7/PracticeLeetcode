package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Medium**    78. Subsets
 * 
 * Given an integer array nums of unique elements, return all possible subsets (the power set).

   The solution set must not contain duplicate subsets. Return the solution in any order.

	Example 1:
		Input: nums = [1,2,3]
		Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

	Example 2:
		Input: nums = [0]
		Output: [[],[0]]

	Constraints:
		1 <= nums.length <= 10
		-10 <= nums[i] <= 10
		All the numbers of nums are unique.
 */
public class Subsets {

	// Both solutions are O(n*2^n) Time complexity
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> subsetList = new ArrayList<>();
		int totalCombinations = 1<<nums.length;
		subsetList.add(new ArrayList<>());
		for(int i=1;i<totalCombinations;i++) {
			subsetList.add(getSubset(nums, i));
		}
		return subsetList;
	}
	
	private List<Integer> getSubset(int[] nums, int val){
		List<Integer> subList = new ArrayList<>();
		int index=0;
		for(int i=val;i>0; i=i>>1) {
			if((i&1) == 1) {
				subList.add(nums[index]);
			}
			index++;
		}
		return subList;
	}

	public List<List<Integer>> subsetsBackTracking(int[] nums) {
		List<List<Integer>> subsetsList = new ArrayList<>();
		//subsetsList.add(new ArrayList<>());
		backTrack(nums, 0, nums.length, new ArrayList<>(), subsetsList);
		return subsetsList;
	}

	private void backTrack(int[] nums, int i, int len, List<Integer> list,
						   List<List<Integer>> subsetsList) {
		if(i>=len){
			subsetsList.add(new ArrayList<>(list));
			return;
		}
		list.add(nums[i]);
		backTrack(nums, i+1, len, list, subsetsList);
		list.remove(list.size()-1);
		backTrack(nums, i+1,len, list, subsetsList);

	}
	
	public static void printList(List<List<Integer>> resultList) {
		for(List<Integer> list : resultList) {
			System.out.print("{");
			for(int i=0;i<list.size();i++) {
				System.out.print(list.get(i));
				if(i!=list.size()-1) {
					System.out.print(",");
				}
			}
			System.out.println("}");
		}
		System.out.println("No.of combinations "+ resultList.size());
	}

	public static void main(String[] args) {
		Subsets subsets = new Subsets();
		int[] nums1 = {1,2,3, 4};
		printList(subsets.subsets(nums1));
		System.out.println("-----back track--------");
		printList(subsets.subsetsBackTracking(nums1));
		System.out.println("-------example 2------");
		int[] nums2 = {0};
		printList(subsets.subsets(nums2));
		System.out.println("-----back track--------");
		printList(subsets.subsetsBackTracking(nums2));
	}

}
