package model.program;

import model.ADT.*;
import model.interfaces.*;
import model.Statements.*;
import model.exceptions.*;
import model.Statements.file.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrgState {
	MyIStack<IStmt> exeStack;
	MyIDictionary<String, Value> symTable;
	MyIDictionary<String, BufferedReader> fileTable;
	IHeap<Integer, Value> heapTable;
	MyIList<Value> out;
	IStmt originalProgram;
	static int id;
	int prgId;
	
	public static synchronized void manageId() {
		id = id + 1;
	}
	
	public int getPrgId() {
		return this.prgId;
	}


	public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String,Value> symTable, MyIList<Value> out, IStmt prg) {
		this.exeStack = exeStack;
		this.symTable = symTable;
		fileTable = new MyDictionary<String, BufferedReader>();
		this.out = out;
		this.heapTable = new Heap<Integer, Value>();
		
		this.prgId = id;
		
		originalProgram = prg;
		this.exeStack.push(prg);
	}

	public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String,Value> symTable, MyIList<Value> out, MyIDictionary<String, BufferedReader> fileTable, IStmt prg) {
		this.exeStack = exeStack;
		this.symTable = symTable;
		this.fileTable = fileTable;
		this.out = out;
		this.heapTable = new Heap<Integer, Value>();

		this.prgId = id;
		
		originalProgram = prg;
		this.exeStack.push(prg);
	}

	public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String,Value> symTable, MyIList<Value> out, MyIDictionary<String, BufferedReader> fileTable, IHeap<Integer,Value> heap, IStmt prg) {
		this.exeStack = exeStack;
		this.symTable = symTable;
		this.fileTable = fileTable;
		this.out = out;
		this.heapTable = heap;

		this.prgId = id;
		
		originalProgram = prg;
		this.exeStack.push(prg);
	}
	
	public PrgState(IStmt prg) {
		exeStack = new MyStack<IStmt>();
		symTable = new MyDictionary<String, Value>();
		fileTable = new MyDictionary<String, BufferedReader>();
		out = new MyList<Value>();
		this.heapTable = new Heap<Integer, Value>();
		
		this.originalProgram = prg;

		this.prgId = id;
		
		exeStack.push(prg);
	}
	
	public MyIDictionary<String, BufferedReader> getFileTable(){
		return fileTable;
	}
	
	public void setFileTable(MyIDictionary<String, BufferedReader> newFileTable) {
		this.fileTable = newFileTable;
	}
	
	
	///<heap>///
	public IHeap<Integer, Value> getHeapTable(){
		return heapTable;
	}
	public void setHeapTable(Heap<Integer, Value> newHeapTable) {
		this.heapTable = newHeapTable;
	}
	///</heap>///
	public MyIStack<IStmt> getExeStack() {
		return exeStack;
	}

	public void setExeStack(MyIStack<IStmt> exeStack) {
		this.exeStack = exeStack;
	}

	public MyIDictionary<String, Value> getSymTable() {
		return symTable;
	}

	public void setSymTable(MyIDictionary<String, Value> symTable) {
		this.symTable = symTable;
	}

	public MyIList<Value> getOut() {
		return out;
	}

	public void setOut(MyIList<Value> out) {
		this.out = out;
	}	
	
	public IStmt getOriginalProgram() {
		return originalProgram;
	}

	public void setOriginalProgram(IStmt originalProgram) {
		this.originalProgram = originalProgram;
	}
	
	
	public void addOut(Value val) {
		this.out.add(val);
	}
	
	public boolean isNotComplete() {
		return !exeStack.isEmpty();
	}
	
	public PrgState oneStep() throws MyException, ADTEmpty, VarNotFound {
		if(!isNotComplete()) {
			throw new ADTEmpty("No more statements on stack");
		}
		IStmt curStmt = exeStack.pop();
		return curStmt.execute(this);
	}
	
	public String toString() {
		return(
				"Id = " + Integer.toString(prgId) + System.lineSeparator() +
				"ExeStack: " + this.exeStack.toString() + System.lineSeparator() +
				"SymTable: " + this.symTable.toString() + System.lineSeparator() +
				"FileTable: " + this.fileTable.toString() + System.lineSeparator() +
				"HeapTable: " + this.heapTable.toString() + System.lineSeparator() +
				"Output: " + this.out.toString() + System.lineSeparator() + System.lineSeparator()
				);
	}

//	public MyIDictionary<String, Value> cloneSymTable() {
//		MyIDictionary<String,Value> clone = new MyDictionary<String,Value>();
//		List<String> str_list = new ArrayList<String>();
//		List<Value> val_list = new ArrayList<Value>();
//		for(Map.Entry<String,Value> entry : this.symTable.entrySet()) {
//			clone.put(entry.getKey(), entry.getValue());
//		}
//		return clone;
//	}
	
	
	
	
}
