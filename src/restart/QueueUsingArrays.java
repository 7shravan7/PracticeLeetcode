package restart;



public class QueueUsingArrays {


}

class Queue {

    int[] values;

    int maxSize = 100;

    int start;

    int end;

    int currSize;

    Queue() {
        // Implement the Constructor
        values = new int[maxSize];
        start = end = -1;
        currSize= 0;
    }

    /*----------------- Public Functions of Queue -----------------*/

    boolean isEmpty() {
        // Implement the isEmpty() function
        return currSize==0;
    }

    void enqueue(int data) {
        // Implement the enqueue() function
        end = (end+1)%maxSize;
        values[end] = data;
        if(start==-1){
            start = end;
        }
        currSize++;
    }

    int dequeue() {
        // Implement the dequeue() function
        int firstElement = values[start];
        start = (start+1)%maxSize;
        currSize--;
        return firstElement;
    }

    int front() {
        // Implement the front() function
        return values[start];
    }
}
