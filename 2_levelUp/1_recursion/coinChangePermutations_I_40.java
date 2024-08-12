// Given N coins, N numbers for denominations of N coins and amount, calculate and print the permutations 
// of N coins(non-duplicate) using which the amount can be paid. 

// INPUT
// 5
// 2 3 5 6 7
// 12

// OUTPUT
// 2-3-7-.
// 2-7-3-.
// 3-2-7-.
// 3-7-2-.
// 5-7-.
// 7-2-3-.
// 7-3-2-.
// 7-5-.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class coinChangePermutations_I_40 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] parts = br.readLine().split(" ");
        int amt = Integer.parseInt(br.readLine());

        int[] coins = new int[n];
        for(int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(parts[i]);
        }

        solve(coins, amt, new boolean[n], "");
    }

    // similar to PERMUTATIONS I
    // each unused coin(since duplicates not allowed) is a valid option at each level
    public static void solve(int[] coins, int amt, boolean[] usedCoins, String asf) {
        if(amt < 0) return;     // no need to proceed as only coins with +ve value are present

        if(amt == 0) {      // BASE CASE
            System.out.println(asf + ".");
            return;
        }

        for(int i = 0; i < usedCoins.length; i++) {     // check all coins
            if(!usedCoins[i]) {     // choose only valid coins - unused ones
                usedCoins[i] = true;
                solve(coins, amt - coins[i], usedCoins, asf + coins[i] + "-");
                usedCoins[i] = false;
            }
        }
    }
}
