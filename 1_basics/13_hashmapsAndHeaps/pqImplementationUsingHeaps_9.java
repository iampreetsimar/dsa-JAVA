// 1. You are required to complete the code of our Priority Queue class using the heap data structure. 
// Implement the functions to achieve what is explained in the theoretical discussion.
// 2. Here is the list of functions that you are supposed to complete:
//     2.1. add -> Should accept new data.
//     2.2. remove -> Should remove and return smallest value, if available or print 
//                    "Underflow" otherwise and return -1.
//     2.3. peek -> Should return smallest value, if available or print "Underflow" 
//                  otherwise and return -1.
//     2.4. size -> Should return the number of elements available.
// 3. Input and Output is managed for you.

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
// 10
// 10
// 10
// 20
// 20
// 30
// 30
// 40
// 40
// 50

import java.io.*;
import java.util.ArrayList;

public class pqImplementationUsingHeaps_9 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue qu = new PriorityQueue();

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

    public static class PriorityQueue {
        ArrayList<Integer> data;    // current implementation for MIN PRIORITY
    
        public PriorityQueue() {
          data = new ArrayList<>();
        }
    
        public void add(int val) {  // TC ~ O(logN)
            data.add(val);  // add to last -> breaks HOP    -> O(1)
            upheapify(data.size() - 1);     // call upheapify from last idx where addition happens -> O(logN)
        }

        // moves from leaf to all way up
        // maintains HOP
        private void upheapify(int childIdx) {      // TC ~ O(logN)
            if(childIdx == 0)   // if current idx is 0(root) -> BASE CASE
                return;

            int parentIdx = (childIdx - 1)/2;      // find current idx's parent idx

            int parentVal = data.get(parentIdx);    // get value at parent idx
            int childVal = data.get(childIdx);      // get value at child idx
            if(childVal < parentVal) {      // if child has higher priority -> swap child and parent's values
                swap(childIdx, parentIdx);      
                upheapify(parentIdx);       // recursive call from child's new idx
            }
        }

        private void swap(int idx1, int idx2) {     // swap values at idx1 and idx2
            int val1 = data.get(idx1);
            int val2 = data.get(idx2);
            data.set(idx1, val2);
            data.set(idx2, val1);
        }
    
        public int remove() {       // TC ~ O(logN)
            if(data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }

            // highest priority item at idx 0(root)
            swap(0, data.size() - 1);     // swap item at idx 0 to item in last -> highest priority in last
            int val = data.remove(data.size() - 1);     // remove current highest priority item   -> O(1)
            downHeapify(0);     // item at idx 0 breaks HOP -> call downHeapify -> O(logN)
            return val;     // return highest priority item
        }

        // moves from root all the way down -> maintains HOP
        private void downHeapify(int parentIdx) {   // TC ~ O(logN)
            int leftChildIdx = (2 * parentIdx) + 1;     // left child Idx
            int rightChildIdx = (2 * parentIdx) + 2;    // right child Idx
            int highestPriorityIdx = parentIdx;     // assume parent has highest priority

            // if left child present -> left child has higher priority than current highest priority
            if(leftChildIdx < data.size() && data.get(leftChildIdx) < data.get(highestPriorityIdx)) {
                highestPriorityIdx = leftChildIdx;  // set highest priority to left child
            }

            // if right child present -> right child has higher priority than current highest priority
            if(rightChildIdx < data.size() && data.get(rightChildIdx) < data.get(highestPriorityIdx)) {
                highestPriorityIdx = rightChildIdx; // set highest priority to right child
            }

            if(highestPriorityIdx != parentIdx) {    // if highest priority and current parent are different
                swap(highestPriorityIdx, parentIdx);    // swap values of current parent and that of highest priority
                downHeapify(highestPriorityIdx);    // parent placed at highest priority's pos -> call downheapify
            }
        }
    
        public int peek() {     // TC ~ O(1)
            if(data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            return data.get(0);     // highest priority item at 0th idx(root)
        }
    
        public int size() {     // TC ~ O(1)
            return data.size();     // return total items present
        }
    }
}
