import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class que3_en_de_n {

  public static float key_matrix[][];
  public static float inverse_matrix[][];
  public static float adjoint[][];
  public static int l;
  public static int N;

  /************************* */

  public static class matrix {

    public static void print(float mat[][], int n) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          System.out.print(mat[i][j] + " ");
        }
        System.out.println();
      }
    }
 

    public static void getCofactor(
      float A[][],
      float temp[][],
      int p,
      int q,
      int n
    ) {
      int i = 0, j = 0;

      // Looping for each element of the matrix
      for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
          // Copying into temporary matrix only those element
          // which are not in given row and column
          if (row != p && col != q) {
            temp[i][j++] = A[row][col];

            // Row is filled, so increase row index and
            // reset col index
            if (j == n - 1) {
              j = 0;
              i++;
            }
          }
        }
      }
    }

    /* Recursive function for finding determinant of matrix.
    n is current dimension of A[][]. */
    public static float determinant(float A[][], int n) {
      float D = 0; // Initialize result

      // Base case : if matrix contains single element
      if (n == 1) return A[0][0];

      float[][] temp = new float[N][N]; // To store cofactors

      int sign = 1; // To store sign multiplier

      // Iterate for each element of first row
      for (int f = 0; f < n; f++) {
        // Getting Cofactor of A[0][f]
        getCofactor(A, temp, 0, f, n);
        D += sign * A[0][f] * determinant(temp, n - 1);

        // terms are to be added with alternate sign
        sign = -sign;
      }

      return D;
    }

    // Function to get adjoint of A[N][N] in adj[N][N].
    public static void adjoint(float A[][], float[][] adj) {
      if (N == 1) {
        adj[0][0] = 1;
        return;
      }

      // temp is used to store cofactors of A[][]
      int sign = 1;
      float[][] temp = new float[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          // Get cofactor of A[i][j]
          getCofactor(A, temp, i, j, N);

          // sign of adj[j][i] positive if sum of row
          // and column indexes is even.
          sign = ((i + j) % 2 == 0) ? 1 : -1;

          // Interchanging rows and columns to get the
          // transpose of the cofactor matrix
          adj[j][i] = (sign) * (determinant(temp, N - 1));
        }
      }
    }

    // Function to calculate and store inverse, returns false if
    // matrix is singular
    public static boolean inverse(float A[][], float[][] inverse) {
      // Find determinant of A[][]
      float det = determinant(A, N);
      if (det == 0) {
        System.out.print("Singular matrix, can't find its inverse");
        return false;
      }

      // Find adjoint
      float[][] adj = new float[N][N];
      adjoint(A, adj);

      // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
      for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) inverse[i][j] =
        (adj[i][j] / (float) det) %26;
        

      return true;
    }

    /************************************ */

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
  }

  public static void print(float arr[][]) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.println(arr[i][j] + " ");
      }
      System.out.println();
    }
  }

  /***************************************************** */
  /************ENCRYPTION******************** */
  public static String hill_cipher(String plain_text) {
    String encrypted_text = "";
    int n = plain_text.length();
 
    int idx = 0;
    ArrayList<float[][]> arr = new ArrayList<>();
    ArrayList<float[][]> encrypted_arr = new ArrayList<>();

    for (float i = 0; i < n / l; i++) {
      float temp[][] = new float[l][1];
      
      for (int j = 0; j < l; j++) {
        if (idx >= n || plain_text.charAt(idx)=='.') {
          continue;
        }
        temp[j][0] = (plain_text.charAt(idx++) - 'a') % 26;
      }
      
      arr.add(temp);
      float enrypted[][] = matrix.matrix_mul(key_matrix, temp);
      
      encrypted_arr.add(enrypted);

      System.out.println("encrypted => ");
      print(enrypted);
      System.out.println("------------");
    }
    for (int i = 0; i < encrypted_arr.size(); i++) {
      for (int j = 0; j < encrypted_arr.get(i).length; j++) {
        // System.out.println(encrypted_arr.get(i)[j][0]);
        // encrypted_text += rev_hm.get(encrypted_arr.get(i)[j][0]);
        encrypted_text += (char) ( ((encrypted_arr.get(i)[j][0] %26) + 'a')  );
        // System.out.println("encrypted_arr.get(i)[j][0] => "+encrypted_arr.get(i)[j][0]);
      }
    }
    /* now multiply the text_matrix witht the key_matrix and get the encrypted one  */
    // System.out.println("encrypted_text: " + encrypted_text);
    return encrypted_text;
  }

  /*********DECRYPTION************* */
  public static String decryption(String encrypted_text) {
    String dencrypted_text = "";
    int n = encrypted_text.length();

    int idx = 0;
    ArrayList<float[][]> arr = new ArrayList<>();
    ArrayList<float[][]> dencrypted_arr = new ArrayList<>();
    for (int i = 0; i < n / 2; i++) {
      float temp[][] = new float[l][1];
      for (int j = 0; j < l; j++) {
        if (i + j >= n) {
          continue;
        }
        temp[j][0] = encrypted_text.charAt(idx++) - 'a';
      }
      arr.add(temp);
      float dencrypted[][] = matrix.matrix_mul(inverse_matrix, temp);
      dencrypted_arr.add(dencrypted);
        // print(arr.get(i));
        // System.out.println();
        // print(dencrypted);
        // System.out.println("------------");
    }
    for (int i = 0; i < dencrypted_arr.size(); i++) {
      for (int j = 0; j < dencrypted_arr.get(i).length; j++) {
        // System.out.println(dencrypted_arr.get(i)[j][0]);
        // dencrypted_text += rev_hm.get(dencrypted_arr.get(i)[j][0]);
        dencrypted_text += (char) ((( dencrypted_arr.get(i)[j][0]  )% 26+ 'a') );
      }
    }
    /* now multiply the text_matrix witht the key_matrix and get the encrypted one  */
    // System.out.println("dencrypted_text: " + dencrypted_text);
    return dencrypted_text;
  }

  /********** */
  public static void init(int n) {
    key_matrix = new float[n][n];
    inverse_matrix = new float[n][n];
    adjoint = new float[n][n];
    l = n;
    N = n;
  }

  /********************* */
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    // System.out.println("Enter the value of n: ");
    // int n = sc.nextInt();
    // init(n);
    // System.out.println("Enter the values row and then column wise.");
    // for (int i = 0; i < n; i++) {
    //   for (int j = 0; j < n; j++) {
    //     key_matrix[i][j] = sc.nextInt();
    //   }
    // }
    // System.out.println("The given matrix is this : ");
    // matrix.print(key_matrix, n);

    // System.out.print("\nThe Adjoint is :\n");
    // matrix.adjoint(key_matrix, adjoint);
    // matrix.print(adjoint,n);

    // System.out.print("\nThe Inverse is :\n");
    // if (matrix.inverse(key_matrix, inverse_matrix))
    //    matrix.print(inverse_matrix, n);
        l =3;
    key_matrix = new float[][] { { 6,24,1 }, { 13,16,10 }, {20,17,15} };
    inverse_matrix = new float[][] { {8,5,10},{21,8,21},{21,12,8} };

    /******************* */

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

      // Now, 'inputString' contains the content of the file

      System.out.println("Input String: " + inputString.toString());

      String encrypted_text = hill_cipher(inputString.toString());

        String decrypted_text = decryption(encrypted_text);

      System.out.println("encrypted_text: " + encrypted_text);
        System.out.println("decrypted_text: " + decrypted_text);
      /******************************** */
    } catch (FileNotFoundException e) {
      // Handle the exception if the file is not found
      System.err.println("File not found: " + e.getMessage());
    }

    /**************************** */
    sc.close();
  }
}
