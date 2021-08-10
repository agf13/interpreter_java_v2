package model.exp;

import model.interfaces.*;
import model.ADT.*;
import model.values.*;
import model.exceptions.*;
import model.types.*;

public class ArithExp implements Exp{
	Exp LExp;
	Exp RExp;
	int op; //operator: 1 = (+); 2 = (-); 3 = (*); 4 = (/)
	char op_char;
	

	public Type typecheck(MyIDictionary<String,Value> symTable, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty{
		Value auxL = LExp.eval(symTable,heap);
		Value auxR = RExp.eval(symTable,heap);

		Type t1 = auxL.getType();
		Type t2 = auxR.getType();

//		if(t1.equals(t2){
//
//		}
//		else{
//			throw new MyException("not correct type");
//		}
		return null;
	}
	
	public ArithExp(Exp Le, Exp Re, int operand) throws MyException {
		this.LExp = Le;
		this.RExp = Re;
		
		if(operand == 1){
			this.op_char = '+';
			this.op = 1;
		}
		else if(operand == 2) {
			this.op_char = '-';
			this.op = 2;
		}
		else if(operand == 3) {
			this.op_char = '*';
			this.op = 3;
		}
		else if(operand == 4) {
			this.op_char = '/';
			this.op = 4;
		}
		else {
			throw new MyException("Unexisting operator");
		}
	}

	public ArithExp(char op_char, Exp Le, Exp Re) throws MyException{
		this(Le,Re,op_char);
	}
	
	public ArithExp(Exp Le, Exp Re, char op_char) throws MyException {
		this.LExp = Le;
		this.RExp = Re;
		
		if(op_char == '+'){
			this.op_char = '+';
			this.op = 1;
		}
		else if(op_char == '-') {
			this.op_char = '-';
			this.op = 2;
		}
		else if(op_char == '*') {
			this.op_char = '*';
			this.op = 3;
		}
		else if(op_char == '/') {
			this.op_char = '/';
			this.op = 4;
		}
		else {
			throw new MyException("Unexisting operator");
		}
	}
	
	public ArithExp(Exp Le, Exp Re, String op_char) throws MyException {
		this.LExp = Le;
		this.RExp = Re;
		
		if(op_char == "+"){
			this.op_char = '+';
			this.op = 1;
		}
		else if(op_char == "-") {
			this.op_char = '-';
			this.op = 2;
		}
		else if(op_char == "*") {
			this.op_char = '*';
			this.op = 3;
		}
		else if(op_char == "/") {
			this.op_char = '/';
			this.op = 4;
		}
		else {
			throw new MyException("Unexisting operator");
		}
	}
	

	public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer, Value> heap) throws ADTEmpty, MyException, VarNotFound {
		Value LVal = this.LExp.eval(tbl,heap);
		Value RVal = this.RExp.eval(tbl,heap);
		if(!RVal.getType().equals(new IntType()) || !LVal.getType().equals(new IntType())) {
			throw new MyException("Types don't match");
		}
		int n1 = ((IntValue)LVal).getVal();
		int n2 = ((IntValue)RVal).getVal();
		if(this.op == 1) {
			return new IntValue(n1+n2);
		}
		else if(this.op == 2) {
			return new IntValue(n1-n2);
		}
		else if(this.op == 3) {
			return new IntValue(n1*n2);
		}
		else{
			if(n2 == 0) {
				throw new MyException("Cannot divide with 0");
			}
			return new IntValue(n1/n2);
		}
	}
	
	public String toString() {
		return (this.LExp.toString() + this.op_char + this.RExp.toString());
	}
	
	
}
