// 1. You are to implement QueueToStackAdapter class. 
// 2. You'll be using 2 queues given as data members to implement following stack functionality :
// 2.1 size - gives total elements in stack.
// 2.2 push - pushes a val to stack in LIFO manner. Should be push efficient, i.e., only take O(1) in time
// 2.3 pop - pops from stack in LIFO manner. 
// 2.4 top - gives top value of stack.

// * NOTE - Push operation should be O(1) in time.

import java.io.*;
import java.util.*;

// Queue to Stack Adapter - Push Efficient -> Push in O(1)
public class QToStackAdapter_PushEff_21 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        QueueToStackAdapter s = new QueueToStackAdapter();
    
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

    public static class QueueToStackAdapter {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStackAdapter() {
            mainQ = new ArrayDeque<>();
            helperQ = new ArrayDeque<>();
        }

        int size() {
            // total elements in stack -> total items in mainQ
            return mainQ.size();
        }

        int pop() {
            if(size() == 0) {
                // stack empty -> mainQ empty
                System.out.println("Stack Underflow");
                return -1;
            }

            // item to be popped -> at back of mainQ
            // all items removed from mainQ and added to helperQ except last one
            while(mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }

            // item to be popped from stack
            int val = mainQ.remove();

            // swap references
            Queue<Integer> temp = mainQ;
            mainQ = helperQ; 
            helperQ = temp;

            return val;
        }

        int top() {
            if(size() == 0) {
                // stack empty -> mainQ empty
                System.out.println("Stack Underflow");
                return -1;
            }

            // item to be popped -> at back of mainQ
            // all items removed from mainQ and added to helperQ except last one
            while(mainQ.size() > 1) {
                helperQ.add(mainQ.remove());
            }

            // item on top of stack
            int val = mainQ.remove();

            // top item added back to queue
            helperQ.add(val);

            // swap references
            Queue<Integer> temp = mainQ;
            mainQ = helperQ; 
            helperQ = temp;

            return val;
        }

        void push(int val) {
            // val pushed to top of stack -> would be at end of mainQ
            mainQ.add(val);
        }
    }
}
