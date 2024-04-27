import java.util.HashMap;
import java.util.Map;

public class q3 {

    public static int key=-1;

    public static String encrypt(String plaintext) {
        Map<Character, Integer> frequencyMap = calculateFrequency(plaintext);

        // Find the most frequent character
        char mostFrequent = findMostFrequent(frequencyMap);

        // Encrypt the most frequent character as 'e'
        int diff=mostFrequent-'e';
         key = Math.abs(diff)%26;
         
        return caesarCipherEncrypt(plaintext, key);
    }
    public static String caesarCipherEncrypt(String plainText, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encryptedText.append((char) (((c - base + shift) % 26 + 26) % 26 + base));
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    private static Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }
        return frequencyMap;
    }

    public static String decrypt(String encryptedText, int shift) {
        
        return caesarCipherEncrypt(encryptedText, -shift);
    }

    private static char findMostFrequent(Map<Character, Integer> frequencyMap) {
        char mostFrequent = 'a';
        int maxFrequency = 0;

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequent = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return mostFrequent;
    }

    public static void main(String[] args) {
        String plaintext = "hello world";
        System.out.println("Original: " + plaintext);

        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText,key);
        System.out.println("Decrypted: " + decryptedText);
    }
}
