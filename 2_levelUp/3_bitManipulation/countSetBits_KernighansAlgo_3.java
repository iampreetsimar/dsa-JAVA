// Given a number, count the number of set bits in the given number. 

// INPUT
// 26 <-> 11010

// OUTPUT 
// 3

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class countSetBits_KernighansAlgo_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int count = 0;
        while(n != 0) {     // O(# of set bits)
            int rsbm = n & -n;      
            n = n - rsbm;   // n's rsb becomes 0 -> n's new rsb shifts to next set bit in left direction if any
            count++;
        }
        System.out.println(count);
    }
}
