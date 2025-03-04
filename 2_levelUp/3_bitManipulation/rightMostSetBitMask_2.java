// Given a number n, print its right most set bit mask;

// INPUT
// 26 <-> 11010

// OUTPUT
// 2 <-> 10

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class rightMostSetBitMask_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int rmsbm = (n & -n);       // n & (~n + 1)
        System.out.println("Number in binary: " + Integer.toBinaryString(n));
        System.out.println("Right Most Set Bit Mask: " + Integer.toBinaryString(rmsbm));
    }
}
