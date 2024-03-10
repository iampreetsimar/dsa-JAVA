// 1. You are given a number n.
// 2. You've to write code to print the pattern given in output format below.

// Input Format
// A number n

// Output Format
// .  .  *
// .  .  *  *
// *  *  *  *  *
// .  .  *  *
// .  .  *

import java.util.*;

public class pattern_17 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int space = n/2;
        int col = 1;

        for(int row = 1; row <= n; row++) {
            for(int sp = 1; sp  <= space; sp++) {
                if(row == n/2 + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }

            for(int st = 1; st <= col; st++) {
                System.out.print("*\t");
            }

            if(row <= n/2)
                col++;
            else
                col--;

            System.out.println();
        }
        
    }
}