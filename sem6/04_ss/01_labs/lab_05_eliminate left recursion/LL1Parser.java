import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LL1Parser {

    // Grammar rules
    private Map<String, List<String>> grammarRules = new HashMap<>();
    private Map<String, Set<String>> firstSets = new HashMap<>();
    private Map<String, Set<String>> followSets = new HashMap<>();
    private Map<String, Map<String, String>> parseTable = new HashMap<>();

    // Start symbol of the grammar
    private String startSymbol;

    // Constructor to read grammar rules from file
    public LL1Parser(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                String nonTerminal = parts[0].trim();
                String[] productions = parts[1].split("\\|");
                if (grammarRules.isEmpty()) startSymbol = nonTerminal;
                grammarRules.put(nonTerminal, Arrays.asList(productions));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate First sets
    private void calculateFirstSets() {
        for (String nonTerminal : grammarRules.keySet()) {
            calculateFirst(nonTerminal);
        }
    }

    private void calculateFirst(String symbol) {
        if (firstSets.containsKey(symbol)) return;

        Set<String> firstSet = new HashSet<>();

        for (String production : grammarRules.get(symbol)) {
            String[] symbols = production.trim().split("\\s+");
            String firstSymbol = symbols[0];

            if (!firstSymbol.equals(symbol) && grammarRules.containsKey(firstSymbol)) {
                calculateFirst(firstSymbol);
                firstSet.addAll(firstSets.get(firstSymbol));
            } else {
                firstSet.add(firstSymbol);
            }
        }

        firstSets.put(symbol, firstSet);
    }

    // Method to calculate Follow sets
    private void calculateFollowSets() {
        for (String nonTerminal : grammarRules.keySet()) {
            calculateFollow(nonTerminal);
        }
    }

    private void calculateFollow(String symbol) {
        if (followSets.containsKey(symbol)) return;

        Set<String> followSet = new HashSet<>();

        if (symbol.equals(startSymbol)) {
            followSet.add("$");
        }

        for (Map.Entry<String, List<String>> entry : grammarRules.entrySet()) {
            for (String production : entry.getValue()) {
                String[] symbols = production.trim().split("\\s+");
                for (int i = 0; i < symbols.length; i++) {
                    if (symbols[i].equals(symbol)) {
                        if (i < symbols.length - 1) {
                            String nextSymbol = symbols[i + 1];
                            if (grammarRules.containsKey(nextSymbol)) {
                                followSet.addAll(firstSets.get(nextSymbol));
                                followSet.remove("ε");
                                if (firstSets.get(nextSymbol).contains("ε")) {
                                    followSet.addAll(followSets.get(entry.getKey()));
                                }
                            } else {
                                followSet.add(nextSymbol);
                            }
                        } else if (!entry.getKey().equals(symbol)) {
                            followSet.addAll(followSets.get(entry.getKey()));
                        }
                    }
                }
            }
        }

        followSets.put(symbol, followSet);
    }

    // Method to construct parse table
    private void constructParseTable() {
        for (String nonTerminal : grammarRules.keySet()) {
            for (String production : grammarRules.get(nonTerminal)) {
                Set<String> firstSet = calculateFirstForProduction(production);
                for (String terminal : firstSet) {
                    if (!terminal.equals("ε")) {
                        addToParseTable(nonTerminal, terminal, production);
                    }
                }
                if (firstSet.contains("ε")) {
                    for (String terminal : followSets.get(nonTerminal)) {
                        addToParseTable(nonTerminal, terminal, production);
                    }
                }
            }
        }
    }

    private Set<String> calculateFirstForProduction(String production) {
        Set<String> firstSet = new HashSet<>();
        String[] symbols = production.trim().split("\\s+");
        for (String symbol : symbols) {
            if (grammarRules.containsKey(symbol)) {
                firstSet.addAll(firstSets.get(symbol));
                if (!firstSets.get(symbol).contains("ε")) {
                    break;
                }
            } else {
                firstSet.add(symbol);
                break;
            }
        }
        return firstSet;
    }

    private void addToParseTable(String nonTerminal, String terminal, String production) {
        if (!parseTable.containsKey(nonTerminal)) {
            parseTable.put(nonTerminal, new HashMap<>());
        }
        parseTable.get(nonTerminal).put(terminal, production);
    }

    // Method to parse input string
    public boolean parse(String input) {
        Stack<String> stack = new Stack<>();
        stack.push("$");
        stack.push(startSymbol);

        int index = 0;
        while (!stack.isEmpty()) {
            String symbol = stack.pop();
            String currentInput = input.substring(index, index + 1);

            if (grammarRules.containsKey(symbol)) {
                String production = parseTable.get(symbol).get(currentInput);
                if (production != null) {
                    String[] symbols = production.trim().split("\\s+");
                    for (int i = symbols.length - 1; i >= 0; i--) {
                        if (!symbols[i].equals("ε")) {
                            stack.push(symbols[i]);
                        }
                    }
                } else {
                    return false;
                }
            } else if (symbol.equals(currentInput)) {
                index++;
            } else {
                return false;
            }
        }

        return index == input.length();
    }

    public static void main(String[] args) {
        LL1Parser parser = new LL1Parser("grammar.txt"); // Provide the filename with grammar rules
        parser.calculateFirstSets();
        parser.calculateFollowSets();
        parser.constructParseTable();

        // Example input strings to test
        String[] inputs = {"id+id*id", "id+id+id", "id*id", "id+id*id+","id"};

        for (String input : inputs) {
            System.out.println("Input: " + input);
            boolean accepted = parser.parse(input);
            if (accepted) {
                System.out.println("Accepted");
            } else {
                System.out.println("Rejected");
            }
            System.out.println();
        }
    }
}
