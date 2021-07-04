package dp.cci_book;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
	
	/*
	 * clone the previous set and add curr item to it to get curr subset
	 * P(0) = {}
	 * P(1) = {},// {1} add curr item 1 to each prev sub list
	 * P(2) = {},{1},// {2},{1,2} add curr item 2 to each prev sub list
	 * P(3) = {},{1},{2},{1,2}, // {3},{1,3},{2,3},{1,2,3} // add curr item 3 to each prev sub list
	 */
	public List<List<Integer>> getPowerSet(int[] arr, int size){
		List<List<Integer>> currSubSetList = new ArrayList<>();
		if(size==0) {
			currSubSetList.add(new ArrayList<>());
		} else {
			List<List<Integer>> prevSubSetList = getPowerSet(arr, size-1);
			currSubSetList.addAll(prevSubSetList);
			for(List<Integer> prevSubSet : prevSubSetList) {
				List<Integer> oldSub = new ArrayList<>();
				oldSub.addAll(prevSubSet);
				oldSub.add(arr[size-1]);
				currSubSetList.add(oldSub);
			}
		}
		return currSubSetList;
	}
	
	/*
	 * using binary representation
	 * if n=3
	 * 000 - {}
	 * 001 - {1}
	 * 010 - {2}
	 * 011 - {1,2}
	 * 100 - {3}
	 * 101 - {1,3}
	 * 110 - {2,3}
	 * 111 - {1,2,3}
	 */
	public List<List<Integer>> getPowerSetByCombinatorics(int[] arr){
		List<List<Integer>> powerSetList = new ArrayList<>();
		int length = arr.length;
		int noOfCombinations = 1 << length; //2^n combinations
		powerSetList.add(new ArrayList<>());
		for(int i=1;i<noOfCombinations;i++) {
			powerSetList.add(getSubSetList(arr, i));
		}
		
		return powerSetList;
	}
	
	private List<Integer> getSubSetList(int[] arr, int x) {
		List<Integer> subSetList = new ArrayList<>();
		int index=0;
		for(int i=x;i>0;i=i>>1) { // here right shifting to get next bit position
			//System.out.println("i:"+i+","+i+"&1="+(i&1));
			if((i&1) == 1) { // check if that bit is set or not
				subSetList.add(arr[index]);
			}
			index++;
		}
		return subSetList;
	}
	
	public void printList(List<List<Integer>> powerSetList) {
		for(List<Integer> list : powerSetList) {
			System.out.print("{");
			for(int i=0;i<list.size();i++) {
				System.out.print(list.get(i));
				if(i!=list.size()-1) {
					System.out.print(",");
				}
			}
			System.out.println("}");
		}
		System.out.println("No.of combinations "+ powerSetList.size());
	}
	
	public static void main(String[] args) {
		PowerSet ps = new PowerSet();
		int[] arr = {1,2,3,4,5};
		List<List<Integer>> powerSetList = ps.getPowerSet(arr, arr.length);
		System.out.println("Using clone and add approach");
		ps.printList(powerSetList);
		List<List<Integer>> powerSetList1 = ps.getPowerSetByCombinatorics(arr);
		System.out.println("Using Combinatorics");
		ps.printList(powerSetList1);
	}

}
