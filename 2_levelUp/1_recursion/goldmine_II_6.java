// Given N rows, M cols and N * M numbers representing each cell's available gold amount in a goldmine.
// You are allowed to take one step left, right, up or down from your curr position.
// You can't visit a cell with 0 amount of gold and the same cell more than once.
// Identify the path where max amount of gold that can be dug out from the mine if you start and stop
// collecting gold from any position in the mine that has some gold and return max gold amount.

// NOTE: NEED TO FIND PATH WITH MAX GOLD AND NOT MAX CONNECTED COMPONENT SUM!!

// INPUT
// 6 6
// 0 1 4 2 8 2
// 4 3 6 5 0 4
// 1 2 4 1 4 6
// 2 0 7 3 2 2
// 3 1 5 9 2 4
// 2 7 0 8 5 1

// 3 3
// 0 6 0
// 5 8 7
// 0 9 0

// OUTPUT
// 120
// 24

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class goldmine_II_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sizes = br.readLine().split(" ");
        int n = Integer.parseInt(sizes[0]);
        int m = Integer.parseInt(sizes[1]);

        int[][] mine = new int[n][m];

        for(int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                mine[i][j] = Integer.parseInt(parts[j]);
            }
        }

        System.out.println(solve(mine));
    }

    public static int solve(int[][] mine) {
        boolean[][] visited = new boolean[mine.length][mine[0].length]; // to track visited cells
        int maxPathSum = 0;

        for(int i = 0; i < mine.length; i++) {  // checks all cells of mine to get all path configs
            for(int j = 0; j < mine[0].length; j++) {
                if(mine[i][j] != 0) {   // if cell has gold -> start a path from it
                    int pathSum = getMaxPathSum(mine, i, j, visited);
                    if(pathSum > maxPathSum) 
                        maxPathSum = pathSum;
                }
            }
        }
        return maxPathSum;
    }

    public static int getMaxPathSum(int[][] mine, int row, int col, boolean[][] visited) {
        if(row < 0 || col < 0 || row >= mine.length || col >= mine[0].length 
           || mine[row][col] == 0 || visited[row][col]) // BASE CASE
            return 0;   // return 0 as sum

        visited[row][col] = true;   // mark curr cell as visited

        // recursive calls in 4 directions return max path sum from them
        int leftCallSum = getMaxPathSum(mine, row, col - 1, visited);
        int rightCallSum = getMaxPathSum(mine, row, col + 1, visited);
        int upCallSum = getMaxPathSum(mine, row - 1, col, visited);
        int downCallSum = getMaxPathSum(mine, row + 1, col, visited);

        visited[row][col] = false;  // unmark cell from visited while backtracking

        // choose max path sum among 4 directions and add self's gold amount to it and return
        return mine[row][col] + Math.max(leftCallSum, Math.max(rightCallSum, Math.max(upCallSum, downCallSum)));
    }
}
