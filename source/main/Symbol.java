package source.main;

import java.util.Objects;

public class Symbol {
  public String value;

  Symbol(String inputValue) {
    value = inputValue;
  }

  // Modificamos la funcion hashcode para que los mapas funcionen bien con objetos de esta clase como clave
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  // Modificamos la funcion equals para que los mapas funcionen bien con objetos de esta clase como clave
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if(obj == null || obj.getClass()!= this.getClass())
      return false;

    Symbol symbol = (Symbol) obj;

    return (symbol.value.equals(this.value));
  }
}
