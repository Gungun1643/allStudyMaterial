import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class que1_2x2 {
 
  public static int l = 2; 
  public static float key_matrix[][] = { { 3, 0 }, { 0, 2 } };
  public static float inverse_matrix[][] = new float[key_matrix.length][key_matrix[0].length];

  

  public static void print(float text_matrix[][]) {
    for (int i = 0; i < text_matrix.length; i++) {
      for (int j = 0; j < text_matrix[0].length; j++) {
        System.out.print(text_matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static float[][] matrix_mul(float[][] key_matrix, float[][] temp) {
    float ans[][] = new float[temp.length][temp[0].length];
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[0].length; j++) {
        for (int k = 0; k < temp.length; k++) {
          ans[i][j] += (key_matrix[i][k] * temp[k][j]) % 26;
        }
      }
    }
    return ans;
  }
/************************ */
  public static String hill_cipher(String plain_text) {
    String encrypted_text = "";
    int n = plain_text.length();

    int idx = 0;
    ArrayList<float[][]> arr = new ArrayList<>();
    ArrayList<float[][]> encrypted_arr = new ArrayList<>();
    for (int i = 0; i < n / 2; i++) {
      float temp[][] = new float[l][1];
      for (int j = 0; j < l; j++) {
        if (idx >= n) {
          continue;
        }
        temp[j][0] = plain_text.charAt(idx++) - 'a';
      }
      arr.add(temp);
      float enrypted[][] = matrix_mul(key_matrix, temp);
      encrypted_arr.add(enrypted);
      print(arr.get(i));
      System.out.println();
      print(enrypted);
      System.out.println("------------");
    }
    for (int i = 0; i < encrypted_arr.size(); i++) {
      for (int j = 0; j < encrypted_arr.get(i).length; j++) {
        // System.out.println(encrypted_arr.get(i)[j][0]);
        // encrypted_text += rev_hm.get(encrypted_arr.get(i)[j][0]);
        encrypted_text += (char)((encrypted_arr.get(i)[j][0]  )+'a');
      }
    }
    /* now multiply the text_matrix witht the key_matrix and get the encrypted one  */
    // System.out.println("encrypted_text: " + encrypted_text);
    return encrypted_text;
  }
  /***************** */

  public static void calculate_inverse_matrix(float key_matrix[][]) {
    float det =
      (key_matrix[0][0] * key_matrix[1][1]) -
      (key_matrix[0][1] * key_matrix[1][0]);
      System.out.println("determinant : "+det);
    // System.out.println("\ndeterminant = " + det);

    inverse_matrix[0][0] = ((key_matrix[1][1]) / det);
    inverse_matrix[1][1] = ((key_matrix[0][0]) / det);
    inverse_matrix[0][1] = ((-key_matrix[0][1]) / det);
    inverse_matrix[1][0] = ((-key_matrix[1][0]) / det);
    // System.out.println("inverse_matrix: ");
    // print(inverse_matrix);
  }
  /****************************** */
  public static String decryption(String encrypted_text){
    String dencrypted_text = "";
    int n = encrypted_text.length();

    int idx = 0;
    ArrayList<float[][]> arr = new ArrayList<>();
    ArrayList<float[][]> dencrypted_arr = new ArrayList<>();
    for (int i = 0; i <= n / 2; i++) {
      float temp[][] = new float[l][1];
      for (int j = 0; j < l; j++) {
        if (idx >= n) {
          continue;
        }
        temp[j][0] = (encrypted_text.charAt(idx++) - 'a');
      }
      arr.add(temp);
      float dencrypted[][] = matrix_mul(inverse_matrix, temp);
       
      dencrypted_arr.add(dencrypted);
    //   print(arr.get(i));
    //   System.out.println();
    //   print(dencrypted);
    //   System.out.println("------------");
    }
    int cnt=0;
    for (int i = 0; i < dencrypted_arr.size(); i++) {
      for (int j = 0; j < dencrypted_arr.get(i).length; j++) {
        // System.out.println(dencrypted_arr.get(i)[j][0]);
        // dencrypted_text += rev_hm.get(dencrypted_arr.get(i)[j][0]);
      
        if(cnt>=encrypted_text.length() || dencrypted_arr.get(i)[j][0]==-1){continue;}
        dencrypted_text += (char)(dencrypted_arr.get(i)[j][0]+'a');
        cnt++;
      }
    }
    /* now multiply the text_matrix witht the key_matrix and get the encrypted one  */
    // System.out.println("dencrypted_text: " + dencrypted_text);
    return dencrypted_text;
  }

  /***************** */

  public static void main(String args[]) {
    /*initializing the hashmap */
  
    // System.out.println(hm);
    /* defining the key_matrix. */
 String filePath = "source.txt";

        // Attempt to read the file
        try {
            // Create a File object
            File file = new File(filePath);

            // Create a Scanner to read the file
            Scanner scanner = new Scanner(file);

            // Read the input string
            StringBuilder inputString = new StringBuilder();
            while (scanner.hasNextLine()) {
                inputString.append(scanner.nextLine());
            }

            // Close the scanner
            scanner.close();

            System.out.println("key_matrix");
            print(key_matrix);

            // Now, 'inputString' contains the content of the file

            System.out.println("Input String: " + inputString.toString());

            String encrypted_text= hill_cipher(inputString.toString());

            calculate_inverse_matrix(key_matrix);
           
         String decrypted_text= decryption(encrypted_text);
          
         System.out.println("inverse matrix");
         print(inverse_matrix);
         System.out.println("encrypted_text: "+encrypted_text);
         System.out.println("decrypted_text: "+decrypted_text);




            /******************************** */
        } catch (FileNotFoundException e) {
            // Handle the exception if the file is not found
            System.err.println("File not found: " + e.getMessage());
        }

    /**************** */

   

  }
}
