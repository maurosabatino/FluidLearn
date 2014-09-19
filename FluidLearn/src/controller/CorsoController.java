package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import corso.*;

import javax.servlet.http.HttpServletRequest;

public class CorsoController {
	
	/*-----------Corso------------------*/
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static Corso inputCorso(HttpServletRequest request){
		Corso c = new Corso();
		if(!(request.getParameter("nome")==null))
			c.setNome(request.getParameter("nome"));
		if(!(request.getParameter("descrizione")==null))
			c.setDescrizione(request.getParameter("descrizione"));
		
		return c;
	}
	
	public static Corso nuovoCorso(HttpServletRequest request) throws SQLException{
		return DatabaseController.InsertCorso(inputCorso(request));
	}
	
	public static Corso modificaCorso(HttpServletRequest request) throws SQLException{
		Corso corso = inputCorso(request);
		String idCorso = request.getParameter("idCorso");
		corso.setIdCorso(Integer.parseInt(idCorso));
		DatabaseController.UpdateCorso(corso);
		return corso;
	}
	
	public static boolean eliminaCorso(HttpServletRequest request) throws NumberFormatException, SQLException{
		String idCorso = request.getParameter("idCorso");
		return DatabaseController.DeleteCorso(Integer.parseInt(idCorso));
	}
	/*-----------unità di apprendimento--------------*/
	public static UnitaDA inputUDA(HttpServletRequest request) throws ParseException{
		UnitaDA UDA = new UnitaDA();
		if(!(request.getParameter("nome")==null))
			UDA.setNome(request.getParameter("nome"));
		if(!(request.getParameter("descrizione")==null))
			UDA.setDescrizione(request.getParameter("descrizione"));
		if(!(request.getParameter("idCorso")==null))
			UDA.setIdCorso(Integer.parseInt(request.getParameter("idCorso")));
		if(!(request.getParameter("dataAttivazione")==null)){
			String data = request.getParameter("dataAttivazione");
			if(!(data.equals("") ||data==null)){
				UDA.setData(new SimpleDateFormat("yyyy-MM-dd").parse(data));
			}
		}
		return UDA;
	}
	
	public static UnitaDA nuovaUDA(HttpServletRequest request) throws ParseException, SQLException{
		return DatabaseController.insertUDA(inputUDA(request));
	}

	public static UnitaDA modificaUDA(HttpServletRequest request) throws ParseException, SQLException{
		UnitaDA UDA = inputUDA(request);
		String idUDA = request.getParameter("idUDA");
		UDA.setIdUDA(Integer.parseInt(idUDA));
		return DatabaseController.updateUDA(UDA);
	}
	public static boolean deleteUDA(HttpServletRequest request) throws NumberFormatException, SQLException{
		String idUDA = request.getParameter("idUDA");
		return DatabaseController.deleteUDA(Integer.parseInt(idUDA));
	}
	
	
	/*------------percorso di apprendimento--------*/
	
	public static PercorsoDiApprendimento inputPercorsoDA(HttpServletRequest request) throws NumberFormatException, SQLException{
		UnitaDA uda2 = null;
		UnitaDA uda1 = null;
		if(!(request.getParameter("iduda1")==null)){
			uda1 = DatabaseController.selectUDA(Integer.parseInt(request.getParameter("iduda1")));
		}
		if(!(request.getParameter("iduda2")==null)){
			uda2 = DatabaseController.selectUDA(Integer.parseInt(request.getParameter("iduda2")));
		}
		return new PercorsoDiApprendimento(uda1, uda2);
	}
	public static PercorsoDiApprendimento nuovoPercorsoDA(HttpServletRequest request) throws SQLException{
		PercorsoDiApprendimento percorso = inputPercorsoDA(request);
		if(!cicli(percorso.getUda2().getIdUDA(),percorso.getUda1()) && percorso.getUda1().getData().before(percorso.getUda2().getData())) 
			return DatabaseController.insertPercorso(percorso);
		else return null;
	}
	
	public static boolean deletePercorsoDiapprendimento(HttpServletRequest request) throws SQLException{
		PercorsoDiApprendimento percorso = inputPercorsoDA(request);
		for(int i : percorso.getUda1().getUDADipendenti()){
			if(i == percorso.getUda2().getIdUDA() ) {
				DatabaseController.deletePercorsoDA(percorso);
				return true;
			}
		}
		return false;
	}
	
	public static boolean cicli(int a, UnitaDA uda) throws SQLException{
		boolean ciclo = false;
		
		for(int i : uda.getUDADipendenti()){
			if(i == a) return true;
			else ciclo = ciclo || cicli(a, DatabaseController.selectUDA(i)); 
		}
		
		return ciclo;
	}
	
	/*------nodo di apprendimento-------------*/
	public static Nodo inputNodo(HttpServletRequest request){
		Nodo nodo = new NodoLeaf();
		if(!(request.getParameter("idUDA")==null))
			nodo.setIdUDA(Integer.parseInt(request.getParameter("idUDA")));
		if(!(request.getParameter("nome")==null))
			nodo.setNome(request.getParameter("nome"));
		if(!(request.getParameter("descrizione")==null))
			nodo.setDescrizione(request.getParameter("descrizione"));
		return nodo;
	}
	public static Nodo nuovoNodo(HttpServletRequest request) throws SQLException{
		return DatabaseController.insertNodo(inputNodo(request));
	}
	public static Nodo inputNodoLeaf(HttpServletRequest request) throws SQLException{
		int idNodo = Integer.parseInt(request.getParameter("idNodoPadre"));
		Nodo nodo = new NodoComposite();
		nodo.setIdNodo(idNodo);
		Nodo nd = inputNodo(request);
		((NodoComposite)nodo).addNodo(nd);
		return nodo;
	}
	public static Nodo nuovoNodoLeaf(HttpServletRequest request) throws SQLException{
		return DatabaseController.insertNodoLeaf(inputNodoLeaf(request));
	}
	
	
}