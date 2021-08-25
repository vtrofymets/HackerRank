import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.System.in;

/**
 * @author Vlad Trofymets
 */
public class PrimeChecker {

    public static void main(String[] args) {
        Prime prime = new Prime();
        prime.checkPrime(2);
        prime.checkPrime(3);
        prime.checkPrime(125);
        prime.checkPrime(127);
    }

    public static class Prime {

        public void checkPrime(Integer... integers) {
            if(integers.length > 0) {
                for (Integer n : integers) {
                    if (isPrime(n)) {
                        System.out.print(n + " ");
                    }
                }
            }
            System.out.println();
        }

        public static boolean isPrime(Integer n) {
            if (n > 1) {
                int sqrt = (int) Math.sqrt(n);
                for (int i = 2; i <= sqrt; i++) {
                    if (n % i == 0) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    }
}
