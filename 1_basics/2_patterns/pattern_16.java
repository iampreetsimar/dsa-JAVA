// 1. You are given a number n.
// 2. You've to write code to print the pattern given in output format below.

// Input Format
// A number n

// Output Format
// 1  .  .  .  .  .  1
// 1  2  .  .  .  2  1
// 1  2  3  .  3  2  1
// 1  2  3  4  3  2  1

import java.util.*;

public class pattern_16 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int space = 2 * n - 3;

        for(int row = 1; row <= n; row++) {
            for(int col = 1; col <= row; col++) {
                System.out.print(col + "\t");
            }

            for(int sp = 1; sp <= space; sp++) {
                System.out.print(".\t");
            }

            int col = row;

            // last row case
            if(row == n)
                col = row - 1;

            while(col >= 1) {
                System.out.print(col + "\t");
                col--;
            }

            space -= 2;
            System.out.println();
        }
    }
}