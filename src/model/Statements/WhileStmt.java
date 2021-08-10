package model.Statements;

import model.exceptions.*;
import model.interfaces.*;
import model.Statements.*;
import model.exp.*;
import model.types.*;
import model.values.*;
import model.program.*;

public class WhileStmt implements IStmt {
	Exp exp;
	IStmt stmt;
	
	public WhileStmt(Exp exp, IStmt stmt) {
		this.exp = exp;
		this.stmt = stmt;
	}
	
	public String toString() {
		return ("while(" + this.exp.toString() + ")" + "{" + this.stmt.toString() + "}");
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty {
		MyIDictionary<String, Value> symTable = state.getSymTable();
		MyIStack<IStmt> stk = state.getExeStack();
		IHeap<Integer, Value> heap = state.getHeapTable();
		
		Value cond = exp.eval(symTable,heap);
		if(cond.getType().equals(new IntType())) {
			if(((IntValue)cond).getVal() != 0) {
				stk.push(this);
				stk.push(stmt);
			}
		}
		if(cond.getType().equals(new BoolType())) {
			if(((BoolValue)cond).getVal() == true) {
				stk.push(this);
				stk.push(stmt);
			}
		}
		
		
		return null;
	}
	
}
