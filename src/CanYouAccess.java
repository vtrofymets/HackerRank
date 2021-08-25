import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Vlad Trofymets
 */
public class CanYouAccess {

    public static final int num = 7;

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Object o;
        o = new Inner().new Private();
        Method method = o.getClass()
                .getDeclaredMethod("powerof2", int.class);
        method.setAccessible(true);
        System.out.println(num + " is " + method.invoke(o, num));
    }

    static class Inner {
        private class Private {
            private String powerof2(int num) {
                return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
            }
        }
    }
}