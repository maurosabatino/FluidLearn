package controller;

import java.sql.SQLException;

import corso.*;

import javax.servlet.http.HttpServletRequest;

public class CorsoController {
	public static Corso inputCorso(HttpServletRequest request){
		Corso c = new Corso();
		if(!(request.getParameter("nome")==null))
			c.setNome(request.getParameter("nome"));
		if(!(request.getParameter("descrizione")==null))
			c.setDescrizione(request.getParameter("descrizione"));
		return c;
	}
	
	public static Corso nuovoCorso(HttpServletRequest request) throws SQLException{
		Corso c = inputCorso(request);
		c = DatabaseController.InsertCorso(c);
		return c;
	}
	public static Corso modificaCorso(HttpServletRequest request) throws SQLException{
		Corso corso = CorsoController.inputCorso(request);
		String idCorso = request.getParameter("idCorso");
		corso.setIdCorso(Integer.parseInt(idCorso));
		DatabaseController.UpdateCorso(corso);
		return corso;
	}
	public static boolean eliminaCorso(HttpServletRequest request) throws NumberFormatException, SQLException{
		String idCorso = request.getParameter("idCorso");
		return DatabaseController.DeleteCorso(Integer.parseInt(idCorso));
	}
}