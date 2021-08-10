package model.Statements;

import model.types.*;
import model.interfaces.*;
import model.program.PrgState;
import model.exceptions.*;
import model.Statements.*;
import model.exp.*;
import model.ADT.*;

public class VarDeclStmt implements IStmt{
	String name;
	Type type;
	
	public VarDeclStmt(String name, Type type) {
		this.name = name;
		this.type = type;
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound{
		MyIDictionary<String, Value> symTable = state.getSymTable();
		if(!symTable.containsKey(name)) {
			symTable.put(name, type.defaultValue());
		}
		else {
			throw new MyException("Variable already existing"); 
		}
		
		
		return null;
	}
	
	public String toString() {
		return (type.toString() + " " + name);
	}
}
