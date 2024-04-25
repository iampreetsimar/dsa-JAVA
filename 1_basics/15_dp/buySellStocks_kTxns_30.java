// Given N: number of days, N numbers: ith numbers is price of stock on ith day, and k:max txns allowed.
// Calculate and print the max profit we can make if atmost k transactions are allowed.
// NOTE: There can be no overlapping transactions. One transaction needs to be closed(a buy followed by a sell) 
// before opening another transaction(another buy).

// INPUT
// 6
// 9 6 7 6 3 8
// 3

// OUTPUT
// 6

import java.io.*;

public class buySellStocks_kTxns_30 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stockPrice = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            stockPrice[i] = Integer.parseInt(row[i]);
        }
        int k = Integer.parseInt(br.readLine());

        System.out.println(tradeStocksKTxnsRec(stockPrice, 0, 0, k));
        System.out.println(tradeStocksKTxns(stockPrice, k));
        System.out.println(tradeStocksKTxnsOpt(stockPrice, k));
    }

    // similar to BUY SELL STOCKS - 2 TXNS
    // instead of 2, we have k txns now
    public static int tradeStocksKTxnsRec(int[] stockPrice, int ithDay, int buyOrSell, int k) {
        if(ithDay == stockPrice.length || k == 0)
            return 0;

        int profit = 0;

        if(buyOrSell == 0) {
            profit = Math.max(0 + tradeStocksKTxnsRec(stockPrice, ithDay + 1, buyOrSell, k),
            -stockPrice[ithDay] + tradeStocksKTxnsRec(stockPrice, ithDay + 1, 1, k));
        }

        if(buyOrSell == 1) {
            profit = Math.max(0 + tradeStocksKTxnsRec(stockPrice, ithDay + 1, buyOrSell, k),
            stockPrice[ithDay] + tradeStocksKTxnsRec(stockPrice, ithDay + 1, 0, k - 1));
        }

        return profit;
    }

    // use dp of size [k + 1][n] for txns:0 -> k and days: 0 -> n - 1
    // dp[i][j]: max profit in j days if atmost i txns are closed
    // dp[0][j]: 0 -> no profit if no txns are allowed
    // dp[i][0]: 0 -> no profit if only 1 day present -> buy today, sell today
    // TC: O(N^3): to compute dp[i][j]: find max b/w
    //    dp[i][j - 1]: if i txns were closed on (j - 1)th day
    //    dp[i - 1][0]: if i-1 txns were closed on 0th day -> ith txn: buy on 0th day, sell on jth day
    //    dp[i - 1][1]: if i-1 txns were closed on 1st day -> ith txn: buy on 1st day, sell on jth day
    //    .......
    //    dp[i - 1][j - 1]: if i-1 txns were closed on (j-1)th day -> ith txn: buy on (j-1)th day, sell on jth day
    public static int tradeStocksKTxns(int[] stockPrice, int k) {
        int[][] dp = new int[k + 1][stockPrice.length];

        for(int txn = 1; txn <= k; txn++) {
            for(int day = 1; day < stockPrice.length; day++) {
                dp[txn][day] = dp[txn][day - 1];    // dp[i][j - 1] -> i txns

                for(int prevDay = 0; prevDay < day; prevDay++) {    
                    int gainForTxnMinus1TillPrevDay = dp[txn - 1][prevDay]; // i - 1 txns profit
                    int gainForTthTxn = stockPrice[day] - stockPrice[prevDay];  // ith txn profit

                    int gainForTTxns = gainForTthTxn + gainForTxnMinus1TillPrevDay; // i txns profit
                    if(gainForTTxns > dp[txn][day]) // set profit from i txns everytime a higher profit is found
                        dp[txn][day] = gainForTTxns;    // atlast will have max profit for i txns in j days
                }
            }
        }
        return dp[k][stockPrice.length - 1];    // max profit for k txns in n days
    }

    // similar to above approach
    // TC: O(N^2)
    // To compute gains for i-1 txns, we see common gains -> since we're selling on jth day for each comp,
    // we'll track the remaining gains(gain from i - 1 txns and buy) and choose max from them
    // Don't have traverse again, compute and track max common gain at each day
    public static int tradeStocksKTxnsOpt(int[] stockPrice, int k) {
        int[][] dp = new int[k + 1][stockPrice.length];

        for(int txn = 1; txn <= k; txn++) {
            int maxCommonGainForTxnMinus1 = dp[txn - 1][0] - stockPrice[0]; // dp[i - 1][0] - price[0](buy on 0th day)

            for(int day = 1; day < stockPrice.length; day++) {
                // dp[i - 1][1 -> j - 1] - price[1->j - 1]
                // if above common gain comp > common gain comp computed in prev iteration 
                // set above as new max common gain comp for i-1 txns + buy
                if(dp[txn - 1][day - 1] - stockPrice[day - 1] > maxCommonGainForTxnMinus1)
                    maxCommonGainForTxnMinus1 = dp[txn - 1][day - 1] - stockPrice[day - 1];

                // add today's SP to max common gain to get i txns gain
                // find max b/w above and gain from i txns done j-1 days
                dp[txn][day] = Math.max(maxCommonGainForTxnMinus1 + stockPrice[day], dp[txn][day - 1]);
            }
        }

        return dp[k][stockPrice.length - 1];    // max profit for k txns in n days
    }
}
