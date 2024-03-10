// 1. You are given a number n.
// 2. You've to create a pattern of * and separated by tab as shown in output format.

// Input Format
// A number n

// Output Format
// .  .  *  .  .
// .  *  .  *  .
// *  .  .  .  *
// .  *  .  *  .
// .  .  *  .  . 

import java.util.*;

public class pattern_10 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++) {
                if(row - col == n/2 || col - row == n/2 || row + col == n - n/2 +1 || row + col == n + n/2 + 1)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            
            System.out.println();
        }
    }
}