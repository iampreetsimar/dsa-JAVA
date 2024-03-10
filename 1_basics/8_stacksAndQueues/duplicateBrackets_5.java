// 1. You are given a string exp representing an expression.
// 2. Assume that the expression is balanced  i.e. the opening and closing brackets match with each other.
// 3. But, some of the pair of brackets maybe extra/needless. 
// 4. You are required to print true if you detect extra brackets and false otherwise.

// e.g.'
// ((a + b) + (c + d)) -> false
// (a + b) + ((c + d)) -> true

// Input Format
// A string str

// Output Format
// true or false

// Constraints
// 0 <= str.length <= 100

// Sample Input
// (a + b) + ((c + d))

// Sample Output
// true

import java.util.Scanner;
import java.util.Stack;

public class duplicateBrackets_5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();

        System.out.println(isDuplicate(input));
    }

    public static boolean isDuplicate(String input) {
        Stack<Character> s = new Stack<>();

        // traverse through input string
        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // if char is other than ')', push onto stack
            if(ch != ')')
                s.push(ch);
            else {
                // char ch is ')'
                // check top of stack
                // if '(' on top -> no content inside bracket pair -> duplicacy present -> return true
                if(s.peek() == '(')
                    return true;

                // pop until there is a '(' on top of stack
                while(s.peek() != '(')
                    s.pop();

                // pop '(' from stack
                s.pop();
            }
        }

        // traversal complete -> no duplicacy -> return false
        return false;
    }
}
