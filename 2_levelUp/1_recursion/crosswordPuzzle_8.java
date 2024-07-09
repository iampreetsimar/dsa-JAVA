// Given a 10 * 10 2D array containing only '+' and '-' characters, w/c represent a crossword puzzle.
// Given N number of words which need to be filled into the puzzle.
// Cells containing '-' are to be filled with the given words.

// INPUT
// +  -  +  +  +  +  +  +  +  + 
// +  -  +  +  +  +  +  +  +  + 
// +  -  +  +  +  +  +  +  +  + 
// +  -  -  -  -  -  +  +  +  + 
// +  -  +  +  +  -  +  +  +  + 
// +  -  +  +  +  -  +  +  +  + 
// +  +  +  +  +  -  +  +  +  + 
// +  +  -  -  -  -  -  -  +  + 
// +  +  +  +  +  -  +  +  +  + 
// +  +  +  +  +  -  +  +  +  + 
// LONDON DELHI ICELAND ANKARA

// OUTPUT
// +  L  +  +  +  +  +  +  +  + 
// +  O  +  +  +  +  +  +  +  + 
// +  N  +  +  +  +  +  +  +  + 
// +  D  E  L  H  I  +  +  +  + 
// +  O  +  +  +  C  +  +  +  + 
// +  N  +  +  +  E  +  +  +  + 
// +  +  +  +  +  L  +  +  +  + 
// +  +  A  N  K  A  R  A  +  + 
// +  +  +  +  +  N  +  +  +  + 
// +  +  +  +  +  D  +  +  +  + 

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class crosswordPuzzle_8 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] arr = 
        // {
        //     {'+', '-', '+'},
        //     {'-', '-', '-'},
        //     {'+', '-', '+'}
        // };
        {
            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
            {'+', '-', '-', '-', '-', '-', '+', '+', '+', '+'},
            {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
            {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
            {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
            {'+', '+', '-', '-', '-', '-', '-', '-', '+', '+'},
            {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
            {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        };
        String[] words = br.readLine().split(" ");

        solve(arr, words, 0);
    }

    // level-options recursion
    // level on word: parameter on word idx, base case on word idx
    // options on each cell: for loop
    public static void solve(char[][] arr, String[] words, int wIdx) {
        if(wIdx == words.length) {  // BASE CASE
            System.out.println("Config: ");
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        String word = words[wIdx];
        for(int i = 0; i < arr.length; i++) {   // check all valid options
            for(int j = 0; j < arr[0].length; j++) {
                // valid options - if cell empty or same char already placed
                if(arr[i][j] == '-' || arr[i][j] == word.charAt(0)) {
                    //  find all valid configs - both types of placement: horizontal and vertical
                    if(canWordBePlacedHorizontally(arr, i, j, word)) {
                        boolean[] visited = placeWordHorizontally(arr, i, j, word);
                        solve(arr, words, wIdx + 1);    // move to next level
                        unplaceWordPlacedHorizontally(arr, i, j, visited);  // backtracking
                    }

                    if(canWordBePlacedVertically(arr, i, j, word)) {
                        boolean[] visited = placeWordVertically(arr, i, j, word);
                        solve(arr, words, wIdx + 1);    // move to next level
                        unplaceWordPlacedVertically(arr, i, j, visited);    // backtracking
                    }
                }
            }
        }
    }

    public static boolean canWordBePlacedHorizontally(char[][] arr, int i, int j, String word) {
        if(j - 1 >= 0 && arr[i][j - 1] != '+') // left cell of word start should be outside range or have '+'
            return false;

        if(j + word.length() < arr[0].length && arr[i][j + word.length()] != '+')
            return false;   // right cell of word end should be outside range or have '+'

        for(int jj = 0; jj < word.length(); jj++) {
            if(j + jj >= arr[0].length) // can word be placed fully
                return false;

            if(arr[i][j + jj] == '-' || arr[i][j + jj] == word.charAt(jj))
                continue;   // if empty cell or char of word already placed
            else
                return false;
        }
        return true;    // word can be placed horizontally
    }

    public static boolean[] placeWordHorizontally(char[][] arr, int i, int j, String word) {
        boolean[] visited = new boolean[word.length()];
        for(int jj = 0; jj < word.length(); jj++) {
            if(arr[i][j + jj] == '-') { // if empty cell
                arr[i][j + jj] = word.charAt(jj);   // add char to cell
                visited[jj] = true; // mark visited true so that only umark char w/c are added by us
            }
        }
        return visited;
    }

    public static void unplaceWordPlacedHorizontally(char[][] arr, int i, int j, boolean[] visited) {
        for(int jj = 0; jj < visited.length; jj++) {
            if(visited[jj]) // char added by us
                arr[i][j + jj] = '-';   // remove it while backtracking
        }
    }

    public static boolean canWordBePlacedVertically(char[][] arr, int i, int j, String word) {
        if(i - 1 >= 0 && arr[i - 1][j] != '+')
            return false;   // upward cell of word start should be outside range or have '+'

        if(i + word.length() < arr.length && arr[i + word.length()][j] != '+')
            return false;   // downward cell of word end should be outside range or have '+'

        for(int ii = 0; ii < word.length(); ii++) {
            if(i + ii >= arr.length)    // can word be placed fully
                return false;

            if(arr[i + ii][j] == '-' || arr[i + ii][j] == word.charAt(ii))
                continue;   // if empty cell or char of word already placed
            else
                return false;
        }
        return true;    // word can be placed vertically
    }

    public static boolean[] placeWordVertically(char[][] arr, int i, int j, String word) {
        boolean[] visited = new boolean[word.length()];
        for(int ii = 0; ii < word.length(); ii++) {
            if(arr[i + ii][j] == '-') { // if empty cell
                arr[i + ii][j] = word.charAt(ii);   // add char to cell
                visited[ii] = true; // mark visited true so that only umark char w/c are added by us
            }
        }
        return visited;
    }

    public static void unplaceWordPlacedVertically(char[][] arr, int i, int j, boolean[] visited) {
        for(int ii = 0; ii < visited.length; ii++) {
            if(visited[ii]) // char added by us
                arr[i + ii][j] = '-';   // remove it while backtracking
        }
    }
}
