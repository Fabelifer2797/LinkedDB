package org.datosI.linkeddb.aplicacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorVentanaStores implements Initializable{
	
    @FXML
    private TextField TextFieldNombreStore;

    @FXML
    private Button BotonCrearStore;
    
    @FXML
    private Label LabelValidacionNombreStore;
    
    private ControladorVentanaPrincipal controladorPrincipal = new ControladorVentanaPrincipal();

    @FXML
    void AlmacenarStore(ActionEvent event) {
    	
    	String NombreStore = TextFieldNombreStore.getText();
    	
    	if(NombreStore.isEmpty())
    	{
    		LabelValidacionNombreStore.setText("Campo vacio. Inserte un nombre por favor.");
    	}
    	else
    	{
    		controladorPrincipal.AgregarStoresTreeView(NombreStore);
    		Stage stage = (Stage) BotonCrearStore.getScene().getWindow();
    		stage.close();
    	}
    	

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
	}

}
