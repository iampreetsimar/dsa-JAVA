// Given N: N * N chess board, calculate and print the combinations in which N knights can be placed
// on the N * N chess board. 

// NOTE: -> Knights are similar ,i.e, (k, k, k, ...). 
//       -> No knight should be able to kill another, thus, need to check valid positions.

// INPUT
// 3

// OUTPUT
// kkk
// ---
// --- (once such combination)

// ...

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class nKnightsComb_2D_As_1D_KnightChooses_37 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(0, n, new boolean[n][n], -1);
    }

    // similar to NQUEENS COMBINATIONS - 2D as 1D - QUEEN  CHOOSES but instead of queens, place knights
    // only cell w/c are after the last filled cell are options
    // options become valid if knight can be placed there
    public static void solve(int kpsf, int tk, boolean[][] board, int lastFilledCell) {
        if(kpsf == tk) {    // BASE CASE
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j]) 
                        System.out.print("k" + " ");
                    else
                        System.out.print("-" + " ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = lastFilledCell + 1; i < board.length * board.length; i++) { // options are cells after lastFilledCell - always empty
            int row = i / board.length;
            int col = i % board.length;

            if(canKnightBePlaced(board, row, col)) {    // valid options are only those where knight cannot kill annother knight
                board[row][col] = true;
                solve(kpsf + 1, tk, board, i);  // cell #: i becomes lastFilledCell for next knight 
                board[row][col] = false;
            }
        }
    }

    // for a cell to be a valid position for knight, check in 4 directions w/c are before the curr cell as cells after are always empty
    public static boolean canKnightBePlaced(boolean[][] board, int row, int col) {
        int X[] = {-1, -2, -2, -1};     // row values for 4 directions
        int Y[] = {2, 1, -1,  -2};      // col values for 4 directions

        for(int k = 0; k < X.length; k++) {     // check 4 directions w.r.t. curr cell
            int x = row + X[k];
            int y = col + Y[k];

            if(x >= 0 && x < board.length && y >= 0 && y < board.length && board[x][y])   
                return false;
        }

        return true;    // no knight placed in cells where curr knight can kill them, curr knight can be placed
    }
}
