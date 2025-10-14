
import java.util.*;

public class EvenOdd {

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("ENTER A NUMBER : ");
            int n = sc.nextInt();
            String r = (n % 2 == 0) ? "EVEN" : "ODD";
            System.out.println(r);
        } catch (InputMismatchException e) {
            System.out.println("INVALID INPUT!");
        }
    }
}
