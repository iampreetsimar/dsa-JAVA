// Given N: number of friends. Each friend can stay single or pair up with any of its friends.
// Calculate and print the number of ways in which these friends can stay single or pair up.

// Eg.
// 1 person can stay single/pair up in 1 way : just stay single.
// 2 friends(12) can stay singles or pair up in 2 ways: 1 - 2, 12.
// 3 friends(123) can stay single or pair up in 4 ways: 1 - 2 - 3, 1 - 23, 12 - 3, 13 - 2.

// INPUT
// 4

// OUTPUT
// 10

import java.io.*;

public class friendsPairing_23 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        System.out.println(friendsPairing(n));
        System.out.println(friendsPairingTab(n));
    }

    // since we need only count and not the actual way paths, we're not making (n - 1) calls for f(n-2)
    // as f(n - 2) will return same count -> so we're only multiplying to counts
    public static int friendsPairing(int n) {
        if(n == 1 || n == 2) // BASE CASE: 1 friend: 1 way -> stay single
            return n;   // 2 friends: 2 ways -> both single or both paired up

        return friendsPairing(n - 1) +  // f(n - 1): return no. of ways when nth friend stays single
        (n - 1) * friendsPairing(n - 2); // f(n - 2): return no. of ways when nth friend pairs up with its friends
    }   // since there are (n - 1) friends for nth -> it pairs up with each of them

    // dp[idx]: number of ways for idx friends to stay single or pair up
    public static int friendsPairingTab(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;  // 1 friend -> 1 way -> stay single
        dp[2] = 2;  // 2 friends -> 2 ways -> both single or both paired up

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
        }

        return dp[n];
    }
}
