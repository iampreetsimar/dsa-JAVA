// 1. You are given two numbers n and k. You are required to rotate n, k times to the right. 
// If k is positive, rotate to the right i.e. remove rightmost digit and make it leftmost. 
// Do the reverse for negative value of k. Also k can have an absolute value larger than number of digits in n.
// 2. Take as input n and k.
// 3. Print the rotated number.
// 4. Note - Assume that the number of rotations will not cause leading 0's in the result. e.g. such an input will not be given
//    n = 12340056
//    k = 3
//    r = 05612340

// Input Format
// "n" where n is any integer.
// "K" where k is any integer.

// Output Format
// "r", the rotated number

// Constraints
// 1 <= n < 10^9
// -10^9 < k < 10^9

// Sample Input
// 562984
// 2

// Sample Output
// 845629

import java.util.*;

public class rotateNumber_8 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int k = scn.nextInt();

        int temp = num, cd = 0;

        // counting digits
        while(temp != 0) {
            temp /= 10;
            cd++;
        }

        // balance k
        k = k % cd;

        // if k < 0
        if(k < 0) k += cd;

        int div = (int)Math.pow(10, k);
        int mult = (int)Math.pow(10, cd - k);

        int a = num / div;
        int b = num % div;
        int result = b * mult + a;
        System.out.println(result);
    }
}
