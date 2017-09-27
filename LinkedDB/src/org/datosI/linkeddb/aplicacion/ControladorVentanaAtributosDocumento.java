package org.datosI.linkeddb.aplicacion;

import org.datosI.linkeddb.listasEnlazadas.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class ControladorVentanaAtributosDocumento implements Initializable{

	@FXML
    private ComboBox<String> ComboBoxTipoAtributo;

    @FXML
    private RadioButton RadioBNoRequerido;

    @FXML
    private RadioButton RadioBSiForanea;

    @FXML
    private Label LabelMensajesError;

    @FXML
    private Button BotonCrearDocumento;

    @FXML
    private TextField TextFieldNombreAtributo;

    @FXML
    private Button BotonAgregarOtroAtributo;

    @FXML
    private RadioButton RadioBNoForanea;

    @FXML
    private RadioButton RadioBNoPrimaria;

    @FXML
    private RadioButton RadioBSiPrimaria;

    @FXML
    private TextField TextFieldDocumentoForaneo;

    @FXML
    private RadioButton RadioBSiRequerido;

    @FXML
    private TextField TextFieldAtributoForaneo;

    @FXML
    private TextField TextFieldValorPorDefecto;
    
    private ControladorVentanaNombreDocumento controladorNombreDocumento = new ControladorVentanaNombreDocumento();
    private ControladorVentanaPrincipal controladorPrincipal = new ControladorVentanaPrincipal();
    private static ToggleGroup group1 = new ToggleGroup();
    private static ToggleGroup group2 = new ToggleGroup();
    private static ToggleGroup group3 = new ToggleGroup();
    private static RadioButton RequeridoSelection;
    private static RadioButton PrimariaSelection;
    private static RadioButton ForaneaSelection;
    private static String requerido;
    private static String primaria;
    private static String foranea; 
    private ArrayList<AtributosDocumentosJSON> arregloAtributos = new ArrayList<AtributosDocumentosJSON>();
    
    
    @FXML
    void AgregarOtroAtributo(ActionEvent event) {
    	
    	if(Validaciones() == true)
    	{
    	
        	String nombreAtributo = TextFieldNombreAtributo.getText();
        	String valorDefecto = TextFieldValorPorDefecto.getText();
        	String documentoForaneo = TextFieldDocumentoForaneo.getText();
        	String atributoForaneo = TextFieldAtributoForaneo.getText();
        	String tipoAtributo = ComboBoxTipoAtributo.getSelectionModel().getSelectedItem();
        	
        	if(valorDefecto.isEmpty())
        	{
        		valorDefecto = "";
        	}
        	else if(documentoForaneo.isEmpty() && atributoForaneo.isEmpty())
        	{
        		documentoForaneo = "";
        		atributoForaneo = "";
        	}
        	

        	AtributosDocumentosJSON atributosDocumento = new AtributosDocumentosJSON(nombreAtributo, tipoAtributo, requerido, valorDefecto, primaria, foranea, documentoForaneo, atributoForaneo);
        	arregloAtributos.add(atributosDocumento);
        	ResetearVentanaAtributos();
    	}
    	else
    	{
    		System.out.println("Error en las validaciones");
    	}
    	
    }		

    @FXML
    void CrearDocumento(ActionEvent event) {
    	
    	if(Validaciones() == true)
    	{

        	String nombreAtributo = TextFieldNombreAtributo.getText();
        	String valorDefecto = TextFieldValorPorDefecto.getText();
        	String documentoForaneo = TextFieldDocumentoForaneo.getText();
        	String atributoForaneo = TextFieldAtributoForaneo.getText();
        	String tipoAtributo = ComboBoxTipoAtributo.getSelectionModel().getSelectedItem();
        	
        	
        	if(valorDefecto.isEmpty())
        	{
        		valorDefecto = "";
        	}
        	else if(documentoForaneo.isEmpty() && atributoForaneo.isEmpty())
        	{
        		documentoForaneo = "";
        		atributoForaneo = "";
        	}
        	
        	
        	AtributosDocumentosJSON atributosDocumento = new AtributosDocumentosJSON(nombreAtributo, tipoAtributo, requerido, valorDefecto, primaria, foranea, documentoForaneo, atributoForaneo);
        	arregloAtributos.add(atributosDocumento);
        	controladorPrincipal.AgregarDocumentosTreeView(controladorNombreDocumento.getNombreDocumento(), arregloAtributos);
        	Stage stage = (Stage) BotonCrearDocumento.getScene().getWindow();
    		stage.close();
    		
    		
    	}
    	else
    	{
    		System.out.println("Error en las validaciones");
    	}
    }
    
    public void ResetearVentanaAtributos()
    {
    	TextFieldNombreAtributo.clear();;
    	TextFieldValorPorDefecto.clear();;
    	TextFieldDocumentoForaneo.clear();;
    	TextFieldAtributoForaneo.clear();
    	RadioBSiRequerido.setSelected(true);
    	RadioBNoPrimaria.setSelected(true);
    	RadioBNoForanea.setSelected(true);
    	ComboBoxTipoAtributo.getSelectionModel().select(0);
    	LabelMensajesError.setText("");
    }
    
    public boolean Validaciones()
    {
    	
    	String nombreAtributo = TextFieldNombreAtributo.getText();
    	String valorDefecto = TextFieldValorPorDefecto.getText();
    	String documentoForaneo = TextFieldDocumentoForaneo.getText();
    	String atributoForaneo = TextFieldAtributoForaneo.getText();
    	
    	try
    	{

    		System.out.println( RequeridoSelection.getText());
    		requerido = RequeridoSelection.getText() ;
    	}
    	catch(Exception e)
    	{

    		System.out.println("Elemento requerido no seleccionado");
    		requerido = "Si";
    	}
    	
    	try
    	{

    		System.out.println( PrimariaSelection.getText());
    		primaria = PrimariaSelection.getText();
    	}
    	catch(Exception e)
    	{

    		System.out.println("Elemento llave primaria no seleccionado");
    		primaria = "No";
    	}
    	
    	try
    	{

    		System.out.println( ForaneaSelection.getText());
    		foranea = ForaneaSelection.getText();
    	}
    	catch(Exception e)
    	{

    		System.out.println("Elemento llave foranea no seleccionado");
    		foranea = "No";
    	}
    	
    	if(nombreAtributo.isEmpty())
    	{
    		LabelMensajesError.setText("El campo relacionado al nombre del atributo esta vacio. Porfavor inserte un nombre válido");
    		return false;
    	}
    	
    	else if( requerido == "No" && valorDefecto.isEmpty())
    	{
    		LabelMensajesError.setText("Si el atributo es no requerido se debe insertar un valor por defecto por favor");
    		return false;
    	}
    	
    	
    	else if((primaria == "Si") && (foranea == "Si"))
    	{
    		LabelMensajesError.setText("Un atributo no puede ser llave foranea y primaria a la vez");
    		return false;
    	}
    	
    	else if((foranea == "Si") && ((documentoForaneo.isEmpty())||(atributoForaneo.isEmpty())))
    	{
    		LabelMensajesError.setText("Si el atributo es llave foránea se debe indicar el documento y el atributo al cual hace "
    				+ "referencia por favor");
    		return false;
    	}
    	
    	else if((foranea == "Si")  && (documentoForaneo.isEmpty() == false) && (atributoForaneo.isEmpty() == false))
    	{
    		if(controladorPrincipal.getListaDocumentos().BuscarDocumento(documentoForaneo) == null )
    		{
    			LabelMensajesError.setText("El documento de referencia ingresado no existe. Por favor inserte un documento válido");
    			return false;
    		}
    		else
    		{
    			ArrayList<AtributosDocumentosJSON> arregloAtributos = controladorPrincipal.getListaDocumentos().BuscarDocumento(documentoForaneo).getArregloAtributos();
    			int indice = 0;
    			
    			while(indice < arregloAtributos.size())
    			{
    				AtributosDocumentosJSON atributos = arregloAtributos.get(indice);
    				if( atributoForaneo.equals(atributos.getNombre()) && atributos.getLlavePrimaria() == "Si")
    				{
    					
    					return true;
    				}
    				else
    				{
    					indice++;
    				}
    			}
    			LabelMensajesError.setText("El atributo de referencia ingresado no existe en el documento o no es llave primaria. Por favor ingrese un atributo válido");
    			return false;
    		}
    	}
    		
    	else
    	{
    		return true;
    	}
    }
    
    public boolean Prueba()
    {
    	TextFieldNombreAtributo.setText(null);
    	return TextFieldNombreAtributo.getText().isEmpty();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		arregloAtributos.clear();
		
		ObservableList<String> estadosComboBox = FXCollections.observableArrayList("String","Entero","Flotante","Fecha-Hora");
		ComboBoxTipoAtributo.setItems(estadosComboBox);
		ComboBoxTipoAtributo.getSelectionModel().select(0);
		
		RadioBSiRequerido.setText("Si");
		RadioBNoRequerido.setText("No");
		RadioBSiRequerido.setToggleGroup(group1);
		RadioBSiRequerido.setSelected(true);
		RadioBNoRequerido.setToggleGroup(group1);
		
		RadioBSiPrimaria.setText("Si");
		RadioBNoPrimaria.setText("No");
		RadioBSiPrimaria.setToggleGroup(group2);
		RadioBNoPrimaria.setToggleGroup(group2);
		RadioBNoPrimaria.setSelected(true);
		
		RadioBSiForanea.setText("Si");
		RadioBNoForanea.setText("No");
		RadioBSiForanea.setToggleGroup(group3);
		RadioBNoForanea.setToggleGroup(group3);
		RadioBNoForanea.setSelected(true);
		
        group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                RequeridoSelection = (RadioButton)t1.getToggleGroup().getSelectedToggle(); 
            
                

            }
        });
        
        
        group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                PrimariaSelection = (RadioButton)t1.getToggleGroup().getSelectedToggle(); 
            
                

            }
        });
        
        group3.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                ForaneaSelection = (RadioButton)t1.getToggleGroup().getSelectedToggle(); 
            
                

            }
        });
        
		
	}

}
