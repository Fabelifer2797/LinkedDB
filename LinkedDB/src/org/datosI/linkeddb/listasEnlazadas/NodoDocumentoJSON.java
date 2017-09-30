package org.datosI.linkeddb.listasEnlazadas;

import java.util.ArrayList;

public class NodoDocumentoJSON {
	
	private String nombre;
	private ArrayList<AtributosDocumentosJSON> arregloAtributos;
	private NodoDocumentoJSON siguiente;
	private NodoDocumentoJSON anterior;
	private NodoDocumentoJSON documentoSiguiente;
	private NodoObjetoJSON objetoSiguiente;


	public NodoDocumentoJSON(String Nombre, ArrayList<AtributosDocumentosJSON> ArregloAtributos)
	{
		this.setNombre(Nombre);
		this.setArregloAtributos(ArregloAtributos);
		this.setSiguiente(null);
		this.setAnterior(null);
		this.setDocumentoSiguiente(null);
		this.setObjetoSiguiente(null);
	}
	


	public NodoDocumentoJSON(NodoDocumentoJSON Siguiente, NodoDocumentoJSON Anterior, String Nombre, ArrayList<AtributosDocumentosJSON> ArregloAtributos, NodoDocumentoJSON DocumentoSiguiente,
			NodoObjetoJSON ObjetoSiguiente)
	{
		this.setNombre(Nombre);
		this.setArregloAtributos(ArregloAtributos);
		this.setSiguiente(Siguiente);
		this.setAnterior(Anterior);
		this.setDocumentoSiguiente(DocumentoSiguiente);
		this.setObjetoSiguiente(ObjetoSiguiente);
	}
	
	public NodoDocumentoJSON getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoDocumentoJSON siguiente) {
		this.siguiente = siguiente;
	}

	public NodoDocumentoJSON getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoDocumentoJSON anterior) {
		this.anterior = anterior;
	}
	
	public NodoDocumentoJSON getDocumentoSiguiente() {
		return documentoSiguiente;
	}

	public void setDocumentoSiguiente(NodoDocumentoJSON documentoSiguiente) {
		this.documentoSiguiente = documentoSiguiente;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<AtributosDocumentosJSON> getArregloAtributos() {
		return arregloAtributos;
	}
	public void setArregloAtributos(ArrayList<AtributosDocumentosJSON> arregloAtributos) {
		this.arregloAtributos = arregloAtributos;
	}



	public NodoObjetoJSON getObjetoSiguiente() {
		return objetoSiguiente;
	}



	public void setObjetoSiguiente(NodoObjetoJSON objetoSiguiente) {
		this.objetoSiguiente = objetoSiguiente;
	}
	
}
