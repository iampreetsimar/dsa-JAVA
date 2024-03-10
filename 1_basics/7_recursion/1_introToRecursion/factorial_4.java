// 1. You are given a number n.
// 2. You are required to calculate the factorial of the number. Don't change the signature of factorial function.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is.
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Input Format
// A number n

// Output Format
// factorial of n

// Constraints
// 0 <= n <= 10

// Sample Input
// 5

// Sample Output
// 120

import java.util.*;

public class factorial_4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        System.out.println(factorial(n));
    }

    public static int factorial(int n) {
        if(n == 0)
            return 1;

        int factNminusOne = factorial(n - 1);
        int factN = n * factNminusOne;
        return factN;
    }
}
