// Given N number of domino shaped magnets. You've to place these magnets in M * N board under following rules:
//     -> each box of 1*2 or 2*1 can contain a magnet or can be empty. 
//     -> empty box can be represented by 'X' and magnets are represented by '+' and '-' sign. 
//     -> digits along the left and top side of the board represents the number of '+' in 
//        corresponding rows and cols. 
//     -> digits along the right and bottom side of the board represents the number of '-' in 
//        corresponding rows and cols.
//     -> -1 denotes that the corresponding row and col can have any number of '+' and '-' signs. 
//     -> No two adjacent cell can have same sign. 

// INPUT FORMAT
// Number M and Number N
// M * N characters containing only 'L', 'R', 'T' and 'B'
//     (For 1*2 box, 'L' represents the left end and 'R' represents the right end)
//     (For 2*1 box, 'T' represents the top end and 'B' represents the bottom end)
// N integers representing the count of '+' along the top edge
// M integers representing the count of '+' along the left edge
// M integers representing the count of '-' along the right edge
// N integers representing the count of '-' along the bottom edge

// OUTPUT FORMAT
// M * N characters containing only '+', '-' and 'X'

// INPUT -1
// 5 6
// LRLRTT 
// LRLRBB
// TTTTLR
// BBBBTT
// LRLRBB
// 1 -1 -1 2 1 -1  -> top
// 2 3 -1 -1 -1    -> left 
// -1 -1 -1 1 -1   -> right
// 2 -1 -1 2 -1 3  -> bottom

// OUTPUT - 1
// + - + - X - 
// - + - + X + 
// X X + - + - 
// X X - + X + 
// - + X X X - 

// INPUT - 2
// 4 3
// TTT
// BBB 
// TLR
// BLR 
// 2 -1 -1
// -1 -1 2 -1
// 0 -1 -1 -1
// -1 -1 2

// OUTPUT - 2
// + X +
// – X –
// + – +
// – + –

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class magnets_47 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int M = Integer.parseInt(parts[0]);
        int N = Integer.parseInt(parts[1]);

        char[][] board = new char[M][N];
        for(int i = 0; i < board.length; i++) {
            char[] row = br.readLine().toCharArray();
            board[i] = row;
        }

        int[] top = new int[N];
        int[] bottom = new int[N];
        int[] left = new int[M];
        int[] right = new int[M];

        String[] topParts = br.readLine().split(" ");
        for(int i = 0; i < top.length; i++) {
            top[i] = Integer.parseInt(topParts[i]);
        }

        String[] leftParts = br.readLine().split(" ");
        for(int i = 0; i < left.length; i++) {
            left[i] = Integer.parseInt(leftParts[i]);
        }

        String[] rightParts = br.readLine().split(" ");
        for(int i = 0; i < right.length; i++) {
            right[i] = Integer.parseInt(rightParts[i]);
        }

        String[] bottomParts = br.readLine().split(" ");
        for(int i = 0; i < bottom.length; i++) {
            bottom[i] = Integer.parseInt(bottomParts[i]);
        }

        char[][] res = new char[M][N];
        for(char[] row: res) {
            Arrays.fill(row, 'X');
        }

        boolean flag = solve(board, top, left, right, bottom, res, 0, 0);
        if(flag) display(res);
        else System.out.println("No config possible!");
    }

    public  static boolean solve(char[][] board, int[] top, int[] left, int[] right, 
                              int[] bottom, char[][] res, int row, int col) {
        if(col == board[0].length) { col = 0; row++; }

        if(row == board.length) {
            if(isConfigValid(res, top, left, right, bottom)) return true;
            return false;
        }
        
        if(board[row][col] == 'L') {
            if(canBePlaced(res, top, left, right, bottom, row, col, '+') &&
                                    canBePlaced(res, top, left, right, bottom, row, col + 1, '-')) {
                res[row][col] = '+'; res[row][col + 1] = '-';
                if(solve(board, top, left, right, bottom, res, row, col + 2)) return true;
                res[row][col] = 'X'; res[row][col + 1] = 'X';
            }

            if(canBePlaced(res, top, left, right, bottom, row, col, '-') &&
                                    canBePlaced(res, top, left, right, bottom, row, col + 1, '+')) {
                res[row][col] = '-'; res[row][col + 1] = '+';
                if(solve(board, top, left, right, bottom, res, row, col + 2)) return true;
                res[row][col] = 'X'; res[row][col + 1] = 'X';
            }
        }else if(board[row][col] == 'T') {
            if(canBePlaced(res, top, left, right, bottom, row, col, '+') &&
                                    canBePlaced(res, top, left, right, bottom, row + 1, col, '-')) {
                res[row][col] = '+'; res[row + 1][col] = '-';
                if(solve(board, top, left, right, bottom, res, row, col + 1)) return true;
                res[row][col] = 'X'; res[row + 1][col] = 'X';
            }

            if(canBePlaced(res, top, left, right, bottom, row, col, '-') &&
                                    canBePlaced(res, top, left, right, bottom, row + 1, col, '+')) {
                res[row][col] = '-'; res[row + 1][col] = '+';
                if(solve(board, top, left, right, bottom, res, row, col + 1)) return true;
                res[row][col] = 'X'; res[row + 1][col] = 'X';
            }
        }

        // exclude
        if(solve(board, top, left, right, bottom, res, row, col + 1)) return true; 

        return false;
    }

    public static boolean canBePlaced(char[][] res, int[] top, int[] left, int[] right, int[] bottom, 
                                      int row, int col, char sign) {
        if(col - 1 >= 0 && res[row][col - 1] == sign) return false;
        if(row - 1 >= 0 && res[row - 1][col] == sign) return false;
        if(col + 1 < res[0].length && res[row][col + 1] == sign) return false;

        if(sign == '+') {
            int cir = countSignInRow(res, row, sign);
            if(left[row] != -1 && cir >= left[row]) return false;

            int cic = countSignInCol(res, col, sign);
            if(top[col] != -1 && cic >= top[col]) return false;
        } else {
            int cir = countSignInRow(res, row, sign);
            if(right[row] != -1 && cir >= right[row]) return false;

            int cic = countSignInCol(res, col, sign);
            if(bottom[col] != -1 && cic >= bottom[col]) return false;
        }
        return true;
    }

    public static int countSignInRow(char[][] res, int row, char sign) {
        int signCounter = 0;
        for(int j = 0; j < res[0].length; j++) {
            if(res[row][j] == sign) signCounter++;
        }
        return signCounter;
    }

    public static int countSignInCol(char[][] res, int col, char sign) {
        int signCounter = 0;
        for(int i = 0; i < res.length; i++) {
            if(res[i][col] == sign) signCounter++;
        }
        return signCounter;
    }

    public static boolean isConfigValid(char[][] res, int[] top, int[] left, int[] right, int[] bottom) {
        for(int i = 0; i < res.length; i++) {
            int cirPos = countSignInRow(res, i, '+');
            if(left[i] != -1 && cirPos != left[i])  return false;

            int cirNeg = countSignInRow(res, i, '-');
            if(right[i] != -1 && cirNeg != right[i])  return false;
        }

        for(int j = 0; j < res[0].length; j++) {
            int cicPos = countSignInCol(res, j, '+');
            if(top[j] != -1 && cicPos != top[j]) return false;

            int cicNeg = countSignInCol(res, j, '-');
            if(bottom[j] != -1 && cicNeg != bottom[j]) return false;
        }

        return true;
    }

    public static void display(char[][] arr) {
        System.out.println("Board: ");
        for(char[] row: arr) {
            for(char ch: row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}
