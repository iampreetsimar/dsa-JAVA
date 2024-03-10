// 1. You're given a pattern of upto 8 length characters 'i' and 'd'. 
// 2. 'd' stands for decreasing and 'i' stands for increasing. 
// 3. You have to print the smallest number, using the digits 1-9 only without repitition, such that
//    the digit decreases following a 'd' and increases following an 'i'.

// eg:
// d -> 21
// i -> 12
// ddd -> 4321
// iii -> 1234
// dddiddd -> 43218765
// iiddd -> 126543

// Input Format
// Input is managed for you 

// Output Format
// Smallest sequence of digits(1-9) without duplicacy and following the pattern

// Constraints
// 0 < str.length <= 8
// str contains only 'd' and 'i'

// Sample Input
// dddiddd

// Sample Output
// 43218765

import java.util.*;

public class smallestNumFollowingPattern_16 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        solve(str);
    }

    // prints smallest number using digits 1-9 without repitition
    // and following pattern 
    public static void solve(String str) {
        Stack<Integer> s = new Stack<>();
        int digit = 1;

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch == 'd') {
                // push digit to stack and increment digit
                s.push(digit);
                digit++;
            } else {
                // push digit to stack and increment digit
                s.push(digit);
                digit++;

                // Pop from stack and print num before 'i' char
                while(s.size() > 0) {
                    System.out.print(s.pop());
                }
            }
        }

        // Push last digit to stack as well
        // eg. for str.length = 1, number should have 2 digits
        s.push(digit);
        while(s.size() > 0) {
            System.out.print(s.pop());
        } 
        System.out.println();
    }
}
