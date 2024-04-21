// Given N: number of days, N numbers: ith numbers is price of stock on ith day.
// Calculate and print the max profit we can make if infinite transactions are allowed.
// NOTE: There can be no overlapping transactions. One transaction needs to be closed(a buy followed by a sell) 
// before opening another transaction(another buy).

// INPUT
// 9
// 11 6 7 19 4 1 6 18 4

// OUTPUT
// 30

import java.io.*;

public class buySellStocks_InfiniteTransactions_26 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stockPrice = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            stockPrice[i] = Integer.parseInt(row[i]);
        }

        System.out.println(tradeStocksInfiniteTransactions(stockPrice));
    }

    // GREEDY APPROACH as cannot use DP due to no overlapping subproblems
    // Infinite transactions: max profit -> adding up all upwards trends
    //                                   -> adding 0 from downward trends instead of actual loss
    // if SP(ith) day >= SP(i-1)th day -> upward trend -> update SP day
    // else: book profit till last SP day -> one transaction closed
    //       open new transaction -> initialize BP and SP to ith day for new transaction
    public static int tradeStocksInfiniteTransactions(int[] stockPrice) {
        int overallProfit = 0;
        int potentialBPDay = 0; // 0th day
        int potentialSPDay = 0; // 0th day

        for(int i = 1; i < stockPrice.length; i++) {
            if(stockPrice[i] >= stockPrice[i - 1]) {    // upward trend
                    potentialSPDay = i; // update SP day
            } else {    // downward trend -> book profit from last upward trend
                overallProfit += stockPrice[potentialSPDay] - stockPrice[potentialBPDay];
                potentialBPDay = potentialSPDay = i;    // initialize new transaction from today
            }
        }

        // if last day ends on a peak -> haven't booked last upward trend profit -> book it
        overallProfit += stockPrice[potentialSPDay] - stockPrice[potentialBPDay];
        return overallProfit;
    }
}
