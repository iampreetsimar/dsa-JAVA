// 1. You are given a list of lists, where each list is sorted.
// 2. You are required to complete the body of mergeKSortedLists function. 
// The function is expected to merge k sorted lists to create one sorted list.

// Constraints
// Space complextiy = O(k)
// Time complexity = nlogk
// where k is the number of lists and n is number of elements across all lists.

// Sample Input
// 4
// 5
// 10 20 30 40 50
// 7
// 5 7 9 11 19 55 57
// 3
// 1 2 3
// 2
// 32 39

// Sample Output
// 1 2 3 5 7 9 10 11 19 20 30 32 39 40 50 55 57 

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class mergeKSortedLists_8 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int k = scn.nextInt();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for(int i = 0; i < k; i++){
            ArrayList<Integer> list = new ArrayList<>();
            int n = scn.nextInt();
            for(int j = 0; j < n; j++){
                list.add(scn.nextInt());
            }
            lists.add(list);
        }

        ArrayList<Integer> mlist = mergeKSortedLists(lists);
        for(int val: mlist){
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Pair object stores - list index, data index and the data itself
    // since object is a Custom Class, can't be compared using <, > or ==
    // need to implement interface Comparable for this situation
    // Comparable interface requires to write a compareTo() which returns - , + or 0 value.
    // Based on this value, comparison is done
    public static class Pair implements Comparable<Pair> {
        int listIdx;
        int dataIdx;
        int data;

        Pair(int listIdx, int dataIdx, int data) {
            this.listIdx = listIdx;
            this.dataIdx = dataIdx;
            this.data = data;
        }

        // both objects typecasted to Comparable type
        // compares thisObject.compareTo(otherObject) > 0 or < 0 or == 0
        // when thisObject.compareTo(otherObject) < 0 => thisObject is smaller b/w two
        // as sorting needed on data of object, compare data of both pair objects
        // this.data > other.data -> returns a +ve value
        // this.data == other.data -> returns 0
        // this.data < other.data -> returns a -ve value -> priority given to smaller value(this object)
        public int compareTo(Pair other) {  
            return this.data - other.data; 
        }
    }

    // merge all k sorted lists into one sorted list and return it
    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> rv = new ArrayList<>();
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();  // min-priority queue -> priority given to smallest

        // add 0th item from all k lists to pq
        // since k lists are themselves sorted, the smallest value of merged list will be one of the items at 0th idx 
        for(int i = 0; i < lists.size(); i++) {    
            Pair p = new Pair(i, 0, lists.get(i).get(0));
            pq.add(p);
        }

        // removes items from pq in sorted manner -> adds items in linear manner based on following condition
        while(pq.size() > 0) {  
            Pair p = pq.remove();   // pq removes smallest item(current object) present in pq
            rv.add(p.data);     // adds smallest item to result in sorted order

            // condition
            if(p.dataIdx + 1 < lists.get(p.listIdx).size()) {  // if next idx in current object's list present
                p.dataIdx++;    // update idx with next item's idx
                p.data = lists.get(p.listIdx).get(p.dataIdx);   // update data with next item's data
                pq.add(p);   // current object has same listIdx, updated dataIdx and updated data -> add to pq
            }
        }
  
        return rv;  // sorted merged result in rv -> return rv
    }
}
