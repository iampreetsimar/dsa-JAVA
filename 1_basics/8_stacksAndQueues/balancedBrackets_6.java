// 1. You are given a string exp representing an expression.
// 2. You are required to check if the expression is balanced i.e. closing brackets and opening brackets match up well.

// e.g.
// [(a + b) + {(c + d) * (e / f)}] -> true
// [(a + b) + {(c + d) * (e / f)]} -> false
// [(a + b) + {(c + d) * (e / f)} -> false
// [(a + b) + {(c + d) * (e / f)}]) -> false

// Input Format
// A string str

// Output Format
// true or false

// Constraints
// 0 <= str.length <= 100

// Sample Input
// [(a + b) + {(c + d) * (e / f)}]

// Sample Output
// true

import java.util.Scanner;
import java.util.Stack;

public class balancedBrackets_6 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();

        System.out.println(isBalanced(exp));
    }

    public static boolean isBalanced(String exp) {
        Stack<Character> s = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') {
                // only push opening brackets to stack
                s.push(ch);
            } else if(ch == ')' || ch == '}' || ch == ']') {
                // if exp ch is any closing bracket

                // check if stack is empty -> # CLOSING Brackets > # OPENING Brackets
                // return false
                if(s.isEmpty()) return false;

                // BRACKET MISMATCH CASE
                // return false
                if((ch == ')' && s.peek() != '(') || 
                   (ch == '}' && s.peek() != '{') ||
                   (ch == ']' && s.peek() != '['))
                    return false;

                // BRACKET MATCHED
                // pop matched bracket from stack
                s.pop(); 
            }
        }

        // Traversal Complete
        
        // if stack is not empty -> # OPENING Brackets > # CLOSING Brackets
        // return false
        if(!s.isEmpty())    return false;

        // All negative cases has been checked
        // exp has BALANCED brackets -> return true
        return true;
    }
}
