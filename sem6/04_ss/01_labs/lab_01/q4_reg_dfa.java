import dk.brics.automaton.*;

public class q4_reg_dfa {

    public static void main(String[] args) {
        String regex = "(0|1)*01(0|1)*"; // Your regular expression

        // Convert regular expression to NFA
        RegExp regExp = new RegExp(regex);
        Automaton nfa = regExp.toAutomaton();

        // Convert NFA to DFA
        Automaton dfa = Operations.determinize(nfa);

        // Display the DFA transitions table
        displayDFATable(dfa);
    }

    private static void displayDFATable(Automaton dfa) {
        // Get the states and alphabet from the DFA
        int numStates = dfa.getNumStates();
        int alphabetSize = dfa.getStart().getTransitions().size();

        // Display header
        System.out.print("State\t");
        for (int i = 0; i < alphabetSize; i++) {
            System.out.print(" | " + i + "\t");
        }
        System.out.println();

        // Display transitions
        for (int state = 0; state < numStates; state++) {
            System.out.print(state + "\t");

            for (int symbol = 0; symbol < alphabetSize; symbol++) {
                int nextState = dfa.step(state, symbol);
                System.out.print(" | " + nextState + "\t");
            }
            System.out.println();
        }
    }
}
