// Given M: length of highway(range), 
//       N: number of bill boards, 
//       N numbers: position(P) of bill-boards,
//       N numbers: revenue(R) corresponding to each position(P),
//       T: bill-boards can only be placed AFTER distance T.

// Find the max revenue that can be generated.

// INPUT
// 20
// 5
// 6 7 12 14 15
// 5 8  5  3  1
// 5

// OUTPUT
// 11

import java.io.*;
import java.util.HashMap;

public class highwayBillboard_31 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] pos = new int[n];
        int[] rev = new int[n];
        String[] rowPos = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            pos[i] = Integer.parseInt(rowPos[i]);
        }
        String[] rowRev = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            rev[i] = Integer.parseInt(rowRev[i]);
        }
        int t = Integer.parseInt(br.readLine());

        System.out.println(maxRevGenDP(pos, rev, t));
        System.out.println(maxRevGenDPOpt(m, pos, rev, t));
    }

    // use dp[] of size [n] for billboards:0->n-1
    // dp[i]: max revenue gen if ith bb is picked
    // if ith bb is picked -> valid bb before ith: pos[ith] - pos[before BB] > T(cannot be equal , needs to be more)
    // find max rev gen b/w valid prev bbs of ith bb, add ith bb's revenue to that max
    // Also track max b/w all max revenues -> solution
    // TC: O(N^2)
    public static int maxRevGenDP(int[] pos, int[] rev, int t) {
        int maxRev = 0;
        int[] dp = new int[pos.length];
        dp[0] = rev[0]; // 1st bb is always picked -> no bb before it

        for(int ithBB = 1; ithBB < dp.length; ithBB++) {
            int maxPrevRevForIthBB = 0;
            for(int jthBB = 0; jthBB < ithBB; jthBB++) {    // finds max rev from before bb's
                if(pos[ithBB] - pos[jthBB] > t) {   // only valid before bb's
                    maxPrevRevForIthBB = Math.max(maxPrevRevForIthBB, dp[jthBB]);
                }
            }
            dp[ithBB] = maxPrevRevForIthBB + rev[ithBB]; // add ith bb's rev to max valid before bb' rev
            maxRev = Math.max(maxRev, dp[ithBB]); // track if ith bb's rev is max or not overall
        }

        return maxRev; 
    }

    // use dp[] of size [m + 1] for highway range:0 -> m
    // dp[i]: max rev till highway range i
    // TC: O(M)
    public static int maxRevGenDPOpt(int m, int[] pos, int[] rev, int t) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < pos.length; i++) {   // add all pos:rev of bb to map
            map.put(pos[i], rev[i]);
        }   // needed as not all ranges of highway are bb positions

        int[] dp = new int[m + 1];

        for(int i = 0; i <= m; i++) {
            if(map.containsKey(i)) {    // if curr range is bb pos
                // if bb not picked -> max rev is same as max rev of curr range - 1
                int currBBNotPicked = dp[i - 1];

                // if bb picked -> max rev: curr range bb pos's rev + 0(T excluded range) + 
                //                          max rev from prev valid range(i - t - 1)
                // i - t - 1 should be >= 0, otherwise can go to -ve range
                int currBBPicked = (i - t - 1 >= 0) ? map.get(i) + 0 + dp[i - t - 1] : map.get(i) + 0;

                // max rev for curr range -> max rev from picked/not picked choices
                dp[i] = Math.max(currBBNotPicked, currBBPicked);  
            } else {
                // if curr range is not bb pos -> max rev for curr range is same as max rev from prev range
                // i-1: prev range must be >= 0, otherwise can go to -ve range
                dp[i] = (i - 1) >= 0 ? dp[i - 1] : 0;
            }  
        }

        return dp[m]; // max rev for highway
    }
}
