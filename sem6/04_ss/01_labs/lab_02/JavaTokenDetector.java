import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaTokenDetector {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java JavaTokenDetector <input_file>");
            return;
        }

        String filePath = args[0];
        String code = readCodeFile(filePath);

        if (code != null) {
            detectTokens(code);
        }
    }

    private static String readCodeFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readString(path);
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    private static void detectTokens(String code) {
        // Java keywords
        String[] keywords = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
                "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally",
                "float", "for", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
                "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile",
                "while"};

        // Regular expressions for different types of tokens
        String identifierPattern = "[a-zA-Z_][a-zA-Z0-9_]*";
        String constantPattern = "\\b\\d+\\b";
        String operatorPattern = "[-+*/%=<>!&|^]+";
        String parenthesesPattern = "[(){}\\[\\]]";
        String semicolonPattern = ";";
        String commaPattern = ",";
        String commentsPattern = "//.*|/\\*[^*]*\\*+([^/*][^*]*\\*+)* /";

        // Combine all patterns
        String pattern = String.join("|", keywords) + "|" +
                identifierPattern + "|" +
                constantPattern + "|" +
                operatorPattern + "|" +
                parenthesesPattern + "|" +
                semicolonPattern + "|" +
                commaPattern + "|" +
                commentsPattern;

        // Compile the pattern
        Pattern tokenPattern = Pattern.compile(pattern);
        Matcher matcher = tokenPattern.matcher(code);

        System.out.println("Token Table:");

        // Iterate through matches
        while (matcher.find()) {
            String token = matcher.group().trim();
            if (!token.isEmpty()) {
                // Print the token and its meaning
                System.out.println(token + " -> " + getTokenMeaning(token));
            }
        }
    }

    private static String getTokenMeaning(String token) {
        // Provide meanings for each token type
        if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            return "Identifier";
        } else if (token.matches("\\b\\d+\\b")) {
            return "Constant";
        } else if (token.matches("[-+*/%=<>!&|^]+")) {
            return "Operator";
        } else if (token.matches("[(){}\\[\\]]")) {
            return "Parenthesis";
        } else if (token.equals(";")) {
            return "Semicolon";
        } else if (token.equals(",")) {
            return "Comma";
        } else if (token.matches("//.*|/\\*[^*]*\\*+([^/*][^*]*\\*+)* /")) {
            return "Comment";
        } else {
            return "Keyword or Other";
        }
    }
}
