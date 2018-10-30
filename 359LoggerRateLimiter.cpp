/*
 * =====================================================================================
 *
 Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

 Example:

 MovingAverage m = new MovingAverage(3);
 m.next(1) = 1
 m.next(10) = (1 + 10) / 2
 m.next(3) = (1 + 10 + 3) / 3
 m.next(5) = (10 + 3 + 5) / 3
*/
class MovingAverage {

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        buffer = new int[size];
        sum = 0;
        idx = 0;
        cursize = 0;
        capacity = size;
    }

    // idx will be the next insert position, and also to be removed position
    public double next(int val) {
        if(cursize == capacity)
        {
            sum -= buffer[idx];
        }
        else
            ++cursize;

        sum += val;
        buffer[idx] = val;
        idx = (idx+1)%capacity;

        return (double)sum / cursize;
    }

    int[] buffer;
    int sum, idx, cursize, capacity;
}
