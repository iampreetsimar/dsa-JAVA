// Given N -> count of coins.
// Given N numbers -> denomination of N coins.
// Given amt.
// Calculate and print number of COMBINATIONS of the N coins using w/c amount 'amt' can be paid.

// NOTE: INFINITE supply of each coin denomination is available,i.e, same coin denomination can be used
//       for many installments in payment of 'amt'.

//       You are required to find the count of COMBINATIONS and not PERMUTATIONS,i.e,  
//       2 + 2 + 3 = 7, 2 + 3 + 2 = 7 and 3 + 2 + 2 = 7 are different permutations of same combination.
//       These will counted as 1 for our problem.

// INPUT
// 4
// 2 3 5 6
// 7

// OUTPUT
// 2

import java.io.*;

public class coinChangeCombinations_8 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(row[i]);
        }
        int amt = Integer.parseInt(br.readLine());

        System.out.println(countCombinations(coins, amt, 0, 0));
        System.out.println(countCombinationsMem(coins, amt));
    }

    public static int countCombinations(int[] coins, int amt, int idx, int sum) {
        if(sum == amt)  // BASE CASE: sum equals amt -> combination formed
            return 1;   // return 1 count
        
        if(sum > amt)   // sum > amt -> no combination -> return 0
            return 0;
        
        if(idx == coins.length) // sum is not formed and reached end of array
            return 0;   // no combination -> return 0
        
        // count INCLUDED: coin included in sum but again given as choice so idx remains same
        // count EXCLUDED: coin excluded in sum and excluded from another choice so idx is incremented
        // total combination count = count included + count excluded
        return countCombinations(coins, amt, idx, sum + coins[idx]) +  
               countCombinations(coins, amt, idx + 1, sum);
    }

    // use dp[] of size [amt + 1] to store combinations for amount in range: 0 -> amt
    // dp[idx]: count of combinations whose sum equals idx
    // move from easiest problem(dp[0]) to hardest problem(dp[amt])
    public static int countCombinationsMem(int[] coins, int amt) {
        int[] dp = new int[amt + 1];
        dp[0] = 1;  // 1 combn to make amount:0 -> use no coins

        // traverse dp[] completely using a coin before moving to next coin
        // this ensures no permutations are counted
        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];    // curr coin

            // move from amount: curr coin to amt because if amount(j) < coin, coin cannot make the amount(j)
            for(int j = coin; j < dp.length; j++) { 
                dp[j] += dp[j - coin];  // if(dp[j - coin]) has combns 
            }   // -> dp[j] can also make combns by adding coin at end
        }

        return dp[amt]; // return count of all combinations using coins[] whose sum equals amt
    }
}
