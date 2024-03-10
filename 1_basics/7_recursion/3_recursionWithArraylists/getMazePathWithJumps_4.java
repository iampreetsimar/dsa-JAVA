// 1. You are given a number n and a number m representing number of rows and columns in a maze.
// 2. You are standing in the top-left corner and have to reach the bottom-right corner. 
// 3. In a single move you are allowed to jump 1 or more steps horizontally (as h1, h2, .. ), 
// or 1 or more steps vertically (as v1, v2, ..) or 1 or more steps diagonally (as d1, d2, ..). 
// 4. Complete the body of getMazePathsWithJumpsOptimized function - without changing signature - 
// to get the list of all paths that can be used to move from top-left to bottom-right.
// Use sample input and output to take idea about output.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number n
// A number m

// Output Format
// Contents of the arraylist containing paths as shown in sample output

// Constraints
// 0 <= n <= 10
// 0 <= m <= 10

// Sample Input
// 2
// 2

// Sample Output
// [h1v1, v1h1, d1]

import java.util.*;

public class getMazePathWithJumps_4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();

        // 0 based indexing
        System.out.println(getMazePathsWithJumpsOptimized(0, 0, n, m));
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePathsWithJumpsOptimized(int sr, int sc, int dr, int dc) {
        if(sr == dr - 1 && sc == dc - 1) {
            // base case
            // 0 based indexing

            // adding empty string element as 1 path from (n - 1, m - 1) to destination i.e., stand there
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> allPaths = new ArrayList<>();

        // horizontal paths
        for(int hJumpSize = 1; hJumpSize < dc - sc; hJumpSize++) {
            // dr = 4, dc = 4
            // hJumpSize for sc = 0 : 1 -> 3
            // hJumpSize for sc = 1 : 1 -> 2
            // hJumpSize for sc = 2 : 1 
            // hJumpSize for sc = 3 : 0 
            // hJumpSize for sc = 4 : Will not reach here

            ArrayList<String> hPaths = getMazePathsWithJumpsOptimized(sr, sc + hJumpSize, dr, dc);

            for(String path : hPaths) {
                allPaths.add("h" + hJumpSize + path);
            }
        }

        // vertical paths
        for(int vJumpSize = 1; vJumpSize < dr - sr; vJumpSize++) {
            // dr = 4, dc = 4
            // vJumpSize for sr = 0 : 1 -> 3
            // vJumpSize for sr = 1 : 1 -> 2
            // vJumpSize for sr = 2 : 1 
            // vJumpSize for sr = 3 : 0 
            // vJumpSize for sr = 4 : Will not reach here

            ArrayList<String> vPaths = getMazePathsWithJumpsOptimized(sr + vJumpSize, sc, dr, dc);

            for(String path : vPaths) {
                allPaths.add("v" + vJumpSize + path);
            }
        }

        // diagonal paths
        for(int dJumpSize = 1; dJumpSize < dc - sc && dJumpSize < dr - sr; dJumpSize++) {
            // dr = 4, dc = 4
            // dJumpSize for sr = 0, sc = 0 : 1 -> 3
            // dJumpSize for sr = 0, sc = 1 : 1 -> 2
            // dJumpSize for sr = 0, sc = 2 : 1
            // dJumpSize for sr = 0, sc = 3 : 0
            // dJumpSize for sr = 0, sc = 4 : Will not reach here

            // dJumpSize for sr = 1, sc = 0 : 1 -> 2
            // dJumpSize for sr = 1, sc = 1 : 1 -> 2
            // dJumpSize for sr = 1, sc = 2 : 1
            // dJumpSize for sr = 1, sc = 3 : 0
            // dJumpSize for sr = 1, sc = 4 : Will not reach here

            // dJumpSize for sr = 2, sc = 0 : 1
            // dJumpSize for sr = 2, sc = 1 : 1
            // dJumpSize for sr = 2, sc = 2 : 1
            // dJumpSize for sr = 2, sc = 3 : 0
            // dJumpSize for sr = 2, sc = 4 : Will not reach here

            // dJumpSize for sr = 3, sc = 0 : 0
            // dJumpSize for sr = 3, sc = 1 : 0
            // dJumpSize for sr = 3, sc = 2 : 0
            // dJumpSize for sr = 3, sc = 3 : 0
            // dJumpSize for sr = 3, sc = 4 : Will not reach here

            // dJumpSize for sr = 4, sc = 0 : Will not reach here
            // dJumpSize for sr = 4, sc = 1 : Will not reach here
            // dJumpSize for sr = 4, sc = 2 : Will not reach here
            // dJumpSize for sr = 4, sc = 3 : Will not reach here
            // dJumpSize for sr = 4, sc = 4 : Will not reach here

            ArrayList<String> dPaths = getMazePathsWithJumpsOptimized(sr + dJumpSize, sc + dJumpSize, dr, dc);

            for(String path : dPaths) {
                allPaths.add("d" + dJumpSize + path);
            }
        }

        return allPaths;
    }
}
