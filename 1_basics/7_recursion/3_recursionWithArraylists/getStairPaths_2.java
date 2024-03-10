// 1. You are given a number n representing number of stairs in a staircase.
// 2. You are standing at the bottom of staircase. You are allowed to climb 1 step, 2 steps or 3 steps in one move.
// 3. Complete the body of getStairPaths function - without changing signature - to get the list of all paths that 
// can be used to climb the staircase up. Use sample input and output to take idea about output.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number n

// Output Format
// Contents of the arraylist containing paths as shown in sample output

// Constraints
// 0 <= n <= 10

// Sample Input
// 3

// Sample Output
// [111, 12, 21, 3]

import java.util.*;

public class getStairPaths_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        System.out.println(getStairPaths(n));
        System.out.println(getStairPathsOptimized(n));
    }

    public static ArrayList<String> getStairPaths(int n) {
        if(n == 0) {    // positive base case
            // since there is one way to reach from 0 to 0.
            // add an empty string
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        } else if(n < 0) {      // negative base case
            // since there is no way to reach from -1 or -2 to 0.
            // return empty arraylist
            ArrayList<String> base = new ArrayList<>();
            return base;
        }

        ArrayList<String> paths1 = getStairPaths(n - 1);
        ArrayList<String> paths2 = getStairPaths(n - 2);
        ArrayList<String> paths3 = getStairPaths(n - 3);

        // appending 1 to all paths from n - 1 to 0
        ArrayList<String> allPaths = new ArrayList<>();
        for(String path : paths1) {
            allPaths.add(1 + path);
        }

        // appending 2 to all paths from n - 2 to 0
        for(String path : paths2) {
            allPaths.add(2 + path);
        }

        // appending 3 to all paths from n - 3 to 0
        for(String path : paths3) {
            allPaths.add(3 + path);
        }

        return allPaths;
    }

    public static ArrayList<String> getStairPathsOptimized(int n) {
        if(n == 0) {    // positive base case
            // since there is one way to reach from 0 to 0.
            // add an empty string
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> allPaths = new ArrayList<>();

        // no unnecessary calls to n < 0
        // we can add a check here to overall number of calls will be reduced
        if(n - 1 >= 0) {
            ArrayList<String> paths1 = getStairPaths(n - 1);

            // appending 1 to all paths from n - 1 to 0
            for(String path : paths1) {
                allPaths.add(1 + path);
            }
        }

        if(n - 2 >= 0) {
            ArrayList<String> paths2 = getStairPaths(n - 2);

            // appending 2 to all paths from n - 2 to 0
            for(String path : paths2) {
                allPaths.add(2 + path);
            }
        }

        if(n - 3 >= 0) {
            ArrayList<String> paths3 = getStairPaths(n - 3);

            // appending 3 to all paths from n - 3 to 0
            for(String path : paths3) {
                allPaths.add(3 + path);
            }
        }
        
        return allPaths;
    }
}
