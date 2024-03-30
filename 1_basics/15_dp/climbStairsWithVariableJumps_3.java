// Given number N, representing # of stairs.
// You are on 0th step and are required to climb to the top.
// Also given N numbers, where ith element's value represents - till how far from the step you
// could jump in a single move
// Print the # of different paths via which you can climb to the top.

// INPUT
// 10
// 3
// 3
// 0
// 2
// 1
// 2
// 4
// 2
// 0
// 0

// OUTPUT
// 5

import java.io.*;

public class climbStairsWithVariableJumps_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] moves = new int[n];
        for(int i = 0; i < n; i++) {
            moves[i] = Integer.parseInt(br.readLine());
        }

        // src: 0, dest: n
        // moves[]: stores max jump allowed at ithStep for (0->n-1) thSteps
        // dp[] of size n+1 to store results for 0->N steps
        System.out.println(countJumpPaths(0, moves));   
        System.out.println(countJumpPathsMem(0, moves, new int[n + 1]));
        System.out.println(countJumpPathsTab(moves));
    }

    public static int countJumpPaths(int ithStep, int[] moves) {
        if(ithStep > moves.length)    // -ve BASE Case: 0 path to reach dest:n
            return 0;

        if(ithStep == moves.length)
            return 1;   // +ve BASE CASE: 1 path to reach dest:n

        int pathCount = 0;
        for(int jump = 1; jump <= moves[ithStep]; jump++) { // loop all jump possibilities
            pathCount += countJumpPaths(ithStep + jump, moves); // add total paths
        }

        return pathCount;   // return # of paths to reach from ithStep to dest:n
    }

    // dp[ithStep]: # of paths to reach from ithStep to dest:n
    // moves from hardest problem(src:0) to easiest problem(dest:n)
    public static int countJumpPathsMem(int ithStep, int[] moves, int[] dp) {
        if(ithStep > moves.length)  // -ve BASE CASE
            return 0;

        if(ithStep == moves.length) // +ve BASE CASE
            return 1;

        if(dp[ithStep] != 0)    // if # of paths already stored in dp[], return stored result
            return dp[ithStep];

        for(int jump = 1; jump <= moves[ithStep]; jump++) { // loop all jump possibilities
            dp[ithStep] += countJumpPathsMem(ithStep + jump, moves, dp);    
        }   // store total # of paths from ithStep to dest:n in dp[ithStep]
        return dp[ithStep];     // return stored result
    }

    // dp[ithStep]: # of paths to reach from ithStep to dest:n
    // move from easiest problem(dest:n) to hardest problem(src:0)
    // dp[0]: stores all paths to reach from src:0 to dest:n
    public static int countJumpPathsTab(int[] moves) {
        int n = moves.length;
        int[] dp = new int[n + 1];  
        dp[n] = 1;  // BASE CASE: 1 path to reach from dest:n to dest:n

        for(int ithStep = n - 1; ithStep >= 0; ithStep--) { // move from n-1 to 0 problem
            for(int jump = 1; jump <= moves[ithStep] && ithStep + jump <= n; jump++) {
                // loop all jump possiblities from ithStep 
                // all possibilities in range till dest:n
                dp[ithStep] += dp[ithStep + jump];  // add all jump possibilities step paths
            }
        }
        return dp[0];   // return # of paths from src:0 to dest:n
    } 
}