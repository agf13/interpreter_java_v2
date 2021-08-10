package model.values;

import model.types.*;
import model.interfaces.*;

public class BoolValue implements Value{
	boolean val;
	
	public BoolValue() {
		this.val = false;
	}
	
	public BoolValue(boolean val) {
		this.val = val;
	}
	
	public Type getType() {
		return new BoolType();
	}
	
	public boolean getVal() {
		return this.val;
	}
	
	public void setVal(boolean val) {
		this.val = val;
	}
	
	public boolean equals(Object another) {
		if(another instanceof BoolValue) {
			if(this.val == ((BoolValue)another).getVal())
				return true;
			return false;
		}
		else
			return false;
	}
	
	public String toString() {
		return Boolean.toString(val);
	}
}
