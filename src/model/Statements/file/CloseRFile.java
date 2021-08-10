package model.Statements.file;

import model.exceptions.*;
import model.interfaces.*;
import model.program.*;
import model.values.*;
import model.ADT.*;
import model.types.*;
import model.values.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
	Exp exp;
	
	public CloseRFile(Exp exp) {
		this.exp = exp;
	}
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String,Value> symTable = state.getSymTable(); 
		MyIDictionary<String,BufferedReader> fileTable = state.getFileTable();
		IHeap<Integer, Value> heap = state.getHeapTable();
		
		Value val = exp.eval(symTable,heap);
		if(val.getType().equals(new StringType())) {
			//we know the expresion evaluates to a string
			String fileName = ((StringValue)val).getVal();
			if(fileTable.containsKey(fileName)) {
				//the file exists
				BufferedReader br = fileTable.getValue(fileName);
				try {
					br.close();
				} catch (IOException e) {
					throw new MyException("CloseRFile: " + e.getMessage());
				}
				fileTable.remove(fileName);
				String val_key = symTable.getKey(val);
				symTable.remove(val_key);
				
			}
			else {
				throw new MyException("CloseRFile: no file found with the name: " + fileName + "!");
			}
		}
		else {
			throw new MyException("CloseRFile: parameter should be of StringType!");
		}
		
		return null;
	}
	
	public String toString() {
		return ("CloseRFile(\""+exp.toString()+"\")");
	}

}
