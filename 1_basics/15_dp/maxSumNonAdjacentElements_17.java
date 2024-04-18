// Given N: count of elements, N numbers: N elements.
// Find max sum of a subsequence with no adjacent elements.

// INPUT
// 6
// 5 10 10 100 10 6

// OUTPUT
// 116

import java.io.*;

public class maxSumNonAdjacentElements_17 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] items = new int[n];
        String[] row = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(row[i]);
        }

        System.out.println(maxSumNonAdjElements(items, 0));
        System.out.println(maxSumNonAdjElementsTabOpt(items));
        System.out.println(maxSumNonAdjElementsTabAlternative(items));
    }

    // RECURSIVE - ALTERNATIVE
    public static int maxSumNonAdjElements(int[] items, int idx) {
        if(idx >= items.length) // BASE CASE: idx is out of bounds -> no item present   
            return 0;   // max sum will be 0

        // incl curr item -> move to 2nd next item(return curr item + max sum from 2nd next item till end) 
        // excl curr item -> move to next item(return max sum from next item till end)
        // return max from both choices -> return max sum from curr item till end
        return Math.max(items[idx] + maxSumNonAdjElements(items, idx + 2), 
                        maxSumNonAdjElements(items, idx + 1));
    }

    // inclSum at idx -> maxSum of non-adj subseq.(chosen from items at index:0->idx) where items[idx] is included
    // exclSum at idx -> maxSum of non-adj subseq.(chosen from items at index:0->idx) where items[idx] is excluded
    public static int maxSumNonAdjElementsTabOpt(int[] items) {
        int inclSum = items[0]; // initial sum for incl 0th item
        int exclSum = 0;    // initial sum for excl 0th item

        for(int idx = 1; idx < items.length; idx++) {
            // curr item can be incl only if prev item was excl
            // add curr item to prev excl max sum -> becomes max sum for curr item
            int currInclSum = items[idx] + exclSum; 

            // curr item can be excl if prev item was incl as well as excl
            // choose max sum from both choices -> becomes max sum for curr item as well
            int currExclSum = Math.max(inclSum, exclSum);

            // curr sum becomes prev sums for next item
            inclSum = currInclSum;  
            exclSum = currExclSum;
        }

        // choose max from max sum for a subseq having non adjacent items
        return Math.max(inclSum, exclSum);  
    }

    // use RECURSIVE - ALTERNATIVE approach
    // use dp[] here but can do this in constant space
    // dp[idx]: max sum of a non adj subseq using items at index:0->idx 
    public static int maxSumNonAdjElementsTabAlternative(int[] items) {
        int[] dp = new int[items.length];
        dp[0] = items[0];   // base case -> max sum always when 1st item picked

        for(int i = 1; i < dp.length; i++) {
            int pick = items[i];    // if curr item picked -> add curr item to picked max sum
            if(i > 1)   // also can only add 2nd prev item -> add max sum from it to picked max sum
                pick += dp[i - 2];  
            
            int notPick = dp[i - 1];  // if curr item not picked -> max sum used from prev item's max sum

            dp[i] = Math.max(pick, notPick);    // curr item's max sum: max b/w 2 choices
        }

        return dp[dp.length - 1];   
    }
}
