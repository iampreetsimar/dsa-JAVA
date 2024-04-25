// Given N: number of days, N numbers: ith numbers is price of stock on ith day.
// Calculate and print the max profit we can make if atmost 2 transactions are allowed.
// NOTE: There can be no overlapping transactions. One transaction needs to be closed(a buy followed by a sell) 
// before opening another transaction(another buy).

// INPUT
// 9
// 11 6 7 19 4 1 6 18 4

// OUTPUT
// 30

import java.io.*;

public class buySellStocks_2Txns_29 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stockPrice = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            stockPrice[i] = Integer.parseInt(row[i]);
        }

        System.out.println(tradeStocks2TxnsRec(stockPrice, 0, 0, 2));
        System.out.println(tradeStocks2Txns(stockPrice));
    }

    // buyOrSell - 0 : can buy the stock -> 2 choices present
    //              1 - don't buy today -> move to buy next day
    //              2 - buy today -> can only sell on next day now -> txn is still open
    // buyOrSell - 1 : can sell the stock -> 2 choices present
    //              1 - don't sell today -> move to sell next day
    //              2 - sell today -> can buy on next day now -> txn is closed -> txn cap decrements by 1
    public static int tradeStocks2TxnsRec(int[] stockPrice, int ithDay, int buyOrSell, int txnCap) {
        if(ithDay == stockPrice.length || txnCap == 0)  // no more days left to trade or no more txns
            return 0;   // return 0 as profit from here

        int maxProfit = 0;

        if(buyOrSell == 0) {    // can buy stock -> 2 choices present
            // 1 - don't buy stock today, buy on next day -> profit from today: 0
            maxProfit = Math.max(0 + tradeStocks2TxnsRec(stockPrice, ithDay + 1, buyOrSell, txnCap), 
            -stockPrice[ithDay] + tradeStocks2TxnsRec(stockPrice, ithDay + 1, 1, txnCap));
        }   // 2 - buy today -> profit from today: loan of today's price -> can sell now on next day

        if(buyOrSell == 1) {    // can sell stock -> 2 choices present
            // 2 - don't sell today, sell on next day -> profit from today: 0
            maxProfit = Math.max(0 + tradeStocks2TxnsRec(stockPrice, ithDay + 1, buyOrSell, txnCap), 
            stockPrice[ithDay] + tradeStocks2TxnsRec(stockPrice, ithDay + 1, 0, txnCap - 1));
        }   // 2 - sell today -> profit: SP today -> txn closed -> can buy now on next today -> txn decrement by 1

        return maxProfit; 
    }

    // similar to BUY SELL STOCK - 1 TXN
    // For each day: we find max profit until today's SP(buying day from first day or until today)
    //  and max profit from today's BP(from selling day today until last day)
    // Max of total of both txns profit(checked at each day) gives max profit
    // In a day, a txn can be closed and next txn can be opened
    public static int tradeStocks2Txns(int[] stockPrice) {
        int leastBP = stockPrice[0];
        int[] maxProfitUptoIthDay = new int[stockPrice.length];
        for(int i = 1; i < stockPrice.length; i++) {    // each day is a selling day
            if(stockPrice[i] < leastBP)     // find least BP(till today) for today's SP to make max profit
                leastBP = stockPrice[i];

            int maxProfitSoldToday = stockPrice[i] - leastBP;

            // track max profit upto ith Day
            if(maxProfitSoldToday > maxProfitUptoIthDay[i - 1])
                maxProfitUptoIthDay[i] = maxProfitSoldToday;
            else    
                maxProfitUptoIthDay[i] = maxProfitUptoIthDay[i - 1];
        }

        int maxSP = stockPrice[stockPrice.length - 1];
        int[] maxProfitFromJthDayTillEnd = new int[stockPrice.length];  
        for(int j = stockPrice.length - 2; j >= 0; j--) {   // each day is a buying day
            if(stockPrice[j] > maxSP)   // find max SP(from today) for today's BP to make max profit
                maxSP = stockPrice[j];

            int maxProfitBoughtToday = maxSP - stockPrice[j];

            // track max profit from today till end
            if(maxProfitBoughtToday > maxProfitFromJthDayTillEnd[j + 1])
                maxProfitFromJthDayTillEnd[j] = maxProfitBoughtToday;
            else
                maxProfitFromJthDayTillEnd[j] = maxProfitFromJthDayTillEnd[j + 1];
        }

        int maxProfit = 0;
        for(int k = 0; k < stockPrice.length; k++) { 
            int profitForTwoTxns = maxProfitUptoIthDay[k] + maxProfitFromJthDayTillEnd[k];
            if(profitForTwoTxns > maxProfit)
                maxProfit = profitForTwoTxns;
        }   // max total of profits from both txns gives overall max profit
        return maxProfit;
    }
}
