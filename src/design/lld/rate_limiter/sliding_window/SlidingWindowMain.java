package design.lld.rate_limiter.sliding_window;

import design.lld.rate_limiter.BucketMain;
import design.lld.rate_limiter.RateLimiter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindowMain implements BucketMain {

    Map<String, RateLimiter> userMap;

    public SlidingWindowMain(int bucketCapacity, List<String> userList) {
        userMap = new HashMap<>();
        userList.forEach(users->{
            if("user2".equals(users)){
                userMap.put(users, new SlidingWindowRateLimiter(3, 1));
            } else {
                userMap.put(users, new SlidingWindowRateLimiter(bucketCapacity, 2));
            }
        });
    }

    @Override
    public void accessApplication(String userId) {
        String threadName = Thread.currentThread().getName();
        if(userMap.get(userId).grantAccess()) {
            System.out.println("["+threadName+ "] "+userId+" --> accessing Application");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("["+threadName+ "] "+userId+" --> 429 Too Many Requests");
        }
    }
}
