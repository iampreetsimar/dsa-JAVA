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

// - q1 
// q2 - 

// - q1 
// - q2

// q2 - 
// q1 -

// ....

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class queensPerm_I_2D_As_2D_QueenChooses_31 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solve(0, n, new int[n][n]);
    }

    // similar to PERMUTATIONS - I - ITEM CHOOSES
    // use level-options approach:
    //          -> queen on levels(as queen chooses the options)
    //          -> any unfilled cell in board as options
    // we get (# of combinations * n!) total arrangements by arranging n queens in each combination
    public static void solve(int qpsf, int n, int[][] board) {
        if(qpsf == n) {     // BASE CASE
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] != 0) 
                        System.out.print("q" + board[i][j] + " ");
                    else
                        System.out.print("-" + " ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < n; i++) {    // n * n options
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 0) {  // only unfilled are valid
                    board[i][j] = qpsf + 1;
                    solve(qpsf + 1, n, board);  // increment qpsf by 1
                    board[i][j] = 0;    // unfill cell while backtracking
                }
            }
        }
    }
}
