import java.util.Scanner;

public class q2 {
    private static boolean is_valid(String str){
        /*get the first index of b=> if -1=> return false ....
         * if index is valid then check there should be no a after it.
         */
        int idx=str.indexOf('b');
        System.out.println("idx : "+idx);
        /* check for the atleast one occurence of 'b' */
        if(idx==-1){return false;}
        /* */
        for(int i=idx+1;i<str.length();i++){
            if(str.charAt(i)=='a'){return false;}
        }
        return true;
    }
    public static void main(String args[]){
         Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        if (is_valid(input)) {
            System.out.println("The string is in the form of a*b+");
        } else {
            System.out.println("The string is NOT in the form of a*b+");
        }
        scanner.close();
    }
}
