package model.Statements.file;

import model.exceptions.*;
import model.exp.*;
import model.types.*;
import model.values.*;
import model.interfaces.*;
import model.program.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt{
	Exp exp;
	String var;
	
	Value val_exp;
	Value val_var;

	public ReadFile(Exp exp, String var) {
		this.exp = exp;
		this.var = var;
	}
	public ReadFile(String var, Exp exp) {
		this(exp, var);
	}
	
	public Type typecheck(MyIDictionary<String,Value> symList, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty{
		//to do this later
		return null;
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String,Value> symTable = state.getSymTable();
		MyIDictionary<String,BufferedReader> fileTable = state.getFileTable();
		IHeap<Integer, Value> heap = state.getHeapTable();
		if(symTable.containsKey(var)) {
			val_var = symTable.getValue(var);
			if(val_var.getType().equals(new IntType())) {
				//now that we know our variable is an int, let's try opening the file
				val_exp = exp.eval(symTable,heap);
				if(val_exp.getType().equals(new StringType())) {
					String fileName = ((StringValue)val_exp).getVal();
					if(fileTable.containsKey(fileName)) {
						//the file exists, and our variable is of IntType
						BufferedReader br = fileTable.getValue(fileName);
						String line = null;
				        try{
				            line = br.readLine();
				        } catch (IOException e) {
				            //throw new FileException("cannot read from this file!!\n");
				        	throw new MyException("Cannot read from this file!");
				        }

				        int value;
				        if(line == null){
				            value = 0;
				        }
				        else
				            try {
				                value = Integer.parseInt(line);
				            }
				            catch (NumberFormatException e){
				                value = 0;
				            }
				        symTable.put(var, new IntValue(value));
					}
					else {
						throw new MyException("No file fonud with the given name!");
					}
				}
				else {
					throw new MyException("The name of the file should be a string!");
				}
			}
			else {
				throw new MyException("Variable needs to be of IntType");
			}
		}
		else {
			throw new VarNotFound("Variable name not found");
		}
		
		return null;
	}
	
	public String toString() {
		return "readFile(" + exp.toString() + ", " + var.toString() + ")";
	}
}
