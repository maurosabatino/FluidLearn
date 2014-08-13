package InterfacciaHtml;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.*;
import corso.*;

public class HtmlCorso {
	
	public static String formInsertCorso(){
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo corso</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del Corso</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" ></div>");
		sb.append("</div>");
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\"></textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"inserisciCorso\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"OK\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	
	public static String mostraCorso(int idCorso) throws SQLException{
		Corso c = DatabaseController.SelectCorso(idCorso);
		StringBuilder sb = new StringBuilder();
		sb.append("<p>nome: "+c.getNome()+"</p>");
		sb.append("<p>descrizione: "+c.getDescrizione()+"</p>");
		sb.append("<p><a href=\"Servlet?operazione=formModificaCorso&idCorso="+c.getIdCorso()+"\"> modifica Corso</a></p>");
		return sb.toString();
	}
	
	public static String mostraAllCorsi() throws SQLException{
		ArrayList<Corso> corsi = DatabaseController.SelectAllCorso();
		StringBuilder sb = new StringBuilder();
		for(Corso c : corsi){
			sb.append("<p>nome: "+c.getNome()+", descrizione: "+c.getDescrizione()+", ");
			sb.append("<a href=\"Servlet?operazione=formModificaCorso&idCorso="+c.getIdCorso()+"\"> modifica</a>,");
			sb.append("<a href=\"Servlet?operazione=eliminaCorso&idCorso="+c.getIdCorso()+"\"> elimina</a></p>");
		}
			return sb.toString();
	}
	
	public static String formModificaCorso(int idCorso) throws SQLException{
		Corso c = DatabaseController.SelectCorso(idCorso);
		StringBuilder sb = new StringBuilder();
		sb.append("<form action=\"Servlet\" name=\"dati\" method=\"POST\" role=\"form\">");
		sb.append("<div class=\"panel panel-default\"> <div class=\"panel-body\"> <h4>Crea un nuovo corso</h4>");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-xs-6 col-md-6\"><label for=\"nome\">Nome Del Corso</label> <input type=\"text\" name=\"nome\" id=\"nome\" class=\"form-control\" placeholder=\"nome\" value=\""+c.getNome()+"\" ></div>");
		sb.append("</div>");
		sb.append("<br><div class=\"wrapper\">");
		sb.append("<div class=\"content-main\"><label for=\"descrizione\">Descrizione</label></div>");
		sb.append("<div class=\"content-secondary\"><textarea rows=\"5\" cols=\"140\" name=\"descrizione\" id=\"descrizione\" class=\"textarea\" placeholder=\"Descrizione\">"+c.getDescrizione()+"</textarea></div>");
		sb.append("</div>");
		sb.append("<input type=\"hidden\" name=\"idCorso\" value=\""+c.getIdCorso()+"\">");
		sb.append("<input type=\"hidden\" name=\"operazione\" value=\"modificaCorso\">");
		sb.append("<input type=\"submit\" name =\"submit\" value=\"OK\">");
		sb.append("</div> </div>");
		sb.append("</form>");
		return sb.toString();
	}
	
}
