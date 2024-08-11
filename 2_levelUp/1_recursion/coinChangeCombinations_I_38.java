// Given N coins, N numbers for denominations of N coins and amount, calculate and print the combinations 
// of N coins(non-duplicate) using which the amount can be paid. 

// INPUT
// 5
// 2 3 5 6 7
// 12

// OUTPUT
// 2-3-7-.
// 5-7-.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class coinChangeCombinations_I_38 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] parts = br.readLine().split(" ");
        int amt = Integer.parseInt(br.readLine());

        int[] coins = new int[n];
        for(int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(parts[i]);
        }

        solve(0, coins, amt, "");
    }

    // similar to COMBINATIONS I
    // we get 2^n combinations, can optimize it to stop call when amt becomes < 0.
    // each coin has two choices: include its value in amt, exclude its value in amt
    public static void solve(int idx, int[] coins, int amt, String asf) {
        if(idx == coins.length) {   // BASE CASE: all coins choices complete
            if(amt == 0) {  // combinations where selected coins make amt value
                System.out.println(asf + ".");
            }
            return;
        }

        solve(idx + 1, coins, amt - coins[idx], asf + coins[idx] + "-");    // include coin
        solve(idx + 1, coins, amt - 0, asf);    // exclude coin
    }
}
