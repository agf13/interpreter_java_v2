package model.Statements;

import model.exceptions.*;
import model.interfaces.*;
import model.program.*;

public class PrintStmt implements IStmt{
	Exp toPrintExp;
	
	public PrintStmt(Exp exp) {
		this.toPrintExp = exp;
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound{
		try {
			state.getOut().add(toPrintExp.eval(state.getSymTable(),state.getHeapTable()));
		}
		catch(Exception e) {
			throw new MyException(e.getMessage());
		}
		
		return null;
	}
	
	public String toString() {
		return ("print(" + this.toPrintExp.toString() + ")");
	}
	
}
