package model.types;

import model.types.*;
import model.interfaces.*;
import model.values.*; 

public class RefType implements Type{
	 Type inner;
	 
	 public RefType(Type inner) {
		 this.inner=inner;
	 }
	 
	 public Type getInner() {
		 return inner;
	 }
	 
	 public boolean equals(Object another){
		 if (another instanceof RefType)
			 return inner.equals(((RefType)another).getInner());
		 else
			 return false;
	 }
	 
	 public String toString() {
		 return "Ref(" +inner.toString()+")";
	 }
	 
	 public Value defaultValue() { 
		 return new RefValue(0,inner);
	 }
}
