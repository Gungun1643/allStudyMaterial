import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class p1 {

  public static String encrypt(String plain_text, String key) {
    plain_text= plain_text.toLowerCase();
    StringBuilder cipher_text = new StringBuilder();
    int key_length = key.length();
    int idx = 0;
    for (char ch : plain_text.toCharArray()) {
      if (Character.isLetter(ch)) {
        int shift = key.charAt(idx) - 'a';
        char encrypted_ch = (char) (((ch - 'a' + shift) % 26) + 'a');
        cipher_text.append(encrypted_ch);
        idx = (idx + 1) % key_length;
      } else {
        cipher_text.append(ch);
      }
    }
    return cipher_text.toString();
  }

  public static String decrypt(String cipher_text, String key) {
    StringBuilder plain_text = new StringBuilder();
    int key_length = key.length();
    int idx = 0;

    for (char ch : cipher_text.toCharArray()) {
      if (Character.isLetter(ch)) {
        int shift = key.charAt(idx) - 'a';
        char decrypted_ch = (char) (((ch - 'a' - shift + 26) % 26) + 'a');
        plain_text.append(decrypted_ch);
        idx = (idx + 1) % key_length;
      } else {
        plain_text.append(ch);
      }
    }

    return plain_text.toString();
  }

  public static void main(String[] args) {
    String plain_text = "";
    String key = "";

    try {
      BufferedReader br = new BufferedReader(new FileReader("input.txt"));


      plain_text = br.readLine();
      key = br.readLine();
      System.out.println("Plain_text : "+plain_text);
      System.out.println("Key : "+key);
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    /*ENCRYPT THE PLAIN TEXT USING THE KEY. */
    String cipher_text = encrypt(plain_text, key);
    System.out.println("Cipher text is : " + cipher_text);

    /*WRITE THE CIPHER TEXT TO FILE. */
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("output1.txt"));
      bw.write(cipher_text);
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    /*READ INPUT CIPHER TEXT FROM THE FILE. */
    String input_cipher_text = "";

    try {
      BufferedReader br = new BufferedReader(new FileReader("output1.txt"));
      input_cipher_text = br.readLine();
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    /* DECRYPT CIPHERTEXT USING KEY. */
    String decrypted_plain_text = decrypt(input_cipher_text, key);
    // System.out.println("Decrypted Plain text: ", decrypted_plain_text);
    System.out.print("Decrypted Plaintext: ");
    System.out.println(decrypted_plain_text);

    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("output2.txt"));
      bw.write(decrypted_plain_text);
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
