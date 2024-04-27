import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class p1 {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String symtabFileName = "symtab.txt";
        String intermediateFileName = "intermediate.txt";

        Map<String, Integer> symtab = new HashMap<>();
        int locationCounter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             PrintWriter symtabWriter = new PrintWriter(new FileWriter(symtabFileName));
             PrintWriter intermediateWriter = new PrintWriter(new FileWriter(intermediateFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                // Ignore lines with less than 2 parts
                if (parts.length < 2) {
                    continue;
                }

                String label = parts[0];
                String mnemonic = parts[1];
                String operand = (parts.length > 2) ? parts[2] : "";

                if (!label.equals("NAN")) {
                    symtab.put(label, locationCounter);
                    symtabWriter.println(label + "\t" + locationCounter);
                }

                intermediateWriter.println(locationCounter + "\t" + line);

                switch (mnemonic) {
                    case "START":
                        locationCounter = Integer.parseInt(operand);
                        break;
                    case "WORD":
                        locationCounter += 3;
                        break;
                    case "RESW":
                        locationCounter += 3 * Integer.parseInt(operand);
                        break;
                    case "BYTE":
                        if (operand.startsWith("X'") || operand.startsWith("C'")) {
                            int length = operand.length() - 3;
                            locationCounter += (operand.startsWith("X'")) ? (length / 2) : length;
                        }
                        break;
                    case "RESB":
                        locationCounter += Integer.parseInt(operand);
                        break;
                    case "END":
                        break;
                    default:
                        locationCounter += 3; // Default increment for instructions
                        break;
                }
            }

            System.out.println("Pass 1 completed successfully.");
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }
}
