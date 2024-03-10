// 1. You are given an postfix expression. 
// 2. You are required to evaluate it and print its value.
// 2. You are required to convert it to infix and print it. 
// 3. You are required to convert it to prefix and print it. 

// NOTE : Use brackets in infix expression indicating precedence.

// Constraints
// 1. Expression is valid postfix expression
// 2. The only operators used are +, -, *, /. 
// 3. All operands are single digit numbers. 

// Sample Input
// 264*8/+3- -> postfix


// Sample Output
// 2 -> evaluation
// ((2+((6*4)/8))-3) -> infix
// -+2/*6483 -> prefix

import java.util.Scanner;
import java.util.Stack;

public class postfixExpressions_26 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();

        solve(exp);
    }

    public static void solve(String exp) {
        Stack<Integer> evaluateS = new Stack<>();
        Stack<String> infixS = new Stack<>();
        Stack<String> prefixS = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(Character.isDigit(ch)) {
                // ch is a digit -> push to 3 stacks
                evaluateS.push(ch - '0');   // push int value of ch to evaluate
                infixS.push(ch + "");
                prefixS.push(ch + "");
            } else if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                evaluatePostfix(evaluateS, ch);
                convertToInfix(infixS, ch);
                convertToPrefix(prefixS, ch);
            }
        }

        System.out.println(evaluateS.peek());   // evaluated result
        System.out.println(infixS.peek());      // infix exp
        System.out.println(prefixS.peek());     // prefix exp
    }

    public static void evaluatePostfix(Stack<Integer> evaluateS, char op) {
        // pop v2, v1 from evaluateS -> push evaluated v1opv2 
        int v2 = evaluateS.pop();
        int v1 = evaluateS.pop();

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
        // pop v2, v1 from infixS -> push string "(v1 op v2)"
        String v2 = infixS.pop();
        String v1 = infixS.pop();

        infixS.push("(" + v1 + op + v2 + ")");
    }

    public static void convertToPrefix(Stack<String> prefixS, char op) {
        // pop v2, v1 from prefixS -> push string "op v1 v2"
        String v2 = prefixS.pop();
        String v1 = prefixS.pop();

        prefixS.push(op + v1 + v2);
    }
}
