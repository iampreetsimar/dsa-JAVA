// Given N: representing friends numbered 1 to N, each one can stay single or pair up
// with one other friend.
// Print all configs in w/c friends can remain single or can be paired up with a friend.

// NOTE: ONLY PRINT COMBINATIONS AND NOT PERMUTATIONS!!

// INPUT
// 3

// OUTPUT
// (1)(2)(3)
// (1)(2,3)
// (1,2)(3)
// (1,3)(2)

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class friendsPairing_II_10 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        solve(1, n, new boolean[n + 1], "");
    }

    // use level-options recursive approach
    // level: friend number | parameter and base case on friend number
    // options: already used friend | single option | pairing options with friends who are ahead
    // this approach generates only combination as at a level only 1 friend is given options
    // For permutations: all friends need to be given options at all levels
    public static void solve(int frndIdx, int n, boolean[] usedFriend, String asf) {
        if(frndIdx > n) {   // BASE CASE
            System.out.println(asf);
            return;
        }

        if(usedFriend[frndIdx]) {   // friend has already been used either singly or by pairing up
            solve(frndIdx + 1, n, usedFriend, asf); // move to next friend without any changes
            return;
        }

        usedFriend[frndIdx] = true; // stay single option - mark self as used
        solve(frndIdx + 1, n, usedFriend, asf + "(" + frndIdx + ")"); // move to next friend | add self as single

        // even if we loop from 1st friend to nth friend, output remains same due to usedFriend[]
        // and as at a level only 1 friend is given options and loop runs for all friends.
        // using frndIdx + 1 just so loop is smaller
        for(int j = frndIdx + 1; j <= n; j++) { // pair up with friends who are ahead
            if(!usedFriend[j]) {    // can only pair up if friends who are ahead are not already used
                usedFriend[j] = true;   // mark friend as used who is pairing up with you
                solve(frndIdx + 1, n, usedFriend, asf + "(" + frndIdx + "," + j + ")"); // move to your next friend
                usedFriend[j] = false;  // unmark friend while backtracking
            }
        }

        usedFriend[frndIdx] = false;    // stay single option - unmark self while backtracking
    }
}
