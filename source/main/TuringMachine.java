package source.main;

import java.util.*;

import javax.sound.midi.SysexMessage;

public class TuringMachine {
  private Set<State> states;
  private Alphabet alphabet;
  private Alphabet tapeAlphabet;
  private State initialState;
  private Symbol whiteSymbol;
  private Set<State> finalStates;
  private Delta transitions;
  private Boolean stringAccepted;
  private Boolean printTrace;
  private int head;
  private Tape tape;

  TuringMachine(Set<String> inputStates, Set<String> inputAlphabet,
                Set<String> inputTapeAlphabet, String inputInitialState,
                String inputWhiteSymbol, Vector<Vector<String>> inputTransitions,
                Set<String> inputFinalStates) {
    
    stringAccepted = false;

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

  Boolean checkString(String inputString) {
    tape.loadTape(inputString);
    head = 0;
    State currentState = initialState;
    State nextState = initialState;
    Symbol symbolToWrite;
    String movement;
    //State nextState = findNextState(initialState, tape.get(head));
    Map.Entry<State, Symbol> mapKey = new AbstractMap.SimpleEntry<State, Symbol>(initialState, tape.getFromIndex(head));
    //int i = 0;
    while (transitions.getTransitionsMap().get(mapKey) != null) {
    //   while (i < 5) {
      //System.out.println("Current State " + currentState.getName());
      nextState = transitions.getTransitionsMap().get(mapKey).iterator().next().getKey();
      symbolToWrite = transitions.getTransitionsMap().get(mapKey).iterator().next().getValue().getKey();
      movement = transitions.getTransitionsMap().get(mapKey).iterator().next().getValue().getValue();
      //Modificar MT
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
      // System.out.println("Next State: " + nextState.getName());
      // System.out.println("head: " + head);
      //System.out.println("Caracter leido: " + readSymbol.value);
      mapKey = new AbstractMap.SimpleEntry<State, Symbol>(nextState, readSymbol);
      currentState = nextState;
    }

    tape.printTape(head);
    //System.out.println("Current State " + currentState.getName());
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
}
