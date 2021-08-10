package model.interfaces;

import model.exceptions.*;
import model.program.*;

public interface IStmt {
	PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty;
	public String toString();
}
