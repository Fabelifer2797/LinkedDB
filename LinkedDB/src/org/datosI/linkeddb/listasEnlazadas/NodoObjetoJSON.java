package org.datosI.linkeddb.listasEnlazadas;

import java.util.ArrayList;

public class NodoObjetoJSON {
	
	private NodoObjetoJSON siguiente;
	private NodoObjetoJSON objetoSiguiente;
	private NodoObjetoJSON llaveForanea;
	private ArrayList<AtributosObjetosJSON> arregloAtributos;
	private static int contador = 0;
	private int ID;
	



	public NodoObjetoJSON(ArrayList<AtributosObjetosJSON> ArregloAtributos)
	{
		this.setSiguiente(null);
		this.setArregloAtributos(ArregloAtributos);
		this.setObjetoSiguiente(null);
		this.setLlaveForanea(null);
		contador++;
		this.setID(contador);

	}
	
	public NodoObjetoJSON( ArrayList<AtributosObjetosJSON> ArregloAtributos, NodoObjetoJSON Siguiente, NodoObjetoJSON ObjetoSiguiente,
			NodoObjetoJSON LlaveForanea)
	{
		this.setSiguiente(Siguiente);
		this.setObjetoSiguiente(ObjetoSiguiente);
		this.setLlaveForanea(LlaveForanea);
		this.setArregloAtributos(ArregloAtributos);
		contador++;
		this.setID(contador);

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
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public NodoObjetoJSON getObjetoSiguiente() {
		return objetoSiguiente;
	}

	public void setObjetoSiguiente(NodoObjetoJSON objetoSiguiente) {
		this.objetoSiguiente = objetoSiguiente;
	}

	public NodoObjetoJSON getLlaveForanea() {
		return llaveForanea;
	}

	public void setLlaveForanea(NodoObjetoJSON llaveForanea) {
		this.llaveForanea = llaveForanea;
	}
	
	

}
