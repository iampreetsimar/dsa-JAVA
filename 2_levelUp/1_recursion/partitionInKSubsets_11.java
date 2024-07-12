// Given N and k, where N: # of elements and k: # of subsets.
// Partition N elements in k subsets and prints all such configs.

// NOTE: No subset should be empty! Also only print combinations and not permutations!

// INPUT
// 3 2

// OUTPUT
// [1, 2] [3]
// [1, 3] [2]
// [1] [2, 3]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class partitionInKSubsets_11 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int k = Integer.parseInt(parts[1]);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            ans.add(new ArrayList<Integer>());
        }
        solve(1, n, k, 0, ans);
    }

    // ans: list of k sets, initially all set are empty
    // ssfCount: count of non-emmpty subsets so far in ans
    // i: ith element used as level having options
    // if n-1 persons creates k sets, nth person can only be added to those k sets
    // otherwise, n-1 persons creates k-1 sets, nth person can create kth set of itself.
    public static void solve(int i, int n, int k, int ssfCount, ArrayList<ArrayList<Integer>> ans) {
        if(i > n) { // BASE CASE
            if(ssfCount == k) {     // non-empty sets == k, valid result
                for(ArrayList<Integer> set: ans) {
                    System.out.print(set + " ");
                }
                System.out.println();
            }
            return;
        }

        for(ArrayList<Integer> set: ans) {
            if(set.size() > 0) {    // if non-empty set present, add self to it
                set.add(i);
                solve(i + 1, n, k, ssfCount, ans); // non-empty set already present, count remains same
                set.remove(set.size() - 1); // remove self from set while backtracking
            } else {    // non-empty set not present, make a non-empty set by adding self
                set.add(i);
                solve(i + 1, n, k, ssfCount + 1, ans);  // non-empty set so far count incremented by 1
                set.remove(set.size() - 1); // remove self from set while backtracking
                break;  // imp to prevent permutations - creates only single non-empty set by adding self
            }
        }
    }
}
