// 1. You are given a number x.
// 2. You are given another number n.
// 3. You are required to calculate x raised to the power n. Don't change the signature of power function .

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number x
// A number n

// Output Format
// x raised to the power n

// Constraints
// 1 <= x <= 10
// 0 <= n <= 9

// Sample Input
// 2
// 5

// Sample Output
// 32

import java.util.*;

public class powerLinear_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int x = scn.nextInt();
        int n = scn.nextInt();

        System.out.println(powerLinear(x, n));
    }

    public static int powerLinear(int x, int n) {
        if(n == 0)
            return 1;       // x^0 = 1

        int pow = x * powerLinear(x, n - 1);
        return pow;
    }
}
