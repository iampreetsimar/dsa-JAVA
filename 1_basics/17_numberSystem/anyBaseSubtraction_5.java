// Given two numbers and their base, subtract them(num2 - num1) and print the result in same base. 
// NOTE: num2 > num1

// INPUT 
// 1212
// 236
// 8
// -1

// OUTPUT
// 754

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class anyBaseSubtraction_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num1 = Integer.parseInt(br.readLine());
        while(num1 != -1) {
            int num2 = Integer.parseInt(br.readLine());
            int base = Integer.parseInt(br.readLine());
            System.out.println("Result: " + anyBaseSubtraction(num1, num2, base));
            num1 = Integer.parseInt(br.readLine());
        }
    }

    public static int anyBaseSubtraction(int num1, int num2, int base) {
        int res = 0, carry = 0, mul = 1;
        while(num2 > 0) {
            int dig1 = (num1 % 10);
            num1 /= 10;
            int dig2 = (num2 % 10) + carry;
            num2 /= 10;

            int diff = 0;
            if(dig2 < dig1) {
                carry = -1;
                diff = dig2 + base - dig1;
            } else {
                carry = 0;
                diff = dig2 - dig1;
            }
            res += (diff * mul);
            mul *= 10;
        }
        return res;
    }
}
