
import java.util.*;

public class SeperateWords {

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("ENTER A SENTENCE : ");
            String input = sc.nextLine();
            String words[] = input.split("\\s+");
            System.out.println("EACH WORDS OF ENTERED SINGLE LINE : ");
            for (String word : words) {
                System.out.println(word);
            }
        } catch (InputMismatchException e) {
            System.err.println("INVALID INPUT!");
        }
    }
}
