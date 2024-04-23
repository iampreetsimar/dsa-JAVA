// Given N: number of days, N numbers: ith numbers is price of stock on ith day.
// Calculate and print the max profit we can make if infinite transactions are allowed but have to cooldown
// for 1 day after closing a txn ,i.e, we cannot buy a stock on the next day after we sell, have to cooldown
// for atleast 1 day before buying again. 
// NOTE: There can be no overlapping transactions. One transaction needs to be closed(a buy followed by a sell) 
// before opening another transaction(another buy).

// INPUT
// 12
// 10 15 17 20 16 18 22 20 22 20 23 25

// OUTPUT
// 19

import java.io.*;

public class buySellStocksWithCooldown_InfiniteTxns_28 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stockPrice = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            stockPrice[i] = Integer.parseInt(row[i]);
        }

        System.out.println(tradeStocksWithCoolDownInfiniteTxnsRec(stockPrice, 0, 0));
        System.out.println(tradeStocksWithCoolDownInfiniteTxns(stockPrice));
    }

    // buyOrSell - 0 : can buy the stock -> 2 choices, either don't buy curr or buy curr and sell at next
    // buyOrSell - 1 : can sell the stock -> 2 choices, don't sell curr or sell curr and buy at next to next
    public static int tradeStocksWithCoolDownInfiniteTxnsRec(int[] stockPrice, int idx, int buyOrSell) {
        if(idx >= stockPrice.length)
            return 0;
        
        int profit = 0;

        if(buyOrSell == 0) {    // can buy
                             // don't buy today -> move to next
            profit = Math.max(0 + tradeStocksWithCoolDownInfiniteTxnsRec(stockPrice, idx + 1, 0), 
            -stockPrice[idx] + tradeStocksWithCoolDownInfiniteTxnsRec(stockPrice, idx + 1, 1));
            // buy today -> price decreased from profit
        }

        if(buyOrSell == 1) {    // can sell
                              // dont' sell today -> move to next
            profit = Math.max(0 + tradeStocksWithCoolDownInfiniteTxnsRec(stockPrice, idx + 1, 1), 
            stockPrice[idx] + tradeStocksWithCoolDownInfiniteTxnsRec(stockPrice, idx + 2, 0));
            // sell today -> price added to profit and cooldown applied
        }

        return profit; // max profit
    }

    // similar to INFINITE TXNS - WITH FEE
    // buyStateProfit(bsp) - max profit at ith day having an extra share bought
    // sellStateProfit(ssp) - max profit at ith day having equal number of bought and sold shares
    // cooldownStateProfit(csp) - max profit(ith day) on adding a cooldown when a txn was closed at (i-1)th day
    public static int tradeStocksWithCoolDownInfiniteTxns(int[] stockPrice) {
        int bspIminus1thDay = -stockPrice[0];   // taking loan to buy share
        int sspIminus1thDay = 0;    // cannot sell since don't have share before 0th day
        int cspIminus1thDay = 0;    // cannot add cooldown if no txn was closed(0 sell)

        for(int i = 1; i < stockPrice.length; i++) {
            // use max b/w prev bsp(no buy on ith day) or buy at ith day after prev cooldown
            int bspIthDay = Math.max(bspIminus1thDay, cspIminus1thDay - stockPrice[i]);

            // use max b/w prev ssp(no sell on ith day) or sell(close txn) at ith day after prev open txn
            int sspIthDay = Math.max(sspIminus1thDay, bspIminus1thDay + stockPrice[i]);

            // use max b/w prev csp(no cooldown on ith day) or add cooldown after prev closed txn
            int cspIthDay = Math.max(cspIminus1thDay, sspIminus1thDay);

            // profits at ith day become profits at (i-1)th day for next iteration(day)
            bspIminus1thDay = bspIthDay;
            sspIminus1thDay = sspIthDay;
            cspIminus1thDay = cspIthDay;
        }

        // ssp(has all closed txns) will always have more profit than bsp(has one open txns) or csp(cooldowns)
        return sspIminus1thDay;  // cooldowns - has missed opportunities of trading
    }
}
