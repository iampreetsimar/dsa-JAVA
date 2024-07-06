// Given a list of words, a list of alphabets(might be repeating) and score each alphabet from a to z.
// Find max score of any valid set of words formed by using the given alphabets.

// NOTE: - A word cannot be used more than one time.
//       - Each alphabet can be used only once.
//       - It is not necessary to use all the given alphabets.

// INPUT
// dog cat dad good    -> words
// a b c d d d g o o   -> alphabets
// 1 0 9 5 0 0 3 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 -> score of each alphabet

// OUTPUT
// 23

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class maxScoreOfWords_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] words = br.readLine().split(" ");
        
        String[] chars = br.readLine().split(" ");
        
        int[] scoreArr = new int[26];
        String[] scores = br.readLine().split(" ");
        for(int i = 0; i < scoreArr.length; i++) {
            scoreArr[i] = Integer.parseInt(scores[i]);
        }

        int[] freqArr = new int[26];
        for(String ch: chars) {
            freqArr[ch.charAt(0) - 'a']++;
        }
        
        System.out.println(solution(words, freqArr, scoreArr, 0));
    }

    public static int  solution(String[] words, int[] freqArr, int[] scoreArr, int currIdx) {
        if(currIdx == words.length) {   // BASE CASE
            return 0;   // no char -> score : 0 
        }

        // exclude curr word choice: curr word's score would be 0
        int scoreNotTake = 0 + solution(words, freqArr, scoreArr, currIdx + 1);
        int scoreCurrWord = 0;
        int scoreTake = 0;

        String word = words[currIdx];
        boolean canWordBeTaken = true;  // flag to check if curr word can be included

        // mark freq of chars: decrement their freq 
        for(int i = 0; i < word.length(); i++) {    
            char ch = word.charAt(i);
            if(freqArr[ch - 'a'] <= 0)  // if no freq left -> word can't be included
                canWordBeTaken = false; // not breaking here, otherwise we'll need to track till which char
                                        // freq was decremented and increment till that char during unmarking
            freqArr[ch - 'a']--; scoreCurrWord += scoreArr[ch - 'a'];
        }

        if(canWordBeTaken)    // include curr word only if word can be taken
            scoreTake = scoreCurrWord + solution(words, freqArr, scoreArr, currIdx + 1);

        // unmark(increment) freq of chars due to backtracking
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i); freqArr[ch - 'a']++;
        }

        return Math.max(scoreNotTake, scoreTake);
    }
}
