// Given N: number of houses.
// In next N rows, given 3 space separated numbers: cost of painting nth house with Red, Blue or Green color.
// Calculate and print the min cost of painting all houses without painting any consecutive house with same color.

// INPUT
// 4
// 1 5 7
// 5 8 4
// 3 2 9
// 1 2 4

// OUTPUT
// 8

import java.io.*;

public class paintHouse_18 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n][3];
        for(int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for(int j = 0; j < costs[0].length; j++) {
                costs[i][j] = Integer.parseInt(row[j]);
            }
        }
        
        System.out.println(minPaintCost(costs, 0, -1));
        System.out.println(minPaintCostTabOpt(costs));
    }

    // RECURSIVE APPROACH
    // ithHouse: curr house to be painted
    // color(0:red, 1:blue, 2:green, -1: no color): color of prev house painted
    // no 2 adjacent houses can have same color -> only valid calls
    public static long minPaintCost(int[][] costs, int ithHouse, int prevColor) {
        if(ithHouse == costs.length)
            return 0;

        if(prevColor == 0) {
            long minCostBlue = costs[ithHouse][1] + minPaintCost(costs, ithHouse + 1, 1);
            long minCostGreen = costs[ithHouse][2] + minPaintCost(costs, ithHouse + 1, 2);
            return Math.min(minCostBlue, minCostGreen);
        } else if(prevColor == 1) {
            long minCostRed = costs[ithHouse][0] + minPaintCost(costs, ithHouse + 1, 0);
            long minCostGreen = costs[ithHouse][2] + minPaintCost(costs, ithHouse + 1, 2);
            return Math.min(minCostRed, minCostGreen);
        } else if(prevColor == 2) {
            long minCostRed = costs[ithHouse][0] + minPaintCost(costs, ithHouse + 1, 0);
            long minCostBlue = costs[ithHouse][1] + minPaintCost(costs, ithHouse + 1, 1);
            return Math.min(minCostBlue, minCostRed);
        } else {
            long minCostRed = costs[ithHouse][0] + minPaintCost(costs, ithHouse + 1, 0);
            long minCostBlue = costs[ithHouse][1] + minPaintCost(costs, ithHouse + 1, 1);
            long minCostGreen = costs[ithHouse][2] + minPaintCost(costs, ithHouse + 1, 2);
            return Math.min(minCostRed, Math.min(minCostBlue, minCostGreen));
        }
    }

    // OPTIMIZED DP-TABULATION: constant space
    // DP-TABULATION meaning -> dp[ithHouse][jthColor]: min cost of painting till ithHouse 
    // where ithHouse is painted with jthColor but (i-1)th house is not painted with jthColor
    public static long minPaintCostTabOpt(int[][] costs) {
        long minCostRed = costs[0][0];  // only curr house painted red
        long minCostBlue = costs[0][1]; // only curr house painted blue
        long minCostGreen = costs[0][2]; // only curr house painted green

        for(int ithHouse = 1; ithHouse < costs.length; ithHouse++) {
            // cost of painting till curr house with jth color : add cost of choosing jth color for curr house + 
            // min cost b/w cost of painting till (i - 1) houses such that (i-1)th house did not have jth color

            long currCostRed = costs[ithHouse][0] + Math.min(minCostBlue, minCostGreen);
            long currCostBlue = costs[ithHouse][1] + Math.min(minCostRed, minCostGreen);
            long currCostGreen = costs[ithHouse][2] + Math.min(minCostRed, minCostBlue);

            // min cost till curr house becomes min cost till prev houses for next iteration
            minCostRed = currCostRed;
            minCostBlue = currCostBlue;
            minCostGreen = currCostGreen;
        }

        // choose min cost to paint all houses such that no 2 adj houses have same color
        return Math.min(minCostRed, Math.min(minCostBlue, minCostGreen));
    }
}
