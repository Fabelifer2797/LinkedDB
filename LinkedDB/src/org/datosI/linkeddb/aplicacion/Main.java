package org.datosI.linkeddb.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/org/datosI/linkeddb/aplicacion/VentanaPrincipal.fxml"));
		Scene scene = new Scene(root);
		

		primaryStage.setTitle("LinkedDB");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args){
		launch(args);
	}

}