// Given N and k, 
//     N: total # of soldiers standing in a circle having position marked from 0 to N-1

// A cruel king want to execute them, he starts executing soldiers starting from 0th position and proceeds
// around the circle in clockwise direction.
// In each step, k - 1 soldiers are skipped and the kth soldier is executed.
// The elimination proceeds around the circle(which is becoming smaller and smaller as the ececuted soldiers 
// are removed), until only the last soldier remains, who is given freedom.

// Find the position of the remaining soldier.

// INPUT
// 8 3
// 9 4
// 7 4

// OUTPUT
// 6
// 0
// 1

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class josephusProblem_4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int k = Integer.parseInt(parts[1]);

        System.out.println(solution(n, k));
    }   
    
    public static int solution(int n, int k) {
        if(n == 1)  // BASE CASE: only 1 soldier left -> 0th position
            return 0;

        // returns position of survivor among n - 1 soldiers
        int survivedInNMinus1 = solution(n - 1, k); 

        // formula to convert survivor position from n-1 to n soldiers
        return (survivedInNMinus1 + k) % n; 
    }
}
