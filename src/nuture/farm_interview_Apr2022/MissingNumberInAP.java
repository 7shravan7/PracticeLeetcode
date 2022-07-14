package nuture.farm_interview_Apr2022;

/*
   Given an array that represents elements of arithmetic progression in order.
   One element other than first and last is missing in the progression,
   find the missing number.

    Input: arr[]  = {2, 4, 8, 10, 12, 14}
    Output: 6

    Input: arr[]  = {1, 6, 11, 16, 21, 31};
    Output: 26
 */
public class MissingNumberInAP {

    public int getMissingNumber(int[] arr) {
        int size = arr.length;
        int firstElement = arr[0];
        int lastElement = arr[size-1];
        int d = (lastElement - firstElement)/(size);
        int low = 1; // first element is already correct
        int high = size-1;
        while(low<high){
            int mid = low + (high-low)/2;
            int index = (arr[mid]-firstElement)/d;
            if(index==mid) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return arr[high]-d;
    }

    public static void main(String[] args) {
        MissingNumberInAP missingNumber = new MissingNumberInAP();
        int[] arr  = {2, 4, 8, 10, 12, 14};
        System.out.println(missingNumber.getMissingNumber(arr));
        int[] arr2  = {1, 6, 11, 16, 21, 31};
        System.out.println(missingNumber.getMissingNumber(arr2));
    }
}
