package org.datosI.linkeddb.aplicacion;

import org.datosI.linkeddb.documentosJSON.DocumentoJSON;
import org.datosI.linkeddb.listasEnlazadas.*;
import org.json.simple.JSONObject;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class ControladorVentanaPrincipal implements Initializable {
	

    @FXML
    private Button BotonCrearStores;
    
    
    @FXML
    private AnchorPane PanelVentanaPrincipal;
    
    @FXML
    public TreeView<String> TreeViewPrincipal;
    
    @FXML
    private Button BotonCommit;

	@FXML
    private TableView<List<String>> TableViewObjetosJSON;

    

    
    private static TreeItem<String> rootStores = new TreeItem<>("Stores");
    private static TreeItem<String> storeSeleccionado;
    private static NodoDocumentoJSON documentoSeleccionado;
    private static ListaDoble ListaStores = new ListaDoble();
    private static ListaDobleCircular ListaDocumentos = new ListaDobleCircular();
    private static ListaSimple ListaObjetos = new ListaSimple();
    private static List<List<String>> data = new ArrayList<>();
    
    public ControladorVentanaPrincipal() {
    	

	}
    
    public ListaDobleCircular getListaDocumentos()
    {
    	return ListaDocumentos;
    }
    
    public ListaSimple getListaObjetos()
    {
    	return ListaObjetos;
    }
    
    public NodoDocumentoJSON getDocumentoSeleccionado() {
		return documentoSeleccionado;
	}

	public void setDocumentoSeleccionado(NodoDocumentoJSON documentoSeleccionado) {
		ControladorVentanaPrincipal.documentoSeleccionado = documentoSeleccionado;
	}
    
    
    public void AgregarStoresTreeView(String nombreStore)
    {
		TreeItem<String> item = new TreeItem<String>(nombreStore);
		rootStores.getChildren().add(item);
    	NodoStoreJSON nodoStore = new NodoStoreJSON(nombreStore);
    	ListaStores.InsertarFinalLD(nodoStore);;
    }
    
    public void AgregarDocumentosTreeView( String nombreDocumento, ArrayList<AtributosDocumentosJSON> arregloAtributos)
    {
    	TreeItem<String> item  = new TreeItem<String>(nombreDocumento);
    	storeSeleccionado.getChildren().add(item);
    	NodoDocumentoJSON nodoDocumento = new NodoDocumentoJSON(nombreDocumento, arregloAtributos);
    	ListaDocumentos.InsertarFinalListaDC(nodoDocumento);
    	NodoStoreJSON temporalStore = ListaStores.BuscarStore(storeSeleccionado.getValue());
    	NodoDocumentoJSON temporalDocumento = ListaDocumentos.BuscarDocumento(nombreDocumento);
    	
    	if(temporalStore.getDocumentoSiguiente() == null)
    	{
    		temporalStore.setDocumentoSiguiente(temporalDocumento);
    	}
    	else
    	{
    		NodoDocumentoJSON aux = temporalStore.getDocumentoSiguiente();
    		
    		while(aux.getDocumentoSiguiente() != null)
    		{
    			aux = aux.getDocumentoSiguiente();
    		}
    		
    		aux.setDocumentoSiguiente(temporalDocumento);
    	}

    }
    
    public void AgregarObjetosMemoria(NodoObjetoJSON nodoObjeto, ArrayList<NodoObjetoJSON> arregloNodosObjetos)
    {
    	ListaObjetos.InsertarFinalListaSimple(nodoObjeto);
    	NodoDocumentoJSON temporal = documentoSeleccionado;
    	
    	if(temporal.getObjetoSiguiente() == null)
    	{
    		temporal.setObjetoSiguiente(nodoObjeto);
    		EnlazarForaneos(nodoObjeto, arregloNodosObjetos);
    	}
    	
    	else
    	{
    		NodoObjetoJSON temporal2 = temporal.getObjetoSiguiente();
    		
    		while(temporal2.getObjetoSiguiente() != null)
    		{
    			temporal2 = temporal2.getObjetoSiguiente();
    		}
    		
    		temporal2.setObjetoSiguiente(nodoObjeto);
    		EnlazarForaneos(nodoObjeto, arregloNodosObjetos);
    	}
    }
    
    public void EnlazarForaneos(NodoObjetoJSON nodoObjeto ,ArrayList<NodoObjetoJSON> arregloNodoObjetos)
    {
    	for(int i = 0; i < arregloNodoObjetos.size(); i++)
    	{
    		NodoObjetoJSON aux = arregloNodoObjetos.get(i);
    		
    		if(aux.getLlaveForanea() == null)
    		{
    			aux.setLlaveForanea(nodoObjeto);
    		}
    		
    		else
    		{
    			while(aux.getLlaveForanea() != null)
    			{
    				aux = aux.getLlaveForanea();
    			}
    			
    			aux.setLlaveForanea(nodoObjeto);
    		}
    	}
    }
    
    public void InicializarTreeView()
    {
    	TreeViewPrincipal.setRoot(rootStores);
    	TreeViewPrincipal.setShowRoot(false);
    }
    
  
	@FXML
    void ItemSeleccionado(MouseEvent event)  {
    	
    	try{
    		TreeViewPrincipal.setContextMenu(null);
    		TreeItem<String> item = TreeViewPrincipal.getSelectionModel().getSelectedItem();

    		if((event.getClickCount() == 2 )&& (VerificarStore(item.getValue()))){

    			try{
    				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/datosI/linkeddb/aplicacion/VentanaNombreDocumento.fxml"));
    				Parent parent = (Parent) fxmlLoader.load();
    				Stage stage = new Stage();
    				stage.setTitle("Ventana Nombre Documento");
    				stage.setResizable(false);
    				stage.setScene(new Scene(parent));
    				stage.show();
    				storeSeleccionado = item  ;
    			}

    			catch (Exception e)
    			{
    				System.out.println("No se pudo abrir ventana nombre documento");
    			}

    		}
    		else if( (event.getButton() == MouseButton.SECONDARY ) && (VerificarStore(item.getValue()) == false) )
    		{

    			MenuItem crearObjetoItem = new MenuItem("Crear un Objeto");
    			MenuItem mostrarObjetosItem = new MenuItem("Mostrar Objetos");
    			MenuItem eliminarLosObjetos = new MenuItem("Eliminar todos los objetos");
    			MenuItem eliminarDocumento = new MenuItem("Eliminar documento");
    			MenuItem buscarObjetos = new MenuItem("Buscar Objetos por atributo");
    			MenuItem buscarObjetoPorLlave = new MenuItem("Buscar Objetos por llave");
    			documentoSeleccionado = ListaDocumentos.BuscarDocumento(item.getValue());
    			
    			crearObjetoItem.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    		        	try{
    		        		
    		        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/datosI/linkeddb/aplicacion/VentanaCrearObjetos.fxml"));
    		        		Parent parent = (Parent) fxmlLoader.load();
    		        		Stage stage = new Stage();
    		        		stage.setTitle("Ventana Crear Objetos");
    		        		stage.setResizable(false);
    		        		stage.setScene(new Scene(parent));
    		        		stage.show();
    		        		
    		        	}
    		        	
    		        	catch (Exception e)
    		        	{
    		        		System.out.println("No se pudo abrir la ventana crear objetos");
    		        	}
    					System.out.println("Crear un objeto click");
    					
    				}
    			}
    					);
    			
    			mostrarObjetosItem.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					MostrarObjetos();
    					System.out.println("Mostrar objetos click");
    				}
    			}
    					);
    			
    			eliminarLosObjetos.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					
    					System.out.println("Eliminar todos los objetos click");
    				}
    			}
    					);
    			
    			eliminarDocumento.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					System.out.println("Eliminar un documento click");
    				}
    			}
    					);
    			
    			buscarObjetos.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					System.out.println("Buscar un objeto click");
    				}
    			}
    					);
    			
    			buscarObjetoPorLlave.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					System.out.println("Buscar Objetos por llave click");
    				}
    			}
    					);
    			
    			TreeViewPrincipal.setContextMenu(new ContextMenu(crearObjetoItem,mostrarObjetosItem,eliminarLosObjetos,eliminarDocumento,
    					buscarObjetos,buscarObjetoPorLlave));
    			System.out.println("Click Derecho en el item documento");
    		}


    	}

    	catch(Exception e)
    	{
    		//System.out.println(e.toString());
    		System.out.println("El item seleccionado de dicha forma no contiene ninguna función");
    	}
    	

    }
    

	public boolean VerificarStore(String nombre)
    {
    	NodoStoreJSON nodoTemporal = ListaStores.BuscarStore(nombre);
    	
    	if(nodoTemporal != null)
    	{
    		return true;
    	}
    	
    	else
    	{
    		return false;
    	}
    }

    @FXML
    void MostrarVentanaStores(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/datosI/linkeddb/aplicacion/VentanaStores.fxml"));
    		Parent parent = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Ventana Stores");
    		stage.setResizable(false);
    		stage.setScene(new Scene(parent));
    		stage.show();
    		
    	}
    	
    	catch (Exception e)
    	{
    		System.out.println("No se pudo abrir ventana stores");
    	}

    }
    
    @FXML
    void RealizarCommit(ActionEvent event) {
    	
    	GuardarArchivosJSON();
    }
    
    @SuppressWarnings("unchecked")
	public void GuardarArchivosJSON()
    {
    	NodoStoreJSON nodoStore = ListaStores.getPrimero();
    	
    	while(nodoStore != null)
    	{
    		ArrayList<File> arregloRutas = GuardarStoresJSON(nodoStore);
    		ArrayList<JSONObject> arregloObjetosJSON = new ArrayList<JSONObject>();
    		ArrayList<String> arregloNombresD = new ArrayList<String>();
    		NodoDocumentoJSON nodoDocumento = nodoStore.getDocumentoSiguiente();
    		while (nodoDocumento != null)
    		{
    			arregloObjetosJSON.add(GuardarDocumentosJSON(nodoDocumento,arregloRutas));
    			arregloNombresD.add(nodoDocumento.getNombre());
    			JSONObject rootObjeto = new JSONObject();
    			NodoObjetoJSON nodoObjeto = nodoDocumento.getObjetoSiguiente();
    			while(nodoObjeto != null)
    			{
    				DocumentoJSON documento2 = new DocumentoJSON();
    				rootObjeto.put( Integer.toString(nodoObjeto.getID()), documento2.EscribirObjetos(nodoObjeto.getArregloAtributos()));
    				nodoObjeto= nodoObjeto.getObjetoSiguiente();
    			}
    			
    			GuardarObjetosJSON(nodoDocumento.getNombre(),arregloRutas.get(0),rootObjeto);
    			nodoDocumento = nodoDocumento.getDocumentoSiguiente();
    		}
    		
    		JSONObject rootDocumento = new JSONObject();
    		
    		for(int i = 0; i < arregloObjetosJSON.size(); i++)
    		{
    			rootDocumento.put(arregloNombresD.get(i),arregloObjetosJSON.get(i));
    		}
    		
    		File metadata = new File(arregloRutas.get(1).getAbsolutePath());
    		try
    		{
    			FileWriter escritura = new FileWriter(metadata);
    			escritura.write(rootDocumento.toJSONString());
    			escritura.close();
    		}
    		
    		catch (IOException e)
    		
    		{
    			System.out.println(e.toString());
    		}
    		
    		nodoStore = nodoStore.getSiguiente();
    	}
    }
    
    public ArrayList<File> GuardarStoresJSON(NodoStoreJSON nodoStore)
    {
    	String rutaFinal = "F:\\Fabri\\TEC\\Ing. en Computadores\\II Semestre 2017\\Algoritmos y Estructuras de Datos I\\Proyectos\\Proyecto 1\\Directorio Principal\\";
    	String nombreStore = nodoStore.getNombre();
    	String nombreMetaData = "MetaData" + nodoStore.getNombre() + ".json";
    	File carpetaStore = new File(rutaFinal + nombreStore);
    	File metadataStore = new File(rutaFinal + nombreStore + File.separator + nombreMetaData);
    	ArrayList<File> rutasFinales = new ArrayList<File>();
    	
    	if(carpetaStore.exists())
    	{
    		try
    		{
    			FileWriter borrado = new FileWriter(metadataStore.getAbsolutePath());
    			borrado.write("");
    			borrado.close();
    			
    		}
    		
    		catch(IOException e)
    		{
    			System.out.println(e.toString());
    		}
    		
			rutasFinales.add(carpetaStore);
    		rutasFinales.add(metadataStore);
    		return rutasFinales;
    		
    	}
    	
    	else
    	{
    		carpetaStore.mkdir();
    		try
    		{
    			metadataStore.createNewFile();
    		}
    		catch(IOException e)
    		{
    			System.out.println(e.toString());
    		}
    		
    		rutasFinales.add(carpetaStore);
    		rutasFinales.add(metadataStore);
    		return rutasFinales;
    	}
    }
    
    public JSONObject GuardarDocumentosJSON(NodoDocumentoJSON nodoDocumento, ArrayList<File> arregloRutas)
    {
    	String nombreDocumento = nodoDocumento.getNombre() + ".json";
    	String rutaDocumento = arregloRutas.get(0).getAbsolutePath() + File.separator + nombreDocumento;
    	File documento = new File(rutaDocumento);
    	DocumentoJSON documentoJSON = new DocumentoJSON();
    	
    	if(documento.exists())
    	{
    		JSONObject documento1 = documentoJSON.MetaDataJSON(nodoDocumento.getArregloAtributos());
    		return documento1;
    		
    	}
    	
    	else
    	{
    		try
    		{
    			documento.createNewFile();
    		}
    		catch(IOException e)
    		{
    			System.out.println(e.toString());
    		}
    		
    		JSONObject documento1 = documentoJSON.MetaDataJSON(nodoDocumento.getArregloAtributos());
    		return documento1;
    	}
    }
    
    public void GuardarObjetosJSON(String nombreDocumento, File rutaCarpeta, JSONObject raizObjeto)
    {
    	File ObjetoJSON = new File(rutaCarpeta.getAbsolutePath() + File.separator + nombreDocumento + ".json");
    	
		try
		{
			FileWriter escritura = new FileWriter(ObjetoJSON.getAbsolutePath());
			escritura.write(raizObjeto.toJSONString());
			escritura.close();
			
		}
		
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
    	
    	
    }
 

	public void MostrarObjetos()
    {
		data.clear();
		TableViewObjetosJSON.getItems().clear();
		TableViewObjetosJSON.getColumns().clear();
		ArrayList<AtributosDocumentosJSON> arregloAtributos = documentoSeleccionado.getArregloAtributos();
		
		for (int i = 0; i < arregloAtributos.size(); i++)
		{
			int Index = i;
			
			TableColumn<List<String>, String> atributoColumna = new TableColumn<>(arregloAtributos.get(i).getNombre());
			atributoColumna.setMinWidth(125);
			atributoColumna.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(Index)));
			TableViewObjetosJSON.getColumns().add(atributoColumna);
			
			
		}
		
		NodoObjetoJSON nodoObjeto = documentoSeleccionado.getObjetoSiguiente();
		
		if(nodoObjeto == null)
		{
	    	ObservableList<List<String>> lista = FXCollections.observableArrayList(data);
	    	TableViewObjetosJSON.setItems(lista);
		}
		
		else
		{
			
			while(nodoObjeto != null)
			{
				
				List<String> row = new ArrayList<>();
				ArrayList<AtributosObjetosJSON> arreglo1 = nodoObjeto.getArregloAtributos();
				
				for(int i = 0; i < arreglo1.size(); i++)
				{
					row.add(arreglo1.get(i).getAtributo());
				}
				
				data.add(row);
				nodoObjeto = nodoObjeto.getObjetoSiguiente();
				
			}
			
	    	ObservableList<List<String>> lista = FXCollections.observableArrayList(data);
	    	TableViewObjetosJSON.setItems(lista);	
		}	
    	
    }
 
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		InicializarTreeView();
		
	}
	


}
