import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TokenParser {
    // Returns 'true' if the character is a DELIMITER.
    static boolean isDelimiter(char ch) {
        return ch == ' ' || ch == '+' || ch == '-' || ch == '*' ||
                ch == '/' || ch == ',' || ch == ';' || ch == '>' ||
                ch == '<' || ch == '=' || ch == '(' || ch == ')' ||
                ch == '[' || ch == ']' || ch == '{' || ch == '}';
    }

    // Returns 'true' if the character is an OPERATOR.
    static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' ||
                ch == '/' || ch == '>' || ch == '<' ||
                ch == '=';
    }

    // Returns 'true' if the string is a VALID IDENTIFIER.
    static boolean validIdentifier(String str) {
        if (str.charAt(0) == '0' || str.charAt(0) == '1' || str.charAt(0) == '2' ||
                str.charAt(0) == '3' || str.charAt(0) == '4' || str.charAt(0) == '5' ||
                str.charAt(0) == '6' || str.charAt(0) == '7' || str.charAt(0) == '8' ||
                str.charAt(0) == '9' || isDelimiter(str.charAt(0)))
            return false;
        return true;
    }

    // Returns 'true' if the string is a KEYWORD.
    static boolean isKeyword(String str) {
        return str.equals("if") || str.equals("else") ||
                str.equals("while") || str.equals("do") ||
                str.equals("break") ||
                str.equals("continue") || str.equals("int")
                || str.equals("double") || str.equals("float")
                || str.equals("return") || str.equals("char")
                || str.equals("case") || str.equals("char")
                || str.equals("sizeof") || str.equals("long")
                || str.equals("short") || str.equals("typedef")
                || str.equals("switch") || str.equals("unsigned")
                || str.equals("void") || str.equals("static")
                || str.equals("struct") || str.equals("goto");
    }

    // Returns 'true' if the string is an INTEGER.
    static boolean isInteger(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2'
                    && str.charAt(i) != '3' && str.charAt(i) != '4' && str.charAt(i) != '5'
                    && str.charAt(i) != '6' && str.charAt(i) != '7' && str.charAt(i) != '8'
                    && str.charAt(i) != '9' || (str.charAt(i) == '-' && i > 0))
                return false;
        }
        return true;
    }

    // Returns 'true' if the string is a REAL NUMBER.
    static boolean isRealNumber(String str) {
        int len = str.length();
        boolean hasDecimal = false;

        if (len == 0)
            return false;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2'
                    && str.charAt(i) != '3' && str.charAt(i) != '4' && str.charAt(i) != '5'
                    && str.charAt(i) != '6' && str.charAt(i) != '7' && str.charAt(i) != '8'
                    && str.charAt(i) != '9' && str.charAt(i) != '.' ||
                    (str.charAt(i) == '-' && i > 0))
                return false;
            if (str.charAt(i) == '.')
                hasDecimal = true;
        }
        return hasDecimal;
    }

    // Extracts the SUBSTRING.
    static String subString(String str, int left, int right) {
        return str.substring(left, right + 1);
    }

    // // Parsing the input STRING.
    // static void parse(String str) {
    //     int left = 0, right = 0;
    //     int len = str.length();

    //     while (right < len && left <= right) {
    //         if (!isDelimiter(str.charAt(right)))
    //             right++;

    //         if (isDelimiter(str.charAt(right)) && left == right) {
    //             if (isOperator(str.charAt(right)))
    //                 System.out.println("'" + str.charAt(right) + "' IS AN OPERATOR");

    //             right++;
    //             left = right;
    //         } else if (isDelimiter(str.charAt(right)) && left != right ||
    //                 (right == len - 1 && left != right)) {
    //             String subStr = subString(str, left, right - 1);

    //             if (isKeyword(subStr))
    //                 System.out.println("'" + subStr + "' IS A KEYWORD");

    //             else if (isInteger(subStr))
    //                 System.out.println("'" + subStr + "' IS AN INTEGER");

    //             else if (isRealNumber(subStr))
    //                 System.out.println("'" + subStr + "' IS A REAL NUMBER");

    //             else if (validIdentifier(subStr) && !isDelimiter(str.charAt(right - 1)))
    //                 System.out.println("'" + subStr + "' IS A VALID IDENTIFIER");

    //             else if (!validIdentifier(subStr) && !isDelimiter(str.charAt(right - 1)))
    //                 System.out.println("'" + subStr + "' IS NOT A VALID IDENTIFIER");
    //             left = right;
    //         }
    //     }
    // }
    static void parse(String str) {
        int left = 0, right = 0;
        int len = str.length();
    
        while (right < len) {
            if (!isDelimiter(str.charAt(right)))
                right++;
    
            if (right == len || (isDelimiter(str.charAt(right)) && left == right)) {
                if (left == right && isDelimiter(str.charAt(right))) {
                    if (isOperator(str.charAt(right)))
                        System.out.println("'" + str.charAt(right) + "' IS AN OPERATOR");
                } else {
                    String subStr = subString(str, left, right - 1);
    
                    if (isKeyword(subStr))
                        System.out.println("'" + subStr + "' IS A KEYWORD");
                    else if (isInteger(subStr))
                        System.out.println("'" + subStr + "' IS AN INTEGER");
                    else if (isRealNumber(subStr))
                        System.out.println("'" + subStr + "' IS A REAL NUMBER");
                    else if (validIdentifier(subStr) && !isDelimiter(str.charAt(right - 1)))
                        System.out.println("'" + subStr + "' IS A VALID IDENTIFIER");
                    else if (!validIdentifier(subStr) && !isDelimiter(str.charAt(right - 1)))
                        System.out.println("'" + subStr + "' IS NOT A VALID IDENTIFIER");
                }
                left = ++right;
            } else {
                right++;
            }
        }
    }
    

    // DRIVER FUNCTION
    public static void main(String[] args) {
        // maximum length of string is 100 here
        // String str = "int a = b + 1c; ";

        // // calling the parse function
        // parse(str);

        /******************************** */
         // Relative path to your Java code file (assuming it's in the same directory)
    String filePath = "test.java";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        StringBuilder codeBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            codeBuilder.append(line).append("\n");
        }

        String javaCode = codeBuilder.toString();
        System.out.println("Java Code:\n" + javaCode);

        // calling the parse function
        parse(javaCode);
    } catch (IOException e) {
        e.printStackTrace();
    }


    }
}
