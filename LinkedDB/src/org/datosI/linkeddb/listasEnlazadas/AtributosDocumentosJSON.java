package org.datosI.linkeddb.listasEnlazadas;


public class AtributosDocumentosJSON {
	
	private String nombre;
	private String tipo;
	private String requerido;
	private String valorPorDefecto;
	private String llavePrimaria;
	private String llaveForanea;
	private String documentoForaneo;
	private String atributoForaneo;
	

	
	
	public AtributosDocumentosJSON(String nombre, String tipo, String requerido, String valorPorDefecto,
			String llavePrimaria, String llaveForanea, String documentoForaneo, String atributoForaneo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.requerido = requerido;
		this.valorPorDefecto = valorPorDefecto;
		this.llavePrimaria = llavePrimaria;
		this.llaveForanea = llaveForanea;
		this.documentoForaneo = documentoForaneo;
		this.atributoForaneo = atributoForaneo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRequerido() {
		return requerido;
	}
	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}
	public String getValorPorDefecto() {
		return valorPorDefecto;
	}
	public void setValorPorDefecto(String valorPorDefecto) {
		this.valorPorDefecto = valorPorDefecto;
	}
	public String getLlavePrimaria() {
		return llavePrimaria;
	}
	public void setLlavePrimaria(String llavePrimaria) {
		this.llavePrimaria = llavePrimaria;
	}
	public String getLlaveForanea() {
		return llaveForanea;
	}
	public void setLlaveForanea(String llaveForanea) {
		this.llaveForanea = llaveForanea;
	}
	public String getDocumentoForaneo() {
		return documentoForaneo;
	}
	public void setDocumentoForaneo(String documentoForaneo) {
		this.documentoForaneo = documentoForaneo;
	}
	public String getAtributoForaneo() {
		return atributoForaneo;
	}
	public void setAtributoForaneo(String atributoForaneo) {
		this.atributoForaneo = atributoForaneo;
	}

}
