import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author Vlad Trofymets
 */
public class JavaComparator {

    public static void main(String[] args) {

    }

}

class Checker implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        if (o1.score > o2.score) {
            return -1;
        } else if (o1.score < o2.score) {
            return 1;
        } else {
            return o1.name.compareTo(o2.name);
        }
    }
}

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
