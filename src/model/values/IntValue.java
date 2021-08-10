package model.values;

import model.interfaces.*;
import model.types.*;

public class IntValue implements Value{
	int val;
	
	public IntValue() {
		this.val = 0;
	}
	
	public IntValue(int val) {
		this.val = val;
	}
	
	public Type getType() {
		return new IntType();
	}
	
	public int getVal() {
		return this.val;
	}
	
	public void setVal(int val) {
		this.val = val;
	}
	
	public boolean equals(Object another){
		if (another instanceof IntValue) {
			if(this.val == ((IntValue)another).getVal()) 
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public String toString() {
		return Integer.toString(val);
	}
}
