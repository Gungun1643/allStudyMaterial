import java.util.*;

public class LR1Parser {
    // Define the grammar rules
    static Map<String, List<List<String>>> grammar = new HashMap<>();
    static {
        grammar.put("S'", Arrays.asList(Arrays.asList("S")));
        grammar.put("S", Arrays.asList(Arrays.asList("C", "C")));
        grammar.put("C", Arrays.asList(Arrays.asList("c", "C"), Arrays.asList("d")));
    }

    // Define the augmented grammar
    static Map<String, List<List<String>>> augmentedGrammar = new HashMap<>();
    static {
        augmentedGrammar.put("S'", Arrays.asList(Arrays.asList("S")));
        augmentedGrammar.put("S", Arrays.asList(Arrays.asList("C", "C")));
        augmentedGrammar.put("C", Arrays.asList(Arrays.asList("c", "C"), Arrays.asList("d")));
    }

    // Function to compute closure of a set of LR(1) items
    static List<List<String>> closure(List<List<String>> items) {
        List<List<String>> newItems = new ArrayList<>(items);
        while (true) {
            boolean added = false;
            for (List<String> item : newItems) {
                String prod = item.get(0);
                int pos = Integer.parseInt(item.get(1));
                String lookahead = item.get(2);
                if (pos < prod.length()) {
                    String nextSymbol = prod.substring(pos, pos + 1);
                    if (augmentedGrammar.containsKey(nextSymbol)) {
                        for (List<String> rule : augmentedGrammar.get(nextSymbol)) {
                            List<String> newItem = new ArrayList<>(rule);
                            newItem.add(0, nextSymbol);
                            newItem.add(1, "0");
                            newItem.add(2, lookahead);
                            if (!newItems.contains(newItem)) {
                                newItems.add(newItem);
                                added = true;
                            }
                        }
                    }
                }
            }
            if (!added) break;
        }
        return newItems;
    }

    // Function to compute goto function for a set of LR(1) items and a symbol
    static List<List<String>> goTo(List<List<String>> items, String symbol) {
        List<List<String>> newItems = new ArrayList<>();
        for (List<String> item : items) {
            String prod = item.get(0);
            int pos = Integer.parseInt(item.get(1));
            String lookahead = item.get(2);
            if (pos < prod.length() && prod.substring(pos, pos + 1).equals(symbol)) {
                List<String> newItem = new ArrayList<>(item);
                newItem.set(1, String.valueOf(pos + 1));
                newItems.add(newItem);
            }
        }
        return closure(newItems);
    }

    // Construct LR(1) canonical collection of sets of items
    static List<List<List<String>>> constructCanonicalCollection() {
        List<List<List<String>>> canonicalCollection = new ArrayList<>();
        List<List<String>> startItems = closure(augmentedGrammar.get("S'"));
        canonicalCollection.add(startItems);
        for (int i = 0; i < canonicalCollection.size(); i++) {
            List<List<String>> items = canonicalCollection.get(i);
            Set<String> symbols = new HashSet<>();
            for (List<String> item : items) {
                String prod = item.get(0);
                int pos = Integer.parseInt(item.get(1));
                if (pos < prod.length()) {
                    symbols.add(prod.substring(pos, pos + 1));
                }
            }
            for (String symbol : symbols) {
                List<List<String>> goToItems = goTo(items, symbol);
                if (!goToItems.isEmpty() && !canonicalCollection.contains(goToItems)) {
                    canonicalCollection.add(goToItems);
                }
            }
        }
        return canonicalCollection;
    }

    // Construct LR(1) parsing table
    static Map<Integer, Map<String, String>> constructParsingTable(List<List<List<String>>> canonicalCollection) {
        Map<Integer, Map<String, String>> parsingTable = new HashMap<>();
        for (int i = 0; i < canonicalCollection.size(); i++) {
            parsingTable.put(i, new HashMap<>());
            List<List<String>> items = canonicalCollection.get(i
