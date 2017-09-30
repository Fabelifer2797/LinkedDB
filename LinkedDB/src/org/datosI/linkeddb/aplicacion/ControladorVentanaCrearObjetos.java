package org.datosI.linkeddb.aplicacion;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.datosI.linkeddb.listasEnlazadas.AtributosDocumentosJSON;
import org.datosI.linkeddb.listasEnlazadas.AtributosObjetosJSON;
import org.datosI.linkeddb.listasEnlazadas.NodoDocumentoJSON;
import org.datosI.linkeddb.listasEnlazadas.NodoObjetoJSON;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorVentanaCrearObjetos implements Initializable{
	
    @FXML
    private TextField TextFieldInsercion;

    @FXML
    private Label LabelNombreAtributo;

    @FXML
    private Button BotonContinuar;

    @FXML
    private Button BotonCrearObjeto;

    @FXML
    private Label LabelTipoAtributo;
    
    @FXML
    private Label LabelValidaciones;
    
    private ControladorVentanaPrincipal controladorPrincipal = new ControladorVentanaPrincipal();
    private static ArrayList<AtributosDocumentosJSON> arregloAtributosDocumento = new ArrayList<>();
    private static ArrayList<NodoObjetoJSON> arregloNodoObjetos;
    private static ArrayList<AtributosObjetosJSON> arregloAtributosObjetos;
    private static int contadorArreglo;
    private static boolean alerta = false;

    @FXML
    void ContinuarAgregandoAtributos(ActionEvent event) {
    	
    	alerta = true;
    	boolean validarDatos = ValidarDatos();
    	boolean validarForaneo = ValidarForaneo();
    	
    	if(validarDatos == true && validarForaneo == true)
    	{
    		System.out.println("Validaciones correctas");
    		String atributo = TextFieldInsercion.getText();
        	AtributosDocumentosJSON atributosDocumento = arregloAtributosDocumento.get(contadorArreglo);
        	String nombreAtributo = atributosDocumento.getNombre();
        	AtributosObjetosJSON atributoNuevo = new AtributosObjetosJSON(nombreAtributo, atributo);
        	arregloAtributosObjetos.add(atributoNuevo);
    		ResetearValores();
    		contadorArreglo++;
    		InsertarLabels();
    	}
    	
    	else
    	{
    		System.out.println("Error en las validaciones");
    	}
    	
    	

    }

    @FXML
    void CrearObjetoFinal(ActionEvent event) {
    	
    	alerta = true;
    	boolean validarDatos = ValidarDatos();
    	boolean validarForaneo = ValidarForaneo();
    	
    	if(validarDatos == true && validarForaneo == true)
    	{
    		System.out.println("Validaciones correctas");
    		String atributo = TextFieldInsercion.getText();
        	AtributosDocumentosJSON atributosDocumento = arregloAtributosDocumento.get(contadorArreglo);
        	String nombreAtributo = atributosDocumento.getNombre();
        	AtributosObjetosJSON atributoNuevo = new AtributosObjetosJSON(nombreAtributo, atributo);
        	arregloAtributosObjetos.add(atributoNuevo);
    		ResetearValores();
    		contadorArreglo++;
    		InsertarLabels();
    	}
    	
    	else
    	{
    		System.out.println("Error en las validaciones");
    	}

    }
    
    public void ResetearValores()
    {
    	LabelNombreAtributo.setText("");
    	LabelTipoAtributo.setText("");
    	LabelValidaciones.setText("");
    	TextFieldInsercion.clear();
    }
    
    public void AsignarArregloAtributos(NodoDocumentoJSON nodoDocumento)
    {
    	arregloAtributosDocumento = nodoDocumento.getArregloAtributos();
    }
    
    public void InsertarLabels()
    {
       	if(contadorArreglo == arregloAtributosDocumento.size() && alerta == true)
    	{
    		NodoObjetoJSON nodoObjeto = new NodoObjetoJSON(arregloAtributosObjetos);
    		controladorPrincipal.AgregarObjetosMemoria(nodoObjeto, arregloNodoObjetos);
    		Stage stage1 = (Stage) BotonContinuar.getScene().getWindow();
    		stage1.close();
    		MostrarVentanaAfirmacion();
    		
    	}
       	
       	else if (contadorArreglo == arregloAtributosDocumento.size() && alerta == false)
       	{
    		NodoObjetoJSON nodoObjeto = new NodoObjetoJSON(arregloAtributosObjetos);
    		controladorPrincipal.AgregarObjetosMemoria(nodoObjeto, arregloNodoObjetos);
    		System.out.println("Se creo el objeto sin mensaje de afirmación");
       	}
       	
       	else
       	{
        	AtributosDocumentosJSON atributosDocumento = arregloAtributosDocumento.get(contadorArreglo);
        	String tipoAtributo = atributosDocumento.getTipo();
        	String nombreAtributo = atributosDocumento.getNombre();
        	String requerido = atributosDocumento.getRequerido();
        	String defecto = atributosDocumento.getValorPorDefecto();
        	
     
        	if( (requerido == "Si") && (contadorArreglo == arregloAtributosDocumento.size() - 1))
        	{
        		LabelTipoAtributo.setText(tipoAtributo);
            	LabelNombreAtributo.setText(nombreAtributo);
            	BotonContinuar.setVisible(false);
            	BotonCrearObjeto.setVisible(true);
        	}
        	
        	else if((requerido == "Si") && (contadorArreglo != arregloAtributosDocumento.size() - 1))
        	{
            	LabelTipoAtributo.setText(tipoAtributo);
            	LabelNombreAtributo.setText(nombreAtributo);
        	}
        	
        	else
        	{
        		AtributosObjetosJSON atributosObjeto = new AtributosObjetosJSON(nombreAtributo, defecto);
        		arregloAtributosObjetos.add(atributosObjeto);
        		contadorArreglo++;
        		InsertarLabels();
        	}
       	}

    }
    
    public boolean ValidarDatos()
    {
    	String atributo = TextFieldInsercion.getText();
    	String tipoAtributo = arregloAtributosDocumento.get(contadorArreglo).getTipo();
    	
    	if(atributo.isEmpty())
    	{
    		LabelValidaciones.setText("El campo del atributo se encuentra vacío. Por favor ingrese el valor del atributo");
    		return false;
    	}
    	
    	else if(tipoAtributo == "Entero")
    	{
    		try
    		{
    			@SuppressWarnings("unused")
				int numeroEntero = Integer.parseInt(atributo);
    			return true;
    		}
    		
    		catch(NumberFormatException e)
    		{
    			System.out.println(e.toString());
    			LabelValidaciones.setText("El atributo ingresado no corresponde con un dato tipo entero");
    			return false;
    		}
    	}
    	
    	else if(tipoAtributo == "Flotante" )
    	{
       		try
    		{
    			@SuppressWarnings("unused")
				float numeroFlotante = Float.parseFloat(atributo);
    			return true;
    		}
    		
    		catch(NumberFormatException e)
    		{
    			System.out.println(e.toString());
    			LabelValidaciones.setText("El atributo ingresado no corresponde con un dato tipo flotante");
    			return false;
    		}
    	}
    	
    	else if(tipoAtributo == "Fecha-Hora" )
    	{
    		SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    		
    		try
    		{

    			@SuppressWarnings("unused")
				Date fecha = formatoFechaHora.parse(atributo);
    			return true;

    		} 
    		catch (ParseException e) 
    		{

    			System.out.println(e.toString());
    			LabelValidaciones.setText("El atributo ingresado no corresponde con un dato de tipo fecha-hora. Un ejemplo del formato"
    					+ "de la fecha-hora es 12/05/2017 6:15:30 ");
    			return false;

    		}
    	}
    	
    	else
    	{
    		return true;
    	}
    }
    
    public boolean ValidarForaneo()
    {
    	String atributo = TextFieldInsercion.getText();
    	String esForaneo = arregloAtributosDocumento.get(contadorArreglo).getLlaveForanea();
    	String atributoForaneo = arregloAtributosDocumento.get(contadorArreglo).getAtributoForaneo();
    	String nombreDocumento = arregloAtributosDocumento.get(contadorArreglo).getDocumentoForaneo();
    	
    	if(esForaneo == "Si")
    	{
    		NodoDocumentoJSON nodoDocumento = controladorPrincipal.getListaDocumentos().BuscarDocumento(nombreDocumento);
    		NodoObjetoJSON nodoObjeto = nodoDocumento.getObjetoSiguiente();
    		
    		if(nodoObjeto == null)
    		{
    			
    			LabelValidaciones.setText("El atributo es llave foránea y el valor ingresado no corresponde con un objeto de referencia "
    					+ "válido existente");
    			return false;
    		}
    		else
    		{
        		while(nodoObjeto.getObjetoSiguiente() != null)
        		{
        			ArrayList<AtributosObjetosJSON> arregloTemporal = nodoObjeto.getArregloAtributos();
        			
        			for (int i = 0; i < arregloTemporal.size(); i++)
        			{

        				if(arregloTemporal.get(i).getNombreAtributo().equals(atributoForaneo)  && arregloTemporal.get(i).getAtributo().equals(atributo))
        				{
        					arregloNodoObjetos.add(nodoObjeto);
        					return true;
        				}
        			}
        			
        			nodoObjeto = nodoObjeto.getObjetoSiguiente();
        			
        		}
        		
        		ArrayList<AtributosObjetosJSON> arregloTemporal = nodoObjeto.getArregloAtributos();
        		
    			for (int i = 0; i < arregloTemporal.size(); i++)
    			{
    				if(arregloTemporal.get(i).getNombreAtributo().equals(atributoForaneo)  && arregloTemporal.get(i).getAtributo().equals(atributo))
    				{
    					arregloNodoObjetos.add(nodoObjeto);
    					return true;
    				}
    			}
    			
    			LabelValidaciones.setText("El atributo es llave foránea y el valor ingresado no corresponde con un objeto de referencia "
    					+ "válido existente");
    			return false;
 
    		}


    		
    	}
    	else
    	{
    		return true;
    	}
    }
    
    public void MostrarVentanaAfirmacion()
    {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/datosI/linkeddb/aplicacion/VentanaMensajeCreacionObjeto.fxml"));
    		Parent parent = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Mensaje Afirmación");
    		stage.setResizable(false);
    		stage.setScene(new Scene(parent));
    		stage.show();
    		
    	}
    	
    	catch (Exception e)
    	{
    		System.out.println("No se pudo abrir la ventana mostrar mensaje de afirmación");
    	}
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		BotonCrearObjeto.setVisible(false);
		arregloNodoObjetos = new ArrayList<>();
		arregloAtributosObjetos = new ArrayList<>();
		AsignarArregloAtributos(controladorPrincipal.getDocumentoSeleccionado());
		contadorArreglo = 0;
		alerta = false;
		InsertarLabels();
		
	}

}
