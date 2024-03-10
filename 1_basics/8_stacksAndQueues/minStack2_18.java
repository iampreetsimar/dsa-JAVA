// 1. You're required to complete the code for MinStack class.
// 2. As data members, you have one stack and a min element available in the class.
// 3. Following are the list of functions you are supposed to complete :
// 3.1 push -> Should accept new data in LIFO manner. 
// 3.2 pop -> Should remove and return data in LIFO manner. If not available, print "Stack Underflow" and return -1.
// 3.3 top -> Should return data in LIFO manner. If not available, print "Stack Underflow" and return -1. 
// 3.4 size -> Should return the number of elements in stack. 
// 3.5 min -> Should return the smallest element available in the stack. If not available, 
// print "Stack Underflow" and return -1. Should be O(1) in time and space!!!
//
// Note -> The expectation is all functions are O(1) in time.

// Sample Input
// push 10
// push 20
// push 2
// push 5
// top
// min
// pop
// top
// min
// pop
// top
// min
// pop
// pop
// pop

// Sample Output
// 5
// 2
// 5
// 2
// 2
// 2
// 20
// 10
// 20
// 10
// Stack Underflow

import java.io.*;
import java.util.Stack;

public class minStack2_18 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MinStack s = new MinStack();
    
        String str = br.readLine();
        while(str.equals("quit") == false){
            if(str.startsWith("push")){
                int val = Integer.parseInt(str.split(" ")[1]);
                s.push(val);
            } else if(str.startsWith("pop")){
                int val = s.pop();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("top")){
                int val = s.top();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("size")){
                System.out.println(s.size());
            } else if(str.startsWith("min")){
                int val = s.min();
                if(val != -1){
                    System.out.println(val);
                }
            }

            str = br.readLine();
        }
    }

    public static class MinStack {
        Stack<Integer> data;
        int min;    // represents the min element in the stack

        public MinStack() {
            data = new Stack<>();
        }

        int size() {
            // returns total items in stack
            return data.size();
        }

        void push(int val) {
            // stack empty -> no min -> push val to stack and update min to val as well
            if(size() == 0) {
                data.push(val);
                min = val;
            } else if(val < min) {
                // val < min 
                // store fake value to stack: val + (val - min) 
                // update min to original val
                // => this make top of stack even smaller than min
                // => SPECIAL CASE
                data.push(val + val - min);
                min = val;
            } else {
                // push val to stack
                data.push(val);
            }
        }

        int pop() {
            if(size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            } else if(data.peek() < min) {
                // SPECIAL case detected : original val in min -> return original val
                // need to update min to previous min before val was pushed
                int val = min;
                min = 2 * val - data.pop();
                return val;
            } else {
                // return popped value from stack
                return data.pop();
            }
        }

        int top() {
            if(size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            } else if(data.peek() < min) {
                // SPECIAL CASE detected : original value in min
                return min;
            } else {
                // orginal value on top of stack
                return data.peek();
            }
        }

        int min() {
            // stack empty -> no min
            if(data.size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }

            // min always(at each point) stores the min element of stack
            // return whatever is in min
            return min;
        }
    }
}
