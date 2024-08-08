// Given N: N * N chess board, calculate and print the permutations in which N queens can be placed
// on the N * N chess board. 

// NOTE: -> Queens are distinct ,i.e, (q1, q2, q3, ...). No need to check valid positions here. Just place queen. 

// INPUT
// 2

// OUTPUT
// q1 q2 
// - -

// q1 - 
// q2 - 

// q1 - 
// - q2 

// q2 q1 
// - - 

// q2 - 
// q1 -

// ....

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class queensPerm_II_2D_As_2D_BoxChooses_32 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(0, 0, 0, n, "", new boolean[n]);
    }

    // similar to PERMUTATIONS II - BOX CHOOSES(adds arrangements to COMBINATIONS I)
    // use level-options approach: -> each cell on levels
    //                             -> all unplaced queens or no placement as options
    // use boolean[] to track which queen has been placed so far or not
    // choose only those arrangements where only N queens are placed
    public static void solve(int row, int col, int qpsf, int tq, String asf, boolean[] qp) {
        if(row == tq) {     // BASE CASE
            if(qpsf == tq) {    // only n queens placed
                System.out.println(asf);
            }
            return;
        }

        int nextRow, nextCol; String sep;

        if(col == tq - 1) { // value of nextRow, nextCol and sep based on last col or not
            nextRow = row + 1; nextCol = 0; sep = "\n";
        } else {
            nextRow = row; nextCol = col + 1; sep = " ";
        }

        for(int i = 0; i < qp.length; i++) {  // including choices on all queens
            if(qp[i] == false) {    // only unplaced queens as valid options
                qp[i] = true;   // place queen in curr cell
                solve(nextRow, nextCol, qpsf + 1, tq, asf + "q" + (i + 1) + sep, qp);   // i+1 as i is idx of queen
                qp[i] = false;  // unplace queen while backtracking
            }
        }

        solve(nextRow, nextCol, qpsf, tq, asf + "-" + sep, qp); // excluding choice - no queens placed
    }
}
