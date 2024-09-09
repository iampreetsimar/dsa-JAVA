// Given a number in decimal, convert it to target base.

// INPUT 
// 634
// 8
// 15
// 2
// -1

// OUTPUT
// 1172
// 1111

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class decimalToAnyBase_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        while(num != -1) {
            int base = Integer.parseInt(br.readLine());
            System.out.println("Result: " + decimalToAnyBase(num, base));
            num = Integer.parseInt(br.readLine());
        }
    }

    public static int decimalToAnyBase(int num, int base) {
        int res = 0, mul = 1;
        while(num > 0) {
            res += ((num % base) * mul);
            num /= base;
            mul *= 10;
        }
        return res;
    }
}
