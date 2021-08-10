package model.Statements.file;

import model.interfaces.*;
import model.program.PrgState;
import model.exceptions.*;
import model.values.*;
import model.types.*;
import model.ADT.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


//sth new
public class OpenRFile implements IStmt{
	Exp exp;
	String var_id;
	String fileName;
	
	public OpenRFile(Exp exp) {
		this.exp = exp;
	}
	
	
	public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
		MyIDictionary<String, Value> symTable = state.getSymTable();
		MyIDictionary<String, BufferedReader> fileTable = state.getFileTable(); 
		IHeap<Integer,Value> heap = state.getHeapTable();
		Value val = exp.eval(symTable,heap);
		if(val.getType().equals(new StringType())) {
			fileName = ((StringValue)val).getVal();
			if(fileTable.containsKey(fileName)) {
				throw new MyException("File already existing");
			}
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				fileTable.put(fileName, br);
			} 
			catch (FileNotFoundException e) {
				throw new MyException("File not found");
			}
		}
		else {
			throw new MyException("The name of a file should be string, not " + val.getType().toString());
		}
		return null;
	}
	
	public String toString() {
		//return "openRFile(" + var_id + ",\"" + fileName + "\")";
		return "openRFile(" + "\"" + exp.toString() + "\")";
	}
	
}
