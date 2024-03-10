// 1. You are given a number n.
// 2. You've to create a pattern of * and separated by tab as shown in output format.

// Input Format
// A number n

// Output Format
// . . * . .
// . * * * .
// * * * * *
// . * * * .
// . . * . .

import java.util.*;

public class pattern_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int space = n/2; 
        int col = 1;

        for(int row = 1; row <= n; row++) {
            for(int sp = 1; sp <= space; sp++) {
                System.out.print("\t");
            }

            for(int st = 1; st <= col; st++) {
                System.out.print("*\t");
            }

            if(row <= n/2) {
                space--; col += 2;
            } else {
                space++; col -= 2;
            }

            System.out.println();
        }
    }
}
