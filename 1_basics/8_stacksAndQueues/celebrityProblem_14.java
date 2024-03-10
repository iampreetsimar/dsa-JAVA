// 1. You are given a number n, representing the number of people in a party.
// 2. You are given n strings of n length containing 0's and 1's
// 3. If there is a '1' in ith row, jth spot, then person i knows about person j.
// 4. A celebrity is defined as somebody who knows no other person than himself but everybody else knows him.
// 5. If there is a celebrity print it's index otherwise print "none".

// Note -> There can be only one celebrity. Think why?

                               
// Input Format
// Input is managed for you  

// Output Format
// Index of celebrity or none

// Constraints
// 1 <= n <= 10^4
// e1, e2, .. n * n elements belongs to the set (0, 1)

// Sample Input
// 4
// 0000
// 1011
// 1101
// 1110

// Sample Output
// 0

import java.io.*;
import java.util.*;

public class celebrityProblem_14 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        findCelebrity(arr);
    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"

        Stack<Integer> s = new Stack<>();

        // push all persons' idx to stack,0 -> arr.length - 1
        for(int i = 0; i < arr.length; i++) {
            s.push(i);
        }

        while(s.size() > 1) {
            // pop till only 1 element remains
            
            int p1 = s.pop();
            int p2 = s.pop();

            if(arr[p1][p2] == 1) {
                // p1 knows p2 -> p1 is not a celebrity -> push back p2
                s.push(p2);
            } else {
                // p1 does not know p2 -> p2 is not a celebrity -> push back p1
                s.push(p1);
            }
        }

        // Only 1 person who is a possible celebrity remaining in stack
        int pos = s.pop();

        // Check row and col to confirm if pos is a celebrity
        for(int i = 0; i < arr.length; i++) {
            if(i != pos) {
                if(arr[i][pos] == 0 || arr[pos][i] == 1) {
                    // arr[i][pos] == 0 -> checks col of pos except at pos row
                    // arr[pos][i] == 1 -> checks row of pos except at pos col

                    // pos is not a celebrity
                    System.out.println("none");
                    return;
                }
            }
        }

        // print idx of confirmed celebrity
        System.out.println(pos);
    }
}
