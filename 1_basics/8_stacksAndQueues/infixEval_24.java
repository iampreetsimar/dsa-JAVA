// 1. You are given an infix expression. 
// 2. You are required to evaluate and print its value. 

// Constraints
// 1. Expression is balanced
// 2. The only operators used are +, -, *, /. 
// 3. Opening and closing brackets - () - are used to impact precedence of operations. 
// 4. + and - have equal precedence which is less than * and / precedence. * and / also have equal precedence. 
// 5. In two operators of equal precedence, give preference to the one of left.
// 6. All operands are single digit numbers. 

// Sample Input
// 2 + 6 * 4 / 8 - 3

// Sample Output
// 2

import java.util.Scanner;
import java.util.Stack;

public class infixEval_24 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();

        evalExpression(exp);
    }

    public static void evalExpression(String exp) {
        Stack<Integer> operandS = new Stack<>();
        Stack<Character> operatorS = new Stack<>();

        // Traversal of exp
        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch == '(') {
                // ch is '(' -> push to operatorS
                operatorS.push(ch);
                
            } else if(Character.isDigit(ch)) {
                // ch is a digit -> convert ch to digit -> push to operandS
                operandS.push(ch - '0');
                
            } else if(ch == ')') {
                // ch is ')' -> Loop till you get '(' -> evaluate for exp b/w ()
                // -> push evaluated result to operandS -> pop '('
                while(operatorS.peek() != '(') {
                    char operator = operatorS.pop();
                    int v2 = operandS.pop();
                    int v1 = operandS.pop();

                    evaluate(operandS, v1, v2, operator);
                }

                // pop '('
                operatorS.pop();

            } else if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // ch is +, -, *, /

                // higher precendence in operatorS top -> evaluate first
                while(operatorS.size() > 0 && operatorS.peek() != '(' &&
                precedence(operatorS.peek()) >= precedence(ch)) {
                    char operator = operatorS.pop();
                    int v2 = operandS.pop();
                    int v1 = operandS.pop();

                    evaluate(operandS, v1, v2, operator);
                }

                // lower precedence in operatorS top ||
                // operatorS empty ||
                // operatorS top is '('
                operatorS.push(ch);
            }
        }

        // operatorS still not empty -> evaluate remaining exp
        while(operatorS.size() > 0) {
            char operator = operatorS.pop();
            int v2 = operandS.pop();
            int v1 = operandS.pop();

            evaluate(operandS, v1, v2, operator);
        }

        // final evaluated result
        System.out.println(operandS.peek());
    }

    public static int precedence(char operator) {
        if(operator == '+' || operator == '-')
            return 2;
        
        return 4;   // operator is * or /
    }

    public static void evaluate(Stack<Integer> operandS, int v1, int v2, char operator) {
        if(operator == '+') 
            operandS.push(v1 + v2);
        else if(operator == '-') 
            operandS.push(v1 - v2);
        else if(operator == '*')
            operandS.push(v1 * v2);
        else 
            operandS.push(v1 / v2);
    }
}
