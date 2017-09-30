package org.datosI.linkeddb.documentosJSON;

import java.util.ArrayList;

import org.datosI.linkeddb.listasEnlazadas.AtributosDocumentosJSON;
import org.datosI.linkeddb.listasEnlazadas.AtributosObjetosJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DocumentoJSON {
	
	
	public DocumentoJSON()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject  MetaDataJSON( ArrayList<AtributosDocumentosJSON> arregloAtributos)
	{
		JSONObject raizDocumento = new JSONObject();
		JSONArray arregloJSON = new JSONArray();
		
		for(int i = 0; i < arregloAtributos.size(); i++)
		{
			JSONObject atributo = new JSONObject();
			atributo.put("Nombre",arregloAtributos.get(i).getNombre());
			atributo.put("Tipo",arregloAtributos.get(i).getTipo());
			atributo.put("Requerido",arregloAtributos.get(i).getRequerido());
			atributo.put("Valor por defecto",arregloAtributos.get(i).getValorPorDefecto());
			atributo.put("Llave primaria",arregloAtributos.get(i).getLlavePrimaria());
			atributo.put("Llave foranea",arregloAtributos.get(i).getLlaveForanea());
			atributo.put("Documento de referencia",arregloAtributos.get(i).getDocumentoForaneo());
			atributo.put("Atributo de referencia",arregloAtributos.get(i).getAtributoForaneo());
			arregloJSON.add(atributo);
			
		}
		
		raizDocumento.put("Atributos", arregloJSON);
		return raizDocumento;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject EscribirObjetos( ArrayList<AtributosObjetosJSON> arregloAtributosObjetos)
	{
		JSONObject raizDocumento = new JSONObject();
		JSONArray arregloJSON = new JSONArray();
		
		for(int i = 0; i < arregloAtributosObjetos.size(); i++)
		{
			JSONObject atributo = new JSONObject();
			atributo.put("Nombre Atributo", arregloAtributosObjetos.get(i).getNombreAtributo());
			atributo.put("Atributo", arregloAtributosObjetos.get(i).getAtributo());
			arregloJSON.add(atributo);
			
		}
		
		raizDocumento.put("Atributos", arregloJSON);
		return raizDocumento;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		DocumentoJSON documento = new DocumentoJSON();
//		AtributosDocumentosJSON atributos1 =  new AtributosDocumentosJSON("Nombre","String","Si","","Si","No","","");
//		AtributosDocumentosJSON atributos2 =  new AtributosDocumentosJSON("Cedula","Entero","Si","","No","No","","");
//		ArrayList<AtributosDocumentosJSON> arreglo1 = new ArrayList<AtributosDocumentosJSON>();
//		arreglo1.add(atributos1);
//		arreglo1.add(atributos2);
//		JSONObject root = new JSONObject();
//		JSONObject resultado = documento.MetaDataJSON(arreglo1);
//		String a = "Documento";
//		root.put(a, resultado);
//		System.out.println(root.toJSONString());
		AtributosObjetosJSON atributos1 = new AtributosObjetosJSON("hola", "a");
		AtributosObjetosJSON atributos2 = new AtributosObjetosJSON("asdas", "asfasf");
		ArrayList<AtributosObjetosJSON> arreglo1 = new ArrayList<>();
		arreglo1.add(atributos1);
		arreglo1.add(atributos2);
		JSONObject root = new JSONObject();
		JSONObject resultado = documento.EscribirObjetos(arreglo1);
		root.put("Objeto", resultado);
		System.out.println(root.toJSONString());
	}

}
