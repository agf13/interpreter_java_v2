package model.values;

import model.interfaces.*;
import model.types.*;

public class RefValue implements Value{
	 int address;
	 Type locationType;
	 
	 public RefValue(int addr, Type locType) {
		 this.address = addr;
		 this.locationType = locType;
	 }

	 public int getAddr() {
		 return address;
	 }
	 
	 public void setAddr(int newAddr) {
		 this.address = newAddr;
	 }
	 
	 public Type getType() {
		 return new RefType(locationType);
	 }
	 
	 public Type getLocationType() {
		 return this.locationType;
	 }
	 
	 public String toString() {
		 return ( "(" + Integer.toString(address) + ", " + this.locationType.toString() + ")");
	 }
	 
}

