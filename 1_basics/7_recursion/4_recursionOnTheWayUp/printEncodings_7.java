// 1. You are given a string str of digits. (will never start with a 0)
// 2. You are required to encode the str as per following rules
//     1 -> a
//     2 -> b
//     3 -> c
//     ..
//     25 -> y
//     26 -> z

// 3. Complete the body of printEncodings function - without changing signature - to calculate and print all encodings of str.
// Use the input-output below to get more understanding on what is required
// 123 -> abc, aw, lc
// 993 -> iic
// 013 -> Invalid input. A string starting with 0 will not be passed.
// 103 -> jc
// 303 -> No output possible. But such a string maybe passed. In this case print nothing.

// Note -> The online judge can't force you to write the function recursively but that is what the spirit of question is. 
// Write recursive and not iterative logic. The purpose of the question is to aid learning recursion and not test you.

// Constraints
// 0 <= str.length <= 10

// Sample Input
// 655196

// Sample Output
// feeaif
// feesf

import java.util.*;

public class printEncodings_7 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        printEncodings(str, "");
    }

    public static void printEncodings(String str, String asf) {
        if(str.length() == 0) {
            // empty string
            System.out.println(asf);
            return;
        } else if(str.length() == 1) {
            // str length is 1 -> digit in str can be 0 or something else
            char ch = str.charAt(0);

            if(ch == '0') return;   // invalid case
            else {
                // convert digit char to alphabet
                int chVal = ch - '0';       // gives actual ch value in int
                char encodedCh = (char)('a' + chVal - 1);       // converts int value to encoded alphabet
                // eg. 'a' + 3 -> ASCII val of 'd' => 'a' + 3 - 1 -> ASCII val of 'c'

                System.out.println(asf + encodedCh);
                return;
            }
        } else {
            // str length is 2 or more
            // 2 CASES - 
            // -> check char at idx 0
            // -> check char  at idx 0,1 together if their int value is <= 26

            // check char at idx 0
            char ch = str.charAt(0);

            // ch can be 0 or something else
            if(ch == '0')   return;     // invalid case
            else {
                // convert digit char to alphabet
                int chVal = ch - '0';       // gives actual ch value in int
                char encodedCh = (char)('a' + chVal - 1);       // converts int value to encoded alphabet

                String ros = str.substring(1);
                printEncodings(ros, asf + encodedCh);
            }

            // check char at idx 0, 1
            String chAt12 = str.substring(0, 2);
            
            // chAt12 can be > 26
            int chAt12Val = Integer.parseInt(chAt12);    // gives actual chAt12 value in int

            if(chAt12Val <= 26) {
                // convert digits to alphabet
                char encodedChAt12 = (char)('a' + chAt12Val - 1);   // converts int value to encoded alphabet

                String ros12 = str.substring(2);
                printEncodings(ros12, asf + encodedChAt12);
            }
        }
    }
}
