// 1. You are given a number n.
// 2. You've to create a pattern as shown in output format.

// Input Format
// A number n

// Pascal triangle - using binomial coefficient
// Output Format
// 1
// 1  1
// 1  2  1
// 1  3  3  1
// 1  4  6  4 1


// using formula nCr+1 = nCr * (n - r)/(r + 1) 
import java.util.*;

public class pattern_13 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        for(int row = 0; row < n; row++){

            // initialise rowC0 as 1 always
            int val = 1;
            for(int col = 0; col <= row; col++) {
                    System.out.print(val + "\t");
                    val = val * (row - col)/ (col + 1); 
            }
            
            System.out.println();
        }
    }
}