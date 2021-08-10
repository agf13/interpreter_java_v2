package main;

import model.program.*;
import model.exceptions.*;
import controller.*;

public class RunExample extends Command {
	 private Ctrl ctr;
	 
	 public RunExample(String key, String desc, Ctrl ctr){
		 super(key, desc);
		 this.ctr=ctr;
	 }
	 
	 @Override
	 public void execute() {
		 try{
			 ctr.allStep(); 
		 }
		 catch (Exception e) {//here you must treat the exceptions that can not be solved in the controller
			 e.printStackTrace();
		 } 
	 }
}
