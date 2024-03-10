// 1. You are required to complete the code of our CustomStack class. The class should mimic the behaviour of java.util.Stack 
//    class and implement LIFO semantic.
// 2. Here is the list of functions that you are supposed to complete
//    2.1. push -> Should accept new data if there is space available in the underlying 
//    array or print "Stack overflow" otherwise.
//    2.2. pop -> Should remove and return last data if available or print "Stack 
//    underflow" otherwise and return -1.
//    2.3. top -> Should return last data if available or print "Stack underflow" 
//    otherwise and return -1.
//    2.4. size -> Should return the number of elements available in the stack.
//    2.5. display -> Should print the elements of stack in LIFO manner (space- 
//    separated) ending with a line-break.
// 3. Input and Output is managed for you.

// Sample Input
// 5
// push 10
// display
// push 20
// display
// push 30
// display
// push 40
// display
// push 50
// display
// push 60
// display
// top
// pop
// display
// top
// pop
// display
// top
// pop
// display
// top
// pop
// display
// top
// pop
// display
// top
// pop
// isEmpty
// quit

// Sample Output
// 10 
// 20 10 
// 30 20 10 
// 40 30 20 10 
// 50 40 30 20 10 
// Stack overflow
// 50 40 30 20 10 
// 50
// 50
// 40 30 20 10 
// 40
// 40
// 30 20 10 
// 30
// 30
// 20 10 
// 20
// 20
// 10 
// 10
// 10
//
// Stack underflow
// Stack underflow

import java.io.*;

public class stackImplement_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        CustomStack st = new CustomStack(n);
    
        String str = br.readLine();
        while(str.equals("quit") == false){
            if(str.startsWith("push")){
                int val = Integer.parseInt(str.split(" ")[1]);
                st.push(val);
            } else if(str.startsWith("pop")){
                int val = st.pop();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("top")){
                int val = st.top();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("size")){
                System.out.println(st.size());
            } else if(str.startsWith("display")){
                st.display();
            } else if(str.startsWith("isEmpty")){
                System.out.println(st.isEmpty());
            }

            str = br.readLine();
        }
    }

    public static class CustomStack {
        int[] data;  // fixed sized(cap - capacity) data array to store stack elements
        int tos;    // top of stack index

        public CustomStack(int cap) {
            data = new int[cap];
            tos = -1;
        }

        int size() {
            // write ur code here

            // if stack is empty, tos = -1 -> size = 0
            // 1 element in stack -> tos = 0 -> size = 1
            return tos + 1;
        }

        void display() {
            // write ur code here

            // Loop from top of stack till tos = 0
            for(int i = tos; i >= 0; i--) {
                System.out.print(data[i] + "-");
            }
            System.out.println();
        }

        void push(int val) {
            // write ur code here

            // if tos == capacity(data.length)  - 1 -> stack full -> print Stack Overflow
            // else increment tos -> data[tos] = val

            if(tos < data.length - 1) 
                data[++tos] = val;
            else
                System.out.println("Stack Overflow"); 
        }

        int pop() {
            // write ur code here

            // if tos < 0 -> stack empty -> print Stack underflow -> return -1
            // else return data[tos] -> decrement tos
            
            if(tos < 0) {
                System.out.println("Stack Underflow");
                return - 1;
            }
            else
                return data[tos--];
        }

        int top() {
            // write ur code here

            // if tos < 0 -> stack empty -> print Stack underflow -> return -1
            // else return data[tos]

            if(tos < 0) {
                System.out.println("Stack Underflow");
                return - 1;
            } 
            else
                return data[tos];
        }

        boolean isEmpty() {
            // if tos < 0 -> stack empty -> return true
            // tos >= 0 -> stack not empty -> return false
            return tos < 0;
        }
    }
}
