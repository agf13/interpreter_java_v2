package model.Statements;  			//time until: 11:45 | started at: 9:06

import model.interfaces.*;
import model.exceptions.*;
import model.program.*;
import model.exp.*;
import model.values.*;
import model.types.*;
import model.ADT.*;

public class SwitchStmt implements IStmt{
	Exp exp;
	Exp exp1, exp2;
	IStmt stmtDefault;
	IStmt stmt1,stmt2;
	
	public SwitchStmt(Exp exp, Exp exp1, IStmt stmt1, Exp exp2, IStmt stmt2, IStmt stmtDefault) {
		this.exp = exp;
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.stmt1 = stmt1;
		this.stmt2 = stmt2;
		this.stmtDefault = stmtDefault;
	}
	

	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String, Value> symTable = state.getSymTable();
		MyIStack<IStmt> stack = state.getExeStack();
		IHeap<Integer,Value> heap = state.getHeapTable();
		
		Value val = exp.eval(symTable,heap);
		Value val1 = exp1.eval(symTable,heap);
		Value val2 = exp2.eval(symTable,heap);

		Type type = val.getType();
		Type type1 = val1.getType();
		Type type2 = val2.getType();
		
		//check the types of val, val1 and val2
		if(type.equals(type1) && type.equals(type2)) {
			if(val.equals(val1)) {
				stack.push(stmt1);
			}
			else if(val.equals(val2)) {
				stack.push(stmt2);
			}
			else {
				stack.push(stmtDefault);
			}
		}
		else {
			throw new MyException("Types of expressions must be identic!");
		}
		
		return null;
	}
	
	public String toString() {
		return (
				"(Switch(" + this.exp.toString() + ")" + System.lineSeparator() +
				"(case (" + this.exp1.toString() + "): " + stmt1.toString() + ')' + System.lineSeparator() +
				"(case (" + this.exp2.toString() + "): " + stmt2.toString() + ')' + System.lineSeparator() +
				"(default: " + this.stmtDefault.toString() + "))"
				);
	}
}
