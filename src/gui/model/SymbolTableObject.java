package gui.model;

import model.values.*;
import model.interfaces.*;

public class SymbolTableObject {

    String Name;
    Value Value;

    public SymbolTableObject(String name, Value value) {
        this.Name = name;
        this.Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Value getValue() {
        return Value;
    }

    public void setValue(Value value) {
        this.Value = value;
    }
}
