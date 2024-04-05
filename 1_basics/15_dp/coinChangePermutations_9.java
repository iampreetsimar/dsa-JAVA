// Given N -> count of coins.
// Given N numbers -> denomination of N coins.
// Given amt.
// Calculate and print number of PERMUTATIONS of the N coins using w/c amount 'amt' can be paid.

// NOTE: INFINITE supply of each coin denomination is available,i.e, same coin denomination can be used
//       for many installments in payment of 'amt'.

//       You are required to find the count of PERMUTATIONS and not COMBINATIONS,i.e,  
//       2 + 2 + 3 = 7, 2 + 3 + 2 = 7 and 3 + 2 + 2 = 7 are different permutations of same combination.
//       These will counted as 3 for our problem.

// INPUT
// 4
// 2 3 5 6
// 7

// OUTPUT
// 5

import java.io.*;

public class coinChangePermutations_9 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(row[i]);
        }
        int amt = Integer.parseInt(br.readLine());

        System.out.println(countPermutations(coins, amt, 0));
        System.out.println(countPermutationsMem(coins, amt));
    }

    public static int countPermutations(int[] coins, int amt, int sum) {
        if(sum == amt)  // BASE CASE: sum equals amt -> a permutation formed
            return 1;   // return 1 count for it
        
        if(sum > amt)   // sum > amt -> permutation not formed -> return 0 as count
            return 0;
        
        int count = 0;

        // each coin given a chance to include in sum -> return count from their calls
        // since INFINITE supply: each coin can be used again in further calls
        for(int coin: coins) {  
            count += countPermutations(coins, amt, sum + coin); // add all counts
        }
        return count;   // return total count of permutations whose sum equals amt
    }

    // use dp[] of size [amt + 1] to store count of permutations for amount ranging: 0 -> amt
    // dp[idx]: count of permutations using coins[] items whose sum == idx
    // move from easiest problem(dp[0]) to hardest problem(dp[amt]) 
    public static int countPermutationsMem(int[] coins, int amt) {
        int[] dp = new int[amt + 1];
        dp[0] = 1;  // 1 permn to make amount:0 -> use no coins

        // traverse dp[] and at each idx for dp[], use all coins to check if permn is possible
        // this ensures all possible permns can be formed and counted
        for(int i = 1; i < dp.length; i++) {
            for(int coin: coins) {  // check all coins at amount: i to count permns from all coins
                if(i >= coin)   // if i < coin -> coin's contribution can make amount -ve
                    dp[i] += dp[i - coin];  // src: i, dest: 0, inter: (i-coin) and inter can be reached from src
            }   // if inter to dest has x permns -> src to dest also has x permns
        }

        return dp[amt]; // return count of permns using items of coins[] whose sum == amt
    }
}
