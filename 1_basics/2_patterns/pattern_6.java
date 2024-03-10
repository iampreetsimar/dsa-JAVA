// 1. You are given a number n.
// 2. You've to create a pattern of * and separated by tab as shown in output format.

// Input Format
// A number n

// Output Format
// *  *  *  .  *  *  *
// *  *  .  .  .  *  *
// *  .  .  .  .  .  *
// *  *  .  .  .  *  *
// *  *  *  .  *  *  *

import java.util.*;

public class pattern_6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int space = 1;
        int col = n/2 + 1;

        for(int row = 1; row <= n; row++) {
            for(int st = 1; st <= col; st++) {
                System.out.print("*\t");
            }

            for(int sp = 1; sp <= space; sp++) {
                System.out.print("\t");
            }

            for(int st = 1; st <= col; st++) {
                System.out.print("*\t");
            }

            if(row <= n/2) {
                space += 2; col--;
            } else {
                space -= 2; col++;
            }

            System.out.println();
        }
        
    }
}