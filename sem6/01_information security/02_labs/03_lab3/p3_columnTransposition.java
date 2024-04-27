import java.util.Arrays;
import java.util.Scanner;

public class p3_columnTransposition {
    public static String encryption(String str){
        String ans ="";
        Character mat[][]=new Character[5][5];
        for(int i=0;i<mat.length;i++){
            Arrays.fill(mat[i],'X');
        }
        /*place the letters row-wise   */
        int idx=0,n=str.length();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                mat[i][j]=str.charAt(idx++);
                if(idx==n){break;}
            }
        }
        /*read it column wise */
        for(int j=0;j<mat[0].length;j++){
            for(int i=0;i<mat.length;i++){
                if(mat[i][j]=='X'){continue;}
                // System.out.print(mat[i][j]);
                ans+= mat[i][j];
            }
            // System.out.println();
            ans+=" ";
        }

        return ans;
    }
    public static String decryption(String str){
        String strs[]=str.split(" ");
        int max_len=0;
        for(int i=0;i<strs.length;i++){
            max_len=Math.max(max_len, strs[i].length());
        }
        String ans="";
        for(int j=0;j<max_len;j++){
            for(int i=0;i<strs.length;i++){
                if(j>=strs[i].length()){continue;}
                if(strs[i].charAt(j)=='|'){
                    ans+= " ";
                }else {
                    ans+= strs[i].charAt(j);
                }
            } 
        }

        return ans;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String str=sc.nextLine();
        /*handle the spaces  */ 
        str= str.replaceAll(" ", "|");
        String encrypted_str=encryption(str);
         
        System.out.println("encrypted str ===> "+encrypted_str); 
        
        String decrypted_str=decryption(encrypted_str);
        
        System.out.println("decrypted_str  ===> "+decrypted_str);
        sc.close();
        // System.out.println(decrypted_str);
    }
}
