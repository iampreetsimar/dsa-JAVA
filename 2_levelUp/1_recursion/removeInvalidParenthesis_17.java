// Given a string which represents an expression having only opening and closing parenthesis.
// Remove minimum number of parenthesis to make the expression valid. 
// If there are multiple answers, print all of them. 

// INPUT
// ()())()

// OUTPUT
// (())()
// ()()()

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;

public class removeInvalidParenthesis_17 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        solve(exp, getMinRemovalsNeeded(exp), new HashSet<String>());
    }

    // BRUTE FORCE and level-options approach
    // levels: min # of invalid parentheses -> decrementing by 1 at each level
    // options: parentheses at each idx of exp
    // initial minRemovalNeeded: min # of invalid parentheses in i/p exp
    public static void solve(String exp, int minRemovalsNeeded, HashSet<String> ans) {
        if(minRemovalsNeeded == 0) {    // BASE CASE
            if(getMinRemovalsNeeded(exp) == 0) {   // min # of invalid parentheses == 0 -> valid exp
                if(!ans.contains(exp)) {    // valid exp is not a duplicate which was found before
                    System.out.println(exp);    // print 
                    ans.add(exp);   // add exp to set -> to prevent duplicates from printing
                }
            }
            return;
        }

        // using String to remove a char from exp
        // for(int i = 0; i < exp.length(); i++) {
        //     String leftHalf = exp.substring(0, i);
        //     String rightHalf = exp.substring(i + 1);
        //     solve(leftHalf + rightHalf, minRemovalsNeeded - 1, ans);
        // }

        // using StringBuilder to remove a char from exp
        StringBuilder sb = new StringBuilder(exp);
        for(int i = 0; i < exp.length(); i++) {
            sb.deleteCharAt(i);
            solve(sb.toString(), minRemovalsNeeded - 1, ans);
            sb.insert(i, exp.charAt(i));
        }
    }

    // returns the minimum number of invalid parentheses required to be removed to make the exp valid
    // use stack
    public static int getMinRemovalsNeeded(String exp) {
        Stack<Character> s = new Stack<>();
        for(int i = 0; i < exp.length(); i++) {
            if(exp.charAt(i) == '(')    // always push
                s.push(exp.charAt(i));  // its ')' pair could be found later in exp
            else {  // ')'
                if(s.isEmpty())   // invalid case
                    s.push(exp.charAt(i));  
                else if(s.peek() == ')')    // invalid case
                    s.push(exp.charAt(i));
                else if(s.peek() == '(')    // pair found -> pop from stack
                    s.pop();
            }
        }
        return s.size();    // parentheses left in stack are min. invalid ones
    }
}
