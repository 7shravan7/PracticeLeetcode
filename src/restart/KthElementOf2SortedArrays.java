package restart;

public class KthElementOf2SortedArrays {

    public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
        if(n>m){
            return kthElement(arr2,arr1,m,n,k);
        }
        if(k==1){
            return Math.min(arr1[0],arr2[0]);
        }
        if(k==n+m){
            return Math.max(arr1[n-1], arr2[m-1]);
        }
        int low = Math.max(0, k-m);
        int high = Math.min(k,n);
        while(low<=high){
            int mid = low + (high-low)/2;
            int rem = k-mid;
            int leftMax1 = mid==0 ? Integer.MIN_VALUE : arr1[mid-1];
            int leftMax2 = rem==0 ? Integer.MIN_VALUE : arr2[rem-1];
            int rightMin1 = mid == n ? Integer.MAX_VALUE : arr1[mid];
            int rightMin2 = rem==m ? Integer.MAX_VALUE: arr2[rem];
            if(leftMax1<=rightMin2 && leftMax2<=rightMin1){
                return Math.max(leftMax1, leftMax2);
            } else if (leftMax1>rightMin2){
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        KthElementOf2SortedArrays kthElementOf2SortedArrays = new KthElementOf2SortedArrays();
        int n=5;
        int m=4;
        int k=8;
        int[] arr1 = {2,3,6,7,9};
        int[] arr2 = {1,4,8,10};
        System.out.println(kthElementOf2SortedArrays.kthElement(arr1,arr2,n,m,k));
    }
}
