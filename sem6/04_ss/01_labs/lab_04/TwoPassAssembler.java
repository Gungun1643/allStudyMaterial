import java.util.*;

class SymbolTableEntry {
    String symbol;
    int address;

    public SymbolTableEntry(String symbol, int address) {
        this.symbol = symbol;
        this.address = address;
    }
}

class LiteralTableEntry {
    String literal;
    int address;

    public LiteralTableEntry(String literal, int address) {
        this.literal = literal;
        this.address = address;
    }
}

public class TwoPassAssembler {
    private List<String> intermediateCode;
    private List<String> machineCode;
    private Map<String, Integer> symbolTable;
    private Map<String, Integer> literalTable;

    public TwoPassAssembler() {
        intermediateCode = new ArrayList<>();
        machineCode = new ArrayList<>();
        symbolTable = new HashMap<>();
        literalTable = new HashMap<>();
    }

    public void pass1(List<String> input) {
        int locationCounter = 0;
        for (String line : input) {
            String[] tokens = line.split("\\s+");
            if (tokens.length >= 2) {
                if (!tokens[1].equals("END")) {
                    intermediateCode.add(line);
                    if (tokens.length >= 3 && !tokens[2].equals("DS") && !tokens[2].equals("DC")) {
                        if (!symbolTable.containsKey(tokens[0])) {
                            symbolTable.put(tokens[0], locationCounter);
                        } else {
                            System.out.println("Duplicate symbol found: " + tokens[0]);
                            // Handle duplicate symbol error
                        }
                    }
                    if (tokens.length >= 4 && tokens[3].startsWith("=")) {
                        if (!literalTable.containsKey(tokens[3])) {
                            literalTable.put(tokens[3], -1); // Address not yet resolved
                        }
                    }
                    locationCounter++;
                } else {
                    intermediateCode.add(line);
                    break;
                }
            }
        }
    }

    public void pass2() {
        for (String line : intermediateCode) {
            String[] tokens = line.split("\\s+");
            StringBuilder machineCodeLine = new StringBuilder();
            if (tokens.length >= 2) {
                String mnemonic = tokens[1];
                switch (mnemonic) {
                    case "MOVER":
                        machineCodeLine.append("01");
                        break;
                    case "MOVEM":
                        machineCodeLine.append("02");
                        break;
                    case "ADD":
                        machineCodeLine.append("03");
                        break;
                    case "SUB":
                        machineCodeLine.append("04");
                        break;
                    case "BC":
                        machineCodeLine.append("05");
                        break;
                    case "LTORG":
                        // Handle LTORG directive
                        break;
                    case "STOP":
                        machineCodeLine.append("06");
                        break;
                    case "MULT":
                        machineCodeLine.append("07");
                        break;
                    default:
                        // Handle unrecognized mnemonic
                        break;
                }
                // Append other necessary fields
                machineCode.add(machineCodeLine.toString());
            }
        }
    }

    public void displaySymbolTable() {
        System.out.println("Symbol Table:");
        for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    public void displayLiteralTable() {
        System.out.println("Literal Table:");
        for (Map.Entry<String, Integer> entry : literalTable.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    public void displayMachineCode() {
        System.out.println("Machine Code:");
        for (String code : machineCode) {
            System.out.println(code);
        }
    }

    public static void main(String[] args) {
        TwoPassAssembler assembler = new TwoPassAssembler();
        List<String> input = Arrays.asList(
            "NAN  START  200    NAN",
            "NAN  MOVER  AREG   ='5'",
            "NAN  MOVEM  AREG   A",
            "LOOP MOVER  AREG   A",
            "NAN  MOVER  CREG   B",
            "NAN  ADD    CREG   ='1'",
            "NAN  MOVER  AREG   A",
            "NAN  MOVER  CREG   B",
            "NAN  MOVER  AREG   A",
            "NAN  MOVER  CREG   B",
            "NAN  MOVER  AREG   A",
            "NAN  BC     ANY    NEXT",
            "NAN  LTORG  NAN    NAN",
            "NAN  MOVER  AREG   A",
            "NEXT SUB    AREG   ='1'",
            "NAN  BC     LT     BACK",
            "LAST STOP   NAN    NAN",
            "NAN  ORIGIN LOOP+2 NAN",
            "NAN  MULT   CREG   B",
            "NAN  ORIGIN LAST+1 NAN",
            "A    DS     1      NAN",
            "BACK EQU    LOOP   NAN",
            "B    DS     1      NAN",
            "NAN  END    NAN    NAN"
        );
        
        assembler.pass1(input);
        assembler.displaySymbolTable();
        assembler.displayLiteralTable();

        assembler.pass2();
        assembler.displayMachineCode();
    }
}
