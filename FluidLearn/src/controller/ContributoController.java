package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import contributo.*;

public class ContributoController {
	public static Azione inputAzione(HttpServletRequest request) throws ParseException{//manca la visibilità del post
		Azione contributo = new Post();
		if(!(request.getParameter("deadline")==null)){//caso sollecitazione
			contributo = new Sollecitazione();
			String data = request.getParameter("deadline");
			if(!(data.equals("") ||data==null)){
				((Sollecitazione)contributo).setDeadline((new SimpleDateFormat("yyyy-MM-dd").parse(data)));
			}
		}
		if(!(request.getParameter("idNodo")==null)) contributo.setIDNodo(Integer.parseInt(request.getParameter("idNodo")));
		//if(!(request.getParameter("idPartecipante")==null)) 
		contributo.setIDPartecipante(1);//per ora è 1, poi quando ci saranno i partecipanti
		Corpo corpo;
		if(!(request.getParameter("idPlugin")==null)){
			corpo = new Artefatto();
			((Artefatto)corpo).setIdPlugin(Integer.parseInt(request.getParameter("idPlugin")));
		}else{
			corpo = new Testo();
		}
		corpo.setText(request.getParameter("testo"));
		contributo.setCorpo(corpo);
		contributo.setData(new Date());
		return contributo;
	}
	public static Azione nuovaAzione(HttpServletRequest request) throws SQLException, ParseException{
		return DatabaseController.insertAzione(inputAzione(request));
	}
	public static Reazione inputReazione(HttpServletRequest request) throws SQLException{
		Reazione contributo;
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		Azione azione = DatabaseController.selectPost(idPost);
		if(azione.hasDeadline())contributo = new Risposta();
		else contributo = new Commento();
		contributo.setIDPartecipante(1);//per ora è 1, poi quando ci saranno i partecipanti
		Corpo corpo;
		if(!(request.getParameter("idPlugin")==null)){
			corpo = new Artefatto();
			((Artefatto)corpo).setIdPlugin(Integer.parseInt(request.getParameter("idPlugin")));
		}else{
			corpo = new Testo();
		}
		corpo.setText(request.getParameter("testo"));
		contributo.setCorpo(corpo);
		contributo.setData(new Date());
		contributo.setIDPost(azione.getIDPost());
		return contributo;
	}
	public static Reazione nuovaReazione(HttpServletRequest request) throws SQLException, ParseException{
		return DatabaseController.insertReazione((inputReazione(request)));
	}
}
