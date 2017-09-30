package org.datosI.linkeddb.listasEnlazadas;


public class ListaSimple {
	
	private NodoObjetoJSON primero;
	
	
	public ListaSimple()
	{
		this.setPrimero(null);
	}


	public NodoObjetoJSON getPrimero() {
		return primero;
	}


	public void setPrimero(NodoObjetoJSON primero) {
		this.primero = primero;
	}
	
	public boolean ListaSimpleVacia()
	{
		return this.getPrimero() == null;
	}
	
	public void InsertarFinalListaSimple(NodoObjetoJSON NodoAtributos)
	{
		if(this.getPrimero() == null)
		{
			this.primero = NodoAtributos;
		}
		else
		{
			NodoObjetoJSON actual = this.primero;
			
			while(actual.getSiguiente() != null)
			{
				actual = actual.getSiguiente();
			}
			
			actual.setSiguiente(NodoAtributos);
		}
	}
	
	public void EliminarNodo(int ID)
	{
		NodoObjetoJSON actual = this.primero;
		NodoObjetoJSON anterior = this.primero;
		boolean encontrado = false;
		
		while(actual != null)
		{
			if(actual.getID() == ID)
			{
				encontrado = true;
			}
			
			if(encontrado)
			{
				break;
			}
			else
			{
				anterior = actual;
				actual = actual.getSiguiente();
			}
		}
		
		if(actual == null)
		{
			System.out.println("Elemento no encontrado");
		}
		else if(actual == this.primero)
		{
			this.primero = actual.getSiguiente();
		}
		else
		{
			anterior.setSiguiente(actual.getSiguiente());
		}
		
	}
	
	public NodoObjetoJSON buscar(AtributosObjetosJSON atributo) {
		
		NodoObjetoJSON actual = this.primero;
		
		while (actual != null) {
			
			for(int i = 0; i < actual.getArregloAtributos().size();i++)
			{
				if((actual.getArregloAtributos().get(i).getNombreAtributo() == atributo.getNombreAtributo()) && 
						(actual.getArregloAtributos().get(i).getAtributo() == atributo.getAtributo()))
				{
					System.out.println("Elemento Encontrado");
					return actual;
				}
				
			}
			
			actual = actual.getSiguiente();
			
		}
		System.out.println("Elemento no Encontrado");
		return null;
	}
	
	public void Imprimir() {
		NodoObjetoJSON actual = this.primero;
		while (actual != null) {
			
			for(int i = 0; i < actual.getArregloAtributos().size();i++)
			{
				System.out.println(actual.getArregloAtributos().get(i).getNombreAtributo() + " : " + 
			actual.getArregloAtributos().get(i).getAtributo() + "\n");
			}
			actual = actual.getSiguiente();
		}		
	}
	
	
}
