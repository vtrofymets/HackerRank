import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Vlad Trofymets
 */
public class StackTask {

    private static final Map<Character, Character> CH_MAP;

    static {
        CH_MAP = new HashMap<>();
        CH_MAP.put('{', '}');
        CH_MAP.put('[', ']');
        CH_MAP.put('(', ')');
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);

        while (sc.hasNext()) {
            var input = sc.next();
            System.out.println(calculate(input));
        }
    }

    private static boolean calculate(String str) {
        if (str.length() % 2 == 0) {
            Deque<Character> deque = new LinkedList<>();

            for (int i = 0; i < str.length(); i++) {
                if (CH_MAP.containsKey(str.charAt(i))) {
                    deque.addFirst(str.charAt(i));
                } else {
                    Character pollC = deque.pollFirst();
                    Character value = CH_MAP.get(pollC);
                    if (!Objects.equals(value, str.charAt(i))) {
                        return false;
                    }
                }
            }
            return deque.isEmpty();
        }
        return false;
    }


}
