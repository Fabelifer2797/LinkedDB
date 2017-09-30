package org.datosI.linkeddb.aplicacion;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorVentanaMensajeCreacionObjeto implements Initializable {
	
    @FXML
    private Button BotonAceptar;

    @FXML
    void Aceptar(ActionEvent event) {
		Stage stage1 = (Stage) BotonAceptar.getScene().getWindow();
		stage1.close();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
