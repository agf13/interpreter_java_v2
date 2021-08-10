package model.exp;

import model.exceptions.*;
import model.interfaces.*;
import model.exp.*;
import model.values.*;
import model.Statements.*;
import model.values.*;
import model.types.*;

public class LogicExp implements Exp{
	Exp L,R;
	int op; //1 '<' | 2 '>' | 3 '!=' | 4 '==' | 5 '<=' | 6 '>='
	String op_char;
	
	public Type typecheck(MyIDictionary<String,Value> symTable, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty{
		Value auxL = L.eval(symTable,heap);
		Value auxR = R.eval(symTable,heap);

		Type t1 = auxL.getType();
		Type t2 = auxR.getType();

		if(t1.equals(new BoolType())){
			if(t2.equals(new BoolType())){
				return new BoolType();
			}
			else{
				throw new MyException("not bool tpye");
			}
		}
		else{
			throw new MyException("not bool type");
		}
	}
	
	public LogicExp(Exp L, Exp R, int op) throws MyException{
		this.L = L;
		this.R = R;

		if(op == 1) {
			this.op_char = "<";
			this.op = 1;
		}
		else if(op == 2) {
			this.op_char = ">";
			this.op = 2;
		}
		else if(op == 3) {
			this.op_char = "!=";
			this.op = 3;
		}
		else if(op == 4) {
			this.op_char = "==";
			this.op = 4;
		}
		else if(op == 5) {
			this.op_char = "<=";
			this.op = 5;
		}
		else if(op == 6) {
			this.op_char = ">=";
			this.op = 6;
		}
		else {
			throw new MyException("Unexisting operator");
		}
	}
	
	public LogicExp(Exp L, Exp R, String op) throws MyException{
		this.L = L;
		this.R = R;

		if(op.equalsIgnoreCase("<")) {
			this.op_char = op;
			this.op = 1;
		}
		else if(op.equalsIgnoreCase(">")) {
			this.op_char = op;
			this.op = 2;
		}
		else if(op.equalsIgnoreCase("!=")) {
			this.op_char = op;
			this.op = 3;
		}
		else if(op.equalsIgnoreCase("==")) {
			this.op_char = op;
			this.op = 4;
		}
		else if(op.equalsIgnoreCase("<=")) {
			this.op_char = op;
			this.op = 5;
		}
		else if(op.equalsIgnoreCase(">=")) {
			this.op_char = op;
			this.op = 6;
		}
		else {
			throw new MyException("Unexisting operator");
		}
	}
	
	
	
	public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer, Value> heap) throws ADTEmpty, MyException, VarNotFound {
		 Value v1,v2;
		 boolean result = false;
		 v1 = L.eval(tbl,heap);
		 v2 = R.eval(tbl,heap);
		 if (v1.getType().equals(new IntType())) {
			 if (v2.getType().equals(new IntType())) {
				 IntValue i1 = (IntValue)v1;
				 IntValue i2 = (IntValue)v2;
				 int n1,n2;
				 n1 = i1.getVal();
				 n2 = i2.getVal();
				 switch(op) {
					 case 1:
						result = (n1 < n2);
						break;
					 case 2:
						 result = (n1 > n2);
						 break;
					 case 3:
						 result = (n1 != n2);
						 break;
					 case 4:
						 result = (n1 == n2);
						 break;
					 case 5:
						 result = (n1 <= n2);
						 break;
					 case 6:
						 result = (n1 >= n2);
						 break;
					default:
						throw new MyException(Integer.toString(op) + " is not a valid operator id. (Only from 1 to 6");
					 
				 }
			 }else
				 throw new MyException("second operand is not an integer");
		 }else {
			 if(v1.getType().equals(new BoolType())) {
				 if(v2.getType().equals(new BoolType())) {
					 if(op_char.equalsIgnoreCase("==")) {
						 return new BoolValue(v1.equals(v2));
					 }
					 if(op_char.contentEquals("!=")) {
						 boolean tmpResult = v1.equals(v2);
						 return new BoolValue(!tmpResult);
					 }
				 }
			 }
			 throw new MyException("first operand is not an integer");
		 }

		 if(result == false)
			 return new BoolValue(false);
		 else
			 return new BoolValue(true);
	 }
	
	public String toString() {
		return (L.toString() + op_char + R.toString());
	}
	
	
	
	
	
	
	
	
}
	
