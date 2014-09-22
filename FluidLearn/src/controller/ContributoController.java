package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import partecipante.Partecipante;
import contributo.*;
import contributo.corpo.Artefatto;
import contributo.corpo.Corpo;
import contributo.corpo.Testo;
import contributo.valutazione.Valutazione;
import contributo.valutazione.ValutazioneInt;
import contributo.valutazione.ValutazioneString;

public class ContributoController {
	
	/*------------azione---------------*/
	public static Azione inputAzione(HttpServletRequest request,int idPartecipante) throws ParseException{//manca la visibilità del post
		Azione contributo = new Post();
		if(!(request.getParameter("deadline")==null)){//caso sollecitazione
			contributo = new Sollecitazione();
			String data = request.getParameter("deadline");
			if(!(data.equals("") ||data==null)){
				((Sollecitazione)contributo).setDeadline((new SimpleDateFormat("yyyy-MM-dd").parse(data)));
			}
		}
		if(!(request.getParameter("visibilita")==null)) contributo.setVisibilita(Integer.parseInt(request.getParameter("visibilita")));
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
	public static Azione eliminaAzione(HttpServletRequest request,Partecipante part) throws NumberFormatException, SQLException{
		int idPost = Integer.parseInt(request.getParameter("idCommento"));
		if(part.isAutorePost(idPost))
			DatabaseController.deleteAzione(idPost);
		return DatabaseController.selectPost(idPost);
	}
	public static Azione modificaAzione(HttpServletRequest request, Partecipante part) throws SQLException, ParseException{
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		Azione azione = DatabaseController.selectPost(idPost);
		if(part.isAutoreCommento(idPost) && !(azione.getStato().getState().equals("CHIUSO"))){
			azione = inputAzione(request, part.getIDPartecipante());
			azione.setIDPost(idPost);;
			DatabaseController.updateAzione(azione);
		} 
		return azione;
	}
	
	
	/*------------reazione-------------*/
	public static Reazione inputReazione(HttpServletRequest request,int idPartecipante) throws SQLException{
		Reazione contributo;
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		Azione azione = DatabaseController.selectPost(idPost);
		if(azione.hasDeadline())contributo = new Risposta();
		else contributo = new Commento();
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
		contributo.setIDPost(azione.getIDPost());
		return contributo;
	}
	public static Reazione nuovaReazione(HttpServletRequest request,int idPartecipante) throws SQLException, ParseException{
		return DatabaseController.insertReazione((inputReazione(request,idPartecipante)));
	}
	public static void eliminaReazione(HttpServletRequest request,Partecipante part) throws NumberFormatException, SQLException{
		int idCommento = Integer.parseInt(request.getParameter("idCommento"));
		if(part.isAutoreCommento(idCommento))
			DatabaseController.deleteReazione(idCommento);		
	}
	public static Reazione modificaReazione(HttpServletRequest request, Partecipante part) throws SQLException{
		int idCommento = Integer.parseInt(request.getParameter("idCommento"));
		if(part.isAutoreCommento(idCommento)){
			Reazione reazione = inputReazione(request, part.getIDPartecipante());
			reazione.setIDCommento(idCommento);
			return DatabaseController.updateReazione(reazione);
		}
		return null;
	}
		
	
	/*--------valutazione------------*/
	public static Valutazione inputValutazione(HttpServletRequest request){
		Valutazione valutazione = null;
		if(!(request.getParameter("tipo")==null)){
			if(Integer.parseInt((request.getParameter("tipo")))==1) valutazione = new ValutazioneInt();
			else valutazione = new ValutazioneString();
		}
		if(!(request.getParameter("visibilita")==null)) valutazione.setVisibilità(Integer.parseInt((request.getParameter("visibilita"))));
		if(!(request.getParameter("voto")==null)) valutazione.setVoto(request.getParameter("voto"));
		if(!(request.getParameter("note")==null)) valutazione.setNote(request.getParameter("note"));
		if(!(request.getParameter("lode")==null)) ((ValutazioneInt)valutazione).setLode(Boolean.parseBoolean(request.getParameter("lode")));
		return valutazione;
	}
	public static Valutazione nuovaValutazione(HttpServletRequest request) throws NumberFormatException, SQLException{
		return DatabaseController.insertValutazione(Integer.parseInt(request.getParameter("idRisposta")), inputValutazione(request));
	}
}
