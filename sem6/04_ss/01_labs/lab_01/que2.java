import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class que2 {
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the string : ");
        String input_str= sc.nextLine();
        if(is_valid_str(input_str)){
            System.out.println("Yes it is of the form : 'a*b+' ");

        }else {
            System.out.println("No it is not of the form : 'a*b+' ");
        }
        sc.close();
    }
    private static boolean is_valid_str(String str){
        String pattern= "^a*b+$";
        /*convert the given above string into pattern */
        Pattern regex= Pattern.compile(pattern);

        /*now match it with the given str. */
        Matcher matcher= regex.matcher(str);

        return matcher.matches();
    }
}
