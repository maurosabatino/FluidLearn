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
	
}