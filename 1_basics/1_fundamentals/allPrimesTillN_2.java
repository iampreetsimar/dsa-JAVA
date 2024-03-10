/* 1. You've to print all prime numbers between a range. 
2. Take as input "low", the lower limit of range.
3. Take as input "high", the higher limit of range.
4. For the range print all the primes numbers between low and high (both included).

Constraints
2 <= low < high < 10 ^ 6

Input Format
low 
high

Output Format
n1
n2
.. all primes between low and high (both included) */

import java.util.*;

public class allPrimesTillN_2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int low = scn.nextInt();
        int high = scn.nextInt();

        for(int num = low; num <= high; num++) {
            boolean flag = true;

            // check if num is prime
            for(int div = 2; div * div <= num; div++) {
                if(num % div == 0) {
                    flag = false;
                    break;
                }
            }

            if(flag)
                System.out.println(num);
        }
    }
}
