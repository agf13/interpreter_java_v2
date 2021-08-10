package model.Statements;

import model.interfaces.*;
import model.exceptions.*;
import model.values.*;
import model.types.*;
import model.exp.*;

public class RH implements Exp{
	Exp exp;
	
	public RH(Exp exp) {
		this.exp = exp;
	}

	public Type typecheck(MyIDictionary<String,Value> symList, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty{
		//TO DO: to do this
		return null;
	}
	
	public Value eval(MyIDictionary<String, Value> symTable, IHeap<Integer, Value> heap) throws ADTEmpty, MyException, VarNotFound{
		Value val = exp.eval(symTable, heap);
		if(val instanceof RefValue) {
			Integer addr = ((RefValue)val).getAddr();
			if(heap.containsKey(addr)) {
				return heap.getValue(addr);
			}
			else{
				throw new VarNotFound("Invaid heap address found in " + exp.toString() + " !");	
			}
		} 
		else {
			throw new MyException("The value for " + exp.toString() + " needs to be of RefValue!");
		}
	}
	
	public String toString() {
		return "RH("+exp.toString()+")";
	}
}
