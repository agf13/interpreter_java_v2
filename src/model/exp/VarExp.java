package model.exp;

import model.exceptions.*;
import model.interfaces.*;
import model.ADT.*;
import model.values.*;

public class VarExp implements Exp{
	String name;
	

	public Type typecheck(MyIDictionary<String,Value> symList, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty{
		return symList.getValue(name).getType();
	}
	
	public VarExp(String name) {
		this.name = name;
	}
	
	public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer, Value> heap) throws ADTEmpty, MyException, VarNotFound {
		return tbl.getValue(name);
	}
	
	public String toString() {
		return name;
	}
	
}
