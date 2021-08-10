package model.Statements;

import model.interfaces.*;
import model.types.*;
import model.values.*;
import model.program.*;
import model.exceptions.*;

public class AssignStmt implements IStmt{
	String name; //name of var
	//Value val; //value of var
	Exp exp; //expression which gives our new Value
	
	/*
	 * public AssignStmt(String name, Value val) { this.name = name; this.val = val;
	 * }
	 */
	
	public AssignStmt(String name, Exp exp) {
		this.name = name;
		this.exp = exp;
	}
	
	public AssignStmt(Exp exp,String name) {
		this(name,exp);
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		//check if variable name is in symTable (it means that it was declared
		if(state.getSymTable().containsKey(name)) {
			Value ourVar = state.getSymTable().getValue(name);
			Type outVarType = ourVar.getType();
			Value newVal = exp.eval(state.getSymTable(),state.getHeapTable());
			if(outVarType.equals(newVal.getType())) {
				state.getSymTable().put(name, newVal);
			}
			else {
				throw new MyException("Value doesn't match the variable type for " + this.name);
			}
		}
		else {
			throw new VarNotFound("Variable " + this.name + "undeclared");
		}
		
		return null;
	}
	
	public String toString() {
		return (name + " = " + exp.toString());
	}
}
