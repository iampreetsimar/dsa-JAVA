// Given N: number of days, N numbers: ith numbers is price of stock on ith day.
// Calculate and print the max profit we can make if only single transaction is allowed.

// INPUT
// 9
// 11 6 7 19 4 1 6 18 4

// OUTPUT
// 17

import java.io.*;

public class buySellStocks_1Transaction_25 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stockPrice = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            stockPrice[i] = Integer.parseInt(row[i]);
        }

        System.out.println(tradeStockOneTransaction(stockPrice));
    }

    // GREEDY APPROACH as cannot use DP due to no overlapping subproblems
    // We'll get max profit if BP is least and SP is max
    // Consider each day as a selling day and compute profit by using least BP available before or on selling day
    // Find max amongst all the gain
    public static int tradeStockOneTransaction(int[] stockPrice) {
        int maxProfit = 0;
        int leastBPDay = 0; // consider 0th day has least BP

        for(int i = 0; i < stockPrice.length; i++) {
            if(stockPrice[i] <= stockPrice[leastBPDay]) // if curr day's BP <= least BP
                leastBPDay = i; // update least BP to today

            int profitIfSoldToday = stockPrice[i] - stockPrice[leastBPDay]; // today's SP with least BP available
            if(profitIfSoldToday > maxProfit)   // if profit for today > max profit available till today
                maxProfit = profitIfSoldToday;  // update today's profit as max
        }

        return maxProfit;   
    }
}
