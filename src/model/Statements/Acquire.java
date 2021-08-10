package model.Statements;

import model.interfaces.*;
import model.exceptions.*;
import model.program.*;
import model.exp.*;
import model.values.*;
import model.types.*;
import model.ADT.*;

public class Acquire implements IStmt{
	String varName;
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String, Value> symTable = state.getSymTable();
		
		if(!symTable.containsKey(varName)) {
			throw new MyException("Var not vound!");
		}
		Value lockVal = symTable.getValue(varName);
		((LockValue)lockVal).incrementSize();
		if(((LockValue)lockVal).ready()) {
			
		}
		else {
			
		}
		
		return null;
	}
	
	public String toString() {
		return "acquire(" + varName + ")";
	}

	
}
