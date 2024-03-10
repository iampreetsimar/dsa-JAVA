// 1. You are given an infix expression. 
// 2. You are required to convert it to postfix and print it. 
// 3. You are required to convert it to prefix and print it. 

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
// 264*8/+3- -> postfix
// -+2/*6483 -> prefix

import java.util.Scanner;
import java.util.Stack;

public class infixConversions_25 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();

        evaluate(exp);
    }

    public static void evaluate(String exp) {
        Stack<Character> operatorS = new Stack<>();
        Stack<String> prefixS = new Stack<>();
        Stack<String> postfixS = new Stack<>();

        // traverse over exp
        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch == '(') {
                // push to operatorS
                operatorS.push(ch);
            } else if(ch == ')') {
                // evaluate till top of operatosS is '('
                while(operatorS.peek() != '(') {
                    // pop operator from operatorS
                    // pop v2 and v1 from postfixS -> push v1v2op to postfixS
                    // pop v2 and v1 from prefixS -> push opv1v2 to prefixS
                    char op = operatorS.pop();
                    convertToPostfix(postfixS, op);
                    convertToPrefix(prefixS, op);
                }

                // pop '(' from operatorS
                operatorS.pop();

            } else if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while(operatorS.size() > 0 && operatorS.peek() != '(' &&
                precedence(operatorS.peek()) >= precedence(ch)) {
                    // precedence(top of operatorS) >= precendence(ch)
                    // pop operator from operatorS
                    // pop v2 and v1 from postfixS -> push v1v2op to postfixS
                    // pop v2 and v1 from prefixS -> push opv1v2 to prefixS
                    char op = operatorS.pop();
                    convertToPostfix(postfixS, op);
                    convertToPrefix(prefixS, op);
                }

                // precedence(top of operatorS) < precendence(ch)
                // direct push ch to operatorS
                operatorS.push(ch);

            } else if((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
                // char is operand
                // push to prefixS and postfixS
                prefixS.push(ch + "");    // need to convert char to string
                postfixS.push(ch + "");
            }
        }

        // operatorS is not empty
        while(operatorS.size() > 0) {
            // pop operator from operatorS
            // pop v2 and v1 from postfixS -> push v1v2op to postfixS
            // pop v2 and v1 from prefixS -> push opv1v2 to prefixS
            char op = operatorS.pop();
            convertToPostfix(postfixS, op);
            convertToPrefix(prefixS, op);
        }

        System.out.println(postfixS.peek());
        System.out.println(prefixS.peek());
    }

    public static void convertToPrefix(Stack<String> prefixS, char op) {
        String v2 = prefixS.pop();
        String v1 = prefixS.pop();
        // push opv1v2
        prefixS.push(op + v1 + v2);
    }

    public static void convertToPostfix(Stack<String> postfixS, char op) {
        String v2 = postfixS.pop();
        String v1 = postfixS.pop();
        // push v1v2op
        postfixS.push(v1 + v2 + op);
    }

    public static int precedence(char operator) {
        if(operator == '+' || operator == '-')
            return 2;

        return 4;   // operator is * or /
    }
}
