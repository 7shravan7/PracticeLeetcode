package design.lld.rate_limiter;

import design.lld.rate_limiter.leaky_bucket.LeakyBucketMain;
import design.lld.rate_limiter.sliding_window.SlidingWindowMain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterApp {

    private static final List<String> usersList = Arrays.asList("user1","user2","user3");

    public static void main(String[] args) {
        leakyBucket();
        slidingWindow();
    }

    private static void leakyBucket() {
        System.out.println("---- Leaky Bucket with 3 users with bucket capacity of 5 ----");
        BucketMain leakyBucket = new LeakyBucketMain(5);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Random random = new Random();
        for(int i=0;i<20;i++){
            String user = usersList.get(random.nextInt(usersList.size()));
            executorService.execute(()->leakyBucket.accessApplication(user));
        }
        executorService.shutdown();
        System.out.println("---- Leaky Bucket ended ----");
    }

    private static void slidingWindow() {
        System.out.println("---- Sliding window with 3 users with bucket capacity of 5 ----");
        BucketMain leakyBucket = new SlidingWindowMain(5, usersList);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Random random = new Random();
        for(int i=0;i<20;i++){
            String user = usersList.get(random.nextInt(usersList.size()));
            executorService.execute(()->leakyBucket.accessApplication(user));
        }
        executorService.shutdown();
        System.out.println("---- Sliding window ended ----");
    }
}
