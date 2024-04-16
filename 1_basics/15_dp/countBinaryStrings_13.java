// Given N, print number of binary strings of length N with no consecutive 0's.

// INPUT
// 6

// OUTPUT
// 21

import java.io.*;

public class countBinaryStrings_13 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(countBinaryStrings(n, ""));
        System.out.println(countBinaryStringsTab(n));
        System.out.println(countBinaryStringsOpt(n));
    }

    public static int countBinaryStrings(int n, String binaryStr) {
        if(n == 0)  // BASE CASE: str length == n
            return 1;   // since we get only valid binary strings, return 1 as count

        // if str.length == 0 or str: "___1" -> we can have two choices to add either 0 or 1 to string
        if(binaryStr.length() == 0 || binaryStr.charAt(binaryStr.length() - 1) == '1') {
            return countBinaryStrings(n - 1, binaryStr + 0) + 
                   countBinaryStrings(n - 1, binaryStr + 1);
        } else {    // if str: "___0" -> cannot add another 0 to string -> only choice to add 1
            return countBinaryStrings(n - 1, binaryStr + 1);
        }
    }

    // PROBLEM of type: INCLUSION/EXCLUSION
    // use dp[] of size [N + 1] to store count of binary strings of length: 0 -> N
    // dp0[]: stores binary strings ending with 0 of length: 0 -> N
    // dp1[]: stored binary strings ending with 1 of length: 0 -> N
    // move from easiest problem(len: 0) to hardest problem(len: N)
    public static int countBinaryStringsTab(int n) {
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];

        // for no consecutive 0's in strings
        for(int len = 0; len <= n; len++) {
            if(len == 0)    // cannot make a string of len:0 ending with either 0 or 1
                dp0[len] = dp1[len] = 0;
            else if(len == 1)   // str of len:1 ending with 0 -> "0" -> count: 1
                dp0[len] = dp1[len] = 1;    // str of len:1 ending with 1 -> "1" -> count: 1
            else {
                dp0[len] = dp1[len - 1];  // only str(len - 1) ending with 1 can make str(len) ending with 0 

                // both str(len - 1) ending with 0 and 1 can make str(len) ending with 1
                dp1[len] = dp0[len - 1] + dp1[len - 1];
            }
        }

        // total binary strings of len:N which are ending with either 0 or 1 but no consecutive 0's
        return dp0[n] + dp1[n]; 
    }

    // since to get count of binary strings(len) ending with either 0 or 1
    // we need the count of binary strings(len - 1) ending with either 0 or 1
    // We can use CONSTANT SPACE instead of arrays
    public static int countBinaryStringsOpt(int n) {
        int countStrEnd0 = 1;   // initial count of binary strings(len:1) ending with 0
        int countStrEnd1 = 1;   // initial count of binary strings(len:1) ending with 1

        // traverse for len:2 -> N
        for(int strLen = 2; strLen <= n; strLen++) {
            // count of binary str(len - 1) ending with 1 == count of binary str(len) ending with 0
            int currCountStrEnd0 = countStrEnd1;    

            // count of binary str(len - 1) ending with 0 + 1 == count of binary str(len) ending with 1
            int currCountStrEnd1 = countStrEnd0 + countStrEnd1;

            // count of binary str(len) becomes binary str(len - 1) for next step
            countStrEnd0 = currCountStrEnd0;    
            countStrEnd1 = currCountStrEnd1;
        }

        // total binary strings of len:N which are ending with either 0 or 1 but no consecutive 0's
        return countStrEnd0 + countStrEnd1; 
    }
}
