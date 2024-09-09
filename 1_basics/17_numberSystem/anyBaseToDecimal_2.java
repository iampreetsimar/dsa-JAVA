// Given a number with its base, convert it to decimal base.

// INPUT 
// 1172
// 8
// 1111
// 2
// -1

// OUTPUT
// 634
// 15

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class anyBaseToDecimal_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        while(num != -1) {
            int base = Integer.parseInt(br.readLine());
            System.out.println("Result: " + anyBaseToDecimal(num, base));
            num = Integer.parseInt(br.readLine());
        }
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
