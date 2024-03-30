// Given number N, representing # of stairs.
// You are on 0th step and are required to climb to the top.
// Also given N numbers, where ith element's value represents - till how far from the step you
// could jump in a single move
// Print the # of minimum moves in which you can climb to the top.
// If there is no path through the staircase, print null.

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
// 4

import java.io.*;

public class climbStairWithMinMoves_4 {
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
        System.out.println(countMinMoves(0, moves));   
        System.out.println(countMinMovesMem(0, moves, new Integer[n + 1]));
        System.out.println(countMinMovesTab(moves));
    }

    public static Integer countMinMoves(int ithStep, int[] moves) {
        if(ithStep > moves.length)  // -ve BASE CASE: no path to dest:n
            return null;    // return null

        if(ithStep == moves.length) // +ve BASE CASE: 1 path to dest:n(stay there)
            return 0;   // but min moves required for the path is 0 -> return 0

        int minMovesCount = Integer.MAX_VALUE;  // identity of min: +infinity
        for(int jump = 1; jump <= moves[ithStep]; jump++) { // loop for all jump possibilities
            Integer currJumpMinMoves = countMinMoves(ithStep + jump, moves);   
            if(currJumpMinMoves != null)   // if there is a path from curr jumped step to dest:n
                minMovesCount = Math.min(minMovesCount, currJumpMinMoves);  // compare min moves from possibilities
        }

        if(minMovesCount != Integer.MAX_VALUE)  // add 1 move for curr ithStep to 
            return minMovesCount + 1;  // min moves required from jumped step to dest:n
        else    // case when no path from ithStep to dest:n
            return null;    // can happen if moves[ithStep]: 0
    } 

    // dp[ithStep] represents min moves required from ithStep to reach dest:n
    // move from larger problem(src:0) to smaller problem(dest:n)
    // result calculated while going down the recursive stack
    public static Integer countMinMovesMem(int ithStep, int[] moves, Integer[] dp) {
        if(ithStep > moves.length)  // -ve BASE CASE
            return null;

        if(ithStep == moves.length) // +ve BASE CASE
            return 0;

        if(dp[ithStep] != null)     // if result for ithStep already computed
            return dp[ithStep]; // return stored result

        int minMovesCount = Integer.MAX_VALUE;
        for(int jump = 1; jump <= moves[ithStep]; jump++) { // loop all jump possibilities
            Integer currJumpMinMoves = countMinMovesMem(ithStep + jump, moves, dp);
            if(currJumpMinMoves != null)
                minMovesCount = Math.min(minMovesCount, currJumpMinMoves);
        }

        // if moves[ithStep]:0 -> no path to dest -> dp[ithStep] is already null, otherwise minMovesCount has value
        if(minMovesCount != Integer.MAX_VALUE)  
            dp[ithStep] = minMovesCount + 1; // store result for ithStep in dp[ithStep] by adding 1 to minMoves

        return dp[ithStep]; // return stored result for ithStep
    }

    // dp[ithStep] represents min moves required from ithStep to reach dest:n
    // move from smaller problem(dest:n) to larger problem(src: 0)
    // dp[0] store minMoves required to reach from 0 to dest
    public static Integer countMinMovesTab(int[] moves) {
        int N = moves.length;
        Integer[] dp = new Integer[N + 1];  // to store 0 -> N
        dp[N] = 0;  // +ve BASE CASE: 1 path with 0 move from dest to dest

        for(int ithStep = N - 1; ithStep >= 0; ithStep--) { // move from n-1 to 0 problem
            if(moves[ithStep] > 0) {    // if jump not possible, dp[ithStep] already null
                int minMovesCount = Integer.MAX_VALUE;  

                // loop all jump possibilities + jump possibility must be in range till N
                for(int jump = 1; jump <= moves[ithStep] && ithStep + jump <= N; jump++) {
                    if(dp[ithStep + jump] != null) {    // if step jumped to has 0 path, it will be null
                        minMovesCount = Math.min(minMovesCount, dp[ithStep + jump]);
                    }   // compare minMoves from all jumped steps
                }

                // if moves[ithStep]:0 -> no path from ithStep to dest 
                // -> dp[ithStep] is already null, otherwise minMovesCount has value
                if(minMovesCount != Integer.MAX_VALUE)
                    dp[ithStep] = minMovesCount + 1; // store result for ithStep in dp[ithStep] by adding 1 to minMoves
            }
        }

        return dp[0];   // return minMoves required to reach dest:n from src:0
    }
}
