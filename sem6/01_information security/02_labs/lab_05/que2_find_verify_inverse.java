import java.util.Scanner;

public  class que2_find_verify_inverse {

    public static class matrix{
        public static void print(float mat[][], int n) {
            for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + " ");
              }
              System.out.println();
            }
          }
        
          public static float get_det(float mat[][], int k) {
            float ans = 0;
            float temp[][] = new float[k][k];
            int m, n;
            if (k == 1) {
              return mat[0][0];
            }
        
            for (int c = 0; c < k; c++) {
              m = 0;
              n = 0;
              for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                  if (i != 0 && j != c) {
                    temp[m][n] = mat[i][j];
                    n++;
                    if (n == k - 1) {
                      n = 0;
                      m++;
                    }
                  }
                }
              }
              ans += Math.pow(-1, c) * mat[0][c] * get_det(temp, k - 1);
            }
            return ans;
          }
        
          public static float[][] cofactor(float x[][], int k) {
            float b[][] = new float[10][10];
            float y[][] = new float[10][10];
            int p, q, m, n;
            int s = 1;
            for (q = 0; q < k; q++) {
              for (p = 0; p < k; p++) {
                m = 0;
                n = 0;
                for (int i = 0; i < k; i++) {
                  for (int j = 0; j < k; j++) {
                    if (i != q && j != p) {
                      b[m][n] = x[i][j];
                      if (n < (k - 2)) {
                        n++;
                      } else {
                        n = 0;
                        m++;
                      }
                    }
                  }
                }
                y[q][p] = s * get_det(b, k - 1);
                s = (-1) * s;
              }
            }
            return transpose(x, y, k);
          }
        
          public static float[][] transpose(float x[][], float y[][], int r) {
            float b[][] = new float[10][10];
            float inv[][] = new float[10][10];
            float d;
            // System.out.println("The cofactor of matrix is : ");
            // print(y, r);
            /* transpose of a co-factor of the matrix is ... */
            for (int i = 0; i < r; i++) {
              for (int j = 0; j < r; j++) {
                b[i][j] = y[j][i];
              }
            }
        
            d = get_det(x, r);
            // System.out.println("The adjoint matrix is : ");
            // print(b, r);
            /*calculate the inverse matrix... */
            for (int i = 0; i < r; i++) {
              for (int j = 0; j < r; j++) {
                inv[i][j] = (b[i][j]) / d;
              }
            }
            // System.out.println("The inverse matrix is : ");
            // print(inv, r);
            return inv;
          }
    }
    /******************************** */
  /*find the inverse of the matrix. */
  /* (A)*(A^-1) = I = (A^-1)*(A) */
  /* (A-1) = adj(A)/ det(A) */
  

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the value of n: ");
    int n = sc.nextInt();
    float[][] mat = new float[n][n];
    System.out.println("Enter the values row and then column wise.");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        mat[i][j] = sc.nextFloat();
      }
    }
    System.out.println("The given matrix is this : ");
    matrix.print(mat, n);

    float determinant = matrix.get_det(mat, n);
    System.out.println("determinant: "+determinant);
    if (determinant == 0) {
      System.out.println("THis is not a valid key-matrix.");
    } else {
      float inv[][]=matrix.cofactor(mat, n);
      matrix.print(inv,n);
    }

    sc.close();
  }
}
