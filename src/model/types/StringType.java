package model.types;

import model.interfaces.*;
import model.values.*;

public class StringType implements Type{

	public boolean equals(Object another) {
		if(another instanceof StringType) 
			return true;
		return false;
	}
	
	public Value defaultValue() {
		return new StringValue();
	}
	
	
	public String toString() {
		return "string";
	}
}
