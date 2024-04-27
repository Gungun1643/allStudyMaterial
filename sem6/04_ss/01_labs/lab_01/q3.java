import java.util.Scanner;

public class q3 {

  public static boolean check_alternating(String str) {
    boolean zero = str.charAt(0) == '0';
    char prev = '$';
    int n = str.length();
    int steps = 0;
    if(n<2){return false;}

    if (zero) {
    //   System.out.println(" q0 -> q1  ");
      for (int i = 0; i < n; i++) {
        char curr = str.charAt(i);
        System.out.println("prev : " + prev + " curr: " + curr);
        if (prev == curr) {
            if(curr=='0'){
                System.out.println(" q1 -> qd ");
            }else {
                System.out.println(" q2 -> qd ");
            }
        //   System.out.println("The String is not alternating ");
          return false;
        }
        if ((prev=='$' || prev == '1') && curr == '0') {
          if (steps == 0) {
            System.out.println(" q0 -> q1 ");
          } else {
            System.out.println(" q2 -> q1 ");
          }
        }else {
            /*curr='0' and prev='1' */
            System.out.println(" q1 -> q2 ");
        }
        prev=curr;
        steps++;
      }
      return true;
    } else {
        /*starts with 1 */


        for (int i = 0; i < n; i++) {
        char curr = str.charAt(i);
        System.out.println("prev : " + prev + " curr: " + curr);
        if (prev == curr) {
            if(curr=='1'){
                System.out.println(" q1 -> qd ");
            }else {
                System.out.println(" q2 -> qd ");
            }
        //   System.out.println("The String is not alternating ");
          return false;
        }
        if ((prev=='$' || prev == '0') && curr == '1') {
          if (steps == 0) {
            System.out.println(" q0 -> q1 ");
          } else {
            System.out.println(" q2 -> q1 ");
          }
        }else {
            /*curr='0' and prev='1' */
            System.out.println(" q1 -> q2 ");
        }
        prev=curr;
        steps++;
      }
    //   return true;
    }

    return true;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a string: ");
    String input = scanner.nextLine();
    // String input="101";

    if (check_alternating(input)) {
      System.out.println("The string is alternating ");
    } else {
      System.out.println("The string is NOT alternating ");
    }
    scanner.close();
  }
}
