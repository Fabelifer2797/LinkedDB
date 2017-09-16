package org.datosI.linkeddb.documentosJSON;

import java.io.File;

public class JsonStores {
	
	private String ruta;
	private String nombreJsonStore;
	private String rutaFinal;


	public JsonStores(String NombreJsonStore)
	{
		this.setRuta("F:\\Fabri\\TEC\\Ing. en Computadores\\II Semestre 2017\\Algoritmos y Estructuras de Datos I\\Proyectos\\Proyecto 1\\Directorio Principal\\");
		this.setNombreJsonStore(NombreJsonStore);
	}


	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public String getNombreJsonStore() {
		return nombreJsonStore;
	}


	public void setNombreJsonStore(String nombreJsonStore) {
		this.nombreJsonStore = nombreJsonStore;
	}
	
	public String getRutaFinal() {
		return rutaFinal;
	}


	public void setRutaFinal(String rutaFinal) {
		this.rutaFinal = rutaFinal;
	}
	
	public void crearJsonStore()
	{
		String nombreStore = this.getNombreJsonStore();
		String rutaStore = this.getRuta();
		this.rutaFinal = rutaStore + nombreStore;
		
		File Store = new File(this.getRutaFinal());
		
		if(Store.mkdirs()) 
		{
			System.out.println("El Store " + nombreStore + " ha sido creado");
		}
		
		else
		{
			System.out.println("El Store no fue creado exitosamente");
		}
		
	
	}
	
	public static void main(String[] args)
	{
//		JsonStores Store1 = new JsonStores("Prueba1");
//		Store1.crearJsonStore();
		
//		JsonStores Store2 = new JsonStores("Prueba2");
//		Store2.crearJsonStore();
//		System.out.println(Store2.getRutaFinal());
	}

}
