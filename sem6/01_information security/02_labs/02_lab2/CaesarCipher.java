import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CaesarCipher {

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

    public static String caesarCipherDecrypt(String encryptedText, int shift) {
        return caesarCipherEncrypt(encryptedText, -shift);
    }

    public static Map<Character, Double> frequencyAnalysis(String encryptedText) {
        Map<Character, Integer> charCount = new HashMap<>();
        int totalChars = 0;

        for (char c : encryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                totalChars++;
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }

        Map<Character, Double> frequencyPercentages = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalChars;
            frequencyPercentages.put(entry.getKey(), percentage);
        }

        return frequencyPercentages;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext message: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the shift value for encryption: ");
        int shift = scanner.nextInt();

        String encryptedText = caesarCipherEncrypt(plaintext, shift);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = caesarCipherDecrypt(encryptedText, shift);
        System.out.println("Decrypted Text: " + decryptedText);

        Map<Character, Double> frequencies = frequencyAnalysis(encryptedText);
        System.out.println("\nFrequency Analysis:");
        for (Map.Entry<Character, Double> entry : frequencies.entrySet()) {
            System.out.printf("%s: %.2f%%\n", entry.getKey(), entry.getValue());
        }
    }
}
