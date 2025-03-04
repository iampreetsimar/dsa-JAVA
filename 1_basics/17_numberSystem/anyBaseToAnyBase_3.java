// Given a number with its base, convert it to a number in target base.

// INPUT 
// 172
// 8
// 2
// -1

// OUTPUT
// 1111010

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class anyBaseToAnyBase_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        while(num != -1) {
            int sourceBase = Integer.parseInt(br.readLine());
            int targetBase = Integer.parseInt(br.readLine());
            System.out.println("Result: " + anyBaseToAnyBase(num, sourceBase, targetBase));
            num = Integer.parseInt(br.readLine());
        }
    }

    public static int anyBaseToAnyBase(int num, int sourceBase, int targetBase) {
        return decimalToAnyBase(anyBaseToDecimal(num, sourceBase), targetBase);
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

    public static int anyBaseToDecimal(int num, int base) {
        int res = 0, mul = 1;
        while(num > 0) {
            res += ((num % 10) * mul);
            num /= 10;
            mul *= base;
        }
        return res;
    }
}
