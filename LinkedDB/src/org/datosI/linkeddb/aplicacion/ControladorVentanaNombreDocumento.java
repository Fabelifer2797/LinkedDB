package org.datosI.linkeddb.aplicacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorVentanaNombreDocumento {
	
    @FXML
    private TextField TextFieldNombreDocumento;

    @FXML
    private Label LabelValidarNombreDocumento;

    @FXML
    private Button BotonAgregarNombreDocumento;
    
    private static String nombreDocumento;
    
    public  ControladorVentanaNombreDocumento() {
		
	}

    @FXML
    void AgregarNombreDocumento(ActionEvent event) {
    	
    	String NombreDocumento = TextFieldNombreDocumento.getText();
    	
    	if(NombreDocumento.isEmpty())
    	{
    		LabelValidarNombreDocumento.setText("Campo vacio. Inserte un nombre válido por favor.");
    	}
    	else
    	{
    		setNombreDocumento(NombreDocumento);
    		Stage stage1 = (Stage) BotonAgregarNombreDocumento.getScene().getWindow();
    		stage1.close();
    		
        	try{
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/datosI/linkeddb/aplicacion/VentanaAtributosDocumento.fxml"));
        		Parent parent = (Parent) fxmlLoader.load();
        		Stage stage = new Stage();
        		stage.setTitle("Ventana Atributos Documento");
        		stage.setResizable(false);
        		stage.setScene(new Scene(parent));
        		stage.show();
        		
        	}
        	
        	catch (Exception e)
        	{
        		System.out.println("No se pudo abrir la ventana de atributos documento");
        	}
    	}

    }

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		ControladorVentanaNombreDocumento.nombreDocumento = nombreDocumento;
	}


}
