package source.main;

import java.util.LinkedList;

public class Tape {
  private LinkedList<Symbol> tape;
  private Symbol whiteSymbol;

  Tape(Symbol inputWhiteSymbol) {
    tape = new LinkedList<Symbol>();
    whiteSymbol = inputWhiteSymbol;
  }

  void loadTape(String inputString) {
    tape.clear();
    for (int i = 0; i < inputString.length(); i++) {
      tape.addLast(new Symbol(String.valueOf(inputString.charAt(i))));
    }
  }

  void changeValueOn(int index, Symbol newValue) {
    tape.set(index, newValue);
  }

  Symbol getFromIndex(int index) {
    if (index < 0) {
      tape.addFirst(whiteSymbol);
      return tape.get(0);
    }
    if (index == tape.size()) {
      tape.addLast(whiteSymbol);
    }
    return tape.get(index);
  }

  void printTape() {

  }
}
