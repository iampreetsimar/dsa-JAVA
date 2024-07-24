// Given an array of N integers, divide these N integers into two subsets such that the difference
// of sum of the two subsets is as minimum as possible. 
// If N is even, both set will contains N/2 items. If N is odd, one set will contain (N-1)/2 items and 
// the other set will contain (N+1)/2 items. 

// If it is not possible to divide, print -1.

// PROBLEM ALSO CALLED - MINIMUM SUBSET SUM DIFFERENCE

// INPUT
// 1 2 3 4 5 6

// OUTPUT
// [1, 3, 6] [2, 4, 5]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class tugOfWar_19 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inp = br.readLine().split(" ");
        int[] arr = new int[inp.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(inp[i]);
        }

        solve(arr, 0, new ArrayList<Integer>(), new ArrayList<Integer>(), 0, 0);
        System.out.println(ans);
    }

    static int minDiff = Integer.MAX_VALUE;
    static String ans = "";

    // each array item has 2 choices - either be in set1 or be in set2
    // 0th idx item has only 1 choice to prevent permutations
    // check set size before choices to prevent invalid calls based on set size
    // level must be the idx of the array item and options are the two choices excepts for 0th idx item
    // set size check < (N + 1)/2 as if set1 has (N+1)/2 items, the other set2 must have (N -1)/2 items
    // set2 still can add another item but execution reaches the base case which prevents invalidity of check 
    public static void solve(int[] arr, int idx, ArrayList<Integer> set1, ArrayList<Integer> set2,
                             int sos1, int sos2) {
        if(idx == arr.length) {     // BASE CASE
            int diff = Math.abs(sos1 - sos2);   // find abs diff b/w sum of both sets
            if(diff < minDiff) {    // new candidate for min diff
                minDiff = diff;     // set new min diff
                ans = set1 + " " + set2;    // sets considered for the new min diff
            }
            return;
        }

        if(idx == 0) {  // 0th idx items has only 1 choices - to be in set1 to prevent permutations
            set1.add(arr[idx]);     // added in set1
            solve(arr, idx + 1, set1, set2, sos1 + arr[idx], sos2); // also track sum of set1
            set1.remove(set1.size() - 1);   // removed while bactracking
            return;  // return otherwise 0th item will have 3 recursive calls
        }
        
        if(set1.size() < (arr.length + 1) / 2) {    // check set1 size before adding another item
            set1.add(arr[idx]);     // added in set1
            solve(arr, idx + 1, set1, set2, sos1 + arr[idx], sos2); // also track sum of set1
            set1.remove(set1.size() - 1);   // removed while bactracking
        }
        
        if(set2.size() < (arr.length + 1) / 2) {    // check set2 size before adding another item
            set2.add(arr[idx]);     // added in set2
            solve(arr, idx + 1, set1, set2, sos1, sos2 + arr[idx]);     // also track sum of set2
            set2.remove(set2.size() - 1);   // removed while bactracking
        }
    }
}
