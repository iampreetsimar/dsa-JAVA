// 1. You are required to complete the code of LLToStackAdapter class. 
// 2. As a data member, a linked list is available.
// 3. You are required to complete following functions -
// 3.1 push - should accept new data in LIFO manner.
// 3.2 pop - should remove and return data in LIFO manner. If not available, print "Stack Underflow" and return -1.
// 3.3 top - sshould return data in LIFO manner. If not available, print "Stack Underflow" and return -1.
// 3.4 size - should return size of stack.

import java.io.*;
import java.util.*;

public class llToStackAdapter_11 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LLToStackAdapter s = new LLToStackAdapter();
    
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
            }

            str = br.readLine();
        }
    }

    public static class LLToStackAdapter {
        LinkedList<Integer> list = new LinkedList<>();

        int size() {
            // total items in list
            return list.size();
        }

        void push(int val) {
            // add item to front of list
            list.addFirst(val);
        }

        int pop() {
            if(size() == 0) {
                // list is empty
                System.out.println("Stack Underflow");
                return -1;
            }

            // remove first item of list
            return list.removeFirst();
        }

        int top() {
            if(size() == 0) {
                // list is empty
                System.out.println("Stack Underflow");
                return -1;
            }

            // return first item of list
            return list.getFirst();
        }
    }
}
