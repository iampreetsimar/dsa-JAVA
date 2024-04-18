// Given N: number of fences, k: number of colors
// Calculate and print the number of ways in which the fences could be painted such that
// not more than 2 consecutive fences have same color.

// INPUT
// 5 3

// OUTPUT
// 180

import java.io.*;

public class paintFence_20 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        
        System.out.println(countPaintFenceWaysDPOpt(n, k));
    }

    // fences range: 1 -> n
    // iiSame at fence f: total ways to paint upto fth fence using k colors such that
    // not more than 2 adj fences have same color and the last two fences have same color

    // ijDiff at fence f: total ways to paint upto fth fence using k color such that
    // not more than 2 adj fences have same color and the last two fences have diff colors

    // totalWaysForFthFence: total valid ways to paint upto fth fence such that
    // not more than 2 adj fences have same color
    public static long countPaintFenceWaysDPOpt(int n, int k) {
        long iiSame = 0;
        long ijDiff = 0;
        long totalWaysForFthFence = 0;

        for(int fthFence = 2; fthFence <= n; fthFence++) {
            if(fthFence == 2) { // only 2 fences 
                // same color on both fences -> k ways as k colors
                iiSame = k;   // red,red | blue,blue | green,green

                // k choices for 1st fence and (k-1) for 2nd fence
                ijDiff = k * (k - 1);   // red,green | red,blue | green,blue | green,red | blue,green | blue,red
            } else {
                // can only add 1 color w/c is ame as prev color in prev diff choice
                iiSame = ijDiff * 1;    // red,green -> red,green,green

                // can add (k - 1) diff color from total to make them diff
                // red,red -> red,red,blue | red,red,green
                // red,green -> red,green,red | red,green,blue
                ijDiff = totalWaysForFthFence * (k - 1); 
            }

            totalWaysForFthFence = iiSame + ijDiff; // total ways for fth fence
        }

        return totalWaysForFthFence;
    }
}
