package main;

import model.interfaces.*;
import model.types.*;
import model.values.*;
import model.exceptions.*;
import model.ADT.*;
import model.exp.*;
import model.Statements.*;
import model.program.*;
import repository.*;
import controller.*;
import model.Statements.file.*;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.*;


public class Main {
	
	//below will be prints
	public static void Print(int msg) {
		System.out.println(Integer.toString(msg));
	}
	public static void Print(String msg) {
		System.out.println(msg);
	}
	public static void Print(Boolean msg) {
		System.out.println(Boolean.toString(msg));
	}
	public static void Print(Set<String> msg) {
		Print(msg.toString());
	}
	public static void Print(Exp e) {
		Print(e.toString());
	}
	public static void Print(PrgState state) {
		System.out.println(state.toString());
	}
	
	//below will be tests:
	public static void test_while() throws MyException, VarNotFound, ADTEmpty, IOException {
		MyIDictionary<String, Value> symTable = new MyDictionary<String, Value>();
		MyIStack<IStmt> stk = new MyStack<IStmt>();
		MyIList<Value> out = new MyList<Value>();
		


		IStmt stmt = new CompStmt(
				new VarDeclStmt("a", new IntType()),
				new CompStmt(
						new AssignStmt("a", new ValueExp(new IntValue(12))),
						new WhileStmt(
								new LogicExp(
										new VarExp("a"),
										new ValueExp(new IntValue(10)),
										">"
										),
								new AssignStmt("a", new ArithExp( new VarExp("a"), new ValueExp(new IntValue(1)),"-"))
								)
						)
				);
		
		PrgState state= new PrgState(stk, symTable, out, stmt);
		List<PrgState> prg = new ArrayList<PrgState>();
		//prg.add(state);
		IRepo repo = new Repo("test_while.txt", prg);
		Ctrl ctr = new Ctrl(repo);
		ctr.allStep();
	}
	
	public static void test_prof_1() throws MyException, VarNotFound, ADTEmpty, IOException {
		MyIDictionary<String, Value> symTable = new MyDictionary<String, Value>();
		MyIList<Value> out = new MyList<Value>();
		MyIStack<IStmt> stk = new MyStack<IStmt>();
		
		IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
				 new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
				VarExp("v"))));
		
		PrgState state = new PrgState(stk, symTable, out, ex1);
		List<PrgState> prg = new ArrayList<PrgState>();
		prg.add(state);
		IRepo repo = new Repo(prg,"test.txt");
		Ctrl ctr = new Ctrl(repo);
		ctr.allStep();
		
	}
	
	//bellow ... main
	public static void main(String[] args) throws MyException, VarNotFound, ADTEmpty, IOException {
		//1st ex from the teacher
		// /*
		IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
				 new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
				VarExp("v"))));
		PrgState prg1 = new PrgState(ex1);
		List<PrgState> listPrgState1 = new ArrayList<PrgState>();
		listPrgState1.add(prg1);
		IRepo repo1 = new Repo(listPrgState1, "ex1.txt");
		Ctrl ctr1 = new Ctrl(repo1);
//		ctr1.allStep();
		// */
		
		
		
		//2nd ex from teacher
		// /*
		IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
				 new CompStmt(new VarDeclStmt("b",new IntType()),
				new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
				ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
				 new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
				ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
		PrgState prg2 = new PrgState(ex2);
		List<PrgState> listPrgState2 = new ArrayList<PrgState>();
		listPrgState2.add(prg2);
		IRepo repo2 = new Repo(listPrgState2, "ex2.txt");
		Ctrl ctr2 = new Ctrl(repo2);
		//ctr2.allStep();
		// */
		
		
		
		//3rd ex form teacher
		// /*
		IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
				 new CompStmt(new VarDeclStmt("v", new IntType()),
				new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
				 new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
				IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
				VarExp("v"))))));
		PrgState prg3 = new PrgState(ex3);
		List<PrgState> listPrgState3 = new ArrayList<PrgState>();
		listPrgState3.add(prg3);
		IRepo repo3 = new Repo(listPrgState3, "ex3.txt");
		Ctrl ctr3 = new Ctrl(repo3);
		//ctr3.allStep();
		// */
		
		
		
		//4th ex from teacher: test reading from file
		IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
				new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("file_a.txt"))),
						new CompStmt(new OpenRFile(new VarExp("varf")),
								new CompStmt(new VarDeclStmt("varc", new IntType()),
										new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
												new CompStmt(new PrintStmt(new VarExp("varc")),
														new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
																new CompStmt(new PrintStmt(new VarExp("varc")),
																		new CloseRFile(new VarExp("varf"))
																		)
																)
														)
												)
										)
								)
						)
				);
		PrgState prg4 = new PrgState(ex4);
		List<PrgState> list4 = new ArrayList<PrgState>();
		list4.add(prg4);
		IRepo repo4 = new Repo(list4,"ex4.txt");
		Ctrl ctr4 = new Ctrl(repo4);
		//ctr4.allStep();
		
		
		
		//5th ex, from me
//		IStmt ex5 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
//				new New("a", new ValueExp(new IntValue(20)))
//				);
//		PrgState prg5 = new PrgState(ex5);
//		List<PrgState> list5 = new ArrayList<PrgState>();
//		list5.add(prg5);
//		IRepo repo5 = new Repo(list5, "ex5.txt");
//		Ctrl ctr5 = new Ctrl(repo5);
//		ctr5.allStep();
		
		
		
		//fork ex
		IStmt fork1 = new CompStmt(new VarDeclStmt("a", new IntType()),
				new CompStmt(new ForkStmt(new CompStmt(new VarDeclStmt("b", new BoolType()),
						new PrintStmt(new VarExp("b"))
						)),
						new CompStmt(new VarDeclStmt("c",new StringType()),
								new PrintStmt(new VarExp("c"))))
				);
		PrgState prg6 = new PrgState(fork1);
		List<PrgState> list6 = new ArrayList<PrgState>();
		list6.add(prg6);
		IRepo repo6 = new Repo(list6, "fork1.txt");
		Ctrl ctr6 = new Ctrl(repo6);
		
		
		
		//test ex
		IStmt test1 = new CompStmt(new VarDeclStmt("a",new IntType()),
				new CompStmt(new VarDeclStmt("b", new IntType()),
						new CompStmt(new VarDeclStmt("c", new IntType()),
								new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
										new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
												new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
														new CompStmt(new SwitchStmt(new ArithExp(new VarExp("a"),new ValueExp(new IntValue(10)), "*"), new ArithExp(new VarExp("b"), new VarExp("c"), "*"), new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))), new ValueExp(new IntValue(10)), new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))), new PrintStmt(new ValueExp(new IntValue(300)))),
																new PrintStmt(new ValueExp(new IntValue(300))))
														)
												)
										)
								)
						)
				);
		PrgState prg7 = new PrgState(test1);
		List<PrgState> list7 = new ArrayList<PrgState>();
		list7.add(prg7);
		IRepo repo7 = new Repo(list7, "testOutput.txt");
		Ctrl ctr7  = new Ctrl(repo7);
		
		
		
		//ex refType1 (heap) 
		IStmt exRefType1 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
				new CompStmt(new New("v",new ValueExp(new IntValue(20))),
						new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
								new CompStmt(new New("a", new VarExp("v")),
										new CompStmt(new PrintStmt(new VarExp("v")),
												new PrintStmt(new VarExp("a")))))));
		
		PrgState prg8 = new PrgState(exRefType1);
		List<PrgState> list8 = new ArrayList<PrgState>();
		list8.add(prg8);
		IRepo repo8 = new Repo(list8, "exRefType.txt");
		Ctrl ctr8 = new Ctrl(repo8);
		
		
		
		//ex refType2 (heap)
		IStmt exRefType2 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
				new CompStmt(new New("v", new ValueExp(new IntValue(20))),
						new CompStmt(new PrintStmt(new RH(new VarExp("v"))),
								new CompStmt(new WH("v", new ValueExp(new IntValue(30))),
										new PrintStmt(new ArithExp(new RH(new VarExp("v")),new ValueExp(new IntValue(5)), "+"))
//										new NopStmt()
										))));
		
		PrgState prg9 = new PrgState(exRefType2);
		List<PrgState> list9 = new ArrayList<PrgState>();
		list9.add(prg9);
		IRepo repo9 = new Repo(list9, "exRefType2.txt");
		Ctrl ctr9 = new Ctrl(repo9);
		
		//menu commands
		TextMenu menu = new TextMenu();
		menu.addCommand(new ExitCommand("0","exit"));
		menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
		menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
		menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
		menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
		menu.addCommand(new RunExample("6",fork1.toString(),ctr6));
		menu.addCommand(new RunExample("7",test1.toString(),ctr7));
		menu.addCommand(new RunExample("8",exRefType1.toString(),ctr8));
		menu.addCommand(new RunExample("9",exRefType2.toString(),ctr9));
		menu.show();
	}
}


















