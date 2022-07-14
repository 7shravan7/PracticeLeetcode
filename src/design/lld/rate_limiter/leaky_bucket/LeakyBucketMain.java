package design.lld.rate_limiter.leaky_bucket;

import design.lld.rate_limiter.BucketMain;
import design.lld.rate_limiter.RateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketMain implements BucketMain {

    private int bucketCapacity;

    Map<String, RateLimiter> userMap;

    public LeakyBucketMain(int bucketCapacity) {
        userMap = new ConcurrentHashMap<>();
        this.bucketCapacity = bucketCapacity;
    }

    @Override
    public void accessApplication(String userId) {
        if(!userMap.containsKey(userId)){
            userMap.put(userId, new LeakyBucketRateLimiter(bucketCapacity));
        }
        String threadName = Thread.currentThread().getName();
        if(userMap.get(userId).grantAccess()) {
            System.out.println("["+threadName+ "] "+userId+" --> accessing Application");
        } else {
            System.out.println("["+threadName+ "] "+userId+" --> 429 Too Many Requests");
        }
    }
}
