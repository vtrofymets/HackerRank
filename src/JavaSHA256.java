import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @author Vlad Trofymets
 */
public class JavaSHA256 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        messageDigest.update(s.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder(2 * digest.length);
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if(hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        System.out.println(sb);
    }

}
