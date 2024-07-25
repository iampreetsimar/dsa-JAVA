// Given N boxes and r non-identical items, place the items in those boxes and print all such configs possible. 

// NOTE: - Items are numbered from 1 to r. 
//       - Number of boxes is greater than number of items, thus, some boxes may remain empty. 

// INPUT
// 4 2

// OUTPUT
// 1200
// 1020
// 1002
// 2100
// 0120
// 0102
// 2010
// 0210
// 0012
// 2001
// 0201
// 0021

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class permutations_I_20 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int nBoxes = Integer.parseInt(parts[0]);
        int rItems = Integer.parseInt(parts[1]);

        solve(new int[nBoxes], 1, rItems);
    }

    // OBJECT/ITEM CHOOSES THE BOX APPROACH
    // for each item, it has the choice of being places in any of the empty boxes
    // level-options -> levels are items from 1-rItems which are DISTINCT
    //               -> valid options are boxes which are 0 when empty
    // nPr = n!/(n-r)! <-> n * n-1 * n-2 * .... * n-r-1
    // 1st item has n boxes to choose from, 2nd item has n-2 boxes to choose from .. and so on.
    public static void solve(int[] boxes, int currItem, int totalItems) {
        if(currItem > totalItems) {     // BASE CASE: as initial currItem is 1
            for(int box: boxes) {
                System.out.print(box);
            }
            System.out.println();
            return;
        }
        
        for(int i = 0; i < boxes.length; i++) { // all boxes are options for curr item
            if(boxes[i] == 0) {     // valid options are empty boxes
                boxes[i] = currItem;    // place curr item in box
                solve(boxes, currItem + 1, totalItems);     // move to next item
                boxes[i] = 0;   // unplace curr item while backtracking
            }
        }
    }
}
