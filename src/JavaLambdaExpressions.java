import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author Vlad Trofymets
 */
public class JavaLambdaExpressions {

    public static void main(String[] args) {
        MyMath myMath = new MyMath();
        PerformOperation palindrome = myMath.isPalindrome();
        System.out.println(MyMath.checker(palindrome, 121));
    }

}

interface PerformOperation {
    boolean check(int a);
}

class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    public PerformOperation isOdd() {
        return i -> i % 2 != 0;
    }

    public PerformOperation isPrime() {
        return n -> {
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
        };
    }

    public PerformOperation isPalindrome() {
        return i -> {
            if (i < 1) {
                return false;
            }
            String x = i + "";
            char[] chars = x.toCharArray();
            for (int j = 0; j < chars.length / 2; j++) {
                char c = chars[j];
                chars[j] = chars[chars.length - 1 - j];
                chars[chars.length - 1 - j] = c;
            }
            String r = new String(chars);
            return Objects.equals(x, r);
        };
    }
}
