package gui;


import model.interfaces.*;
import model.ADT.*;
import model.values.*;
import model.types.*;
import model.Statements.*;
import model.exceptions.*;
import model.exp.ArithExp;
import model.exp.ValueExp;
import model.exp.VarExp;
import model.types.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectionWindowController {

    @FXML
    ListView<String> stmtString;
    List<IStmt> stmtList = new ArrayList<>();

    @FXML
    public void initialize() throws MyException{
        //int v; v=2; print (v)
        
    	IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
				 new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
				VarExp("v"))));
    	
    	IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
				 new CompStmt(new VarDeclStmt("b",new IntType()),
				new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
				ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
				 new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
				ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
    	
        stmtList.add(ex1);
        stmtList.add(ex2);
//        stmtList.add(ex3);
//        stmtList.add(ex4);
//        stmtList.add(ex5);
//        stmtList.add(ex6);
//        stmtList.add(ex7);
//        stmtList.add(ex8);
//        stmtList.add(ex9);
//        stmtList.add(ex10);
//        stmtList.add(ex11);
//        stmtList.add(ex12);
//        stmtList.add(ex13);
//        stmtList.add(ex14);

        ObservableList<String> observableList = FXCollections.observableArrayList();
        
        for(IStmt st : stmtList)
        {
            observableList.add(st.toString());
        }
        stmtString.setItems(observableList);
        stmtString.getSelectionModel().selectFirst();

    }

    @FXML
    public void executeButton(Event evt) throws IOException {
        IStmt stmt = stmtList.get(stmtString.getSelectionModel().getSelectedIndex());

        try {
            //stmt.typeCheck(new MyDictionary<>());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StatementExecutionWindow.fxml"));
            loader.setController(new StatementExecutionWindow(stmt));
            Stage stage = new Stage();
            Parent root = loader.load();
            stage.setTitle("Running program");
            stage.setScene(new Scene(root, 800, 630));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        //catch (MyException e) //to do: maybe going back to this later
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Typecheck failed");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(e.toString())));
            alert.showAndWait();

        }
        //System.out.println(stmt.toString());
    }


}
