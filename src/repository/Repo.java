package repository;

import model.interfaces.*;
import model.exceptions.*;
import model.program.*;

import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Repo implements IRepo{
	List<PrgState> prgStateList;
	String logFilePath = "";
	
	public Repo() {
		this.prgStateList = new ArrayList<PrgState>();
	}
	
	public Repo(String path) {
		this.logFilePath = path;
		this.prgStateList = new ArrayList<PrgState>();
	}
	
	public Repo(List<PrgState> prgStateList) {
		this.prgStateList = prgStateList;
	}
	
	public Repo(List<PrgState> prgStateList, String path) {
		File file = new File(path);
		if(file.delete()) {
			System.out.println("A file with the name " + path + " already existed so it was deleted so that the result of the program will only appear once.");
		}
		
		this.prgStateList = prgStateList;
		this.logFilePath = path;
	}
	
	public Repo(String path, List<PrgState> prgStateList) {
		File file = new File(path);
		if(file.delete()) {
			System.out.println("A file with the name " + path + " already existed so it was deleted so that the result of the program will only appear once.");
		}
		this.prgStateList = prgStateList;
		this.logFilePath = path;
	}
	
	public PrgState getCurPrg() {
		if(prgStateList.isEmpty()) {
			return null;
		}
		return prgStateList.get(prgStateList.size() - 1);
	}

	public List<PrgState> getPrgStateList(){
		return prgStateList;
	}
	//this is same as getPrgStateList(), but with the name given in teachers pdf
	public List<PrgState> getPrgList(){
		return prgStateList;
	}
	
	public void setPrgStateList(List<PrgState> list) {
		this.prgStateList = list;
	}
	//this is same as setPrgStateList(), but with the name given in teachers pdf	
	public void setPrgList(List<PrgState> list) {
		this.prgStateList = list;
	}
	
	public void addState(PrgState state) {
		this.prgStateList.add(state);
	}
	
	public void logPrgStateExec(PrgState state) throws IOException {
		if(!this.logFilePath.isEmpty()) {
			PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
			logFile.write(state.toString());
			logFile.close();
		}
		else {
			System.out.println(state.toString());
		}
	}	
	//same as above, but name to match teacher's pdf
	public void logPrgExec(PrgState state) throws IOException {
		if(!this.logFilePath.isEmpty()) {
			PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
			logFile.write(state.toString());
			logFile.close();
		}
		else {
			System.out.println(state.toString());
		}
	}
	
	///////////////////
    public PrgState getPrgStateById(int id) {
        for(PrgState s: prgStateList) {
            if (s.getPrgId() == id)
                return s;
        }
        return  null;
    }
    
    public List<Integer> getListOfIds() {
        List<Integer> ids = new ArrayList<>();
        for(PrgState s: prgStateList)
            ids.add(s.getPrgId());
        return ids;
    }
}
