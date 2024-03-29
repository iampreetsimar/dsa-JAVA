// Given a number n, print nth element of fibonacci sequence.
// 0th element of sequence is 0 and 1st element is 1.

// INPUT
// n

// OUTPUT
// nth element of fibonacci sequence

import java.io.*;

public class fibonacci_1{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(recursiveFib(n));
        System.out.println(memoizedFib(n, new int[n + 1]));
        System.out.println(tabulatedFib(n));
        System.out.println(tabulatedFibOptimized(n));
    }

    // TC: O(2^N), SC: O(N) for recursive stack
    public static int recursiveFib(int n) {
        if(n == 0 || n == 1)    // BASE CASE
            return n;
        return recursiveFib(n - 1) + recursiveFib(n - 2);  
    }

    // DP APPROACH 1: MEMOIZATION(use recursive approach with stored results)
    // includes an array to store result for 0 to n inputs -> size of dp[]: n + 1
    // TC:O(N), SC:O(N)
    public static int memoizedFib(int n, int[] dp) {
        if(n == 0 || n == 1)    // BASE CASE
            return n;

        if(dp[n] != 0)   // if result for n already stored in dp[], no need for recursive call
            return dp[n];   // return result from dp[n]

        dp[n] = memoizedFib(n - 1, dp) + memoizedFib(n - 2, dp);    // store result of n in dp[]
        return dp[n];       // return result from dp[n]
    }

    // DP APPROACH 2: TABULATION(use iterative approach with stored results)
    // includes a dp[] to store results
    // TC: O(N), SC: O(N)
    public static int tabulatedFib(int n) {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            if(i == 0 || i == 1) {
                dp[i] = i;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }

    // APPROACH: OPTIMIZED TABULATION
    // don't need to store result for entire range
    // instead can use 3 variables as at a time, only need 2 prev values to compute a result
    // TC: O(N), SC:O(1)
    public static int tabulatedFibOptimized(int n) {
        int fibnm2 = 0, fibnm1 = 1;
        int fibn = 0;
        if(n == 0)
            return fibnm2;
        if(n == 1)
            return fibnm1;
        for(int i = 2; i <= n; i++) {
            fibn = fibnm1 + fibnm2;
            fibnm2 = fibnm1;
            fibnm1 = fibn;
        }
        return fibn;
    }
}