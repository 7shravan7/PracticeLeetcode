package restart;

/*
 Given an array of N integers, count the inversion of the array (using merge-sort).

 What is an inversion of an array? Definition: for all i & j < size of array,
 if i < j then you have to find pair (A[i],A[j]) such that A[j] < A[i].
 */
public class CountInversions {

    public static long getInversions(long arr[], int n) {
        long[] temp =new long[n];
        return mergeSort(arr, temp, 0, n-1);
    }

    private static long mergeSort(long arr[], long[] temp ,int start, int end) {
        if(start>=end){
            return 0;
        }
        int mid = start+((end-start)/2);
        long leftVal = mergeSort(arr, temp, start, mid);
        long rightVal = mergeSort(arr, temp, mid+1 ,end);
        return leftVal + rightVal + merge(arr, temp, start, mid+1, end);
    }

    private static long merge(long arr[], long[] temp, int start,int mid, int end){
        long inversionCount=0;
        int i=start;
        int j=mid;
        int k=start;
        while(i<=mid-1 && j<=end){
            if(arr[i]<=arr[j]){
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversionCount += mid-i;
            }
        }
        while(i<=mid-1){
            temp[k++] = arr[i++];
        }
        while(j<=end){
            temp[k++] = arr[j++];
        }
        for(int ii=start;ii<=end;ii++){
            arr[ii] = temp[ii];
        }
        return inversionCount;
    }

    public static void main(String[] args) {
        long[] arr= {5,4,3,2,1};
        System.out.println(getInversions(arr, 5));
    }
}
