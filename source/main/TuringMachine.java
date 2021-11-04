package source.main;

import java.util.Vector;
import java.util.Map;
import java.util.Set;
import java.util.AbstractMap;
import java.util.HashSet;


public class TuringMachine {
  private Set<State> states;
  private Alphabet alphabet;
  private Alphabet tapeAlphabet;
  private State initialState;
  private Symbol whiteSymbol;
  private Set<State> finalStates;
  private Delta transitions;
  private int head;
  private Tape tape;

  TuringMachine(Set<String> inputStates, Set<String> inputAlphabet,
                Set<String> inputTapeAlphabet, String inputInitialState,
                String inputWhiteSymbol, Vector<Vector<String>> inputTransitions,
                Set<String> inputFinalStates) {
    
    head = 0;

    states = new HashSet<State>();
    for (String element: inputStates) {
      State tmpState = new State(element, false);
      states.add(tmpState);
    }

    finalStates = new HashSet<State>();
    for (String element: inputFinalStates) {
      State tmpFinalState = new State(element, false);
      finalStates.add(tmpFinalState);
    }

    alphabet = new Alphabet(inputAlphabet);
    
    tapeAlphabet = new Alphabet(inputTapeAlphabet);

    initialState = new State(inputInitialState, false);

    whiteSymbol = new Symbol(inputWhiteSymbol);

    transitions = new Delta(inputTransitions);

    tape = new Tape(whiteSymbol);
  }

  // Funcion que comprueba si una cadena de entrada es aceptada
  Boolean checkString(String inputString) {
    tape.loadTape(inputString);
    head = 0;
    State currentState = initialState;
    State nextState = initialState;
    Symbol symbolToWrite;
    String movement;
    Map.Entry<State, Symbol> mapKey = new AbstractMap.SimpleEntry<State, Symbol>(initialState, tape.getFromIndex(head));
    
    while (transitions.getTransitionsMap().get(mapKey) != null) {
      nextState = transitions.getTransitionsMap().get(mapKey).iterator().next().getKey();
      symbolToWrite = transitions.getTransitionsMap().get(mapKey).iterator().next().getValue().getKey();
      movement = transitions.getTransitionsMap().get(mapKey).iterator().next().getValue().getValue();
      tape.changeValueOn(head, symbolToWrite);
      Symbol readSymbol;
      if (movement.equals("L")) {
        moveHeadLeft();
        readSymbol = tape.getFromIndex(head);
        if (head == -1) head = 0;
      }
      else {
        moveHeadRight();
        readSymbol = tape.getFromIndex(head);
      }
      mapKey = new AbstractMap.SimpleEntry<State, Symbol>(nextState, readSymbol);
      currentState = nextState;
    }

    tape.printTape(head);
    if (finalStates.contains(currentState)) {
      return true;
    }

    return false;
  }

  void moveHeadRight() {
    head++;
  }

  void moveHeadLeft() {
    head--;
  }

  // Verifica que la definicion de la MT es correcta
  Boolean verifyMachine() {
    if (!states.contains(initialState)) 
      throw new RuntimeException("Estado de inicio inválido");
    if (!tapeAlphabet.getSymbols().contains(whiteSymbol)) 
      throw new RuntimeException("Símbolo blanco inválido");
    if (!states.containsAll(finalStates)) 
      throw new RuntimeException("Estados finales inválidos");
    for (Map.Entry<Map.Entry<State, Symbol>, Set<Map.Entry<State, Map.Entry<Symbol, String>>>> key: 
          transitions.getTransitionsMap().entrySet()) {
      if (states.contains(key.getKey().getKey()) &&
         (tapeAlphabet.getSymbols().contains(key.getKey().getValue())) &&
         (states.contains(key.getValue().iterator().next().getKey())) && //Para na máquina determinista asumimos que es set siempre tiene un elemento
         (tapeAlphabet.getSymbols().contains(key.getValue().iterator().next().getValue().getKey()))) {
           continue;
         }
      throw new RuntimeException("Transición inválida");
    }
    return true;
  }
}
