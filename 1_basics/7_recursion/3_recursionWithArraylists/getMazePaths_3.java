// 1. You are given a number n and a number m representing number of rows and columns in a maze.
// 2. You are standing in the top-left corner and have to reach the bottom-right corner. 
// Only two moves are allowed 'h' (1-step horizontal) and 'v' (1-step vertical).
// 3. Complete the body of getMazePath function - without changing signature - to get the list of all 
// paths that can be used to move from top-left to bottom-right.
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
// 3
// 3

// Sample Output
// [hhvv, hvhv, hvvh, vhhv, vhvh, vvhh]

import java.util.*;

public class getMazePaths_3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();

        // 0 based indexing
        System.out.println(getMazePathsOptimized(0, 0, n, m));
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePathsOptimized(int sr, int sc, int dr, int dc) {
        if(sr == dr - 1 && sc == dc - 1) {
            // BASE CASE
            // zero based indexing
            // return an arraylist with empty string
            // 1 path to destination : just stand
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> allPaths = new ArrayList<>();

        if(sc < dc) {
            // no extra calls where sc == dc
            // add 'h' in front of all the paths returned
            ArrayList<String> horizontalPaths = getMazePathsOptimized(sr, sc + 1, dr, dc);
            for(String path : horizontalPaths) {
                allPaths.add('h' + path);
            }
        }

        if(sr < dr) {
            // no extra calls where sr == dr
            // add 'v' in front of all the paths returned
            ArrayList<String> verticalPaths = getMazePathsOptimized(sr + 1, sc, dr, dc);
            for(String path : verticalPaths) {
                allPaths.add('v' + path);
            }
        }

        return allPaths;
    }
}
