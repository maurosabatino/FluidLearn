package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import contributo.*;
import contributo.corpo.Artefatto;
import contributo.corpo.Corpo;
import contributo.corpo.Testo;

public class ContributoController {
	public static Azione inputAzione(HttpServletRequest request,int idPartecipante) throws ParseException{//manca la visibilità del post
		Azione contributo = new Post();
		if(!(request.getParameter("deadline")==null)){//caso sollecitazione
			contributo = new Sollecitazione();
			String data = request.getParameter("deadline");
			if(!(data.equals("") ||data==null)){
				((Sollecitazione)contributo).setDeadline((new SimpleDateFormat("yyyy-MM-dd").parse(data)));
			}
		}
		if(!(request.getParameter("idUDA")==null)) contributo.setVisibilita(Integer.parseInt(request.getParameter("visibilita")));
		if(!(request.getParameter("idUDA")==null)) contributo.setIDUDA(Integer.parseInt(request.getParameter("idUDA")));
		if(!(request.getParameter("idNodo")==null)) contributo.setIDNodo(Integer.parseInt(request.getParameter("idNodo")));
		if(!(request.getParameter("stato")==null)) contributo.pubblica(String.valueOf(request.getParameter("stato")));
		
		contributo.setIDPartecipante(idPartecipante);
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
	public static Azione nuovaAzione(HttpServletRequest request,int idPartecipante) throws SQLException, ParseException{
		return DatabaseController.insertAzione(inputAzione(request,idPartecipante));
	}
	public static Reazione inputReazione(HttpServletRequest request,int idPartecipante) throws SQLException{
		Reazione contributo;
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		Azione azione = DatabaseController.selectPost(idPost);
		if(azione.hasDeadline())contributo = new Risposta();
		else contributo = new Commento();
		contributo.setIDPartecipante(idPartecipante);//per ora è 1, poi quando ci saranno i partecipanti
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
	public static Reazione nuovaReazione(HttpServletRequest request,int idPartecipante) throws SQLException, ParseException{
		return DatabaseController.insertReazione((inputReazione(request,idPartecipante)));
	}
}
