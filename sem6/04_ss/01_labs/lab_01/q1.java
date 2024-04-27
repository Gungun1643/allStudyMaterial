import java.util.Scanner;

public class q1 {
    private static boolean is_valid(String str){
        for(int i=1;i<str.length();i++){
            if(str.charAt(i)!='a'){return false;}
        }
        return str.charAt(0)=='a';
    }
    public static void main(String args[]){
         Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        if (is_valid(input)) {
            System.out.println("The string is in the form of a*");
        } else {
            System.out.println("The string is NOT in the form of a*");
        }
        scanner.close();
    }
}
