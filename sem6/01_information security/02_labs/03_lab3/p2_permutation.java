import java.util.*;

public class p2_permutation {
    public static String key="XIEOURVLKC";
    public static List<Integer>  k;

    public static void getKey(){
        k=new ArrayList<>();
        PriorityQueue<Character> pq=new PriorityQueue<>();
        for(int i=0;i<key.length();i++){
            pq.add(key.charAt(i));
        }
        HashMap<Character,Integer> h1=new HashMap<>(); 

        int i=0;
        while(!pq.isEmpty()){
            h1.put(pq.poll(), i++);
        }
        for( i=0;i<key.length();i++){
            k.add(h1.get(key.charAt(i)));
        }
        System.out.println("h1 => "+h1);
    }

    
    public static String encryption_util(String str){
        String ans="";
        // LinkedHashMap<Character,Integer> curr_map=str_to_num(str);
        // System.out.println(ans); 

        PriorityQueue<Character> pq=new PriorityQueue<>();
        for(int i=0;i<str.length();i++){
            pq.add(str.charAt(i));
        }
        HashMap<Integer,Character> h1=new HashMap<>();
       
        int i=0;
        while(!pq.isEmpty()){
            h1.put(i++, pq.poll());
        }
        
        // System.out.println("h1 => "+h1); 

        for( i=0;i<k.size() && i<str.length();i++){
            int idx=k.get(i);
            ans+= h1.get(idx);
        }
        // System.out.println("ans =======         "+ans);
        return ans;
    }
    public static String encryption(String str){
        String ans="";
        int n=str.length();
        
        // System.out.println("k ===> "+k);
        int i=0;
        // System.out.println("substrings are => ");
        // System.out.println("-------");
        // System.out.println("-------");
        while(i<n){
            String sub_str=str.substring(i, Math.min(n,i+10));

            // System.out.println("sub_str : "+sub_str);
            
            String encrypted_sub_str=encryption_util(sub_str);
            ans+= encrypted_sub_str;
            i+=10;
            // System.out.println("-------");
            // System.out.println("-------");
        }

        return ans;
    }
    public static String decryption_util(String str){
        // int l=Math.min(10,str.length());
        // String ans= String.join("", Collections.nCopies(l, "$"));
        StringBuilder ans = new StringBuilder(String.valueOf("$").repeat(k.size()));

        // System.out.println("ans => "+ans);
         for(int i=0;i<k.size() && i<str.length();i++){
            if(str.charAt(i)=='|'){
                ans.setCharAt(k.get(i), ' '); 
                
            }else {
                ans.setCharAt(k.get(i), str.charAt(i));
            }
         }
        //  System.out.println(" ans ======> "+ans);
        ans= new StringBuilder(ans.toString().replaceAll("\\$", ""));
        
        // ans= new StringBuilder(ans.toString().replaceAll("\\|", " "));
        //  System.out.println("ans  ========= /////// "+ans);
        return ans.toString();
    }
    public static String decryption(String str){
        int i=0;
        String decrypted_str ="";
        int n=str.length();
        while(i<n){
            String sub_str=str.substring(i, Math.min(n,i+10));
            // System.out.println("sub_str => "+sub_str);
            String sub_str_decrypt= decryption_util(sub_str);
            i+=10;
            decrypted_str+= sub_str_decrypt;
            // System.out.println("====================");
        }
        return decrypted_str;
    }
    public static void main(String args[]){
         
        getKey();
        System.out.println(k);

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String str=sc.nextLine();
        /*handle the spaces  */
        str = str.replace(' ', '|');

        String encrypted_str=encryption(str);
         
        System.out.println("encrypted str ===> "+encrypted_str); 
        
        String decrypted_str=decryption(encrypted_str);
        
        System.out.println("decrypted_str  ===> "+decrypted_str);
        
        // System.out.println(decrypted_str);
        // sc.close();

    }
}
