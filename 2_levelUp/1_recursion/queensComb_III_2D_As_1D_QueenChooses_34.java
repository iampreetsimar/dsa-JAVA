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

public class queensComb_III_2D_As_1D_QueenChooses_34 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(0, n, new boolean[n][n], -1);
    }

    // same approach as QUEENS COMBINATIONS II but instead of 2D, we'll use 1D for loops
    // track lastFilledCell by using cell # instead of using (row, col)
    // using cell #, we can extrapolate (row, col): row = cell # / N | col = cel # % N
    public static void solve(int qpsf, int tq, boolean[][] board, int lastFilledCell) {
        if(qpsf == tq) {    // BASE CASE
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j]) 
                        System.out.print("q" + " ");
                    else
                        System.out.print("-" + " ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = lastFilledCell + 1; i < board.length * board.length; i++) {     // all cells after lastFilledCell
            int row = i / board.length;
            int col = i % board.length;
            board[row][col] = true;
            solve(qpsf + 1, tq, board, i);  // cell i becomes lastFilledCell for next queen
            board[row][col] = false;
        }
    }
}
