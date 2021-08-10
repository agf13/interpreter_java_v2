package model.interfaces;

import model.values.*;
import model.types.*;
import model.exceptions.*;
import model.ADT.*;

public interface Exp {
	
	Type typecheck(MyIDictionary<String,Value> symList, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty;
	Value eval(MyIDictionary<String, Value> symTable, IHeap<Integer, Value> heap) throws ADTEmpty, MyException, VarNotFound;
	public String toString();
}
