/*
 * Given t iterations and a number n t times, print t times if n is prime or not
 * For considered approach, T.C = O(t * sqrt(n))
*/
import java.util.*;

public class isNumberPrime_1{
    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            int t = s.nextInt();

            for(int i = 0; i < t; i++) {
                int n = s.nextInt();

                int count = 0;
                for(int j = 2; j * j <= n; j++) {
                    if(n % j == 0) {
                        count++;
                        break;
                    }     
                }

                if(count == 0)
                    System.out.println("prime");
                else
                    System.out.println("not prime");
            }
        }
    }
}