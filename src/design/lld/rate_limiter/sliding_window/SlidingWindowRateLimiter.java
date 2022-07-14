package design.lld.rate_limiter.sliding_window;

import design.lld.rate_limiter.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.SimpleTimeZone;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRateLimiter implements RateLimiter {

    int capacity;

    int windowTime; // in secs

    Queue<Long> queue;

    public SlidingWindowRateLimiter(int capacity, int windowTime) {
        this.capacity = capacity;
        this.windowTime = windowTime;
        queue = new ConcurrentLinkedDeque<>();
    }

    @Override
    public boolean grantAccess() {
        if(queue.size()==capacity){
            return false;
        }
        Long currentTime = System.currentTimeMillis();
        while (!queue.isEmpty() && (queue.peek()-currentTime)/1000 > windowTime){
            queue.poll();
        }
        queue.offer(currentTime);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        System.out.println(Thread.currentThread().getName()+"  -- "+sdf.format(new Date(currentTime)));
        return true;
    }
}
