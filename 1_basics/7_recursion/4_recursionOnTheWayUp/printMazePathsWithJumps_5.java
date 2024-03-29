// 1. You are given a number n and a number m representing number of rows and columns in a maze.
// 2. You are standing in the top-left corner and have to reach the bottom-right corner. 
// 3. In a single move you are allowed to jump 1 or more steps horizontally (as h1, h2, .. ), 
// or 1 or more steps vertically (as v1, v2, ..) or 1 or more steps diagonally (as d1, d2, ..). 
// 4. Complete the body of printMazePath function - without changing signature - to print the 
// list of all paths that can be used to move from top-left to bottom-right.
// Use sample input and output to take idea about output.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number n
// A number m

// Output Format
// Print paths (one path in each line) in order hinted by Sample output

// Constraints
// 0 <= n <= 5
// 0 <= m <= 5

// Sample Input
// 3
// 3

// Sample Output
// h1h1v1v1
// h1h1v2
// h1v1h1v1
// h1v1v1h1
// h1v1d1
// h1v2h1
// h1d1v1
// h2v1v1
// h2v2
// v1h1h1v1
// v1h1v1h1
// v1h1d1
// v1h2v1
// v1v1h1h1
// v1v1h2
// v1d1h1
// v2h1h1
// v2h2
// d1h1v1
// d1v1h1
// d1d1
// d2

import java.util.*;

public class printMazePathsWithJumps_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();

        // 0 based indexing
        printMazePathsWithJumps(0, 0, n, m, "");
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static void printMazePathsWithJumps(int sr, int sc, int dr, int dc, String psf) {
        if(sr == dr - 1 && sc == dc - 1) {
            // BASE CASE
            // since 0 based indexing
            System.err.println(psf);
            return;
        }

        // horizontal paths with jumps
        for(int hJumpSize = 1; hJumpSize < dc - sc; hJumpSize++) {
            // add "h" + hJumpSize to psf
            printMazePathsWithJumps(sr, sc + hJumpSize, dr, dc, psf + "h" + hJumpSize);
        }

        // vertical paths with jumps
        for(int vJumpSize = 1; vJumpSize < dr - sr; vJumpSize++) {
            // add "v" + vJumpSize to psf
            printMazePathsWithJumps(sr + vJumpSize, sc, dr, dc, psf + "v" + vJumpSize);
        }

        // diagonal paths with jumps
        for(int dJumpSize = 1; dJumpSize < dc - sc && dJumpSize < dr - sr; dJumpSize++) {
            // add "d" + dJumpSize to psf
            printMazePathsWithJumps(sr + dJumpSize, sc + dJumpSize, dr, dc, psf + "d" + dJumpSize);
        }
    }
}
