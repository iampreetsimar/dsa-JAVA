// 1. You're required to complete the code for TwoStack class.
// 2. As data members, you have one array in the class.
// 3. Following are the list of functions you are supposed to complete :
// 3.1 push1 -> Should accept new data in array for stack 1 in LIFO manner. 
// If data array is full. print "Stack Overflow".
// 3.2 push2 -> Should accept new data in array for stack 2 in LIFO manner. 
// If data array is full. print "Stack Overflow".
// 3.3 pop1 -> Should remove and return data in array for stack 1 in LIFO manner. 
// If not available, print "Stack Underflow" and return -1.
// 3.4 pop2 -> Should remove and return data in array for stack 2 in LIFO manner. 
// If not available, print "Stack Underflow" and return -1.
// 3.5 top1 -> Should return data in array for stack 1 in LIFO manner. 
// If not available, print "Stack Underflow" and return -1. 
// 3.6 top2 -> Should return data in array for stack 2 in LIFO manner. 
// If not available, print "Stack Underflow" and return -1. 
// 3.7 size1 -> Should return the number of elements in array for stack 1. 
// 3.8 size2 -> Should return the number of elements in array for stack 2.
//
// Note -> The expectation is all functions are O(1) in time.

import java.io.*;

public class twoStacksInArray_19 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TwoStack s = new TwoStack(n);
    
        String str = br.readLine();
        while(str.equals("quit") == false){
            if(str.startsWith("push1")){
                int val = Integer.parseInt(str.split(" ")[1]);
                s.push1(val);
            } else if(str.startsWith("push2")){
                int val = Integer.parseInt(str.split(" ")[1]);
                s.push2(val);
            } else if(str.startsWith("pop1")){
                int val = s.pop1();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("pop2")){
                int val = s.pop2();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("top1")){
                int val = s.top1();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("top2")){
                int val = s.top2();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("size1")){
                System.out.println(s.size1());
            } else if(str.startsWith("size2")){
                System.out.println(s.size2());
            }

            str = br.readLine();
        }
    }
    
    public static class TwoStack {
        int[] data;
        int tos1;    // top of stack 1
        int tos2;   // top of stack 2

        public TwoStack(int cap) {
            data = new int[cap];


            tos1 = -1;  // initial pos for stack 1
            tos2 = data.length; // initial pos for stack 2
        }

        int size1() {
            // total elements in stack 1 -> from front
            return tos1 + 1;
        }

        int size2() {
            // total element in stack 2 -> from back
            return data.length - tos2;
        }

        void push1(int val) {
            if(tos2 == tos1 + 1) {
                // data array full
                System.out.println("Stack Overflow");
            } else {
                // increment top of stack 1 and push val
                data[++tos1] = val;
            }
        }

        void push2(int val) {
            if(tos2 == tos1 + 1) {
                // data array full
                System.out.println("Stack Overflow");
            } else {
                // decrement top of stack 2 and push val
                data[--tos2] = val;
            }
        }

        int top1() {
            if(tos1 == -1) {
                // stack 1 empty
                System.out.println("Stack Underflow");
                return -1;
            } else {
                // return top of stack 1
                return data[tos1];
            }
        }

        int top2() {
            if(tos2 == data.length) {
                // stack 2 empty
                System.out.println("Stack Underflow");
                return -1;
            } else {
                // return top of stack 2
                return data[tos2];
            }
        }

        int pop1() {
            if(tos1 == -1) {
                // stack 1 empty
                System.out.println("Stack Underflow");
                return -1;
            } else {
                // return top of stack 1 and decrement tos1
                return data[tos1--];
            }
        }

        int pop2() {
            if(tos2 == data.length) {
                // stack 2 empty
                System.out.println("Stack Underflow");
                return -1;
            } else {
                // return top of stack 2 and increment tos2
                return data[tos2++];
            }
        }
    }
}
