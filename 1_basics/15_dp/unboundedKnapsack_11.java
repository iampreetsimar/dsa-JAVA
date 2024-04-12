// Given N: count of items, N numbers: values of N items, N numbers: weights of N items, cap: capacity of bag you have.
// Calculate and print the maximum value that can be created in the bag without overflowing its capacity.

// NOTE: Each item can be taken any number of times, i.e, INFINITE supply.

// INPUT
// 5
// 15 14 10 45 30
// 2   5  1  3  4
// 7

// OUTPUT
// 100

import java.io.*;

public class unboundedKnapsack_11 {
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

        System.out.println(unboundedKnapsack(values, weights, cap, 0));
        System.out.println(unboundedKnapsackTab(values, weights, cap));
    }

    public static int unboundedKnapsack(int[] values, int[] weights, int cap, int idx) {
        if(idx == values.length || cap <= 0) {  // BASE CASE: no more item or bag is full
            return 0;   // cannot add any value -> max value will be 0
        }

        if(weights[idx] > cap)  // if curr item's wt > cap -> only excluding choice -> move to next item
            return unboundedKnapsack(values, weights, cap, idx + 1);
        else  {
            // both choices
            // include: add curr item's val and its wt is decreased from cap but same item can be used again
            // exclude: move to next item
            return Math.max(values[idx] + unboundedKnapsack(values, weights, cap - weights[idx], idx),
                            unboundedKnapsack(values, weights, cap, idx + 1));
        }
    }

    // use dp[] of size [cap + 1] to store max value for bag cap in range: 0 -> cap
    // dp[idx]: max value created when bag cap is idx
    // move from easiest problem(idx:0) to hardest problem(idx:cap)
    public static int unboundedKnapsackTab(int[] values, int[] weights, int cap) {
        int[] dp = new int[cap + 1];
        dp[0] = 0;  // when bag cap: 0, no item can be added -> max value: 0

        // INFINITE supply so we need to use an item's result again and again
        // similar to COIN CHANGE COMBINATION since max value of arrangements will remain same in PERMUTATIONS
        // traverse each item compeletely before moving to next item
        for(int i = 0; i < values.length; i++) {
            for(int j = weights[i]; j < dp.length; j++) { // since item can only be added if bag's cap >= item's wt

                // excluding choice: already stored result created by earlier items at dp[j]
                // including choice: add curr item's value to result stored at dp[curr bag cap - curr item's wt]
                // choose whichever choice creates max value for bag cap: j
                dp[j] = Math.max(dp[j], values[i] + dp[j - weights[i]]); 
            }   
        }

        return dp[cap]; // max value created using infinite supply of items when bag capacity is cap
    }
}
