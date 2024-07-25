// Given N boxes and r identical items, place the items in those boxes and print all such configs possible. 

// NOTE: - Items are identical and all of them are named 'i'. 
//       - Number of boxes is greater than number of items, thus, some boxes may remain empty. 

// INPUT
// 4 2

// OUTPUT
// i i _ _
// i _ i _
// i _ _ i
// _ i i _
// _ i _ i
// _ _ i i

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class combinations_I_21 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int nBoxes = Integer.parseInt(parts[0]);
        int rItems = Integer.parseInt(parts[1]);

        // ssf: box selected so far (item placed so far)
        solve(1, nBoxes, 0, rItems, "");
    }

    // BOX CHOOSES APPROACH
    // need to place r IDENTICAL items in n boxes <-> need to choose r boxes out of n
    // level-options approach -> levels are boxes from 1 to n
    //                        -> options are box chosen or not chosen
    // 2^n = nC0 + nC1 + nC2 + .... + nCn
    // we are making combinations for 2^n and printing configs where only r box are chosen out of n
    // printing configs for nCr
    public static void solve(int currBox, int nBoxes, int ssf, int totalItems, String asf) {
        if(currBox > nBoxes) {  // BASE CASE : we get all comb. for 2^n
            if(ssf == totalItems) {     // valid configs are nCr
                System.out.println(asf);
            }
            return;
        }

        // select curr box <-> item placed in curr box -> move to next box
        solve(currBox + 1, nBoxes, ssf + 1, totalItems, asf + "i ");

        // don't select curr box <-> item will not be places -> move to next box
        solve(currBox + 1, nBoxes, ssf, totalItems, asf + "_ ");
    }
}
