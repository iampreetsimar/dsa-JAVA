// 1. You are required to complete the code of our MedianPriorityQueue class. 
// The class should mimic the behaviour of a PriorityQueue and give highest priority to median of it's data.
// 2. Here is the list of functions that you are supposed to complete
// 2.1. add -> Should accept new data.
// 2.2. remove -> Should remove and return median value, if available or print "Underflow" otherwise and return -1
// 2.3. peek -> Should return median value, if available or print "Underflow" otherwise and return -1
// 2.4. size -> Should return the number of elements available
// 3. Input and Output is managed for you.

// Note -> If there are even number of elements in the MedianPriorityQueue, 
//         consider the smaller median as median value.

// Sample Input
// add 10
// add 20
// add 30
// add 40
// peek
// add 50
// peek
// remove
// peek
// remove
// peek
// remove
// peek
// remove
// peek
// quit

// Sample Output
// 20
// 30
// 30
// 20
// 20
// 40
// 40
// 10
// 10
// 50

import java.io.*;
import java.util.*;

public class medianPriorityQueue_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MedianPriorityQueue qu = new MedianPriorityQueue();
    
        String str = br.readLine();
        while (str.equals("quit") == false) {
          if (str.startsWith("add")) {
            int val = Integer.parseInt(str.split(" ")[1]);
            qu.add(val);
          } else if (str.startsWith("remove")) {
            int val = qu.remove();
            if (val != -1) {
              System.out.println(val);
            }
          } else if (str.startsWith("peek")) {
            int val = qu.peek();
            if (val != -1) {
              System.out.println(val);
            }
          } else if (str.startsWith("size")) {
            System.out.println(qu.size());
          }
          str = br.readLine();
        }
    }

    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        MedianPriorityQueue() {
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        int size() {    // returns total items in both pq
            return left.size() + right.size();
        }

        // adds items to pq
        void add(int val) {
            if(right.size() > 0 && val > right.peek()) {    // add to right if right is not empty & val > right peek
                right.add(val);     // ensures val in correct half
            } else {    // by default, add to left
                left.add(val);
            }

            // rebalance required if gap b/w size > 1
            handleRebalance();     // ensures median stays at peek of either left or right
        }

        private void handleRebalance() {
            if(left.size() - right.size() == 2) {   // left size > right size
                right.add(left.remove());   // remove from left -> add to right
            } else if(right.size() - left.size() == 2) {    // right size > left size
                left.add(right.remove());   // remove from right -> add to left
            }
        }

        int remove() {  // removes and returns current median
            if(this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if(left.size() >= right.size()) {    // median in left peek
                return left.remove();   // removes item from left peek
            } else {    // median in right peek
                return right.remove();  // removes item from right peek
            }
        }

        int peek() {   // returns current median
            if(this.size() == 0) {
                System.out.println("Underflow");
                return - 1;
            } else if(left.size() >= right.size()) {    // median in left peek
                return left.peek();     // returns left peek item
            } else {    // median in right peek
                return right.peek();    // returns right peek item
            }
        }
    }
}
