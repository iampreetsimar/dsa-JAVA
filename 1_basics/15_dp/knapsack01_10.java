// Given N: count of items, N numbers: values of N items, N numbers: weights of N items, cap: capacity of bag you have.
// Calculate and print the maximum value that can be created in the bag without overflowing its capacity.

// NOTE: Each items can be taken 0 or 1 number of times, i.e, FINITE supply.

// INPUT
// 5
// 15 14 10 45 30
// 2   5  1  3  4
// 7

// OUTPUT
// 75

import java.io.*;

public class knapsack01_10 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] values = new int[n];
        int[] weights = new int[n];

        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(row[i]);
        }
        row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(row[i]);
        }

        int cap = Integer.parseInt(br.readLine());

        System.out.println(knapsack01(values, weights, cap, 0));
        System.out.println(knapsack01Tab(values, weights, cap));
    }

    public static int knapsack01(int[] values, int[] weights, int cap, int idx) {
        if(idx == values.length || cap <= 0) {  // BASE CASE: no more item or bag is full
            return 0;   // cannot add any value -> max value will be 0
        }

        if(weights[idx] > cap)  // if curr item's wt > cap -> only excluding choice -> move to next item
            return knapsack01(values, weights, cap, idx + 1);
        else    // both choices -> include has curr item's val and its wt is decreased from cap
            return Math.max(values[idx] + knapsack01(values, weights, cap - weights[idx], idx + 1),
                            knapsack01(values, weights, cap, idx + 1)); // exclude -> move to next item
    }

    // use dp grid of size [N + 1][cap + 1]
    // dp[i][j]: max value created by subset at dp[i] where bag capacity is j
    // dp[N][cap]: max value created by a subset of entire team when bag capacity is cap
    public static int knapsack01Tab(int[] values, int[] weights, int cap) {
        int[][] dp = new int[values.length + 1][cap + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0 || j == 0) {  // i == 0 -> no item(empty subset) | j == 0 -> no bag capacity
                    dp[i][j] = 0;   // no value created -> max values: 0
                } else {
                    int val = values[i - 1];    // curr item's value
                    int wt = weights[i - 1];    // curr item's wt

                    // excluding: max value created before curr item for bag cap: j -> dp[i - 1][j]
                    // including: 
                    // if bag cap >= curr item's wt 
                            // -> max value: curr item's value + max value before curr item for bag cap: j - wt
                    // else: bag cap < curr item's wt -> no value created on including curr item
                    dp[i][j] = Math.max(dp[i - 1][j], (j >= wt) ? val + dp[i - 1][j - wt] : 0);
                }
            }
        }

        return dp[values.length][cap];  // max value using a subset of given items when bag capacity is cap
    }
}
