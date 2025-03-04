// Given a number n, print the numbers produced:
// -> on setting its ith bit
// -> on unsetting its jth bit
// -> on toggling its kth bit
// -> Also, check if its mth bit is on or off. Print true if its on, otherwise false.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class basics_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int i = Integer.parseInt(br.readLine()); 
        int j = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int maskOn = (1 << i);      // use bitwise or
        int maskOff = ~(1 << j);    // use bitwise and
        int maskToggle = (1 << k);      // use bitwise xor
        int maskCheck = (1 << m);       // use bitwise and

        System.out.println("Setting ith bit: " + (n | maskOn));
        System.out.println("Unsetting jth bit: " + (n & maskOff));
        System.out.println("Toggling kth bit: " + (n ^ maskToggle));
        System.out.println("Checking mth bit: " + ((n & maskCheck) == 0 ? false: true));
    }
}
