package gui.model;

import model.interfaces.*;
import model.values.*;

public class HeapObject {
    int Address;
    Value Value;

    public int getAddress() {
        return Address;
    }

    public void setAddress(int address) {
        this.Address = address;
    }

    public Value getValue() {
        return Value;
    }

    public void setValue(Value value) {
        this.Value = value;
    }

    public HeapObject(int address, Value value) {
        this.Address = address;
        this.Value = value;
    }
}
