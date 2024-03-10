// 1. You are given a number n.
// 2. You've to write code to print the pattern given in output format below.

// Input Format
// A number n

// Output Format
// .  .  1
// .  2  3  2
// 3  4  5  4  3
// .  2  3  2 
// .  .  1

import java.util.*;

public class pattern_15 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int space = n/2; 
        int col = 1;
        int val = 1;

        for(int row = 1; row <= n; row++) {

            // spacing
            for(int sp = 1; sp <= space; sp++) {
                System.out.print("\t");
            }

            int fv = val;

            for(int st = 1; st <= col; st++) {
                System.out.print(fv + "\t");

                // increases value
                if(st <= col/2)
                    fv++;
                else    
                    fv--;   // decreases value after half no of columns
            }

            if(row <= n/2) {
                space--;
                col += 2;
                val++;
            } else {
                space++;
                col -= 2;
                val--;
            }

            System.out.println();
        }
    }
}