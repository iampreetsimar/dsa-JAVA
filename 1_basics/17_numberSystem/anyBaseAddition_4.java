// Given two numbers and their base, add them and print the result in same base. 

// INPUT 
// 236
// 754
// 8
// -1

// OUTPUT
// 1212

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class anyBaseAddition_4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num1 = Integer.parseInt(br.readLine());
        while(num1 != -1) {
            int num2 = Integer.parseInt(br.readLine());
            int base = Integer.parseInt(br.readLine());
            System.out.println("Result: " + anyBaseAddition(num1, num2, base));
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
}
