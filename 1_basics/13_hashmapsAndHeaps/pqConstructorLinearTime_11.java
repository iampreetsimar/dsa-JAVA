// Add a constructor for PriorityQueue class which takes an input array and constructs a heap

import java.io.*;
import java.util.ArrayList;

public class pqConstructorLinearTime_11 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = { 10, 5, 8, 17, 6, 44, 49, 3, 11, 34, 16, 7, 42, 48, 79 };
        PriorityQueue qu = new PriorityQueue(input);

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
        ArrayList<Integer> data;

        PriorityQueue() {       // MIN-PRIORITY QUEUE
            data = new ArrayList<>();
        }

        // use upheapify - TC: O(NlogN) for all items in input
        // PriorityQueue(int[] input) {
        //     this();
        //     for(int val: input) {
        //         add(val);
        //     }
        // }

        // use downheapify - TC: O(N) for all items in input
        PriorityQueue(int[] input) {
            this();
            for(int val: input) {   // adds all input items to data -> does not make pq -> only makes CBT
                data.add(val);
            }

            // loop from last parent node idx to Oth idx node
            // no need to downHeapify leaf nodes as leaf nodes have no children
            // makes HOP valid
            // most work for Oth idx node -> downHeapify for logN height
            for(int i = (data.size() / 2) - 1; i >= 0; i--) {
                downHeapify(i);
            }
        }

        public void add(int val) {
            data.add(val);
            upHeapify(data.size() - 1);
        }

        private void upHeapify(int childIdx) {
            if(childIdx == 0)
                return;

            int parentIdx = (childIdx - 1) / 2;

            int parentVal = data.get(parentIdx);
            int childVal = data.get(childIdx);

            if(childVal < parentVal) {
                swap(childIdx, parentIdx);
                upHeapify(parentIdx);
            }
        }

        private void swap(int idx1, int idx2) {
            int val1 = data.get(idx1);
            int val2 = data.get(idx2);
            data.set(idx1, val2);
            data.set(idx2, val1);
        }

        public int remove() {
            if(data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }

            swap(0, data.size() - 1);
            int val = data.remove(data.size() - 1);
            downHeapify(0);
            return val;
        }

        private void downHeapify(int parentIdx) {
            int lcIdx = (2 * parentIdx) + 1;
            int rcIdx = (2 * parentIdx) + 2;
            int minIdx = parentIdx;

            if(lcIdx < data.size() && data.get(lcIdx) < data.get(minIdx)) {
                minIdx = lcIdx;
            }

            if(rcIdx < data.size() && data.get(rcIdx) < data.get(minIdx)) {
                minIdx = rcIdx;
            }

            if(minIdx != parentIdx) {
                swap(minIdx, parentIdx);
                downHeapify(minIdx);
            }
        }

        public int peek() {
            if(data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            return data.get(0);
        }

        public int size() {
            return data.size();
        }
    }
}
