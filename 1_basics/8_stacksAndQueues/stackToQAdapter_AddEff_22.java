// 1. You are to implement StackToQueueAdapter class. 
// 2. You'll be using 2 stacks given as data members to implement following queue functionality :
// 2.1 size - gives total elements in queue.
// 2.2 add - pushes a val to queue in FIFO manner. Should be add efficient, i.e., only take O(1) in time
// 2.3 remove - removes from queue in FIFO manner. 
// 2.4 peek - gives front value of queue.

// * NOTE - Add operation should be O(1) in time.

import java.io.*;
import java.util.*;

// Stack to Queue Adapter - Add Efficient -> Add in O(1)
public class stackToQAdapter_AddEff_22 {
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
            // total element in q -> total items in mainS
            return mainS.size();
        }

        void add(int val) {
            // item to be pushed to mainS -> takes O(1) time
            mainS.push(val);
        }

        int remove() {
            if(mainS.size() == 0) {
                // q empty -> mainS empty
                System.out.println("Queue Underflow");
                return -1;
            }

            // item to be removed is at bottom of mainS -> reverse order using helperS
            // move items to helperS
            while(mainS.size() > 0) {
                helperS.push(mainS.pop());
            }

            // item at top of helperS -> actual item to be removed
            int val = helperS.pop();

            // move back items to mainS -> to get original order
            while(helperS.size() > 0) {
                mainS.push(helperS.pop());
            }

            return val;
        }

        int peek() {
            if(mainS.size() == 0) {
                // q empty -> mainS empty
                System.out.println("Queue Underflow");
                return -1;
            }

            // item to be returned is at bottom of mainS -> reverse order using helperS
            // move items to helperS
            while(mainS.size() > 0) {
                helperS.push(mainS.pop());
            }

            // item at top of helperS -> actual item to be returned
            int val = helperS.peek();

            // move back items to mainS -> to get original order
            while(helperS.size() > 0) {
                mainS.push(helperS.pop());
            }

            return val;
        }
    }
}
