// 1. You are given an prefix expression. 
// 2. You are required to evaluate it and print its value.
// 2. You are required to convert it to infix and print it. 
// 3. You are required to convert it to postfix and print it. 

// NOTE : Use brackets in infix expression indicating precedence.

// Constraints
// 1. Expression is valid prefix expression
// 2. The only operators used are +, -, *, /. 
// 3. All operands are single digit numbers. 

// Sample Input
// -+2/*6483 -> prefix

// Sample Output
// 2 -> evaluation
// ((2+((6*4)/8))-3) -> infix
// 264*8/+3- -> postfix

import java.util.Scanner;
import java.util.Stack;


public class prefixExpressions_27 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();

        solve(exp);
    }

    public static void solve(String exp) {
        Stack<Integer> evaluateS = new Stack<>();
        Stack<String> infixS = new Stack<>();
        Stack<String> postfixS = new Stack<>();

        // Back Traversal
        for(int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);

            if(Character.isDigit(ch)) {
                // ch is digit -> push to all 3 stacks
                evaluateS.push(ch - '0');       // push int value of ch
                infixS.push(ch + "");
                postfixS.push(ch + "");
            } else if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // if ch is an operator
                evaluatePrefix(evaluateS, ch);
                convertToInfix(infixS, ch);
                convertToPostfix(postfixS, ch);
            }
        }

        System.out.println(evaluateS.peek());   // evaluated result
        System.out.println(infixS.peek());      // infix exp
        System.out.println(postfixS.peek());     // postfix exp
    }

    public static void evaluatePrefix(Stack<Integer> evaluateS, char op) {
        // pop v1, v2 from evaluateS -> push evaluated v1opv2 
        int v1 = evaluateS.pop();
        int v2 = evaluateS.pop();

        if(op == '+') 
            evaluateS.push(v1 + v2);
        else if(op == '-')
            evaluateS.push(v1 - v2);
        else if(op == '*') 
            evaluateS.push(v1 * v2);
        else
            evaluateS.push(v1 / v2);
    }

    public static void convertToInfix(Stack<String> infixS, char op) {  
        // pop v1, v2 from infixS -> push string "(v1 op v2)"
        String v1 = infixS.pop();
        String v2 = infixS.pop();

        infixS.push("(" + v1 + op + v2 + ")");
    }

    public static void convertToPostfix(Stack<String> postfixS, char op) {
        // pop v1, v2 from postfixS -> push string "v1 v2 op"
        String v1 = postfixS.pop();
        String v2 = postfixS.pop();

        postfixS.push(v1 + v2 + op);
    }
}
