package org.datosI.linkeddb.aplicacion;

import org.datosI.linkeddb.documentosJSON.DocumentoJSON;
import org.datosI.linkeddb.listasEnlazadas.*;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    
    private static TreeItem<String> rootStores = new TreeItem<>("Stores");
    private static TreeItem<String> storeSeleccionado;
    private static ListaDoble ListaStores = new ListaDoble();
    private static ListaDobleCircular ListaDocumentos = new ListaDobleCircular();
    
    public ControladorVentanaPrincipal() {
    	

	}
    
    public ListaDobleCircular getListaDocumentos()
    {
    	return ListaDocumentos;
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
    			MenuItem eliminarObjetoPorLlave = new MenuItem("Eliminar un objeto por llave");
    			
    			crearObjetoItem.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					System.out.println("Crear un objeto click");
    				}
    			}
    					);
    			
    			mostrarObjetosItem.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
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
    			
    			eliminarObjetoPorLlave.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					System.out.println("Eliminar un objeto por llave click");
    				}
    			}
    					);
    			
    			TreeViewPrincipal.setContextMenu(new ContextMenu(crearObjetoItem,mostrarObjetosItem,eliminarLosObjetos,eliminarDocumento,
    					buscarObjetos,eliminarObjetoPorLlave));
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
    
 
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		InicializarTreeView();
	}
	


}
