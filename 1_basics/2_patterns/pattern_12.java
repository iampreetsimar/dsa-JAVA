// 1. You are given a number n.
// 2. You've to create a pattern as shown in output format.

// Input Format
// A number n

// Output Format
// 0
// 1  1
// 2  3   5
// 8  13  21  34

import java.util.*;

public class pattern_12 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int a = 0;
        int b = 1;
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= row; col++) {
                    // Fibonacci series
                    System.out.print(a + "\t");
                    int c = a + b;
                    a = b;
                    b = c;
            }
            
            System.out.println();
        }
    }
}