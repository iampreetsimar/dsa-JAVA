// Print nth fibonacci term
// eg. n = 6
// Fibonacci sequence : 0 1 1 2 3 5 8 13 . . . 
// Output: 5

import java.util.*;

public class fibonacci_8 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        System.out.println(fibonacci(n));
    }

    public static int fibonacci(int n) {
        if(n == 1)
            return 0;

        if(n == 2)
            return 1;

        int fibN = fibonacci(n - 1) + fibonacci(n - 2);
        return fibN;
    }
}
