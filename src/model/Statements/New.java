package model.Statements;

import model.exceptions.ADTEmpty;
import model.exceptions.MyException;
import model.exceptions.VarNotFound;
import model.interfaces.*;
import model.program.PrgState;
import model.types.*;
import model.values.*;


public class New implements IStmt{
	String var_name;
	Exp exp;
	
	public New(String var_name, Exp exp) {
		this.var_name = var_name;
		this.exp = exp;
	}
	
	public New(Exp exp, String var_name) {
		this(var_name, exp);
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String,Value> symTable = state.getSymTable();
		IHeap<Integer, Value> heap = state.getHeapTable();

		//check if var_name is in symTabe and if it's of RefType
		if(symTable.containsKey(var_name)) {
			Value var_val = symTable.getValue(var_name);
			if(var_val.getType() instanceof RefType) {
				Value exp_val = exp.eval(symTable,heap);
				if(exp_val.getType().equals(((RefValue)var_val).getLocationType())) {
					//associate var_name to the result of exp
					//update symTable so that the RefValue has the address of the new entry created in heap
					Integer newAddr = heap.getFreeAddr();
					heap.put(newAddr, exp_val);
					((RefValue) var_val).setAddr(newAddr);
				}
				else {
					throw new MyException("The types does not match: " + var_name + "  different type than " + exp.toString());
				}
			}
			else {
				throw new MyException("Variable " + var_name + " must be of RefType!");
			}
		}
		else {
			throw new VarNotFound("Variable " + var_name +" not declared!");
		}
		
		return null;
	}
	
	
	public String toString() {
		return ("New("+var_name+","+exp.toString()+")");
	}
	

}
