package org.datosI.linkeddb.aplicacion;

import org.datosI.linkeddb.listasEnlazadas.*;


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

    			MenuItem itemMenu = new MenuItem("Menu Item");
    			System.out.println("Padre " + item.getParent().getValue());
    			itemMenu.setOnAction(new EventHandler<ActionEvent>()
    			{
    				@Override
    				public void handle(ActionEvent arg0)
    				{
    					System.out.println("Menu Item Clicked!");
    				}
    			}
    					);
    			
    			TreeViewPrincipal.setContextMenu(new ContextMenu(itemMenu));
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
    	ListaDocumentos.ImprimirListaDC();
    	System.out.println(ListaStores.getPrimero().getDocumentoSiguiente().getDocumentoSiguiente().getNombre());
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		InicializarTreeView();
	}
	


}
