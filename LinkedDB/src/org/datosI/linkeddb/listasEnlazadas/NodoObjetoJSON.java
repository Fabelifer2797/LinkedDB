package org.datosI.linkeddb.listasEnlazadas;

import java.util.ArrayList;

public class NodoObjetoJSON {
	
	private NodoObjetoJSON siguiente;
	private static int Contador = 0;
	private int ID;
	private int IDF;
	private int IDP;

	private ArrayList<AtributosObjetosJSON> arregloAtributos;
	

	public NodoObjetoJSON(ArrayList<AtributosObjetosJSON> ArregloAtributos,int IDforaneo, int IDprimario)
	{
		this.setSiguiente(null);
		this.setArregloAtributos(ArregloAtributos);
		Contador++;
		this.setID(Contador);
		this.setIDF(IDforaneo);
		this.setIDP(IDprimario);
	}
	
	public NodoObjetoJSON( NodoObjetoJSON Siguiente, int IDforaneo, int IDprimario)
	{
		this.setSiguiente(Siguiente);
		this.setArregloAtributos(null);
		Contador++;
		this.setID(Contador);
		this.setIDF(IDforaneo);
		this.setIDP(IDprimario);
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public int getIDF() {
		return IDF;
	}

	public void setIDF(int iDF) {
		IDF = iDF;
	}

	public int getIDP() {
		return IDP;
	}

	public void setIDP(int iDP) {
		IDP = iDP;
	}


	public NodoObjetoJSON getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoObjetoJSON siguiente) {
		this.siguiente = siguiente;
	}

	public ArrayList<AtributosObjetosJSON> getArregloAtributos() {
		return arregloAtributos;
	}

	public void setArregloAtributos(ArrayList<AtributosObjetosJSON> arregloAtributos) {
		this.arregloAtributos = arregloAtributos;
	}
	
	public void AgregarAtributo(AtributosObjetosJSON Atributo)
	{
		this.getArregloAtributos().add(Atributo);
	}
	
	

}
