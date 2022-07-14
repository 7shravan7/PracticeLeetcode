package restart;

import java.util.Stack;

public class ReverseStackRecursion {

    public static void sortStack(Stack<Integer> stack) {
        // Write your code here.
        if(stack.isEmpty()){
            return;
        }
        int val = stack.pop();
        sortStack(stack);
        insert(stack, val);
    }

    private static void insert(Stack<Integer> stack, int val) {
        if(stack.isEmpty() || stack.peek()<val){
            stack.push(val);
            return;
        }
        // pop till top of stack is less than val || stack is empty using recursion
        int popVal = stack.pop();
        insert(stack, val);
        stack.push(popVal);
    }

    public static void printStack(Stack<Integer> stack){
        for(int i=0;i<stack.size();i++){
            System.out.println(stack.get(i));
        }
        System.out.println("--------");
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(-2);
        stack.push(9);
        stack.push(-7);
        stack.push(3);
        printStack(stack);
        sortStack(stack);
        printStack(stack);
    }

}
