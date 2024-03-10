// 1. You are given a string that contains only lowercase and uppercase alphabets. 
// 2. You have to toggle the case of every character of the given string.

// Input Format
// A String

// Output Format
// A String

// Constraints
// 1 <= length of string <= 1000

// Sample Input
// pepCODinG

// Sample Output
// PEPcodINg

import java.util.*;

public class toggleCase_3 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        System.out.println(toggleCase(str));
    }

    public static String toggleCase(String s) {
        // need string builder as need to update string char values
        // more efficient
        StringBuilder sb = new StringBuilder(s);

        for(int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);

            if(ch >= 'a' && ch <= 'z')          // ch is lowercase
                ch = (char)(ch - 'a' + 'A');    // converts to uppercase char
            else if(ch >= 'A' && ch <= 'Z')     // ch is uppercase
                ch = (char)(ch - 'A' + 'a');    // convertes to lowercase char

            sb.setCharAt(i, ch);    // sets new value of char at index i
        }
        return sb.toString();
    }
}
