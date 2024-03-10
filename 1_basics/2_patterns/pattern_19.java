// 1. You are given a number n.
// 2. You've to write code to print the pattern given in output format below.

// Input Format
// A number n

// Output Format
// *  *  *  .  *
// .  .  *  .  *
// *  *  *  *  *
// *  .  *  .  .
// *  .  *  *  *

import java.util.*;

public class pattern_19 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        for(int r = 1; r <= n; r++) {
            for(int c = 1; c <= n; c++) {
                if(r == 1) {
                    if(c == n || c <= n/2 + 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else if(r <= n/2) {
                    if(c ==n || c == n/2 + 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else if(r == n/2 + 1) {
                    System.out.print("*\t");
                } else if(r < n){
                    if(c == 1 || c == n/2 + 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                } else {
                    if(c == 1 || c >= n/2 + 1)
                        System.out.print("*\t");
                    else
                        System.out.print("\t");
                }
            }

            System.out.println();
        }

            
        
    }
}