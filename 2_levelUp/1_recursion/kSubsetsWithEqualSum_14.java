// Given an array of n DISTINCT integers, divide these n integers into k non-empty subsets such that
// sum of integers of each subset is same.
// If it is not possible, print -1;

// INPUT
// 1 2 3 4 5 6
// 3

// OUTPUT
// [1, 6] [2, 5] [3, 4]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class kSubsetsWithEqualSum_14 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int k = Integer.parseInt(br.readLine());
        int totalSum = 0;
        int[] arr = new int[parts.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
            totalSum += arr[i];
        }

        // base cases
        if(arr.length == 0 || k == 0 || arr.length < k || totalSum % k != 0) {
            System.out.println(-1);     
            return;
        }

        if(k == 1) {    // all items in one set
            String temp = "[";
            for(int i = 0; i < arr.length; i++) {
                temp += (i == arr.length - 1) ? arr[i] + "]" : arr[i] + ", " ;
            }
            System.out.println(temp);
            return;
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            ans.add(new ArrayList<Integer>());
        }

        if(!solve(arr, k, new int[k], totalSum, 0, 0, ans))  System.out.println(-1);
    }

    // similar to PARTITION IN K SUBSETS approach - instead of item, add arr value at idx to subset
    // subsetSum[] tracks sum of each subset:
    //      - if any subset sum > total elements sum/k -> partition not possible
    //      - if any sum not equal -> partition not possible
    // idxth arr element used as level having options
    public static boolean solve(int[] arr, int k, int[] subsetSums, int totalSum, 
                             int ssf, int idx, ArrayList<ArrayList<Integer>> ans) {
        if(idx == arr.length) {     // BASE CASE
            if(ssf == k) {      // all sets must be non-empty
                for(int i = 1; i < k; i++) {    // all sets must have equal sum
                    if(subsetSums[i - 1] != subsetSums[i])  return false;
                }

                for(ArrayList<Integer> set: ans) {
                    System.out.print(set + " ");
                }
                System.out.println();   return true;
            }
            return false; 
        }

        boolean flag = false;
        for(int i = 0; i < ans.size(); i++) {
            ArrayList<Integer> set = ans.get(i);
            if(subsetSums[i] > totalSum/k)  // even a single subset sum cannot be > totalSum/k
                return false;   // no possible valid config using available subsets
            
            boolean res;
            if(set.size() > 0) {    // choice: add self to all non-empty sets
                set.add(arr[idx]);  // add self to non-empty set
                subsetSums[i] += arr[idx];  // add curr idxth arr item to curr set's sum
                res = solve(arr, k, subsetSums, totalSum, ssf, idx + 1, ans);
                if(res) flag = true;    
                subsetSums[i] -= arr[idx];  // remove curr arr item from subset sum while backtracking
                set.remove(set.size() - 1); // remove curr item from subset while backtracking
            } else {    // choice: add self to first empty set 
                set.add(arr[idx]);  // add self to first empty set
                subsetSums[i] += arr[idx];  // add curr idxth arr item to curr set's sum
                res = solve(arr, k, subsetSums, totalSum, ssf + 1, idx + 1, ans);
                subsetSums[i] -= arr[idx];  // remove curr arr item from subset sum while backtracking
                set.remove(set.size() - 1); // remove curr item from subset while backtracking
                break;
            }
            if(res) flag = true;    // if flag turns true even once, there is a possible config
        }
        return flag;    // if flag stays false, no possible config, print -1 in main
    }
}
