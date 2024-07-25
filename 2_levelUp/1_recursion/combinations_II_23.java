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

public class combinations_II_23 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int nBoxes = Integer.parseInt(parts[0]);
        int rItems = Integer.parseInt(parts[1]);

        // initial lastFilledBox: -1 as no box has been filled yet
        solve(new int[nBoxes], 1, rItems, -1);
    }

    // OBJECT/ITEM CHOOSES BOX APPROACH
    // permutations I -> by reducing arrangements -> combinations II
    // level-options -> levels are items from 1-rItems which are DISTINCT
    //               -> valid options are boxes after last filled box(will always be empty)
    // if an item is placed in a box, valid options are only those boxes which come after the last filled box
    // lastFilledBox: the idx of box which was filled last so newer items chooses box which come after it
    // lastFilledBox reduces arrangements and only selections are formed
    public static void solve(int[] boxes, int currItem, int totalItems, int lastFilledBox) {
        if(currItem > totalItems) {     // BASE CASE: all items placed
            for(int box: boxes) {
                if(box == 0) 
                    System.out.print("_");
                else 
                    System.out.print("i");  // since only selections needed
            }
            System.out.println();
            return;
        }

        // all boxes after lastFilledBox are valid options for currItem
        // so no need to check if box is empty or not
        for(int i = lastFilledBox + 1; i < boxes.length; i++) {
            boxes[i] = 1;   // box filled - no need to fill with currItem as only selections are needed
            solve(boxes, currItem + 1, totalItems, i);  // move to next item and curr box idx becomes last filled
            boxes[i] = 0;   // box empty while backtracking
        }
    }
}
