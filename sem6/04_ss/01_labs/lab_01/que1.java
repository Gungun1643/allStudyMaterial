import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class que1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        if (isAStarString(input)) {
            System.out.println("The string is in the form of a*");
        } else {
            System.out.println("The string is NOT in the form of a*");
        }
        scanner.close();
    }

    private static boolean isAStarString(String input) {
        // Define the pattern for a* using regular expression
        String pattern = "^a.*$";

        // Create a Pattern object
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(input);

        // Check if the input string matches the pattern
        return matcher.matches();
    }

}
