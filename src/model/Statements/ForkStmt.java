package model.Statements;

import model.interfaces.*;
import model.program.*;
import model.values.*;
import model.types.*;
import model.exceptions.*;

import java.io.BufferedReader;

import model.ADT.*;

public class ForkStmt implements IStmt{
	IStmt stmt;
	
	public ForkStmt(IStmt stmt) {
		this.stmt = stmt;
	}

	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{		
		MyIStack<IStmt> exeStack_2 = new MyStack<IStmt>();
		MyIDictionary<String, Value> symTable_2 = state.getSymTable().clone_dict();
		MyIDictionary<String, BufferedReader> fileTable_2 = state.getFileTable();
		//MyIDictionary<Integer, Value> heapTable_2 ;
		MyIList<Value> out_2 = state.getOut();
		
		PrgState.manageId();
		
		PrgState prg_2 = new PrgState(exeStack_2, symTable_2, out_2, fileTable_2, this.stmt);
		
		//will return a PrgState;
		return prg_2;
	}
	
	public String toString() {
		return "fork(" + stmt.toString() + ")";
	}
}
