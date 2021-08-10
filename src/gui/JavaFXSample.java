package gui;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;

public class JavaFXSample extends Application {
	final ListView<String> list = new ListView<String>();
	ObservableList<String> items = FXCollections.observableArrayList();
	
	
	public void add_prg(String desc) {
		items.add(desc);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		add_prg("1");
		add_prg("1");
		add_prg("1");
		list.setItems(items);
		
		StackPane root = new StackPane();
		root.getChildren().add(list);
		
		primaryStage.setTitle("Template programs");
		primaryStage.setScene(new Scene(root, 200, 225));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}



}
