// Given a 2D matrix, where 0s represent land and 1s represent water.
// Assume every cell is linked to its north, east, south and west cell.
// Find and count the total number of islands.

// Application of Get Connected Components

// INPUT
// 8 8
// 0 0 1 1 1 1 1 1
// 0 0 1 1 1 1 1 1
// 1 1 1 1 1 1 1 0  
// 1 1 0 0 0 1 1 0
// 1 1 1 1 0 1 1 0
// 1 1 1 1 0 1 1 0
// 1 1 1 1 1 1 1 0
// 1 1 1 1 1 1 1 0

// OUTPUT
// 3

import java.io.*;

public class numberOfIslands_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] input = new int[n][m];
        for(int i = 0; i < input.length; i++) {
            String parts = br.readLine();
            for(int j = 0; j < input[0].length; j++) {
                input[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }

        System.out.println(countIslands(input));
    }

    // visualize graph as a matrix, each cell represents a vtx
    // 1 at vtx represents - water | 0 at vtx represents land
    public static int countIslands(int[][] input) {
        int count = 0;
        boolean[][] visited = new boolean[input.length][input[0].length];
        
        for(int i = 0; i < input.length; i++) {     // similar to gcc in graph adjacency list
            for(int j = 0; j < input[0].length; j++) {  // here check for all cells(vtxs)
                if(input[i][j] == 0 && !visited[i][j]) {  // count component for only land and unvisited vtx 
                    getConnectedComponents(input, i, j, visited);
                    count++;    // after each component call, increase count
                }
            }
        }
        return count;   // return # of land components -> # of islands
    }

    // PROACTIVE Recursion - smart recursion, stupid base case
    // REACTIVE Recursion - stupid recursion, smart base case -> use this
    public static void getConnectedComponents(int[][] input, int i, int j, boolean[][] visited) {
        if(i < 0 || j < 0 || i >= input.length || j >= input[0].length 
           || input[i][j] == 1 || visited[i][j]) {      
            return;  // BASE CASE - out of bounds || curr vtx is water || curr vtx is visited already
        }

        // same concept as HAS PATH - REACTIVE recursive approach
        visited[i][j] = true;   // mark curr vtx as visited -> move to curr vtx's nbrs in NEWS directions
        getConnectedComponents(input, i - 1, j, visited);   // north
        getConnectedComponents(input, i, j + 1, visited);   // east
        getConnectedComponents(input, i, j - 1, visited);   // west
        getConnectedComponents(input, i + 1, j, visited);   // south
    }
}
