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

  void printTape(int headPosition) {
    System.out.println(new String(new char[tape.size() * 4]).replace("\0", "-"));
    for (int i = 0; i < tape.size(); i++) {
      System.out.printf("| %1s ", tape.get(i).value);
    }
    System.out.print("|\n");
    System.out.println(new String(new char[tape.size() * 4]).replace("\0", "-"));
    for (int i = 0; i < headPosition; i++) {
      System.out.printf("%4s"," ");
      //System.out.print(new String(new char[5]).replace("\0", " "));
    }
    System.out.printf("%3s\n","↑");
  }
}
