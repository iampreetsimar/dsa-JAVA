// Given N: N * N chess board, calculate and print the combinations in which N queens can be placed
// on the N * N chess board. 

// NOTE: -> Queens are similar ,i.e, (q, q, q, ...). No need to check valid positions here. Just place queen. 

// INPUT
// 2

// OUTPUT
// q q 
// - -

// q - 
// q - 

// q - 
// - q 

// - q 
// q - 

// - q 
// - q 

// - - 
// q q

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class queensComb_I_2D_As_2D_BoxChooses_30 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        solve(0, 0, 0, n, "");
    }

    // similar approach to COMBINATIONS - I - BOX CHOOSES
    // use level-options approach: 
    //      -> each cell(row, col) on levels
    //      -> 2 choices for each cell(place queen or don't place queen) as options
    // we get all combinations of 2^n*n and choose result where n*nCn which has n queens places in board(n*n)
    public static void solve(int row, int col, int qpsf, int tq, String asf) {
        if(row == tq) {     // BASE CASE
            if(qpsf == tq) {    // n queens places -> n*nCn
                System.out.println(asf);
            }
            return;
        }

        int nextRow, nextCol;
        String sep;

        if(col == tq - 1) {     // if last col: nextRow -> row++, nextCol -> 0 and sep -> nextline 
            nextRow = row + 1;
            nextCol = 0;
            sep = "\n";
        } else {    // nextRow -> row, nextCol -> col + 1 and sep -> " "
            nextRow = row; nextCol = col + 1;
            sep = " ";
        }

        solve(nextRow, nextCol, qpsf + 1, tq, asf + "q" + sep); // place queen and increment qpsf by 1
        solve(nextRow, nextCol, qpsf + 0, tq, asf + "-" + sep); // don't place queen and increment qpsf by 0
    }
}
