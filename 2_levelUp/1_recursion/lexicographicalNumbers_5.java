// Given a number N, print all the numbers from 1 to N in lexicographical(dictionary) order.

// INPUT
// 20

// OUTPUT
// 1
//     10
//     11 
//     12 
//     13 
//     14 
//     15 
//     16 
//     17 
//     18 
//     19
// 2 
//     20
// 3 
// 4
// 5
// 6
// 7
// 8
// 9

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class lexicographicalNumbers_5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        solve(n);
    }

    public static void solve(int n) {
        for(int i = 1; i <= 9; i++) {   // to inititate printing from 1 till max digit 9
            solve(i, n);    // will take care of i's family before moving to next digit start
        }
    }

    public static void solve(int i, int n) {
        if(i > n)   // BASE CASE
            return;

        System.out.println(i);  // prints self

        for(int j = 0; j < 10; j++) {   // make calls to i's family
            solve(10 * i + j, n);   
        }
    }
}
