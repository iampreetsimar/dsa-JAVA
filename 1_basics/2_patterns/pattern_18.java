// 1. You are given a number n.
// 2. You've to write code to print the pattern given in output format below.

// Input Format
// A number n

// Output Format
// *  *  *  *  *  *  *
// .  *  .  .  .  *
// .  .  *  .  *
// .  .  .  *
// .  .  *  *  *
// .  *  *  *  *  *
// *  *  *  *  *  *  *

import java.util.*;

public class pattern_18 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int space = 0;
        int col = n;

        for(int row = 1; row <= n; row++) {
            for(int sp = 1; sp <= space; sp++) {
                System.out.print(".\t");
            }

            for(int st = 1; st <= col; st++) {
                if(row > 1 && row <= n/2 && st > 1 && st < col)
                    System.out.print(".\t");
                else    
                    System.out.print("*\t");
            }

            if(row <= n/2) {
                space++;
                col -= 2;
            } else {
                space--;
                col += 2;
            }

            System.out.println();
        }
    }
}