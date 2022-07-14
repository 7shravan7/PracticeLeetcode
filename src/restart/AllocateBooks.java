package restart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given an array of integers A of size N and an integer B.

College library has N bags,the ith book has A[i] number of pages.

You have to allocate books to B number of students so that maximum number of pages alloted to a student is
minimum.

A book will be allocated to exactly one student.
Each student has to be allocated at least one book.
Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3,
skipping book 2.
Calculate and return that minimum possible number.

NOTE: Return -1 if a valid assignment is not possible.




Input Format

The first argument given is the integer array A.
The second argument given is the integer B.
Output Format

Return that minimum possible number
Constraints

1 <= N <= 10^5
1 <= A[i] <= 10^5
For Example

Input 1:
    A = [12, 34, 67, 90]
    B = 2
Output 1:
    113
Explanation 1:
    There are 2 number of students. Books can be distributed in following fashion :
        1) [12] and [34, 67, 90]
        Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
        2) [12, 34] and [67, 90]
        Max number of pages is allocated to student 2 with 67 + 90 = 157 pages
        3) [12, 34, 67] and [90]
        Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages

        Of the 3 cases, Option 3 has the minimum pages = 113.

Input 2:
    A = [5, 17, 100, 11]
    B = 4
Output 2:
    100
 */
public class AllocateBooks {

    public int books(ArrayList<Integer> A, int B) {
        int size = A.size();
        if(size<B){
            return -1;
        }
        if(size == B) {
            int max = Integer.MIN_VALUE;
            for(Integer num: A){
                max = Math.max(max, num);
            }
            return max;
        }
        int minElement = Integer.MAX_VALUE;
        int totalSum = 0;
        for(Integer num: A) {
            totalSum += num;
            minElement = Math.min(minElement, num);
        }
        int low = minElement;
        int high = totalSum;
        //System.out.println(low+","+high);
        int res=Integer.MAX_VALUE;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isAllocationPossible(mid, A, B)){
                res = Math.min(res, mid);
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return res;
    }

    private boolean isAllocationPossible(int barrier, ArrayList<Integer> pagesList,int studentsCount) {
        int pagesSum = 0;
        int student = 1;
        for(Integer pages:pagesList){
            if(pages>barrier){
                return false;
            }
            if(pagesSum+pages>barrier){
                student += 1;
                if(student >studentsCount){
                    return false;
                }
                pagesSum = pages;
            } else {
                pagesSum += pages;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AllocateBooks ab = new AllocateBooks();
        List<Integer> arr= Arrays.asList(73, 58, 30, 72, 44, 78, 23, 9);
        ArrayList<Integer> A = new ArrayList<>(arr);
        int B = 5;
        System.out.println(ab.books(A,B));
    }

}
