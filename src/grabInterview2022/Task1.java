package grabInterview2022;

import java.util.HashSet;
import java.util.Set;

public class Task1 {

    public int solution(int[] a) {
        Set<Integer> missing = new HashSet<>();
        Set<Integer> store = new HashSet<>();
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (!store.contains(i + 1) && i + 1 != a[i])
                missing.add(i + 1);
            if (i + 1 < a[i])
                store.add(a[i]);
            else
                missing.remove(a[i]);
            if (missing.isEmpty())
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        int[] arr1 = {2,1,3,5,4};
        System.out.println("Number of moments when bulbs shine when turned on for arr1 "+task1.solution(arr1));
        int[] arr2 = {3,1,2};
        System.out.println("Number of moments when bulbs shine when turned on for arr2 "+task1.solution(arr2));
        int[] arr3 = {1,3,7,2,6,4,5};
        System.out.println("Number of moments when bulbs shine when turned on for arr3 "+task1.solution(arr3));
    }
}
