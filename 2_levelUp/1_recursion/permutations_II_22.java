// Given N boxes and r non-identical items, place the items in those boxes and print all such configs possible. 

// NOTE: - Items are numbered from 1 to r. 
//       - Number of boxes is greater than number of items, thus, some boxes may remain empty. 

// INPUT
// 4 2

// OUTPUT
// 12__
// 1_2_
// 1__2
// 21__
// _12_
// _1_2
// 2_1_
// _21_
// __12
// 2__1
// _2_1
// __21

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class permutations_II_22 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int nBoxes = Integer.parseInt(parts[0]);
        int rItems = Integer.parseInt(parts[1]);
        
        solve(1, nBoxes, new int[rItems], 0, rItems, "");
    }

    // BOX CHOOSES APPROACH
    // combinations I -> by adding arrangements to selections -> permutation II
    // each box has choices: places any of items not yet placed or does not place anything
    // items[]: represents which items are placed or not
    // ssf: boxes choosen so far <-> items placed so far
    public static void solve(int currBox, int nBoxes, int[] items, int ssf, int totalItems, String asf) {
        if(currBox > nBoxes) {  // BASE CASE : all boxes choices complete
            if(ssf == totalItems) {     // valid configs where r items placed
                System.out.println(asf);
            }
            return;
        }

        // including choice - places any unplaced items
        for(int i = 0; i < items.length; i++) {
            if(items[i] == 0) {     // check if item has been placed or not
                items[i] = 1;   // item placed

                // (i + 1) as items are numbered from 1-rItems
                // move to next box
                solve(currBox + 1, nBoxes, items, ssf + 1, totalItems, asf + (i + 1)); 

                items[i] = 0;   // item unplaced while backtracking
            }
        }

        // excluding choices - does not place anything
        solve(currBox + 1, nBoxes, items, ssf, totalItems, asf + "_");
    }
}
