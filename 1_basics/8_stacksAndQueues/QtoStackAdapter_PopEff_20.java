// 1. You are to implement QueueToStackAdapter class. 
// 2. You'll be using 2 queues given as data members to implement following stack functionality :
// 2.1 size - gives total elements in stack.
// 2.2 push - pushes a val to stack in LIFO manner.
// 2.3 pop - pops from stack in LIFO manner. Should be pop efficient, i.e., only take O(1) in time
// 2.4 top - gives top value of stack.

// * NOTE - Pop, top and size operations should be O(1) in time.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// Queue to Stack Adapter - Pop Efficient -> Pop, top, size in O(1)
public class QtoStackAdapter_PopEff_20 {
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

            // item to be popped in front of mainQ
            return mainQ.remove();
        }

        int top() {
            if(size() == 0) {
                // stack empty -> mainQ empty
                System.out.println("Stack Underflow");
                return -1;
            }

            // item on top in front of mainQ
            return mainQ.peek();
        }

        void push(int val) {
            // add item to helperQ -> makes added item in front -> popped first in LIFO manner
            helperQ.add(val);

            // remove all items from mainQ and add them to helperQ
            // makes order of items reversed just like a stack
            while(mainQ.size() > 0) {
                helperQ.add(mainQ.remove());
            }

            // swap refs -> makes all items present in mainQ
            Queue<Integer> temp = mainQ;
            mainQ = helperQ;
            helperQ = temp;
        }
    }
}
