package org.datosI.linkeddb.listasEnlazadas;

public class NodoStoreJSON {
	
	private String nombre;
	private NodoStoreJSON siguiente;
	private NodoStoreJSON anterior;
	private NodoDocumentoJSON documentoSiguiente;
	
	public NodoStoreJSON(String Nombre)
	{
		this.setNombre(Nombre);
		this.setSiguiente(null);
		this.setAnterior(null);
		this.setDocumentoSiguiente(null);
	}
	

	public NodoStoreJSON(String Nombre, NodoStoreJSON Siguiente, NodoStoreJSON Anterior,NodoDocumentoJSON DocumentoSiguiente)
	{
		this.setNombre(Nombre);
		this.setSiguiente(Siguiente);
		this.setAnterior(Anterior);
		this.setDocumentoSiguiente(DocumentoSiguiente);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public NodoStoreJSON getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoStoreJSON siguiente) {
		this.siguiente = siguiente;
	}

	public NodoStoreJSON getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoStoreJSON anterior) {
		this.anterior = anterior;
	}
	
	public NodoDocumentoJSON getDocumentoSiguiente() {
		return documentoSiguiente;
	}

	public void setDocumentoSiguiente(NodoDocumentoJSON documentoSiguiente) {
		this.documentoSiguiente = documentoSiguiente;
	}

}
