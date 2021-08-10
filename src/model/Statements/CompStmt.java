package model.Statements;

import model.interfaces.*;
import model.program.PrgState;
import model.exceptions.*;
import model.Statements.*;
import model.ADT.*;

public class CompStmt implements IStmt{
	IStmt first;
	IStmt second;
	
	public CompStmt(IStmt f, IStmt s) {
		this.first = f;
		this.second = s;
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound{
		MyIStack<IStmt> stk = state.getExeStack();
		stk.push(second);
		stk.push(first);
		
		return null;
	}
	
	public String toString() {
		return ("(" + first.toString() + "; " + second.toString() + ")");
	}
}
