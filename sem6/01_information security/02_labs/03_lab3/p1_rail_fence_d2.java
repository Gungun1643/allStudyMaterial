import java.util.Arrays;
import java.util.Scanner;

public class p1_rail_fence_d2{
    public static void print(Character arr[][]){
        for(int r=0;r<arr.length;r++){
            for(int c=0;c<arr[0].length;c++){
                System.out.print(arr[r][c]+" ");
            }
            System.out.println();
        }
    }
    public static String encryption(String str,int depth){
        StringBuilder ans=new StringBuilder();
        int n=str.length();
        Character arr[][]=new Character[depth][n];
        for(int i=0;i<arr.length;i++){
            Arrays.fill(arr[i], '$');
            // System.out.println(arr[i].length+" => ");
        }

        // print(arr);
        // System.out.println("---------");
        // int count=0;
        
        for(int r=0;r<depth;r++){
            int idx=r;
            String curr_word="";
            for(int c=r;c<n;c+=depth){
                if(c>n || r>n || idx>=n){break;}
                // if(idx>n){break;}
                // System.out.println("r : "+r+" c: "+c+" str.charAt(idx) : " +str.charAt(idx));
                arr[r][c]= str.charAt(idx);
                curr_word+= arr[r][c];
                // print(arr);
                // System.out.println("=========");
                idx+= depth;
            }
            ans.append(curr_word);
            ans.append("@");
        }
        // System.out.println("----------");
        // print(arr);
        return ans.toString();
    }
    public static String decryption(String str,int depth){
        String ans="";
        String strs[]=str.split("@");
        int max_len=0;
        for(int i=0;i<strs.length;i++){
            max_len=Math.max(strs[i].length(), max_len);
        }
 
        for(int j=0;j<max_len;j++){
            for(int i=0;i<strs.length;i++){
                if(j>=strs[i].length()){
                    continue;
                }
                ans+= strs[i].charAt(j);
            }
        }
        
        return ans;
    }
    public static void print_encrypted_text(String str){
        String strs[]=str.split("@");
        for(int i=0;i<strs.length;i++){
            System.out.println(strs[i]);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String str=sc.nextLine();
       String encrypted_str= encryption(str,2);
        
    //    System.out.println("hahdflkajdfl ");
    //    System.out.println(encrypted_str);
    System.out.println("printing the encrpted text");
       print_encrypted_text(encrypted_str);
    //    System.out.println(" ========= ");
       String dencrypted_str= decryption(encrypted_str,2);
       System.out.println("decrypted text ===========> "+dencrypted_str);
       sc.close();
        // encryption(str,3);
    }
}