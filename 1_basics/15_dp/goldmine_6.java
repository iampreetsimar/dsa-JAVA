// Given n rows and m columns of a mine.
// Given n*m numbers representing each cell value of the mine. Each cell value is the 
// amount of gold available that can be in that cell.
// You are standing in front of the left wall, and are supposed to dig till the right wall.
// You can start from any row of the left wall.
// Your are allowed to move - 1 cell right-up, or 1 cell right, or 1 cell right down.
// Print the max amount of gold that can be dug from the mine.

// INPUT
// 6 6
// 0 1 4 2 8 2
// 4 3 6 5 0 4
// 1 2 4 1 4 6
// 2 0 7 3 2 2
// 3 1 5 9 2 4
// 2 7 0 8 5 1

// OUTPUT
// 33

import java.io.*;

public class goldmine_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        int[][] mine = new int[n][m];
        for(int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                mine[i][j] = Integer.parseInt(row[j]);
            }
        }

        System.out.println(maxGoldAmount(mine));
        System.out.println(maxGoldAmountMem(mine));
        System.out.println(maxGoldAmountTab(mine));
    }

    public static int maxGoldAmount(int[][] mine) {
        int maxGoldFromMine = Integer.MIN_VALUE;    // identity of max: +infinity
        for(int i = 0; i < mine.length; i++) {  // starting point: all rows of left wall
            maxGoldFromMine = Math.max(maxGoldFromMine, getGoldAmount(mine, i, 0));
        }   // compare max from all rows of left wall
        return maxGoldFromMine;
    }

    public static int getGoldAmount(int[][] mine, int row, int col) {
        if(row < 0 || row == mine.length || col == mine[0].length)  // BASE CASE: out of bounds
            return Integer.MIN_VALUE;   // return identity of max: +infinity

        if(col == mine[0].length - 1)   // BASE CASE: right wall: last col
            return mine[row][col];  // return gold found at last col's cell

        int maxGold = Math.max(getGoldAmount(mine, row - 1, col + 1),  // gold from right up
                               Math.max(getGoldAmount(mine, row, col + 1),  // gold from right
                                        getGoldAmount(mine, row + 1, col + 1)));  // gold from right down
        // add curr cell's gold to max gold from next cell to right wall
        return mine[row][col] + maxGold;   // return max gold from curr cell to right wall
    }

    // dp grid of same size as mine
    // dp[i][j]: max gold found from (i,j) to right wall
    // dp[i][0]: max gold from all rows of left wall to right wall
    // move from hardest problem(src: dp[i][0] - left wall) to easiest problem(dest: dp[i][last col] - right wall)
    public static int maxGoldAmountMem(int[][] mine) {
        int maxGoldFromMine = Integer.MIN_VALUE;
        int[][] dp = new int[mine.length][mine[0].length];  
        for(int i = 0; i < mine.length; i++) {
            maxGoldFromMine = Math.max(maxGoldFromMine, getGoldAmountMem(mine, i, 0, dp));
        }
        return maxGoldFromMine;
    }

    public static int getGoldAmountMem(int[][] mine, int row, int col, int[][] dp) {
        if(row < 0 || row == mine.length || col == mine[0].length)
            return Integer.MIN_VALUE;

        if(col == mine[0].length - 1)
            return mine[row][col];

        if(dp[row][col] != 0)   // if result stored in dp[][] already, return stored result
            return dp[row][col];

        int maxGold = Math.max(getGoldAmountMem(mine, row - 1, col + 1, dp), 
                               Math.max(getGoldAmountMem(mine, row, col + 1, dp), 
                                        getGoldAmountMem(mine, row + 1, col + 1, dp)));
        
        dp[row][col] = mine[row][col] + maxGold;    // store computed result in dp
        return dp[row][col];    // return stored result for curr cell
    }

    // dp grid of same size as mine
    // dp[i][j]: max gold found from (i,j) to right wall
    // dp[i][0]: max gold from all rows of left wall to right wall
    // move from easiest problem(dest: dp[i][last col] - right wall) to hardest problem(src: dp[i][0] - left wall)
    public static int maxGoldAmountTab(int[][] mine) {
        int[][] dp = new int[mine.length][mine[0].length];
        for(int col = mine[0].length - 1; col >= 0; col--) { // move from right to left wall
            for(int row = 0; row < mine.length; row++) {    // and check for all rows
                if(col == mine[0].length - 1) { // last col: no move allowed
                    dp[row][col] = mine[row][col];  // max gold: gold at curr cell
                } else if(row == 0) {   // first row: 1 max but 2 moves allowed - right or right down
                    dp[row][col] = mine[row][col] + // curr cell's gold
                                   Math.max(dp[row][col + 1], // max gold from right
                                            dp[row + 1][col + 1]); // max gold from right down
                } else if(row == mine.length - 1) { // last row: 1 max but 2 moves allowed - right or right up
                    dp[row][col] = mine[row][col] +     // curr cell's gold  
                                   Math.max(dp[row - 1][col + 1], // max gold from right up
                                            dp[row][col + 1]); // max gold from right
                } else {  // remaining cells: 1 max but 3 moves allowed - right up or right or right down
                    dp[row][col] = mine[row][col] + // curr cell's gold  
                                   Math.max(dp[row - 1][col + 1], // max gold from right up
                                   Math.max(dp[row][col + 1], // max gold from right
                                   dp[row + 1][col + 1]));  // max gold from right down
                }
            }
        }

        int maxGoldFromMine = Integer.MIN_VALUE;    
        for(int i = 0; i < mine.length; i++) {  // compare max gold at left wall
            maxGoldFromMine = Math.max(maxGoldFromMine, dp[i][0]);
        }

        return maxGoldFromMine; // return max gold from left to right wall
    }
}
