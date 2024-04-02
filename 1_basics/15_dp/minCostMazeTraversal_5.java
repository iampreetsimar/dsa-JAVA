// Given n rows and m columns of a maze.
// Given n*m numbers representing each cell value of the maze. Each cell value is the cost to enter
// that cell(even top left anf bottom right)
// Starting a top-left cell, reach to bottom-right cell.
// You are allowed to move only 1 step in the right direction or the down direction in one move.
// Traverse through the maze and print the cost of path which is minimum.

// INPUT
// 6 6
// 0 1 4 2 8 2
// 4 3 6 5 0 4
// 1 2 4 1 4 6
// 2 0 7 3 2 2
// 3 1 5 9 2 4
// 2 7 0 8 5 1

// OUTPUT
// 23

import java.io.*;

public class minCostMazeTraversal_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        int[][] maze = new int[n][m];
        for(int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(row[j]);
            }
        }

        System.out.println(minCostPath(maze, 0, 0));
        System.out.println(minCostPathMem(maze, 0, 0, new int[n][m]));
        System.out.println(minCostPathTab(maze));
    }

    public static int minCostPath(int[][] maze, int row, int col) {
        if(row == maze.length || col == maze[0].length) // BASE CASE: out of bounds
            return Integer.MAX_VALUE;   // return identity of min: +infinity

        if(row == maze.length - 1 && col == maze[0].length - 1)     // BASE CASE: dest
            return maze[row][col];  // return cost to enter dest cell

        // minCost: min cost required to reach dest from either bottom or right of cell
        int minCost = Math.min(minCostPath(maze, row + 1, col),  // choose min cost from bottom
                               minCostPath(maze, row, col + 1));    // or right of curr cell

        // add curr cell's cost to minCost - becomes minCost to reach dest from curr cell
        return maze[row][col] + minCost;  // return curr cell's min cost
    }

    // dp grid of same size as maze
    // dp[i][j]: min cost required to reach dest from (i,j)
    // move from hardest problem(src- 0,0) to easiest problem(dest- N-1, M-1)
    public static int minCostPathMem(int[][] maze, int row, int col, int[][] dp) {
        if(row == maze.length || col == maze[0].length)
            return Integer.MAX_VALUE;

        if(row == maze.length - 1 && col == maze[0].length - 1)
            return maze[row][col];

        if(dp[row][col] != 0)   // if result is already stored, return it
            return dp[row][col];

        // otherwise, compute and store in dp[][]
        dp[row][col] = maze[row][col] + Math.min(minCostPathMem(maze, row + 1, col, dp), 
                                                 minCostPathMem(maze, row, col + 1, dp));
        return dp[row][col];
    }

    // dp grid of same size as maze
    // dp[i][j]: min cost required to reach dest from (i,j)
    // move from easiest problem(dest- N-1, M-1) to hardest problem(src- 0,0)
    public static int minCostPathTab(int[][] maze) {
        int[][] dp = new int[maze.length][maze[0].length];

        for(int i = dp.length - 1; i >= 0; i--) {
            for(int j = dp[0].length - 1; j >= 0; j--) {
                if(i == dp.length - 1 && j == dp[0].length - 1) {   // dest
                    dp[i][j] = maze[i][j];  // only cost to enter dest cell
                } else if(i == dp.length - 1) {     // last row - only move allowed is right of cell
                    dp[i][j] = maze[i][j] + dp[i][j + 1];   // add curr cost to min cost from right of cell
                } else if(j == dp[0].length - 1) {  // last col - only move allowed is bottom of cell
                    dp[i][j] = maze[i][j] + dp[i + 1][j];   // add curr cost to min cost from bottom of cell
                } else {    // remaining cells - moves allowed to either right or bottom of cell
                    dp[i][j] = maze[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
                }   // add curr cost to min from min cost from either bottom or right of cell
            }
        }   
        return dp[0][0];    // min cost from src to dest
    }
}
