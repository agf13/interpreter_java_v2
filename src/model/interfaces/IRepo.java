package model.interfaces;

import model.program.*;

import java.io.IOException;
import java.util.List;

public interface IRepo {

	public PrgState getCurPrg();
	public List<PrgState> getPrgStateList();
	public void setPrgStateList(List<PrgState> programStateList);
	public void logPrgStateExec(PrgState state) throws IOException;
	
	public void setPrgList(List<PrgState> programStateList); //same as above, but name to match teacher's pdf
	public List<PrgState> getPrgList();
	public void logPrgExec(PrgState state) throws IOException;//same as above, but name to match teacher's pdf
	public void addState(PrgState state);
	public PrgState getPrgStateById(int id);
	public List<Integer> getListOfIds();
}
