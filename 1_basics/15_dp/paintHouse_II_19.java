// Given N: number of houses, k: number of colors
// In next N rows, given k space separated numbers: cost of painting nth house with one of the k colors.
// Calculate and print the min cost of painting all houses without painting any consecutive house with same color.

// INPUT
// 4 3
// 1 5 7
// 5 8 4
// 3 2 9
// 1 2 4

// OUTPUT
// 8

import java.io.*;

public class paintHouse_II_19 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[][] costs = new int[n][k];
        for(int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for(int j = 0; j < k; j++) {
                costs[i][j] = Integer.parseInt(row[j]);
            }
        }
        
        System.out.println(minPaintCostDP(costs));
        System.out.println(minPaintCostDPOpt(costs));
    }

    // TC: O(N^3)
    // dp[i][j]: minCost to paint till ith houses such that ith house has jth color and
    //           no 2 adj houses have same color
    // dp[i][j] computation: add cost to paint ith house with jth color to
    //          min amongst min cost to paint till (i-1) houses with color other than j                      
    // for each row: we need to traverse in prev row to find min among prev min costs
    public static int minPaintCostDP(int[][] costs) {
        int[][] dp = new int[costs.length][costs[0].length];

        for(int j = 0; j < dp[0].length; j++)
            dp[0][j] = costs[0][j]; // for 0th house: min cost same as paint cost for all colors

        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                int minCostTillIthMinus1 = Integer.MAX_VALUE;
                for(int k = 0; k < dp[0].length; k++) { // find min from min costs till (i-1) houses
                    if(k != j && dp[i - 1][k] < minCostTillIthMinus1) {
                        // prev colors should not be same as curr color
                        minCostTillIthMinus1 = dp[i - 1][k];
                    }     
                }

                dp[i][j] = costs[i][j] + minCostTillIthMinus1;  // curr paint cost + min from (i-1)
            }
        }

        int totalMinCost = Integer.MAX_VALUE;   // find min from min costs till N houses
        for(int j = 0; j < dp[0].length; j++) {
            if(dp[dp.length - 1][j] < totalMinCost)
                totalMinCost = dp[dp.length - 1][j];
        }

        return totalMinCost;
    }

    // TC: O(N^2)
    // dp[i][j]: minCost to paint till ith houses such that ith house has jth color and
    //           no 2 adj houses have same color
    // dp[i][j] computation: add cost to paint ith house with jth color to
    //          min amongst min cost to paint till (i-1) houses with color other than j 
    // instead of traversing for colors in prev row for each row, we use leastMin and secondLeastMin
    // while traversing curr row, we can find leastMin and secondLeastMin costs
    // on moving to next row, instead on travering prev row again, we get min values from these two
    public static int minPaintCostDPOpt(int[][] costs) {
        int[][] dp = new int[costs.length][costs[0].length];

        int leastMinCostTillPrev = Integer.MAX_VALUE;
        int secondLeastMinCostTillPrev = Integer.MAX_VALUE;

        for(int j = 0; j < dp[0].length; j++) {
            dp[0][j] = costs[0][j]; // for 0th house: min cost is same as paint cost

            if(dp[0][j] < leastMinCostTillPrev) {   // if min cost for jth color < least
                // update both least and secondLeast min cost
                secondLeastMinCostTillPrev = leastMinCostTillPrev;
                leastMinCostTillPrev = dp[0][j];
            } else if(dp[0][j] < secondLeastMinCostTillPrev) {  // if min cost for jth < sl
                secondLeastMinCostTillPrev = dp[0][j];  // update only sl min cost
            }
        }
        
        for(int i = 1; i < dp.length; i++) {
            int leastMinCostTillCurr = Integer.MAX_VALUE; // to compute least min cost for curr row
            int secondLeastMinCostTillCurr = Integer.MAX_VALUE; // to compute sl min cost for curr row

            for(int j = 0; j < dp[0].length; j++) {
                // if least min cost for prev == min cost in prev row for jth color 
                // -> use sl min cost as min from prev row
                // cannot use least min if color are same
                if(dp[i - 1][j] == leastMinCostTillPrev) {  
                    dp[i][j] = costs[i][j] + secondLeastMinCostTillPrev;
                } else {    // otherwise -> use least min cost as min from prev row
                    dp[i][j] = costs[i][j] + leastMinCostTillPrev;
                }

                // compute least min cost and sl min cost for curr row
                if(dp[i][j] < leastMinCostTillCurr) {
                    secondLeastMinCostTillCurr = leastMinCostTillCurr;
                    leastMinCostTillCurr = dp[i][j];
                } else if(dp[i][j] < secondLeastMinCostTillCurr) {
                    secondLeastMinCostTillCurr = dp[i][j];
                }
            }

            // update least and sl min cost for curr row as prev row for next iteration
            leastMinCostTillPrev = leastMinCostTillCurr;
            secondLeastMinCostTillPrev = secondLeastMinCostTillCurr;
        }

        return leastMinCostTillPrev; // has least min cost from last row(min cost to paint N houses)
    }
}
