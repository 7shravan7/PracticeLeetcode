package Paypal_OA_Apr22;

/*
You are given two integers  and . Your task is to determine the sum of all the beautiful numbers from the range .

A number represents a beautiful number if it satisfies the following condition:

If the number becomes 1 at some point by replacing it repeatedly with the sum of squares of its digits.
Note: If the number never becomes 1, then the provided number is not a beautiful number.
For further clarification, please refer to the explanation of the sample test case.

Example

Consider L=1, R = 2. You have to determine the sum of all the beautiful numbers from the range .

Here the answer is 1 as 2 is not a beautiful number.

Function description:

Complete the solve function provided in the editor. This function takes the following 2 parameters and returns the sum of all the beautiful numbers from the range .

L: Represents an integer denoting L
R: Represents an integer denoting R
Input format

Note: This is the input format that you must use to provide custom input (available above the Compile and Test button).

The first line contains an integer  denoting the number of test cases.
The next  lines contain two integers  and .
Output format

For each test case, print a value that represents the sum of the beautiful numbers in the range .
Note: You are required to print the answers for each test case in a new line.

Constraints
1<=T<10^6
1<=L<=R<=10^6
Sample Input
2
31 32
7 7
Sample Output
63
7
Time Limit: 1
Memory Limit: 256
Source Limit:
Explanation
For the first test case, both 31 and 32 can be reduced to 1, therefore the output is 63. For example:

Replace 31 by 32 + 12  = 10
Replace 10 by 12 +  02 = 1
 */
public class BeautifulNumbers {

    static long solve(int l, int r){
        long sum = 0;
        for(int i=l;i<=r;i++){
            if(isBeautiful(i)){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }

    // 13
    private static boolean isBeautiful(int n) {
        if(n==1){
            return true;
        }
        int sum = 0;
        while(n>1){
            int lastDigit = n%10;
            sum += lastDigit * lastDigit;
            n = n/10;
        }
        return sum==1;
    }

    public static void main(String[] args) {
        solve(1, 20);
    }

}
