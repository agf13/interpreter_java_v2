package model.types;

import model.interfaces.*;
import model.values.*;

public class BoolType implements Type{
	
	public boolean equals(Object another) {
		if(another instanceof BoolType)
			return true;
		return false;
	}
	
	public Value defaultValue() {
		return new BoolValue();
	}
	
	public String toString() {
		return "bool";
	}
}
