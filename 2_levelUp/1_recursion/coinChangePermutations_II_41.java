// Given N coins, N numbers for denominations of N coins and amount, calculate and print the permutations 
// of N coins(same coin can be used again any number of times) using which the amount can be paid. 

// INPUT
// 5
// 2 3 5 6 7
// 12

// OUTPUT
// 2-2-2-2-2-2-.
// 2-2-2-3-3-.
// 2-2-2-6-.
// 2-2-3-2-3-.
// 2-2-3-3-2-.
// 2-2-3-5-.
// 2-2-5-3-.
// 2-2-6-2-.
// 2-3-2-2-3-.
// 2-3-2-3-2-.
// 2-3-2-5-.
// 2-3-3-2-2-.
// 2-3-5-2-.
// 2-3-7-.
// 2-5-2-3-.
// 2-5-3-2-.
// 2-5-5-.
// 2-6-2-2-.
// 2-7-3-.
// 3-2-2-2-3-.
// 3-2-2-3-2-.
// 3-2-2-5-.
// 3-2-3-2-2-.
// 3-2-5-2-.
// 3-2-7-.
// 3-3-2-2-2-.
// 3-3-3-3-.
// 3-3-6-.
// 3-5-2-2-.
// 3-6-3-.
// 3-7-2-.
// 5-2-2-3-.
// 5-2-3-2-.
// 5-2-5-.
// 5-3-2-2-.
// 5-5-2-.
// 5-7-.
// 6-2-2-2-.
// 6-3-3-.
// 6-6-.
// 7-2-3-.
// 7-3-2-.
// 7-5-.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class coinChangePermutations_II_41 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] parts = br.readLine().split(" ");
        int amt = Integer.parseInt(br.readLine());

        int[] coins = new int[n];
        for(int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(parts[i]);
        }

        solve(coins, amt, "");
    }

    // similar to COIN CHANGE PERMUTATIONS - I but all coins can be used at any level as duplicates are allowed
    public static void solve(int[] coins, int amt, String asf) {
        if(amt < 0) return;     // no need to proceed as amt becomes < 0 

        if(amt == 0) {      // BASE CASE
            System.out.println(asf + ".");
            return;
        }

        for(int i = 0; i < coins.length; i++) {     // all coins are valid options
            solve(coins, amt - coins[i], asf + coins[i] + "-");
        }
    }
}
