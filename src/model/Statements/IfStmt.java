package model.Statements;

import model.interfaces.*;
import model.exceptions.*;
import model.program.*;
import model.types.*;
import model.values.*;


public class IfStmt implements IStmt{
		Exp cond;
		IStmt thenTrue;
		IStmt thenFalse;
		
		public IfStmt(Exp cond, IStmt s1, IStmt s2) {
			this.cond = cond;
			this.thenTrue = s1;
			this.thenFalse = s2;
		}
		
		public PrgState execute(PrgState state) throws MyException, VarNotFound, ADTEmpty{
			MyIStack<IStmt> stack = state.getExeStack();
			IHeap<Integer,Value> heap = state.getHeapTable();
			Value val = null;
			boolean result = false;
			try {
				val = cond.eval(state.getSymTable(),heap);
				if((val.getType()).equals(new IntType())){
					if(((IntValue)val).getVal() != 0) {
						result = true;
					}
				}
				if((val.getType()).equals(new BoolType())) {
					result = ((BoolValue)val).getVal();
					//System.out.println("->" + result);
				}
			}
			catch(MyException e) {
				throw new MyException(e.getMessage());
			}
			catch(VarNotFound e) {
				throw new VarNotFound(e.getMessage());
			}
			catch(ADTEmpty e) {
				throw new ADTEmpty(e.getMessage());
			}
			
			if(result == true) {
				stack.push(this.thenTrue);
			}
			else {
				stack.push(this.thenFalse);
			}
			
			
			return null;
		}
		
		public String toString() {
			return("If(" +cond.toString() + ") " + "then(" + thenTrue.toString() + ") else(" + thenFalse.toString()+")");
		}
}
