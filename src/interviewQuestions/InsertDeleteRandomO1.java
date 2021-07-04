package interviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* **Medium**   380. Insert Delete GetRandom O(1)
 * Implement the RandomizedSet class:

	RandomizedSet() Initializes the RandomizedSet object.
	bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, 
						 false otherwise.
	bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false 
						 otherwise.
	int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one 
					 element exists when this method is called). Each element must have the same probability of being 
					 returned.
	Example 1:
		Input
			["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
			[[], [1], [2], [2], [], [1], [2], []]
		Output
			[null, true, false, true, 2, true, false, 2]
		Explanation
			RandomizedSet randomizedSet = new RandomizedSet();
			randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
			randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
			randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
			randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
			randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
			randomizedSet.insert(2); // 2 was already in the set, so return false.
			randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
	Constraints:
		-231 <= val <= 231 - 1
		At most 105 calls will be made to insert, remove, and getRandom.
		There will be at least one element in the data structure when getRandom is called.

	Follow up: Could you implement the functions of the class with each function works in average O(1) time?
 */
public class InsertDeleteRandomO1 {
	
	List<Integer> elementsList;
	
	Map<Integer, Integer> map;
	
	 /** Initialize your data structure here. */
    public InsertDeleteRandomO1() {
    	elementsList  = new ArrayList<>();
    	map = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) {
        	return false;
        }
        elementsList.add(val);
        int listIndex = elementsList.size()-1;
        map.put(val, listIndex);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)) {
        	int listIndex = map.get(val);
        	// swap last element with this remvoing element
        	int lastIndex = elementsList.size()-1;
        	int lastElement = elementsList.get(lastIndex);
        	elementsList.set(listIndex, lastElement);
        	//elementsList.set(lastIndex, val);
        	elementsList.remove(lastIndex);
        	map.put(lastElement, listIndex);
        	map.remove(val);
        	return true;
        } 
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(elementsList.size());
        return elementsList.get(randomIndex);
    }

	public static void main(String[] args) {
		InsertDeleteRandomO1 randomizedSet = new InsertDeleteRandomO1();
		System.out.println(randomizedSet.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
		System.out.println(randomizedSet.remove(2)); // Returns false as 2 does not exist in the set.
		System.out.println(randomizedSet.insert(2)); // Inserts 2 to the set, returns true. Set now contains [1,2].
		System.out.println(randomizedSet.getRandom()); // getRandom() should return either 1 or 2 randomly.
		System.out.println(randomizedSet.remove(1)); // Removes 1 from the set, returns true. Set now contains [2].
		System.out.println(randomizedSet.insert(2)); // 2 was already in the set, so return false.
		System.out.println(randomizedSet.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2.
	}

}
