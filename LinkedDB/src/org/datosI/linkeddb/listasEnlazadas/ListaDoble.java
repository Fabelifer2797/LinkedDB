package org.datosI.linkeddb.listasEnlazadas;

public class ListaDoble {
	
	private NodoStoreJSON primero;
	
	public ListaDoble()
	{
		this.setPrimero(null);
	}

	public NodoStoreJSON getPrimero() {
		return primero;
	}

	public void setPrimero(NodoStoreJSON primero) {
		this.primero = primero;
	}
	
	public boolean ListaDobleVacia()
	{
		return this.getPrimero() == null;
	}
	
	public void InsertarFinalLD(NodoStoreJSON NodoStore)
	{
		if(this.getPrimero() == null)
		{
			this.setPrimero(NodoStore);
		}
		else
		{
			NodoStoreJSON actual = this.getPrimero();
			
			while(actual.getSiguiente() != null)
			{
				actual = actual.getSiguiente();
			}
			
			actual.setSiguiente(NodoStore);
			NodoStore.setAnterior(actual);
		}
	}
	
	public void EliminarNodoLD(String Nombre)
	{
		
		if(this.getPrimero() == null)
		{
			System.out.println("Lista vacia");
		}
		
		else
		{
			NodoStoreJSON actual = this.getPrimero();
			NodoStoreJSON anterior = null;
			
			while (actual != null)
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
			
			if(actual == null)
			{
				System.out.println("Elemento a eliminar no encontrado");
			}
			else if(actual == this.getPrimero())
			{
				this.setPrimero(actual.getSiguiente());
				actual.setSiguiente(null);
				this.getPrimero().setAnterior(null);
			}
			else if(actual.getSiguiente() == null)
			{
				anterior.setSiguiente(null);
				actual.setAnterior(null);
			}
			
			else
			{
				anterior.setSiguiente(actual.getSiguiente());
				actual.getSiguiente().setAnterior(anterior);
				actual.setSiguiente(null);
				
			}
			
		}
	}
	
	public NodoStoreJSON BuscarStore(String Nombre)
	{
		NodoStoreJSON actual = this.getPrimero();
		
		while(actual != null)
		{
			if(actual.getNombre() == Nombre)
			{
				System.out.println("Elemento Encontrado");
				return actual;
			}
			
			else
			{
				actual = actual.getSiguiente();
			}
		}
		
		System.out.println("Elemento no encontrado");
		return null;
	}
	
	public void ImprimirLD()
	{
		NodoStoreJSON actual = this.getPrimero();
		
		while(actual != null)
		{
			System.out.println("Nombre del store: " + actual.getNombre() + "\n");
			actual = actual.getSiguiente();
		}
	}

}
