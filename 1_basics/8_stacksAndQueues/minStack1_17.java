// 1. You're required to complete the code for MinStack class.
// 2. As data members, you have two stacks available in the class - one for data values and another for minimum values. 
// 3. Following are the list of functions you are supposed to complete :
// 3.1 push -> Should accept new data in LIFO manner. 
// 3.2 pop -> Should remove and return data in LIFO manner. If not available, print "Stack Underflow" and return -1.
// 3.3 top -> Should return data in LIFO manner. If not available, print "Stack Underflow" and return -1. 
// 3.4 size -> Should return the number of elements in stack. 
// 3.5 min -> Should return the smallest element available in the stack. If not available, 
// print "Stack Underflow" and return -1. Will be O(N) in space due to two stacks.
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class minStack1_17 {
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
        Stack<Integer> allData;
        Stack<Integer> minData;

        public MinStack() {
            allData = new Stack<>();
            minData = new Stack<>();
        }

        int size() {
            // return total number of items in allData
            return allData.size();
        }

        void push(int val) {
            // push val to allData
            allData.push(val);

            // if val <= top of minData || minData.size == 0(minData is empty) -> push val to minData as well
            if(minData.size() == 0 || val <= minData.peek())
                minData.push(val);
        }

        int pop() {
            if(allData.size() == 0) {
                // empty allData -> empty minData
                System.out.println("Stack Underflow");
                return -1;
            }

            // pop item from allData
            int val = allData.pop();

            if(val == minData.peek()) {
                // top of allData == top of minData -> item to be popped from both
                minData.pop();
            }

            return val;
        }

        int top() {
            if(allData.size() == 0) {
                // empty allData -> empty minData
                System.out.println("Stack Underflow");
                return -1;
            }

            // top of allData
            return allData.peek();
        }

        int min() {
            // if allData is empty -> minData is empty as well -> no min
            if(size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }

            // otherwise print whatever is on top of minData
            return minData.peek();
        }
    }
}
