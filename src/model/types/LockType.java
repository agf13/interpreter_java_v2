package model.types;

import model.interfaces.*;
import model.values.*;

public class LockType implements Type{

	
	public boolean equals(Object another) {
		if(another instanceof LockType) 
			return true;
		else
			return false;
	}
	
	public Value defaultValue() {
		return new LockValue();
	}
	
	public String toString() {
		return "LockType";
	}
}
