// 1. You are to implement StackToQueueAdapter class. 
// 2. You'll be using 2 stacks given as data members to implement following queue functionality :
// 2.1 size - gives total elements in queue.
// 2.2 add - pushes a val to queue in FIFO manner. 
// 2.3 remove - removes from queue in FIFO manner. Should be remove efficient, i.e., only take O(1) in time
// 2.4 peek - gives front value of queue.

// * NOTE - Remove operation should be O(1) in time.

import java.io.*;
import java.util.*;

// Stack to Queue Adapter - Remove Efficient -> Remove in O(1)
public class stackToQAdapter_RemoveEff_23 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StackToQueueAdapter qu = new StackToQueueAdapter();

        String str = br.readLine();
        while(str.equals("quit") == false){
            if(str.startsWith("add")){
                int val = Integer.parseInt(str.split(" ")[1]);
                qu.add(val);
            } else if(str.startsWith("remove")){
                int val = qu.remove();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("peek")){
                int val = qu.peek();
                if(val != -1){
                    System.out.println(val);
                }
            } else if(str.startsWith("size")){
                System.out.println(qu.size());
            }

            str = br.readLine();
        }
    }

    public static class StackToQueueAdapter {
        Stack<Integer> mainS;
        Stack<Integer> helperS;

        public StackToQueueAdapter() {
            mainS = new Stack<>();
            helperS = new Stack<>();
        }

        int size() {
            // total items in q -> total items in mainS
            return mainS.size();
        }

        int remove() {
            if(size() == 0) {
                System.out.println("Queue Underflow");
                return -1;
            }

            // item to be removed on top of mainS
            return mainS.pop();
        }

        int peek() {
            if(size() == 0) {
                System.out.println("Queue Underflow");
                return -1;
            }

            // item to be peeked on top of mainS
            return mainS.peek();
        }

        void add(int val) {
            // added item is at back of q -> should be at bottom of mainS
            
            // move items from mainS to helperS
            while(mainS.size() > 0) helperS.push(mainS.pop());

            // add item to mainS
            mainS.push(val);

            // move items back from helperS to mainS
            while(helperS.size() > 0) mainS.push(helperS.pop());
        }
    }
}
