package interviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* **Medium**   981. Time Based Key-Value Store
 * Create a timebased key-value store class TimeMap, that supports two operations.

	1. set(string key, string value, int timestamp)
		Stores the key and value, along with the given timestamp.
		
	2. get(string key, int timestamp)
		Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
		If there are multiple such values, it returns the one with the largest timestamp_prev.
		If there are no values, it returns the empty string ("").
 

	Example 1:
		Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],
						["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
		Output: [null,null,"bar","bar",null,"bar2","bar2"]
		Explanation:   
			TimeMap kv;   
			kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
			kv.get("foo", 1);  // output "bar"   
			kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
			kv.set("foo", "bar2", 4);   
			kv.get("foo", 4); // output "bar2"   
			kv.get("foo", 5); //output "bar2" 
			
	Example 2:
		Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],
						["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
		Output: [null,null,null,"","high","high","low","low"]

	Note:
		All key/value strings are lowercase.
		All key/value strings have length in the range [1, 100]
		The timestamps for all TimeMap.set operations are strictly increasing.
		1 <= timestamp <= 10^7
		TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeBasedKeyValueStore {
	
	class Value {
		String strValue;
		int timeStamp;
		Value(String strValue, int timeStamp){
			this.strValue = strValue;
			this.timeStamp = timeStamp;
		}
	}
	
	Map<String, List<Value>> keyValueMap; // since set operations are strictly increasing given as constraint we dont have to sort
	
	
	// public TimeMap(){
    public TimeBasedKeyValueStore() {
    	keyValueMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
    	List<Value> valueList = keyValueMap.get(key);
    	if(valueList == null) {
    		valueList = new ArrayList<>();
    		valueList.add(new Value(value, timestamp));
    		keyValueMap.put(key, valueList);
    	} else {
    		Value mapValue = valueList.get(valueList.size()-1);
    		if(mapValue.timeStamp == timestamp) {
    			mapValue.strValue = value;
    		} else {
    			valueList.add(new Value(value, timestamp));
    		}
    	}
    }
    
    public String get(String key, int timestamp) {
    	if(keyValueMap.containsKey(key)) {
    		List<Value> valueList = keyValueMap.get(key);
    		return binarySearch(valueList, timestamp);
    	}
        return "";
    }
    
    private String binarySearch(List<Value> valueList, int timestamp) {
    	int low = 0;
    	int high = valueList.size()-1;
    	while(low<high) {
    		int mid = low + (high-low)/2;
    		if(valueList.get(mid).timeStamp == timestamp) {
    			return valueList.get(mid).strValue;
    		} else if (valueList.get(mid).timeStamp < timestamp) {
    			if(valueList.get(mid+1).timeStamp>timestamp) {
    				return valueList.get(mid).strValue;
    			}
    			low = mid+1;
    		} else {
    			high = mid-1;
    		}
    	}
    	return valueList.get(low).timeStamp<=timestamp ? valueList.get(low).strValue : "";
    }

	public static void main(String[] args) {
		TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
		store.set("foo", "bar", 1);
		System.out.println(store.get("foo", 1));
		System.out.println(store.get("foo", 3));
		store.set("foo", "bar2", 4);
		System.out.println(store.get("foo", 4));
		System.out.println(store.get("foo", 5));
	}

}
