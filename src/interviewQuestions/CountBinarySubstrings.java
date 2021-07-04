package interviewQuestions;

/* **Easy**     696. Count Binary Substrings
 * 
 * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and 
 * all the 0's and all the 1's in these substrings are grouped consecutively.

   Substrings that occur multiple times are counted the number of times they occur.

	Example 1:
		Input: s = "00110011"
		Output: 6
		Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", 
						"10", "0011", and "01".
					 Notice that some of these substrings repeat and are counted the number of times they occur.
					 Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
					 
	Example 2:
		Input: s = "10101"
		Output: 4
		Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.

	Constraints:
		1 <= s.length <= 105
		s[i] is either '0' or '1'.
 */
public class CountBinarySubstrings {
	
	/*
	 * idea is to group by chars and then find min among consecutive elements and sum it up
	 * Time complexity : O(n)
	 * Space Complexity: O(n)
	 */
	public int countBinarySubstrings(String s) {
		int[] groupArr = new int[s.length()];
		groupArr[0] = 1;
		int count=0;
		int index=0;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i) == s.charAt(i-1)) {
				groupArr[index]++;
			} else {
				index++;
				groupArr[index] = 1;
			}
		}
		for(int i=1;i<=index;i++) {
			count += Math.min(groupArr[i], groupArr[i-1]);
		}
		return count;
	}
	
	/*
	 * idea is same as previous but instead of storing in a separate array using prev and curr variable
	 * Time complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public int countBinarySubstrings1(String s) {
		int prev = 0;
		int curr = 1;
		int count = 0;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i) == s.charAt(i-1)) {
				curr++;
			} else {
				count += Math.min(prev, curr);
				prev = curr;
				curr = 1;
			}
		}
		count += Math.min(prev, curr);
		return count;
	}

	public static void main(String[] args) {
		CountBinarySubstrings countBinary = new CountBinarySubstrings();
		String s1 = "00110011";
		System.out.println("Count Binary Substrings of "+s1+" : "+countBinary.countBinarySubstrings1(s1));
		String s2 = "10101";
		System.out.println("Count Binary Substrings of "+s2+" : "+countBinary.countBinarySubstrings1(s2));
		String s3 = "00011100";
		System.out.println("Count Binary Substrings of "+s3+" : "+countBinary.countBinarySubstrings1(s3));
	}

}
