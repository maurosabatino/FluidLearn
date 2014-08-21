package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import contributo.*;

public class ContributoController {
	public static Azione inputContributo(HttpServletRequest request) throws ParseException{
		Azione contributo = new Post();
		if(!(request.getParameter("IDPartecipante")==null))
			contributo.setIDPartecipante(Integer.parseInt(request.getParameter("IDPartecipante")));
		if(!(request.getParameter("IDNodo")==null))
			contributo.setIDNodo(Integer.parseInt(request.getParameter("IDPartecipante")));
		
		Corpo corpo;
		if(!(request.getParameter("IDPlugin")==null)){
			 corpo = new Artefatto();
			 ((Artefatto)corpo).setIdPlugin(Integer.parseInt(request.getParameter("IDPlugin")));
		}
		else corpo = new Testo();
		
		if(!(request.getParameter("testo")==null))
			corpo.setText(request.getParameter("testo"));
		contributo.setData(new Date());
		if(!(request.getParameter("deadline")==null)){
			SimpleDateFormat df = new SimpleDateFormat();
			Date deadline = df.parse(request.getParameter("deadline"));
			((Sollecitazione)contributo).setDeadline(deadline);
		}
			
		return contributo;
	}
}
