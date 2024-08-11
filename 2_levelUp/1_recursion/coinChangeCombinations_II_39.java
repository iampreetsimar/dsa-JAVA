// Given N coins, N numbers for denominations of N coins and amount, calculate and print the combinations 
// of N coins(same coin can be used again any number of times) using which the amount can be paid. 

// INPUT
// 5
// 2 3 5 6 7
// 12

// OUTPUT
// 2-2-2-2-2-2-.
// 2-2-2-3-3-.
// 2-2-2-6-.
// 2-2-3-5-.
// 2-3-7-.
// 2-5-5-.
// 3-3-3-3-.
// 3-3-6-.
// 5-7-.
// 6-6-.

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class coinChangeCombinations_II_39 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] parts = br.readLine().split(" ");
        int amt = Integer.parseInt(br.readLine());

        int[] coins = new int[n];
        for(int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(parts[i]);
        }

        solve_I(0, coins, amt, "");
        solve_II(0, coins, amt, "");
    }

    // APPROACH - 1
    // similar to coin change combinations I but coin has different 2 choices:
    //      -> include itself but don't move to next coin so it can be used again
    //      -> exclude itself and move to next coin so it cannot be used again
    public static void solve_I(int idx, int[] coins, int amt, String asf) {
        if(amt < 0) return;

        if(idx == coins.length) {
            if(amt == 0) {
                System.out.println(asf + ".");
            }
            return;
        }

        solve_I(idx, coins, amt - coins[idx], asf + coins[idx] + "-");
        solve_I(idx + 1, coins, amt - 0, asf);
    }

    // APPROACH - 2
    // following 2 choices: 
    //          -> all possible including choice by coin at a level and move to next coin
    //          -> excluding choice and move to next coin
    public static void solve_II(int idx, int[] coins, int amt, String asf) {
        if(idx == coins.length) {
            if(amt == 0) System.out.println(asf + ".");
            return;
        }

        // loop runs from max amt that can made using coin times to 1 time
        for(int i = amt/coins[idx]; i >= 1; i--) {
            String str = "";

            for(int k = 0; k < i; k++) {    // adds coin value # of times it is included
                str += coins[idx] + "-";    
            }

            solve_II(idx + 1, coins, amt - (coins[idx] * i), asf + str);    // including choice i times and move to next coin
        }

        solve_II(idx + 1, coins, amt - 0, asf); // excluding choice and move to next coin
    }
}
