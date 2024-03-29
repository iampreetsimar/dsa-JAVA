// Given a number n, representing # of stairs.
// You are on 0th step and are required to climb to the top.
// In one move, you're allowed to climb 1, 2 or 3 stairs.
// Print the # of different paths via which you can climb to the top.

// INPUT
// 4

// OUTPUT
// 7

import java.io.*;

public class climbStairs_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(recursiveCountPaths(n));
        System.out.println(memoizedCountPaths(n, new int[n + 1]));
        System.out.println(tabulatedCountPaths(n));
    }

    public static int recursiveCountPaths(int n) {
        if(n <= 0) {    // BASE CASES
            if(n == 0)      // +ve base case: 1 path to reach 0 from 0
                return 1;   // just stay there
            return 0;   // -ve base case
        }
        return recursiveCountPaths(n - 1) + recursiveCountPaths(n - 2) + recursiveCountPaths(n - 3);
    }

    public static int memoizedCountPaths(int n, int[] dp) {
        if(n <= 0) {
            if(n == 0)
                return 1;
            return 0;
        }

        if(dp[n] != 0)  // use dp[] of size n + 1 tp store n results
            return dp[n];   // if dp[curr n] present -> use result instead of recursive calls

        dp[n] = memoizedCountPaths(n - 1, dp) + memoizedCountPaths(n - 2, dp) 
                   + memoizedCountPaths(n - 3, dp);     // if first call for curr n, store result in dp[]
        return dp[n];   // return stored result from dp[curr n]
    }

    public static int tabulatedCountPaths(int n) {  // ITERATIVE approach
        int[] dp = new int[n + 1];  // dp[i] represents # of paths from i to 0
        for(int i = 0; i <= n; i++) {   // 0 is the smallest problem and n is the largest
            if(i == 0)
                dp[i] = 1;      // 1 path to reach from 0 to 0
            else if(i == 1)     // cannot make 2 and 3 steps jump from 1 to reach 0.
                dp[i] = dp[i - 1];   // # of paths to reach 1 to 0 via 0
            else if(i == 2)     // cannot make 3 steps jump from 2 to reach 0
                dp[i] = dp[i - 1] + dp[i - 2];  // # of paths to reach from 2 to 0 via 1 and via 0
            else    // # of the paths to reach from i to 0 via (i - 1), (i - 2) and (i - 3)
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];  
        }
        return dp[n];       // total # of paths to reach from N to 0
    }
}
