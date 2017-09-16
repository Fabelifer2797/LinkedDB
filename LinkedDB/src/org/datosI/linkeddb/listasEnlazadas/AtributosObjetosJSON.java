package org.datosI.linkeddb.listasEnlazadas;

public class AtributosObjetosJSON {
	
	private String nombreAtributo;
	private String atributo;
	
	
	public AtributosObjetosJSON(String NombreAtributo, String Atributo)
	{
		this.setNombreAtributo(NombreAtributo); 
		this.setAtributo(Atributo);
	}


	public String getNombreAtributo() {
		return nombreAtributo;
	}


	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}


	public String getAtributo() {
		return atributo;
	}


	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

}
