// 1. You are required to complete the code of LLToQueueAdapter class. 
// 2. As a data member, a linked list is available.
// 3. You are required to complete following functions -
// 3.1 add - should accept new data in FIFO manner.
// 3.2 remove - should remove and return data in FIFO manner. If not available, print "Queue Underflow" and return -1.
// 3.3 peek - sshould return data in FIFO manner. If not available, print "Queue Underflow" and return -1.
// 3.4 size - should return size of queue.

import java.io.*;
import java.util.*;

public class llToQAdapter_12 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LLToQueueAdapter qu = new LLToQueueAdapter();

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

    public static class LLToQueueAdapter {
        LinkedList<Integer> list = new LinkedList<>();

        int size() {
            // total items in list
            return list.size();
        }

        void add(int val) {
            // add item to last of list
            list.addLast(val);
        }

        int remove() {
            if(size() == 0) {
                // queue empty
                System.out.println("Queue Underflow");
                return -1;
            }

            // remove item from front of list
            return list.removeFirst();
        }

        int peek() {
            if(size() == 0) {
                // queue empty
                System.out.println("Queue Underflow");
                return -1;
            }

            // return item from front of list
            return list.getFirst();
        }
    }
}
