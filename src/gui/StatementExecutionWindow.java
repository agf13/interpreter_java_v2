package gui;


import model.interfaces.*;
import model.exceptions.*;
import model.ADT.*;
import model.Statements.*;
import model.exp.*;
import model.values.*;
import model.types.*;
import gui.model.*;

import controller.*;
import model.program.*;
import repository.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.util.ArrayList;

public class StatementExecutionWindow {
    IStmt stmt;
    @FXML
    ListView<String> prgView;
    @FXML
    ListView<String> exeStackView;
    @FXML
    ListView<String> outView;
    @FXML
    ListView<String> fileTblView;
    @FXML
    TableView<HeapObject> heapView;
    @FXML
    TableColumn<HeapObject, Integer> heapAddr;
    @FXML
    TableColumn<HeapObject, Value> heapValue;
    @FXML
    TableView<SymbolTableObject> symTblView;
    @FXML
    TableColumn<SymbolTableObject, String> symName;
    @FXML
    TableColumn<SymbolTableObject, Value> symValue;
    @FXML
    Label stmtName;
    @FXML
    Label numPrgStates;
    @FXML
    Button runButton;


    MyIDictionary<String, Value> symTbl;
    IHeap<Integer, Value> heap;
    MyIStack<IStmt> exeStack;
    MyIList<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;
    ArrayList<Integer> prgListIds;
    private Ctrl ctrl;
    int size;

    public StatementExecutionWindow(IStmt stmt)
    {
        this.stmt = stmt;
    }

    @FXML
    public void initialize() {
        stmtName.setText(stmt.toString());
        PrgState state = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), new MyDictionary<>(), new Heap(), stmt);
        IRepo repo = new Repo("..\\repo.txt");
        repo.addState(state);
        ctrl = new Ctrl(repo);
        initPrgState();
        prgView.getSelectionModel().selectFirst();
        runButton.setOnMouseClicked(event -> {
            try {
                if(ctrl.oneStepGUI()) {
                    initPrgState();
                    setAll();
                }
            }
            catch (MyException exp)
            {
                exp.printStackTrace();
            }
        });
        prgView.setOnMouseClicked(event -> setAll());
        heapAddr.setCellValueFactory(new PropertyValueFactory<>("Address"));
        heapValue.setCellValueFactory(new PropertyValueFactory<>("Value"));

        symName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        symValue.setCellValueFactory(new PropertyValueFactory<>("Value"));


        setAll();
    }

    public void initPrgState()
    {
        ObservableList<String> prgListId = FXCollections.observableArrayList();
        prgListIds = (ArrayList<Integer>) ctrl.getIdList();

        for(int id : prgListIds)
            prgListId.add(String.valueOf(id));

        prgView.setItems(prgListId);
        numPrgStates.setText("No of Prg States: "+ String.valueOf(prgListIds.size()));
        if(size != prgListId.size())
        {
            prgView.getSelectionModel().selectFirst();
            size = prgListId.size();
        }
    }

    public void setAll()
    {
        PrgState state = ctrl.getPrgStateById(Integer.parseInt(prgView.getSelectionModel().getSelectedItem()));
        symTbl = state.getSymTable();
        exeStack = state.getExeStack();
        heap = state.getHeapTable();
        out = state.getOut();
        fileTable = state.getFileTable();

        ObservableList<String> exeStackObs = FXCollections.observableArrayList();
        int index = exeStack.getAll().size()-1;
        while (index >= 0)
        {
            exeStackObs.add(exeStack.getAll().get(index).toString());
            index--;
        }
        exeStackView.setItems(exeStackObs);

        ObservableList<String> outObs = FXCollections.observableArrayList();
        for(Value v : out.getAll())
            outObs.add(v.toString());
        outView.setItems(outObs);

        ObservableList<String> fileObs = FXCollections.observableArrayList();
        for(String sv : fileTable.getAll().keySet())
            fileObs.add(sv.toString());
        fileTblView.setItems(fileObs);

        ObservableList<HeapObject> heapObs = FXCollections.observableArrayList();
        for(int key : heap.keySet())
            heapObs.add(new HeapObject(key, heap.getValue(key)));
        heapView.setItems(heapObs);

        ObservableList<SymbolTableObject> symTblObs = FXCollections.observableArrayList();
        for(String name : symTbl.keySet())
            symTblObs.add(new SymbolTableObject(name, symTbl.lookup(name)));
        symTblView.setItems(symTblObs);
    }

}
