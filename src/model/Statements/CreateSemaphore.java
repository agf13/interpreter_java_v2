package model.Statements;

import model.interfaces.*;
import model.exceptions.*;
import model.program.*;
import model.exp.*;
import model.values.*;
import model.types.*;
import model.ADT.*;

public class CreateSemaphore implements IStmt{
	String varName;
	Exp exp;
	
	public CreateSemaphore(String varName, Exp exp) {
		this.varName = varName;
		this.exp = exp;
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String, Value> symTable = state.getSymTable();
		IHeap<Integer,Value> heap = state.getHeapTable();
		
		Value val = exp.eval(symTable,heap);
		if(!val.getType().equals(new IntType())) {
			throw new MyException("Result should be integer!");
		}
		
		//create a LockValue and add it to symTable
		int target = ((IntValue)val).getVal();
		LockValue lv = new LockValue(target);
		symTable.put(varName, lv);
		
		return null;
	}
	
	public String toString() {
		return "(CreateSemaphore(" + this.varName + "," + this.exp.toString() + "))"; 
	}

}
