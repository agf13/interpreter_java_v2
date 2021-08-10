package controller;

import model.interfaces.*;
import model.exceptions.*;
import model.exp.*;
import model.ADT.*;
import model.program.*;
import repository.*;
import model.values.*;
import model.types.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.io.IOException;

public class Ctrl {
	IRepo repo;
	
	ExecutorService executor;
	
	public Ctrl(IRepo repo) {
		this.repo = repo;
	}
	
//	public void allStep() throws MyException, IOException{
//		PrgState prg = this.repo.getCurPrg();
//		repo.logPrgStateExec(prg);
//		
//		if(prg == null) {
//			throw new MyException("No PrgState found");
//		}
//		
//		while(prg.isNotComplete()) {
//			try {
//				prg.oneStep();
//			} 	
////			catch(Exception e) {
////				System.out.println(e.getMessage());
////			}
//			catch(VarNotFound | ADTEmpty e) {
//				System.out.println(e.getMessage());
//			}
//			
//			String out = prg.toString();
//			repo.logPrgStateExec(prg);		
//		}
//	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public void allStep() throws MyException
//    {
//        executor = Executors.newFixedThreadPool(2);
//        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
//
//        while(prgList.size() > 0){
//            Collection<Value> allSymTbl = new ArrayList<>();
//            prgList.forEach(v-> allSymTbl.addAll(v.getSymTable().getContent().values()));
//            List<Integer> addr = getAddrFromSymTable(allSymTbl, prgList.get(0).getHeap().getContent().values());
//            Map<Integer, Value> newHeap = safeGarbageCollector(addr, prgList.get(0).getHeap().getContent());
//            prgList.get(0).getHeap().setContent(newHeap);
//            oneStepForAllPrg(prgList);
//            prgList=removeCompletedPrg(repo.getPrgList());
//        }
//        executor.shutdownNow();
//        repo.setPrgList(prgList);
//    }
    
    public void allStep() throws MyException, IOException {
    	 executor = Executors.newFixedThreadPool(2);
    	 //remove the completed programs
    	 List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
    	 try {
    		 repo.logPrgStateExec(prgList.get(0));
    	 }
    	 catch(IOException e) {
    		 e.printStackTrace();
    	 }
    	 while(prgList.size() > 0){
	    	 oneStepForAllPrg(prgList);
	    	 //remove the completed programs
	    	 prgList=removeCompletedPrg(repo.getPrgList());
    	 }
    	 executor.shutdownNow();
    	 //HERE the repository still contains at least one Completed Prg
    	 // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
    	 //setPrgList of repository in order to change the repository

    	 // update the repository state
    	 repo.setPrgList(prgList);
    	 }


	
	public void oneStepForAllPrg(List<PrgState> prgList) throws MyException{
//        prgList.forEach(p-> {
//            try {
//                repo.logPrgStateExec(p);
//                //System.out.println(p.toString());
//            }
//            catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        });
        List<Callable<PrgState>> callList = prgList.stream().
                map((PrgState p) -> (Callable<PrgState>)(()->{return p.oneStep();})).collect(Collectors.toList());
        try {
            List<PrgState> newProgramList = executor.invokeAll(callList).stream()
                    .map(programStateFuture -> {try{
                          return programStateFuture.get();
                        } catch (ExecutionException e) {
                            throw new RuntimeException("Thread exception: " + e.getMessage());
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Thread exception: " + e.getMessage());
                        }
                    })
                    .filter(programState -> programState != null)
                    .collect(Collectors.toList());
            prgList.addAll(newProgramList);
            prgList.forEach(p-> {
                try {
                    repo.logPrgStateExec(p);
                    //System.out.println(p.toString());
                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            repo.setPrgList(prgList);
        }
        catch (InterruptedException e)
        {
            throw new MyException("Thread exception");
        }
        catch (RuntimeException e)
        {
            throw new MyException("Thread exception");
        }
    }
	

    public boolean oneStepGUI() throws MyException
    {
        List<PrgState> prg = removeCompletedPrg(repo.getPrgList());
        if(prg.size()>0)
        {
            oneStepForAllPrg(prg);
            removeCompletedPrg(repo.getPrgList());
            return true;
        }
        else {
            executor.shutdownNow();
            repo.setPrgList(prg);
            return false;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PrgState getPrgStateById(int id)
    {
        return repo.getPrgStateById(id);
    }

    public List<Integer> getIdList()
    {
        return repo.getListOfIds();
    }
	
	
	public IRepo getRepo() {
		return this.repo;
	}
	
	
	List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
		return inPrgList.stream()
				 .filter(p -> p.isNotComplete())
				 .collect(Collectors.toList());
	}
	
}
