// Given two numbers and their base, multiply them(num2 - num1) and print the result in same base. 
// NOTE: num2 > num1

// INPUT 
// 234
// 76
// 8
// -1

// OUTPUT
// 22710

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class anyBaseMultiplication_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num1 = Integer.parseInt(br.readLine());
        while(num1 != -1) {
            int num2 = Integer.parseInt(br.readLine());
            int base = Integer.parseInt(br.readLine());
            System.out.println("Result: " + anyBaseMultiplication(num1, num2, base));
            num1 = Integer.parseInt(br.readLine());
        }
    }

    public static int anyBaseAddition(int num1, int num2, int base) {
        int res = 0, carry = 0, mul = 1;
        while(num1 > 0 || num2 > 0 || carry > 0) {
            int dig1 = num1 % 10;
            num1 /= 10;
            int dig2 = num2 % 10;
            num2 /= 10;

            int sum = dig1 + dig2 + carry;
            carry = sum / base;
            res += ((sum % base) * mul);
            mul *= 10;
        }
        return res;
    }

    public static int anyBaseMultiplication(int num1, int num2, int base) {
        int res = 0, mul = 1;
        while(num2 > 0) {
            int prod = multiplyByDigit(num1, num2 % 10, base);
            res = anyBaseAddition(res, prod * mul, base);
            mul *= 10;
            num2 /= 10;
        }
        return res;
    }

    public static int multiplyByDigit(int num, int dig, int base) {
        int res = 0, mul = 1, carry = 0;
        while(num > 0 || carry > 0) {
            int prod = ((num % 10) * dig) + carry;
            carry = prod / base;
            res += ((prod % base) * mul);
            num /= 10;
            mul *= 10;
        }
        return res;
    }
}
