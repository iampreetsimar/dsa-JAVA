// Given n: count of elements.
// Given n numbers.
// Given number tar.
// Calculate and print true/false, if there is a subset of elements which add up to tar or not.

// INPUT
// 5
// 4 2 7 1 3
// 10

// OUTPUT
// true

import java.io.*;

public class targetSumSubsets_7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] items = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(row[i]);
        }
        int tar = Integer.parseInt(br.readLine());

        System.out.println(targetSumSubset(items, tar, 0, 0));
        System.out.println(targetSumSubsetTab(items, tar));
    }

    // each items has 2 choices - either include or exclude in the subset and its sum
    // if only +ve items are present, can also add BASE CASES: sos > tar or sos == tar before reaching items.length
    public static boolean targetSumSubset(int[] items, int tar, int idx, int sos) {
        if(idx == items.length) {   // BASE CASE
            return (sos == tar) ? true : false; 
        }

        if(targetSumSubset(items, tar, idx + 1, sos + items[idx]))  // item included in subset and its sum
            return true;
    
        return targetSumSubset(items, tar, idx + 1, sos);   // item not includes in subset and its sum
    }

    // use dp grid of size [items.length + 1][tar + 1]
    // dp[i][j]: if items at row 'i' can have a subset whose sum equals 'j'
    // dp[items.length][tar]: if a subset of items[] has sum equals tar
    public static boolean targetSumSubsetTab(int[] items, int tar) {
        boolean[][] dp = new boolean[items.length + 1][tar + 1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0 && j == 0) {  // first cell: only subset available is {} - empty
                    dp[i][j] = true;    // can only make sum: 0 == j -> set true
                } else if(i == 0) {     // first row: only {} subset, cannot make sum j
                    dp[i][j] = false;   // set false
                } else if(j == 0) {     // first col: all items have an {} subset w/c makes sum j
                    dp[i][j] = true;    // set true
                } else {
                    // curr item: items[i - 1] as i is idx for dp grid rows and not items[]
                    // curr item excluded, items at i - 1 should make sum j = dp[i - 1][j]

                    // curr item included, items at i - 1 should make sum j - curr item
                    // only if j >= curr item. 
                    // If curr item > j -> curr item cannot make sum j, so not included.
                    // = (j >= items[i - 1]) ? dp[i - 1][j - items[i - 1]] : false
                    dp[i][j] = (dp[i - 1][j]) || ((j >= items[i - 1]) ? 
                                                  dp[i - 1][j - items[i - 1]] : false);
                }
            }
        }

        return dp[items.length][tar];   // returns if any subset of items[] can make sos == tar
    }
}
