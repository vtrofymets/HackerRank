import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Vlad Trofymets
 */
public class JavaDequeue {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int uniques = 0;

        for (int i = 0; i < n; i++) {
            // 2,3,4,5
            int num = in.nextInt();
            deque.add(num);
            set.add(num);

            if (deque.size() == m) {
                int s = set.size();
                if (s > uniques) {
                    uniques = s;
                }
                Integer poll = deque.pollFirst();
                if (!deque.contains(poll)) {
                    set.remove(poll);
                }
            }
        }

        System.out.println(uniques);
    }

}
