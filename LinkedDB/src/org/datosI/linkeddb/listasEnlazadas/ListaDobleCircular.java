package org.datosI.linkeddb.listasEnlazadas;


public class ListaDobleCircular {
	
	private NodoDocumentoJSON primero;
	
	public ListaDobleCircular()
	{
		this.setPrimero(null);
	}

	public NodoDocumentoJSON getPrimero() {
		return primero;
	}

	public void setPrimero(NodoDocumentoJSON primero) {
		this.primero = primero;
	}
	
	
	public boolean ListaDobleCircularVacia()
	{
		return this.getPrimero() == null;
	}
	
	public void InsertarFinalListaDC(NodoDocumentoJSON NodoDocumentos)
	{
		if (this.getPrimero() == null)
		{
			this.primero = NodoDocumentos;
			NodoDocumentos.setSiguiente(NodoDocumentos);
			NodoDocumentos.setAnterior(NodoDocumentos);
		}
		
		else
		{
			NodoDocumentoJSON actual = this.getPrimero();
			
			while (actual.getSiguiente() != this.getPrimero())
			{
				actual = actual.getSiguiente();
			}
			
			actual.setSiguiente(NodoDocumentos);
			NodoDocumentos.setAnterior(actual);
			NodoDocumentos.setSiguiente(this.getPrimero());
			this.primero.setAnterior(NodoDocumentos);
		}
	}
	
	public void EliminarNodoDC(String Nombre)
	{
		NodoDocumentoJSON actual = this.getPrimero();
		NodoDocumentoJSON anterior = this.getPrimero().getAnterior();
	
		if(actual == null)
		{
			System.out.println("Lista Vacia");
		}
		
		else if (this.BuscarDocumento(Nombre) == null)
		{
			System.out.println("Elemento a eliminar no encontrado");
		}
		
		else
		{
			while (actual.getSiguiente() != this.getPrimero())
			{
				if(actual.getNombre() == Nombre)
				{
					break;
				}
				
				else
				{
					anterior = actual;
					actual = actual.getSiguiente();
				}
			}
			
			if(actual == this.getPrimero())
			{
				this.primero = actual.getSiguiente();
				anterior.setSiguiente(this.getPrimero());
				this.primero.setAnterior(anterior);
			}
			else
			{
				anterior.setSiguiente(actual.getSiguiente());
				actual.getSiguiente().setAnterior(anterior);
			}
			
		}
		
	}
	
	public NodoDocumentoJSON BuscarDocumento(String Nombre)
	{
		if(ListaDobleCircularVacia())
		{
			return null;
		}
		NodoDocumentoJSON actual = this.getPrimero();
		
		while(actual.getSiguiente() != this.getPrimero())
		{
			if( Nombre.equals(actual.getNombre()))
			{
				System.out.println("Elemento encontrado");
				return actual;
			}
			
			else
			{
				actual = actual.getSiguiente();
			}
		}
		
		if(Nombre.equals(actual.getNombre()))
		{
			System.out.println("Elemento encontrado");
			return actual;
		}
		
		else
		{
			System.out.println("Elemento no encontrado");
			return null;
		}
	}
	
	public void ImprimirListaDC()
	{	
		if(ListaDobleCircularVacia())
		{
			System.out.println("Lista vacia");
			return;
		}
		
		NodoDocumentoJSON actual = this.getPrimero();
		
		
		while (actual.getSiguiente() != this.getPrimero())
		{
			System.out.println("Documento : " + actual.getNombre() + "\n");
			System.out.println("Lista de atributos : " + "\n");
			
			for(int i = 0; i < actual.getArregloAtributos().size(); i++ )
			{
				System.out.println("Nombre del atributo : " + actual.getArregloAtributos().get(i).getNombre() + "\n");
				System.out.println("Tipo del atributo : " + actual.getArregloAtributos().get(i).getTipo() + "\n");
				System.out.println("Atributo requerido : " + actual.getArregloAtributos().get(i).getRequerido() + "\n");
				System.out.println("Valor por defecto : " + actual.getArregloAtributos().get(i).getValorPorDefecto() + "\n");
				System.out.println("Llave Foranea : " + actual.getArregloAtributos().get(i).getLlaveForanea() + "\n");
				System.out.println("Llave Primaria : " + actual.getArregloAtributos().get(i).getLlavePrimaria() + "\n");
				System.out.println("Atributo Foraneo : " + actual.getArregloAtributos().get(i).getAtributoForaneo() + "\n");
				System.out.println("Documento Foraneo : " + actual.getArregloAtributos().get(i).getDocumentoForaneo() + "\n");
			}
			
			actual = actual.getSiguiente();
		}
		
		System.out.println("Documento : " + actual.getNombre() + "\n");
		System.out.println("Lista de atributos : " + "\n");
		
		for(int i = 0; i < actual.getArregloAtributos().size(); i++ )
		{
			System.out.println("Nombre del atributo : " + actual.getArregloAtributos().get(i).getNombre() + "\n");
			System.out.println("Tipo del atributo : " + actual.getArregloAtributos().get(i).getTipo() + "\n");
			System.out.println("Atributo requerido : " + actual.getArregloAtributos().get(i).getRequerido() + "\n");
			System.out.println("Valor por defecto : " + actual.getArregloAtributos().get(i).getValorPorDefecto() + "\n");
			System.out.println("Llave Foranea : " + actual.getArregloAtributos().get(i).getLlaveForanea() + "\n");
			System.out.println("Llave Primaria : " + actual.getArregloAtributos().get(i).getLlavePrimaria() + "\n");
			System.out.println("Atributo Foraneo : " + actual.getArregloAtributos().get(i).getAtributoForaneo() + "\n");
			System.out.println("Documento Foraneo : " + actual.getArregloAtributos().get(i).getDocumentoForaneo() + "\n");
		}
			
	}
	
}
