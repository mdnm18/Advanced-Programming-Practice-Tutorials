
import java.util.*;

public class IncreamentOperators {

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = 5;
            System.out.println("Value of n : " + n);
            System.out.println("USING POST-INCREMENT OPERATOR (n++) : " + (n++));
            System.out.println("AFTER USING POST-INCREMENT OPERATOR (n++) : " + n);
            System.out.println("USING PRE-INCREMENT OPERATOR (++n) : " + (++n));
            System.out.println("AFTER USING PRE-INCREMENT OPERATOR (++n) : " + n);
        }
    }
}
