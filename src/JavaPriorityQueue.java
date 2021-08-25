import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Vlad Trofymets
 */
public class JavaPriorityQueue {

    public static void main(String[] args) {
        List<String> events = List.of("ENTER John 3.75 50", "ENTER Mark 3.8 24", "ENTER AAA 3.8 24",
                "ENTER Shafaet 3.7 35");
        Priorities priorities = new Priorities();
        List<Student> students = priorities.getStudents(events);
        System.out.println(students);
        students = priorities.getStudents(List.of("SERVED"));
        System.out.println(students);
    }

}

class Priorities {

    public static final String DELIMITER = " ";
    private static final Pattern S_PATTERN = Pattern.compile(DELIMITER);
    public static final Comparator<Student> COMPARATOR = Comparator.comparingDouble(Student::getCgpa)
            .reversed()
            .thenComparing(Student::getName);

    private final List<Student> students = new ArrayList<>();

    public List<Student> getStudents(List<String> events) {
        events.forEach(s -> process(S_PATTERN.split(s, 2)));
        return students;
    }

    private void process(String[] strings) {
        EVENT event = EVENT.valueOf(strings[0].trim());

        if (event == EVENT.ENTER) {
            String[] spArr = S_PATTERN.split(strings[1]);
            Student student = new Student(spArr[0].trim(), Double.parseDouble(spArr[1]), Integer.parseInt(spArr[2]));
            students.add(student);
            students.sort(COMPARATOR);
        } else {
            if (!students.isEmpty()) {
                students.remove(0);
            }
        }
    }


}

class Student {

    private final String name;
    private final double cgpa;
    private final int id;

    public Student(String name, double cgpa, int id) {
        this.name = name;
        this.cgpa = cgpa;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", cgpa=" + cgpa + ", id=" + id + '}';
    }
}

enum EVENT {
    ENTER,
    SERVED
}


