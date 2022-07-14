package restart;

import java.util.Arrays;

public class JobSequencingProblem {

    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job arr[], int n) {
        Arrays.sort(arr, (a, b)->b.profit-a.profit);
        int maxDeadline = -1;
        for(int i=0;i<n;i++){
            if(maxDeadline<arr[i].deadline){
                maxDeadline = arr[i].deadline;
            }
        }
        //System.out.println(maxDeadline);
        int jobsCount = 0;
        int totalProfit = 0;
        boolean[] jobs = new boolean[maxDeadline];
        for(int i=0;i<n;i++){
            for(int j=arr[i].deadline;j>0;j--){
                if(!jobs[j-1]){
                    jobs[j-1] = true;
                    jobsCount++;
                    totalProfit += arr[i].profit;
                    break;
                }
            }
            if(jobsCount == n){
                break;
            }
        }
        return new int[] {jobsCount,totalProfit};
    }

    public static void main(String[] args) {
        JobSequencingProblem jobSequencingProblem = new JobSequencingProblem();
        int n1 = 4;
        Job[] arr1 = new Job[4];
        arr1[0] = new Job(1, 4, 20);
        arr1[1] = new Job(2, 1, 10);
        arr1[2] = new Job(3, 2, 40);
        arr1[3] = new Job(4, 2, 30);
        int[] result1 = jobSequencingProblem.JobScheduling(arr1, n1);
        System.out.println(result1[0]+","+result1[1]);
        int n2 = 5;
        Job[] arr2 = new Job[5];
        arr2[0] = new Job(1, 2, 100);
        arr2[1] = new Job(2, 1, 19);
        arr2[2] = new Job(3, 2, 27);
        arr2[3] = new Job(4, 1, 25);
        arr2[4] = new Job(5, 1, 15);
        int[] result2 = jobSequencingProblem.JobScheduling(arr2, n2);
        System.out.println(result2[0]+","+result2[1]);
    }
}

class Job {
    int id;
    int deadline;
    int profit;

    public Job(int id, int deadline, int profit){
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
