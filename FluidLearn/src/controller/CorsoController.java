package controller;

import java.sql.SQLException;

import corso.*;

import javax.servlet.http.HttpServletRequest;

public class CorsoController {
	public static Corso creaCorso(HttpServletRequest request){
		Corso c = new Corso();
		if(!(request.getParameter("nome")==null))
			c.setNome(request.getParameter("nome"));
		if(!(request.getParameter("descrizione")==null))
			c.setDescrizione(request.getParameter("descrizione"));
		return c;
	}
	
	public static Corso nuovoCorso(HttpServletRequest request) throws SQLException{
		Corso c = creaCorso(request);
		c = DatabaseController.InsertCorso(c);
		return c;
	}
	}