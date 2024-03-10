// 1. You've to display the digits of a number in reverse.
// 2. Take as input "n", the number for which digits have to be display in reverse.
// 3. Print the digits of the number line-wise, but in reverse order.

// Input Format
// "n" where n is any integer.

// Output Format
// d1
// d2
// d3
// ... digits of the number in reverse

// Constraints
// 1 <= n < 10^9

// Sample Input
// 65784383

// Sample Output
// 3
// 8
// 3
// 4
// 8
// 7
// 5
// 6

import java.util.*;

public class reverseNumber_6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();

        while(num != 0){
            int rem = num % 10;
            System.out.println(rem);
            num = num / 10;
        }
    }
}
