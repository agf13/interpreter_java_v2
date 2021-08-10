package model.Statements;

import model.exceptions.*;
import model.interfaces.*;
import model.program.*;

public class NopStmt implements IStmt {

	public PrgState execute(PrgState state) throws MyException, VarNotFound{
		return null;
	}
	
	public String toString() {
		return ("(no operation)");
	}
}
