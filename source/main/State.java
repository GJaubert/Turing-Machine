package source.main;

import java.util.Objects;

public class State {
  private boolean final_ = false;
  private String name_;

  State(String name, boolean inputFinal) {
    name_ = name;
    final_ = inputFinal;
  }

  public boolean isFinal() {
    return final_;
  }

  public String getName() {
    return name_;
  }

  // Modificamos la funcion equals para que los mapas funcionen bien con objetos de esta clase como clave
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if(obj == null || obj.getClass()!= this.getClass())
      return false;

    State state = (State) obj;

    return (state.name_.equals(this.name_));
  }

  // Modificamos la funcion hashcode para que los mapas funcionen bien con objetos de esta clase como clave
  @Override
  public int hashCode() {
    return Objects.hash(name_);
  }
}
