import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class NonTerminal {

  private String name;
  private ArrayList<String> rules;

  public NonTerminal(String name) {
    this.name = name;
    rules = new ArrayList<>();
	/* rules and then aenu array list 6e. */
  }

  public void addRule(String rule) {
    rules.add(rule);
  }

  public void setRules(ArrayList<String> rules) {
    this.rules = rules;
  }

  public String getName() {
    return name;
  }

  public ArrayList<String> getRules() {
    return rules;
  }

  public void printRule() {
    System.out.print(name + " -> ");
    for (int i = 0; i < rules.size(); i++) {
      System.out.print(rules.get(i));
      if (i != rules.size() - 1) System.out.print(" | ");
    }
    System.out.println();
  }
}

class Grammar {
/*this class manages the overall grammar and 
then process of the left recursion elimination. */
/*it maintains a list of non-terminal. */
  private ArrayList<NonTerminal> nonTerminals;

  public Grammar() {
    nonTerminals = new ArrayList<>();
  }

  /*this method is to add rules  */
  public void addRule(String rule) {
	/*so we would be given line of rule. */
    boolean nt = false;
    String parse = "";

    for (int i = 0; i < rule.length(); i++) {
      char c = rule.charAt(i);
      if (c == ' ') {
		/*nt => non-terminal. */
        if (!nt) {
			/*if it is non-terminal then we would parse it...and
			 * then add into the list of nonTerminals.
			 */
          NonTerminal newNonTerminal = new NonTerminal(parse);
          nonTerminals.add(newNonTerminal);
		  /*say that it is non-terminal. */
          nt = true;
		  /* again initialize the parse to "" */
          parse = "";
        } else if (parse.length() != 0) {
			/*if the parse.length()!=0 => then we would just 
			 * get that particular nonTermianl and then add that rule.
			 */
          nonTerminals.get(nonTerminals.size() - 1).addRule(parse);
          parse = "";
        }
      } else if (c != '|' && c != '-' && c != '>') {
        parse += c;
/*the special charaters are ignored while parsing the rules. */
      }
    }
    if (parse.length() != 0) {
		/*even after parsing all the characters => If 'parse' still 
		 * contains characters,it means there's remaining rule to be added.
		 */
      nonTerminals.get(nonTerminals.size() - 1).addRule(parse);
	  /*this rule adds to the last non-terminal */
    }
  }


  /****************** */
  public void inputData(String filename) {
    // addRule("S -> Sa | Sb | c | d | A");
    // addRule(" A-> Af | d");
    try {
      Scanner sc = new Scanner(new File(filename)); // Read from the specified file
      while (sc.hasNextLine()) {
		/*each line contains one rule => then line-by-line add each rule. */
        String rule = sc.nextLine();//this is to get the rule
        addRule(rule);// add that rule.
      }
      sc.close();
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + e.getMessage());
    }
    /*    
    } */

  }

  /******SOLVE NON-IMMEDIATE LR*************** */
/* this is to resolve the non-immediate recursion. */
  public void solveNonImmediateLR(NonTerminal A, NonTerminal B) {
    String nameA = A.getName();
    String nameB = B.getName();
/*A and B banne non-terminals 6e to banne na rules alag-alag 
arraylist na andar  rakhwa padse ne.
 */
    ArrayList<String> rulesA = new ArrayList<>();
    ArrayList<String> rulesB = new ArrayList<>();
    ArrayList<String> newRulesA = new ArrayList<>();
    rulesA = A.getRules();
    rulesB = B.getRules();

    for (String rule : rulesA) {
      if (rule.substring(0, nameB.length()).equals(nameB)) {
        for (String rule1 : rulesB) {
          newRulesA.add(rule1 + rule.substring(nameB.length()));
        }
      } else {
        newRulesA.add(rule);
      }
    }
    A.setRules(newRulesA);
  }

  /*********************** */
  public void solveImmediateLR(NonTerminal A) {
    String name = A.getName();
    String newName = name + "'";

	/*alphas => they have the left-recursion */
    ArrayList<String> alphas = new ArrayList<>();
	/*betas => they don't have left-recursion */
    ArrayList<String> betas = new ArrayList<>();
	/*this is to retrieve the rules of non-terminal A */
    ArrayList<String> rules = A.getRules();
	/*newRulesA,newRulesA1 => will store the modified rules for A. */
    ArrayList<String> newRulesA = new ArrayList<>();
    ArrayList<String> newRulesA1 = new ArrayList<>();

    rules = A.getRules();

    // Checks if there is left recursion or not
    for (String rule : rules) {
      if (rule.substring(0, name.length()).equals(name)) {
        alphas.add(rule.substring(name.length()));
		/*if there is left-recursion then add to alphas or else add to betas. */
      } else {
        betas.add(rule);
      }
    }

	/*
	newRulesA => new rules for A
	newRulesA1 => new rules for A'
	 */
    // If no left recursion, exit
    if (alphas.size() == 0) return;

	/* what is the newName =A' (character') */
	/*if there are no betas then just add the non-terminal and leave. */
    if (betas.size() == 0) newRulesA.add(newName);

	/*if there are betas then for each terminal do => BA' 
	(  ) */
    for (String beta : betas) newRulesA.add(beta + newName);

	/*for each alphas you do ... A' -> alpha A' */
    for (String alpha : alphas) newRulesA1.add(alpha + newName);

    // Amends the original rule

	/* set the newRulesA to the rules of A. */
    A.setRules(newRulesA);
	/*this is to add episilon(e) at the end of the production rules for A' */
    newRulesA1.add("\u03B5");

	/*this is to add another Non-terminal into our list...A' */
    // Adds new production rule
    NonTerminal newNonTerminal = new NonTerminal(newName);//this is make a new instance of type NonTerminal
	//this is the set it's rules 
    newNonTerminal.setRules(newRulesA1);
    nonTerminals.add(newNonTerminal);//this is to add that nonTerminal.
  }

  /***************************** */
  public void applyAlgorithm() {
    int size = nonTerminals.size();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < i; j++) {
		/*this is apply for the indirect left-recursion. */
		/*this step is to convert the indirect left-recursion -> direct left-recursion. */
        solveNonImmediateLR(nonTerminals.get(i), nonTerminals.get(j));
      }
      solveImmediateLR(nonTerminals.get(i));
    }
  }

  void printRules() {
    for (NonTerminal nonTerminal : nonTerminals) {
      nonTerminal.printRule();
    }
  }
}

/*************************** */
class Main {

  public static void main(String[] args) {
    Grammar grammar = new Grammar();
    grammar.inputData("grammar.txt");
    grammar.applyAlgorithm();
    grammar.printRules();
  }
}
