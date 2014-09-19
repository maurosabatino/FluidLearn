package controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import partecipante.*;

public class PartecipanteController {
	public static Partecipante inputPartecipante(HttpServletRequest request){
		Partecipante part =  new PartecipanteConcreto();
		if(!(request.getParameter("ruolo")==null)){
			switch(request.getParameter("ruolo")){
			case "tecnico" : part = new Tecnico(part); break;
			case "amministratore" : part = new Amministratore(part); break;
			case "ideatore" : part = new Ideatore(part); break;
			case "utente" : part = new Utente(part); break;
			default : break;
			}
		}
		if(!(request.getParameter("nome")==null))
			part.setNome(request.getParameter("nome"));
		if(!(request.getParameter("password")==null))
			part.setPassword(request.getParameter("password"));
		return part;
	}
	public static Partecipante nuovoPartecipante(HttpServletRequest request) throws SQLException{
		return DatabaseController.insertUtente(inputPartecipante(request));
	}
	
}
