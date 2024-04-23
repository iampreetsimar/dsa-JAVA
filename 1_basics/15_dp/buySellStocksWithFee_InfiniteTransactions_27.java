// Given N: number of days, N numbers: ith numbers is price of stock on ith day and, 
// a number: transaction fee for each transaction.
// Calculate and print the max profit we can make if infinite transactions are allowed but have to pay fee for
// every closed transaction.
// NOTE: There can be no overlapping transactions. One transaction needs to be closed(a buy followed by a sell) 
// before opening another transaction(another buy).

// INPUT
// 12
// 10 15 17 20 16 18 22 20 22 20 23 25
// 3

// OUTPUT
// 13

import java.io.*;

public class buySellStocksWithFee_InfiniteTransactions_27 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stockPrice = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            stockPrice[i] = Integer.parseInt(row[i]);
        }
        int txnFee = Integer.parseInt(br.readLine());

        System.out.println(tradeStocksWithFeeInfiniteTxnsRec(stockPrice, txnFee, 0, 0));
        System.out.println(tradeStocksWithFeeInfiniteTransactions(stockPrice, txnFee));
    }

    // buyOrSell - 0 : can buy the stock -> 2 choices, either don't buy curr or buy curr and sell at next
    // buyOrSell - 1 : can sell the stock -> 2 choices, don't sell curr or sell curr and buy at next
    public static int tradeStocksWithFeeInfiniteTxnsRec(int[] stockPrice, int txnFee, int idx, int buyOrSell) {
        if(idx == stockPrice.length)
            return 0;
        
        int profit = 0;

        if(buyOrSell == 0) {    // can buy
                             // don't buy today -> move to next
            profit = Math.max(0 + tradeStocksWithFeeInfiniteTxnsRec(stockPrice, txnFee, idx + 1, 0), 
            -stockPrice[idx] + tradeStocksWithFeeInfiniteTxnsRec(stockPrice, txnFee, idx + 1, 1));
            // buy today -> price decreased from profit
        }

        if(buyOrSell == 1) {    // can sell
                              // dont' sell today -> move to next
            profit = Math.max(0 + tradeStocksWithFeeInfiniteTxnsRec(stockPrice, txnFee, idx + 1, 1), 
            stockPrice[idx] - txnFee + tradeStocksWithFeeInfiniteTxnsRec(stockPrice, txnFee, idx + 1, 0));
            // sell today -> price added to profit and txnFee applied
        }

        return profit; // max profit
    }

    // similar to DP - INCLUDE/EXCLUDE concept
    // use buy state profit - max profit at ith day having an extra share bought
    // use sell state profit - max profit at ith day having equal number of buying and selling shares
    public static int tradeStocksWithFeeInfiniteTransactions(int[] stockPrice, int txnFee) {
        int bspiMinus1thDayGain = -stockPrice[0];   // we take a loan to buy share at 0th day - no capital
        int sspiMinus1thDayGain = 0;    // can't sell on 0th day as we don't have a share to sell yet

        for(int i = 1; i < stockPrice.length; i++) {
            // max buy profit b/w:
            // - max buy profit at (i-1)th day -> don't buy on ith day
            // - max buy at ith day after using sell profit which closed txn on (i-1)th day -> no overlapping of txns
            int bspithDayGain = Math.max(bspiMinus1thDayGain, sspiMinus1thDayGain - stockPrice[i]);

            // max sell profit b/w:
            // - max sell profit at (i-1)th day -> don't sell on ith day
            // - max sell at ith day and close txn after using buy profit which have an open txn on (i-1)th day
            int sspithDayGain = Math.max(sspiMinus1thDayGain, bspiMinus1thDayGain + stockPrice[i] - txnFee);

            // profits on ith day become profits of (i-1)th day for next iteration
            bspiMinus1thDayGain = bspithDayGain;   
            sspiMinus1thDayGain = sspithDayGain;
        }

        // max sell profit as a closed txn would give more profit than an open one
        return sspiMinus1thDayGain;     // max sell profit at (n-1)th day is the last day
    }
}
