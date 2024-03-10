// 1. You've to display the digits of a number.
// 2. Take as input "n", the number for which digits have to be displayed.
// 3. Print the digits of the number line-wise.

// Input Format
// "n" where n is any integer.

// Output Format
// d1
// d2
// d3
// ... digits of the number

// Constraints
// 1 <= n < 10^9

// Sample Input
// 65784383

// Sample Output
// 6
// 5
// 7
// 8
// 4
// 3
// 8
// 3
import java.util.*;

public class digitsOfNumber_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int count = 0, temp = n;

        // count digits
        while(temp != 0) {
            count++;
            temp = temp / 10;
        }

        int div = (int)Math.pow(10, count - 1);

        // print digits in order
        while(div != 0) {
            System.out.println(n / div);
            n = n % div;
            div = div / 10;
        }
    }
}
