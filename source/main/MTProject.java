package source.main;

import java.util.Scanner;

public class MTProject {
  public static void main(String args[]) {
    try {
      if (args.length == 0) {
        throw new RuntimeException("No ha especificado un fichero de entrada");
      }
      MTReader reader = new MTReader(args[0]);
      TuringMachine mt = new TuringMachine(reader.getStates(), reader.getAlphabet(), 
                                            reader.getTapeAlphabet(), reader.getinitialState(),
                                            reader.getWhiteSymbol(), reader.getTransitions(),
                                            reader.getFinalStates());
      mt.verifyMachine();
      System.out.println("Introduzca una cadena a comprobar (para terminar escriba \"salir\"): ");                                
      String input;
      Scanner userInput = new Scanner(System.in);
      while (true) {
        input = userInput.nextLine();
        if (input.equals("salir")) break;
        if (mt.checkString(input))
          System.out.println("Cadena reconocida ✅");
        else
          System.out.println("Cadena NO reconocida ❌");
      }
      userInput.close();
    } catch (Error error) {
      System.out.println(error);
    }
  }
}
