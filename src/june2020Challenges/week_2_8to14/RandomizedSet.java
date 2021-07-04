package june2020Challenges.week_2_8to14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
	    
	    Map<Integer, Integer> indexMap;
	    
	    List<Integer> elementsList;

	    /** Initialize your data structure here. */
	    public RandomizedSet() {
	        indexMap = new HashMap<>();
	        elementsList = new ArrayList<>();
	    }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	    public boolean insert(int val) {
	        if(!indexMap.containsKey(val)){
	            int newIndex = elementsList.size();
	            elementsList.add(val);
	            indexMap.put(val, newIndex);
	            return true;
	        }
	        return false;
	    }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	    public boolean remove(int val) {
	        if(indexMap.containsKey(val)){
	        	int index= indexMap.get(val);
	            int lastIndex = elementsList.size() -1;
	            int lastElement = elementsList.get(lastIndex);
	            elementsList.set(index, lastElement);
	            elementsList.remove(lastIndex);
	            indexMap.put(lastElement, index);
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
	}

	/**
	 * Your RandomizedSet object will be instantiated and called as such:
	 * RandomizedSet obj = new RandomizedSet();
	 * boolean param_1 = obj.insert(val);
	 * boolean param_2 = obj.remove(val);
	 * int param_3 = obj.getRandom();
	 */
