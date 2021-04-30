import java.util.List;
import java.util.Map;

/*
362. Design Hit Counter
Medium

Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and 
    you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). 
    You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 

Follow up:
What if the number of hits per second could be very large? Does your design scale?
*/
class HitCounter {
    Map<Integer, Integer> countMap;
    List<Integer> timestamps;
    /** Initialize your data structure here. */
    public HitCounter() {
        countMap = new HashMap<>();
        timestamps = new ArrayList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if (timestamps.isEmpty()) {
            countMap.put(timestamp, 1);
        } else {
            countMap.put(timestamp, countMap.get(timestamps.get(timestamps.size() - 1)) + 1);
        }
        timestamps.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        return getAccumulateHits(timestamp, timestamps, countMap) - getAccumulateHits(timestamp - 300, timestamps, countMap);
    }

    private int getAccumulateHits(int timestamp, List<Integer> timestamps, Map<Integer, Integer> countMap) {
        int size = timestamps.size();
        //System.out.println("size is " + size);
        int accumulateHits = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (timestamp >= timestamps.get(i)) {
                accumulateHits = countMap.get(timestamps.get(i));
                //System.out.println("i is " + i);
                return accumulateHits;
            }
        }
        //System.out.println("accumulateHits for timestamp " +  timestamp + " is " + accumulateHits);
        return accumulateHits;
    }
}


/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */