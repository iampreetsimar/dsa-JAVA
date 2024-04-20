// Given N: length of floor which is 2 units wide, i.e., size: (2 * N).
// We also have infinite supply of (2 * 1) tiles.
// Calculate and print numbers of ways floor can be tiled using tiles of given size.

// INPUT
// 8

// OUTPUT
// 34

import java.io.*;

public class tilingWith2Into1Tiles_21 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        System.out.println(tiling2Into1(n));
        System.out.println(tiling2Into1Tab(n));
    }

    public static int tiling2Into1(int untiledFloorLen) {
        if(untiledFloorLen == 1)    // BASE CASE: only way is to place tile vertically
            return 1;

        if(untiledFloorLen == 2) // BASE CASE: both placed either vertically or horizontally
            return 2;

        // f(n - 2) : when tile placed horizontally -> untiled area: n - 2 + special area above placed tiled
        // * 1 for area above horizontally placed tile -> only 1 way to tile it -> place a tile horizontally
        int horizontalTiling = tiling2Into1(untiledFloorLen - 2) * 1; 

        // f(n - 1): when tile placed vertically -> untiled len: n - 1
        int verticalTiling = tiling2Into1(untiledFloorLen - 1); 
        return horizontalTiling + verticalTiling;
    }

    // use dp[] of size [N + 1] for floor len: 0 -> N
    // dp[idx]: total ways to tile idx length of floor
    public static int tiling2Into1Tab(int untiledFloorLen) {
        int[] dp = new int[untiledFloorLen + 1];
        dp[1] = 1;  // only way is to place tile vertically
        dp[2] = 2;  // both placed either vertically or horizontally

        for(int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + (dp[i - 2] * 1);
        }

        return dp[untiledFloorLen];
    }
}
