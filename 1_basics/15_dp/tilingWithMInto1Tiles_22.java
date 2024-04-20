// Given N: length of floor, M: width of floor ,i.e., floor size: (M * N).
// We also have infinite supply of (M * 1) tiles.
// Calculate and print numbers of ways floor can be tiled using tiles of given size.

// INPUT
// 39 16

// OUTPUT
// 61

import java.io.*;

public class tilingWithMInto1Tiles_22 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);
        
        System.out.println(tilingMInto1(n, m));
        System.out.println(tilingMInto1Tab(n, m));
    }

    // n: untiled floor length
    public static int tilingMInto1(int n, int m) {
        if(n < m)   // BASE CASE: only 1 way to place vertically
            return 1;

        if(n == m)  // BASE CASE: 2 ways: either all placed vertically or all horizontally
            return 2;
        
        // when placed vertically -> untiled len: n - 1
        int verTiling = tilingMInto1(n - 1, m); 

        // when placed horizontally -> untiled area: n - m + special area above placed tiled
        // special area size is (m - 1) * m -> 1 way to place a new tile horizontally here
        int horzTiling = tilingMInto1(n - m, m) * 1;
        return verTiling + horzTiling;
    }

    // use dp[] of size [N + 1] for len: 0 -> N
    // dp[idx]: total ways to tile floor of len: idx
    public static int tilingMInto1Tab(int n, int m) {
        int[] dp = new int[n + 1];
        
        for(int i = 1; i <= n; i++) {
            if(i < m)  // only 1 way to place vertically
                dp[i] = 1;
            else if(i == m) // 2 ways: either all placed vertically or all horizontally
                dp[i] = 2; 
            else {
                dp[i] = dp[i - 1] + (dp[i - m] * 1);
            }
        }

        return dp[n];
    }
}
