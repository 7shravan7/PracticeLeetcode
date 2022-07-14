package restart;

public class StackUsingArrays {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(3);
        stack.push(5);
        System.out.println(stack.peek());
        stack.push(7);
        System.out.println(stack.size());
        stack.pop();
        stack.pop();
        System.out.println(stack.peek());
        System.out.println(stack.size());
    }
}

class Stack {
    int[] values;
    int topIndex;

    public Stack() {
        values = new int[1000];
        topIndex = 0;
    }

    public void push(int val){
        values[topIndex] = val;
        topIndex++;
    }

    public int pop() {
        if(topIndex==0){
            return -1;
        }
        int val = values[topIndex-1];
        topIndex--;
        return val;
    }

    public int peek() {
        return topIndex==0 ? -1 : values[topIndex-1];
    }

    public int size() {
        return topIndex;
    }
}
