import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequencyAnalysisCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text to encrypt: ");
        String originalText = scanner.nextLine().toUpperCase();

        // Perform encryption
        String encryptedText = encrypt(originalText);
        System.out.println("Encrypted text: " + encryptedText);

        // Perform decryption
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    private static String encrypt(String text) {
        Map<Character, Character> encryptionMap = createEncryptionMap();
        StringBuilder encryptedText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                encryptedText.append(encryptionMap.get(ch));
            } else {
                encryptedText.append(ch);
            }
        }

        return encryptedText.toString();
    }

    private static String decrypt(String text) {
        Map<Character, Character> decryptionMap = createDecryptionMap();
        StringBuilder decryptedText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                decryptedText.append(decryptionMap.get(ch));
            } else {
                decryptedText.append(ch);
            }
        }

        return decryptedText.toString();
    }

    private static Map<Character, Character> createEncryptionMap() {
        // This is a basic example. In a real scenario, you might want to analyze actual frequency data
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shuffledAlphabet = "QWERTYUIOPASDFGHJKLZXCVBNM";

        Map<Character, Character> encryptionMap = new HashMap<>();

        for (int i = 0; i < alphabet.length(); i++) {
            encryptionMap.put(alphabet.charAt(i), shuffledAlphabet.charAt(i));
        }

        return encryptionMap;
    }

    private static Map<Character, Character> createDecryptionMap() {
        // Create the decryption map by swapping keys and values of the encryption map
        Map<Character, Character> decryptionMap = new HashMap<>();
        Map<Character, Character> encryptionMap = createEncryptionMap();

        for (Map.Entry<Character, Character> entry : encryptionMap.entrySet()) {
            decryptionMap.put(entry.getValue(), entry.getKey());
        }

        return decryptionMap;
    }
}
