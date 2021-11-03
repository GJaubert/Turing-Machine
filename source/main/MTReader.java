package source.main;

import java.io.File;
import java.util.*;

public class MTReader {

  private File file_;
  private Scanner scan_;
  private String currentLine;
  private Set<String> inputStates;
  private Set<String> inputAlphabet;
  private Set<String> inputTapeAlphabet;
  private String initialState;
  private String whiteSymbol;
  private Set<String> finalStates;
  private Vector<Vector<String>> inputTransitions;

  MTReader(String inputFile) {
    file_ = new File(inputFile);
    inputStates = new HashSet<String>();
    inputAlphabet = new HashSet<String>();
    inputTapeAlphabet = new HashSet<String>();
    inputTransitions = new Vector<Vector<String>>();
    finalStates = new HashSet<String>();

    try {
      if (file_.exists()) {
        scan_ = new Scanner(file_);
      }
      else {
        throw new Exception("File not found");
      }
      removeComments();
      readStates();
      readAlphabet();
      readTapeAlphabet();
      readInitialState();
      readWhiteSymbol();
      readFinalState();
      readTransitions();
    } catch(Exception error) {
      System.out.println(error);
    }
  }

  private void removeComments() {
    while (scan_.hasNextLine()) {
      currentLine = scan_.nextLine(); 
      if ((currentLine.charAt(0) == '#')) {
        continue;
      }
      else
        break;
    }
  }

  private void readStates() {
    if (currentLine.length() != 0) {
      String[] states = currentLine.split(" ");
      for (int i = 0; i < states.length; i++) {
        inputStates.add(states[i]);
      }
    } else {
      System.out.println("No States found");
    }
  }

  private void readAlphabet() {
    currentLine = scan_.nextLine();
    if (currentLine.length() != 0) {
      String[] alphabetSymbols = currentLine.split(" ");
      for (int i = 0; i < alphabetSymbols.length; i++) {
        inputAlphabet.add(alphabetSymbols[i]);
      }
    } else {
      System.out.println("No Alphabet found");
    }
  }

  private void readTapeAlphabet() {
    currentLine = scan_.nextLine();
    if (currentLine.length() != 0) {
      String[] tapeAlphabetSymbols = currentLine.split(" ");
      for (int i = 0; i < tapeAlphabetSymbols.length; i++) {
        inputTapeAlphabet.add(tapeAlphabetSymbols[i]);
      }
    } else {
      System.out.println("No Tape Alphabet found");
    }
  }

  private void readInitialState() {
    currentLine = scan_.nextLine();
    if (currentLine.length() != 0) {
      initialState = currentLine;
    } else {
      System.out.println("No Starting State found");
    }
  }

  private void readWhiteSymbol() {
    currentLine = scan_.nextLine();
    if (currentLine.length() != 0) {
      whiteSymbol = currentLine;
    } else {
      System.out.println("No White Symbol found");
    }
  }

  private void readFinalState() {
    currentLine = scan_.nextLine();
    if (currentLine.length() != 0) {
      String[] splittedStates = currentLine.split(" ");
      finalStates = new HashSet<String>();
      Collections.addAll(finalStates, splittedStates);
    } else {
      System.out.println("No Final State found");
    }
  }

  private void readTransitions() {
    while (scan_.hasNextLine()) {
      currentLine = scan_.nextLine();
      String[] splittedTransition = currentLine.split(" ");
      Vector<String> currentTransition = new Vector<String>();
      Collections.addAll(currentTransition, splittedTransition);
      inputTransitions.add(currentTransition);
    }
  }

  public Set<String> getAlphabet() {
    return inputAlphabet;
  }

  public Set<String> getTapeAlphabet() {
    return inputTapeAlphabet;
  }

  public Set<String> getStates() {
    return inputStates;
  }

  public String getinitialState() {
    return initialState;
  }

  public String getWhiteSymbol() {
    return whiteSymbol;
  }

  public Vector<Vector<String>> getTransitions() {
    return inputTransitions;
  }

  public Set<String> getFinalStates() {
    return finalStates;
  }
}
