
import java.util.*;

public class DivisableBy3 {

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("ENTER A NUMBER : ");
            int n = sc.nextInt();
            String r = (n % 3 == 0) ? "the number is divisible by 3" : "the number is not divisible by 3";
            System.out.println(r);
        } catch (InputMismatchException e) {
            System.err.println("INVALID INPUT!");
        }
    }
}