package model.values;

import model.interfaces.*;
import model.types.*;

public class StringValue implements Value{
	String val;
	
	public StringValue() {
		this.val = "";
	}
	
	public StringValue(String val) {
		this.val = val;
	}
	
	public Type getType() {
		return new StringType();
	}
	
	public String getVal() {
		return this.val;
	}
	
	public void setVal(String val) {
		this.val = val;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof StringValue) {
			if(this.val.equals(((StringValue)obj).getVal()))
				return true;
			return false;
		}
		else
			return false;
	}
	
	public String toString() {
		if((this.val).equals(""))
			return "\"\"";
		return ("\"" + this.val + "\"");
	}
}
