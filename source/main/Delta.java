package source.main;

import java.util.*;

public class Delta {

  private Map<Map.Entry<State, Symbol>, Set<Map.Entry<State, Map.Entry<Symbol, String>>>> transitionsMap;

  Delta(Vector<Vector<String>> inputTransitions) {
    transitionsMap = new HashMap<Map.Entry<State, Symbol>, Set<Map.Entry<State, Map.Entry<Symbol, String>>>>();
    for (int i = 0; i < inputTransitions.size(); i++) {
      //Left side of main hash entry
      State tmpState = new State(inputTransitions.get(i).get(0), false);
      Symbol readSymbol = new Symbol(inputTransitions.get(i).get(1));
      Map.Entry<State, Symbol> tmpEntry = new AbstractMap.SimpleEntry<State, Symbol>(tmpState, readSymbol);

      //  Right side of main hash entry
      State destinationState = new State(inputTransitions.get(i).get(2), false);
      // Vector<Symbol> entryStackSymbols = new Vector<Symbol>();
      // for (int j = 4; j < inputTransitions.get(i).size(); j++) {
      //   Symbol tmpSymbol = new Symbol(inputTransitions.get(i).get(j));
      //   entryStackSymbols.add(tmpSymbol);
      // }
      Symbol symbolToBeWritten = new Symbol(inputTransitions.get(i).get(3));
      String movement = inputTransitions.get(i).get(4);
      Map.Entry<Symbol, String> symbolAndMove = new AbstractMap.SimpleEntry<Symbol, String>(symbolToBeWritten, movement);
      Map.Entry<State, Map.Entry<Symbol, String>> rightSideEntry = new AbstractMap.SimpleEntry<State, Map.Entry<Symbol, String>>(destinationState, symbolAndMove);

      //  Creating and adding the main hash entry
      if (transitionsMap.get(tmpEntry) == null) {   //No existe esta key
        Set<Map.Entry<State, Map.Entry<Symbol, String>>> tmpSet = new HashSet<Map.Entry<State, Map.Entry<Symbol, String>>>();
        tmpSet.add(rightSideEntry);
        transitionsMap.put(tmpEntry, tmpSet);
      } else {    //Ya existe esta key
        Set<Map.Entry<State, Map.Entry<Symbol, String>>> newSet = transitionsMap.get(tmpEntry);
        newSet.add(rightSideEntry);
        transitionsMap.put(tmpEntry, newSet);
      }
    }
  }

  public Map<Map.Entry<State, Symbol>, Set<Map.Entry<State, Map.Entry<Symbol, String>>>> getTransitionsMap() {
    return transitionsMap;
  }
}
