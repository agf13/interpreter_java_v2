package model.Statements;

import model.interfaces.*;
import model.program.PrgState;
import model.exceptions.*;
import model.types.*;
import model.values.*;
import model.exp.*;


public class WH implements IStmt{
	String var_name;
	Exp exp;
	
	public WH(String var_name, Exp exp) {
		this.var_name = var_name;
		this.exp = exp;
	}
	
	public WH(Exp exp, String var_name) {
		this(var_name,exp);
	}
	
	
	public Type typecheck(MyIDictionary<String,Value> symList, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty{
		//TO DO: to do this
		return null;
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String,Value> symTable = state.getSymTable();
		IHeap<Integer, Value> heap = state.getHeapTable();
		Value exp_val = exp.eval(symTable, heap);
		if(symTable.containsKey(var_name)) {
			Value var_val = symTable.getValue(var_name);
			if(var_val.getType() instanceof RefType) {
				Integer addr = ((RefValue)var_val).getAddr();
				if(heap.containsKey(addr)) {
					if(exp_val.getType().equals(((RefValue)var_val).getLocationType())) {
						heap.setValue(addr, exp_val);
					}
					else {
						throw new MyException("The types for " + exp.toString() + " and " + var_name + " does not match!");
					}
				}
				else {
					throw new MyException("The address " + addr + " is not in the heap!");
				}
			}
			else {
				throw new MyException("Variable " + var_name + " must be of RefType!");
			}
		}
		else {
			throw new VarNotFound("Variable " + var_name + " not defined!");
		}
		
		return null;
	}
	
	public String toString() {
		return "WH("+var_name+", " + exp.toString() + ")";
	}
}
