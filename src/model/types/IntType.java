package model.types;

import model.interfaces.*;
import model.values.*;

public class IntType implements Type{

	public boolean equals(Object another) {
		if(another instanceof IntType) 
			return true;
		else
			return false;
	}
	
	public Value defaultValue() {
		return new IntValue();
	}
	
	public String toString() {
		return "int";
	}
}
