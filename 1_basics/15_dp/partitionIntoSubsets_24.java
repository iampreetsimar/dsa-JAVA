// Given N: number of elements, k: number of subsets.
// Print number of ways in which these elements can be partitioned into k non empty subsets.

// Eg. n:4, k:3 -> total ways: 6
// 12-3-4
// 1-23-4
// 13-2-4
// 14-2-3
// 1-24-3
// 1-2-34

import java.io.*;

public class partitionIntoSubsets_24 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inp = br.readLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int k = Integer.parseInt(inp[1]);
        
        System.out.println(subsetPartitionCountWays(n, k));
        System.out.println(subsetPartitionCountWaysTab(n, k));
    }

    // n == 0 || k == 0: 0 players or 0 teams available -> 0 ways to partition
    // n < k -> some empty teams will be left -> 0 ways to partition
    // n == k -> 1 player in each team -> 1 way to partition
    // k == 1 -> all players in 1 team -> 1 way to partition
    public static long subsetPartitionCountWays(int n, int k) {
        if(n == 0 || k == 0 || n < k)   // BASE CASES
            return 0;

        if(n == k || k == 1)    // BASE CASES
            return 1;
        
        long kTeams = subsetPartitionCountWays(n - 1, k);   // no of ways for 1->N-1 items to make k teams
        long kMinus1Teams = subsetPartitionCountWays(n - 1, k - 1); // no of ways for 1->N-1 items to make k-1 teams

        return (k * kTeams) +   // nth player changes teams k times + 
                kMinus1Teams;   // nth player makes kth team
    }

    // use dp grid to size [k + 1][n + 1] for teams: 0 -> k and players: 0 -> n
    // dp[i][j] : count of ways to partition j players into i non-empty teams
    public static long subsetPartitionCountWaysTab(int n, int k) {
        if(n == 0 || k == 0 || n < k)   // BASE CASES
            return 0;

        if(n == k || k == 1)    // BASE CASES
         return 1;

        long[][] dp = new long[k + 1][n + 1];

        for(int i = 1; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == 1)  // 1 team -> all players in 1 team -> 1 way to partition
                    dp[i][j] = 1;
                else if(j < i)  // some empty teams will be left -> 0 ways to partition
                    dp[i][j] = 0;
                else if(i == j) // 1 player in each team -> 1 way to partition
                    dp[i][j] = 1;
                else {
                    dp[i][j] = (i * dp[i][j - 1]) + // jth player changes teams from j-1 players for i times + 
                               dp[i - 1][j - 1];  // jth player makes ith team from i-1 teams of j-1 players
                }
            }
        }

        return dp[k][n];    // ways to partition n players/items into k non-empty teams/subsets
    }
}
