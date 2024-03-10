// 1. You are required to display the prime factorization of a number.
// 2. Take as input a number n.
// 3. Print all its prime factors from smallest to largest.
                               
// Input Format
// n, an integer

// Output Format
// p1  p2  p3  p4.. all prime factors of n

// Constraints
// 2 <= n < 10 ^ 9

// Sample Input
// 1440

// Sample Output
// 2 2 2 2 2 3 3 5

import java.util.*;

public class primeFactorisation_10 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();

        // we don't have to loop till number
        // we can get all the factors till sqrt(num)
        for(int div = 2; div * div <= num; div++) {
            while(num % div == 0) {
                num = num / div;
                System.out.print(div + " ");
            }
        }

        if(num != 1) System.out.println(num); // incase we get a prime factor
    }
}
