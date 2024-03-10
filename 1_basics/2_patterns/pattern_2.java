// 1. You are given a number n.
// 2. You've to create a pattern of * and separated by tab as shown in output format.

// Input Format
// A number n

// Output Format
// ... n times
// * * * * *
// * * * *
// * * *
// * *
// *

import java.util.*;

public class pattern_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        for(int row = n; row >= 1; row--) {
            for(int col = 1; col <= row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
