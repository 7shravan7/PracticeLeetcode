package design.lld.rate_limiter.leaky_bucket;

import design.lld.rate_limiter.RateLimiter;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LeakyBucketRateLimiter implements RateLimiter {

    int capacity;
    BlockingQueue<Integer> queue; // blocking queue to restrict extra elements

    public LeakyBucketRateLimiter(int capacity) {
        this.capacity = capacity;
        queue = new ArrayBlockingQueue<>(capacity);
    }


    @Override
    public boolean grantAccess() {
        if(queue.remainingCapacity()==0) {
            return false;
        }
        queue.offer(1); // just basic integer
        return true;
    }
}
